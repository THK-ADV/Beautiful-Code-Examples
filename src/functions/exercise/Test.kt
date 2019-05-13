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
            if (pageData.hasAttribute("Test")) {
                if (includeSuiteSetup) {
                    include(SuiteResponder.SUITE_SETUP_NAME, wikiPage, buffer, "-setup")
                }
                include("SetUp", wikiPage, buffer, "-setup")
            }
            buffer.append(pageData.content)
            if (pageData.hasAttribute("Test")) {
                include("TearDown", wikiPage, buffer, "-teardown")
                if (includeSuiteSetup) {
                    val suitePageName = SuiteResponder.SUITE_TEARDOWN_NAME
                    include(suitePageName, wikiPage, buffer, "-teardown")
                }
            }
            pageData.content = buffer.toString()
            return pageData.getHtml()
        }

        private fun include(pageName: String, wikiPage: WikiPage, buffer: StringBuffer, arg: String) {
            val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
            suiteSetup?.let {
                val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
                val pagePathName = PathParser.render(pagePath)
                buffer.append("!include $arg .$pagePathName\n")
            }
        }

    }

}
