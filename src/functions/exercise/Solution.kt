package functions.exercise

import functions.data.*
import functions.date.PageCrawlerImpl

/**
 * Functions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.32'
 **/
object Solution {

    class SetupTeardownIncluder private constructor(private val pageData: PageData){

        private val newPageContent = StringBuffer()
        private val testPage = pageData.wikiPage
        private val pageCrawler = testPage.getPageCrawler()

        private fun render(isSuite: Boolean): String {
            if(pageData.isTestPage())
                includeSetupAndTeardownPagesInto(isSuite)
            return pageData.getHtml()
        }

        private fun includeSetupAndTeardownPagesInto(isSuite: Boolean) {
            includeSetupPages(isSuite)
            includePageContent()
            includeTeardownPages(isSuite)
            updatePageContent()
        }

        private fun includeSetupPages(isSuite: Boolean) {
            if (isSuite)
                includeSuiteSetupPage()
            includeSetupPage()
        }

        private fun includeSuiteSetupPage() {
            include(SuiteResponder.SUITE_SETUP_NAME, "-setup")
        }

        private fun includeSetupPage() {
            include("SetUp", "-setup")
        }

        private fun includePageContent() {
            newPageContent.append(pageData.content)
        }

        private fun includeTeardownPages(isSuite: Boolean) {
            includeTeardownPage()
            if(isSuite)
                includeSuiteTeardownPage()
        }

        private fun includeTeardownPage() {
            include("TearDown", "-teardown")
        }

        private fun includeSuiteTeardownPage() {
            include(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown")
        }

        private fun updatePageContent() {
            pageData.content = newPageContent.toString()
        }

        private fun include(pageName: String, arg: String) {
            val inheritedPage = findInheritedPage(pageName)
            inheritedPage?.let {
                val pagePathName = getPathNameForPage(inheritedPage)
                buildIncludeDirective(pagePathName, arg)
            }
        }

        private fun findInheritedPage(pageName: String) = PageCrawlerImpl.getInheritedPage(pageName, testPage)

        private fun getPathNameForPage(page: WikiPage) = pageCrawler.getFullPath(page)

        private fun buildIncludeDirective(pagePathName: Any, arg: String) {
            newPageContent.append("\n!include $arg .$pagePathName\n")
        }

        companion object {
            fun render(pageData: PageData, isSuite: Boolean = false) = SetupTeardownIncluder(pageData).render(isSuite)
        }

    }

    private fun PageData.isTestPage() = this.hasAttribute("Test")

}
