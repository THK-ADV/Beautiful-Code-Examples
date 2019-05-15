package functions.example1

import functions.data.*
import functions.data.PageCrawlerImpl

/**
 * Functions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.32'
 **/
object Task {

    class HtmlUtil {

        fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
            val wikiPage = pageData.wikiPage
            val buffer = StringBuffer()
            if(pageData.hasAttribute("Test")) {
                if(includeSuiteSetup) {
                    val suiteSetup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage)
                    suiteSetup?.let {
                        val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
                        val pagePathName = PathParser.render(pagePath)
                        buffer.append("!include -setup .$pagePathName\n")
                    }
                }
                val setup = PageCrawlerImpl.getInheritedPage("SetUp", wikiPage)
                setup?.let {
                    val setupPath = wikiPage.getPageCrawler().getFullPath(setup)
                    val setupPathName = PathParser.render(setupPath)
                    buffer.append("!include -setup .$setupPathName\n")
                }
            }
            buffer.append(pageData.content)
            if(pageData.hasAttribute("Test")) {
                val teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage)
                teardown?.let {
                    val teardownPath = wikiPage.getPageCrawler().getFullPath(teardown)
                    val teardownPathName = PathParser.render(teardownPath)
                    buffer.append("\n!include -teardown .$teardownPathName\n")
                }
                if(includeSuiteSetup) {
                    val suiteTeardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage)
                    suiteTeardown?.let {
                        val pagePath = wikiPage.getPageCrawler().getFullPath(suiteTeardown)
                        val pagePathName = PathParser.render(pagePath)
                        buffer.append("\n!include -teardown .$pagePathName\n")
                    }
                }
            }
            pageData.content = buffer.toString()
            return pageData.getHtml()
        }

    }

}
