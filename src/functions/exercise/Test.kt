package functions.exercise

import functions.data.*
import functions.date.PageCrawlerImpl

/**
 * Functions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.32'
 **/
object Test {

    class SetupTeardownIncluder(val pageData: PageData) {

        private val wikiPage = pageData.wikiPage
        private val buffer = StringBuffer()

        fun render(includeSuiteSetup: Boolean): String {
            if (pageData.isTestPage()) {
                if (includeSuiteSetup) {
                    include(SuiteResponder.SUITE_SETUP_NAME, "-setup")
                }
                include("SetUp",  "-setup")
            }
            buffer.append(pageData.content)

            if (pageData.isTestPage()) {
                include("TearDown",  "-teardown")
                if (includeSuiteSetup) {
                    val suitePageName = SuiteResponder.SUITE_TEARDOWN_NAME
                    include(suitePageName,  "-teardown")
                }
            }
            pageData.content = buffer.toString()
            return pageData.getHtml()
        }

        private fun include(pageName: String, arg: String) {
            val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
            suiteSetup?.let {
                val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
                val pagePathName = PathParser.render(pagePath)
                buffer.append("!include $arg .$pagePathName\n")
            }
        }

    }

    fun PageData.isTestPage() = this.hasAttribute("Test")

}
