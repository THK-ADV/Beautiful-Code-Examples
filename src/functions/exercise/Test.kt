package functions.exercise

import functions.data.*
import functions.date.PageCrawlerImpl

/**
 * Functions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.32'
 **/
object Test {

    class SetupTeardownIncluder {

        fun render(pageData: PageData, includeSuiteSetup: Boolean): String {
            val wikiPage = pageData.wikiPage
            val buffer = StringBuffer()
            if(pageData.hasAttribute("Test")) {
                val arg = "-setup"
                if(includeSuiteSetup) {
                    val pageName = SuiteResponder.SUITE_SETUP_NAME

                    val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
                    suiteSetup?.let {
                        val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
                        val pagePathName = PathParser.render(pagePath)
                        buffer.append("!include $arg .$pagePathName\n")
                    }

                }
                val setupPageName = "SetUp"

                val setup = PageCrawlerImpl.getInheritedPage(setupPageName, wikiPage)
                setup?.let {
                    val setupPath = wikiPage.getPageCrawler().getFullPath(setup)
                    val setupPathName = PathParser.render(setupPath)
                    buffer.append("!include $arg .$setupPathName\n")
                }


            }
            buffer.append(pageData.content)
            if(pageData.hasAttribute("Test")) {
                val teardownPageName = "TearDown"
                val arg = "-teardown"


                val teardown = PageCrawlerImpl.getInheritedPage(teardownPageName, wikiPage)
                teardown?.let {
                    val teardownPath = wikiPage.getPageCrawler().getFullPath(teardown)
                    val teardownPathName = PathParser.render(teardownPath)
                    buffer.append("\n!include $arg .$teardownPathName\n")
                }


                if(includeSuiteSetup) {
                    val suitePageName = SuiteResponder.SUITE_TEARDOWN_NAME


                    val suiteTeardown = PageCrawlerImpl.getInheritedPage(suitePageName, wikiPage)
                    suiteTeardown?.let {
                        val pagePath = wikiPage.getPageCrawler().getFullPath(suiteTeardown)
                        val pagePathName = PathParser.render(pagePath)
                        buffer.append("\n!include $arg .$pagePathName\n")
                    }


                }
            }
            pageData.content = buffer.toString()
            return pageData.getHtml()
        }

    }

}
