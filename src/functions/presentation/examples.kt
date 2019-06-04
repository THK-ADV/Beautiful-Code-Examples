package functions.presentation

import functions.data.*
import java.lang.IllegalArgumentException


/**
 * Beschreibende Namen
 */
object Example1 {

    data class User(val name: String, val id: Int)

    private val users = listOf<User>()

    fun get(i: Int) = users.find { it.id == i }
}

/**
 * Beschreibende Namen
 */
object Example1Solution {

    data class User(val name: String, val id: Int)

    private val users = listOf<User>()

    fun getUserById(id: Int) = users.find { it.id == id }
}


/**
 * Klein
 */
object Example2 {

    fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
        val wikiPage = pageData.wikiPage
        val buffer = StringBuffer()
        if (pageData.hasAttribute("Test")) {
            if (includeSuiteSetup) {
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
        if (pageData.hasAttribute("Test")) {
            val teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage)
            teardown?.let {
                val teardownPath = wikiPage.getPageCrawler().getFullPath(teardown)
                val teardownPathName = PathParser.render(teardownPath)
                buffer.append("\n!include -teardown .$teardownPathName\n")
            }
            if (includeSuiteSetup) {
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

/**
 * Klein
 */
object Example2Solution {

    fun includeSetupAndTeardownPagesInto(isSuite: Boolean) {
        includeSetupPages(isSuite)
        includePageContent()
        includeTeardownPages(isSuite)
        updatePageContent()
    }

    private fun updatePageContent(): Unit = TODO()

    private fun includeTeardownPages(suite: Boolean): Unit = TODO()

    private fun includePageContent(): Unit = TODO()

    private fun includeSetupPages(suite: Boolean): Unit = TODO()

}

/**
 * Eine Sache
 */
object Example3 : A() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        levelIndex = intent.getIntExtra(LEVEL, -1)
        cardIndex = intent.getIntExtra(CARD, -1)


        game = (application as App).gameManager.game
        level = game.getLevelByIndex(levelIndex)
        card = level.getCardByIndex(cardIndex)

        card_title.text = card.name

        val reader = AssetReader()
        game_image.setImageDrawable(reader.getDrawableFromAssets(this, card.background))
        onCardStart(game, level, card)
    }

}

/**
 * Eine Sache
 */
object Example3Solution : A() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        prepareGame()
        onCardStart(game, level, card)
    }

    private fun prepareGame() {
        initGame()
        updateVisualInformation()
    }

    private fun updateVisualInformation() {
        card_title.text = card.name
        game_image.setImageDrawable(AssetReader().getDrawableFromAssets(this, card.background))
    }

    private fun initGame() {
        game = (application as App).gameManager.game
        cardIndex = intent.getIntExtra(CARD, -1)
        levelIndex = intent.getIntExtra(LEVEL, -1)
        level = game.getLevelByIndex(levelIndex)
        card = level.getCardByIndex(cardIndex)
    }

}

/**
 * Ein Abstaktionslevel
 */
object Example4 {

    fun playNextQuestion(user: User, question: Question, answers: List<Answer>) {
        println(question.text)
        val input = readLine() ?: ""
        if (answers.map { it.text.toLowerCase() }.contains(input.toLowerCase())) {
            Database.save(user, question, true)
            println("Richtig, gut gemacht ${user.name}!")
        } else {
            Database.save(user, question, false)
            println("Das war falsch, keine Punkte für dich ${user.name}!")
        }
    }

}

/**
 * Ein Abstaktionslevel
 */
object Example4Solution {

    private fun announceWrongAnswered(user: User) {
        println("Das war falsch, keine Punkte für dich ${user.name}!")
    }

    private fun saveWrongAnswered(user: User, question: Question) {
        Database.save(user, question, false)
    }

    fun playNextQuestion(user: User, question: Question, answers: List<Answer>) {
        val input = getUsersAnswer(question)
        validateAnswer(answers, input, user, question)
    }


    private fun wrongAnswer(user: User, question: Question) {
        saveWrongAnswered(user, question)
        announceWrongAnswered(user)
    }

    private fun saveRightAnswered(user: User, question: Question) {
        Database.save(user, question, true)
    }

    private fun isRightAnswer(answers: List<Answer>, input: String) =
            answers.map { it.text.toLowerCase() }.contains(input.toLowerCase())

    private fun announceRightAnswered(user: User) {
        println("Richtig, gut gemacht ${user.name}!")
    }

    private fun getUsersAnswer(question: Question): String {
        println(question.text)
        return readLine() ?: ""
    }

    private fun correctAnswer(user: User, question: Question) {
        saveRightAnswered(user, question)
        announceRightAnswered(user)
    }

    private fun validateAnswer(answers: List<Answer>, input: String, user: User, question: Question) {
        if (isRightAnswer(answers, input)) {
            correctAnswer(user, question)
        } else {
            wrongAnswer(user, question)
        }
    }

}


/**
 * Step Down Rule
 */
object Example5 {

    private fun announceWrongAnswered(user: User) {
        println("Das war falsch, keine Punkte für dich ${user.name}!")
    }

    private fun saveWrongAnswered(user: User, question: Question) {
        Database.save(user, question, false)
    }

    fun playNextQuestion(user: User, question: Question, answers: List<Answer>) {
        val input = getUsersAnswer(question)
        validateAnswer(answers, input, user, question)
    }


    private fun wrongAnswer(user: User, question: Question) {
        saveWrongAnswered(user, question)
        announceWrongAnswered(user)
    }

    private fun saveRightAnswered(user: User, question: Question) {
        Database.save(user, question, true)
    }

    private fun isRightAnswer(answers: List<Answer>, input: String) =
            answers.map { it.text.toLowerCase() }.contains(input.toLowerCase())

    private fun announceRightAnswered(user: User) {
        println("Richtig, gut gemacht ${user.name}!")
    }

    private fun getUsersAnswer(question: Question): String {
        println(question.text)
        return readLine() ?: ""
    }

    private fun correctAnswer(user: User, question: Question) {
        saveRightAnswered(user, question)
        announceRightAnswered(user)
    }

    private fun validateAnswer(answers: List<Answer>, input: String, user: User, question: Question) {
        if (isRightAnswer(answers, input)) {
            correctAnswer(user, question)
        } else {
            wrongAnswer(user, question)
        }
    }
}

/**
 * Step Down Rule
 */
object Example5Solution {

    fun playNextQuestion(user: User, question: Question, answers: List<Answer>) {
        val input = getUsersAnswer(question)
        validateAnswer(answers, input, user, question)
    }

    private fun validateAnswer(answers: List<Answer>, input: String, user: User, question: Question) {
        if (isRightAnswer(answers, input)) {
            correctAnswer(user, question)
        } else {
            wrongAnswer(user, question)
        }
    }

    private fun correctAnswer(user: User, question: Question) {
        saveRightAnswered(user, question)
        announceRightAnswered(user)
    }

    private fun wrongAnswer(user: User, question: Question) {
        saveWrongAnswered(user, question)
        announceWrongAnswered(user)
    }

    private fun saveWrongAnswered(user: User, question: Question) {
        Database.save(user, question, false)
    }

    private fun saveRightAnswered(user: User, question: Question) {
        Database.save(user, question, true)
    }

    private fun isRightAnswer(answers: List<Answer>, input: String) =
            answers.map { it.text.toLowerCase() }.contains(input.toLowerCase())

    private fun getUsersAnswer(question: Question): String {
        println(question.text)
        return readLine() ?: ""
    }

    private fun announceWrongAnswered(user: User) {
        println("Das war falsch, keine Punkte für dich ${user.name}!")
    }

    private fun announceRightAnswered(user: User) {
        println("Richtig, gut gemacht ${user.name}!")
    }
}

/**
 * Single Responsibility Principle
 */
object Example6 {

    class User(val name: String, val surname: String, val email: String) {
        init {
            if (!isValidEmail()) throw IllegalArgumentException()
        }

        private fun isValidEmail(): Boolean {
            val emailRegex =
                    "[a-zA-Z0-9+._%\\-+]{1,256}@a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()
            return emailRegex.matches(email)
        }
    }
}

/**
 * Single Responsibility Principle
 */
object Example6Solution {

    class User(val name: String, val surname: String, val email: Email)

    class Email(email: String) {

        init {
            if (!isValid(email)) throw IllegalArgumentException()
        }

        private fun isValid(email: String) =
                "[a-zA-Z0-9+._%\\-+]{1,256}@a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex().matches(email)

    }
}

/**
 * Single Responsibility Principle
 */
object Example6SolutionKoltin {

    class User(val name: String, val surname: String, val email: String) {
        init {
            if (!email.isEmail()) throw IllegalArgumentException()
        }
    }

    private val emailRegex = "[a-zA-Z0-9+._%\\-+]{1,256}@a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()
    fun String.isEmail() = emailRegex.matches(this)

}

/**
 * Open Closed Principle
 */
object Example7 {
    interface Weapon {
        fun shoot(): String
    }

    class LaserBeam : Weapon {
        override fun shoot() = "Pew"
    }

    class WeaponsComposer(private val weapons: List<Weapon>) : Weapon {
        override fun shoot(): String = weapons.joinToString { it.shoot() }
    }

    class RockedLauncher : Weapon {
        override fun shoot(): String = "Whoosh"
    }
}

/**
 * Duplikationen
 */
object Example8 {
    fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
        val wikiPage = pageData.wikiPage
        val buffer = StringBuffer()
        if (pageData.hasAttribute("Test")) {
            if (includeSuiteSetup) {
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
        if (pageData.hasAttribute("Test")) {
            val teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage)
            teardown?.let {
                val teardownPath = wikiPage.getPageCrawler().getFullPath(teardown)
                val teardownPathName = PathParser.render(teardownPath)
                buffer.append("\n!include -teardown .$teardownPathName\n")
            }
            if (includeSuiteSetup) {
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

/**
 * Duplikationen
 */
object Example8Solution {
    fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
        val wikiPage = pageData.wikiPage
        val buffer = StringBuffer()
        if (pageData.hasAttribute("Test")) {
            if (includeSuiteSetup) {
                includePage(SuiteResponder.SUITE_SETUP_NAME, wikiPage, buffer, "-setup")
            }
            includePage("SetUp", wikiPage, buffer, "-setup")
        }
        buffer.append(pageData.content)
        if (pageData.hasAttribute("Test")) {
            includePage("TearDown", wikiPage, buffer, "-teardown")
            if (includeSuiteSetup) {
                includePage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage, buffer, "-teardown")
            }
        }
        pageData.content = buffer.toString()
        return pageData.getHtml()
    }

    private fun includePage(pageName: String, wikiPage: WikiPage, buffer: StringBuffer, arg: String) {
        val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
        suiteSetup?.let {
            val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
            val pagePathName = PathParser.render(pagePath)
            buffer.append("!include $arg .$pagePathName\n")
        }
    }

}

/**
 * Anzahl Parameter
 */
object Example9 {
    fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
        val wikiPage = pageData.wikiPage
        val buffer = StringBuffer()
        if (pageData.hasAttribute("Test")) {
            if (includeSuiteSetup) {
                includePage(SuiteResponder.SUITE_SETUP_NAME, wikiPage, buffer, "-setup")
            }
            includePage("SetUp", wikiPage, buffer, "-setup")
        }
        buffer.append(pageData.content)
        if (pageData.hasAttribute("Test")) {
            includePage("TearDown", wikiPage, buffer, "-teardown")
            if (includeSuiteSetup) {
                includePage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage, buffer, "-teardown")
            }
        }
        pageData.content = buffer.toString()
        return pageData.getHtml()
    }

    private fun includePage(pageName: String, wikiPage: WikiPage, buffer: StringBuffer, arg: String) {
        val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
        suiteSetup?.let {
            val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
            val pagePathName = PathParser.render(pagePath)
            buffer.append("!include $arg .$pagePathName\n")
        }
    }

}

/**
 * Anzahl Parameter
 */
object Example9Solution {

    class PageBuilder(val pageData: PageData) {

        private val wikiPage = pageData.wikiPage
        private val buffer = StringBuffer()

        fun testableHtml(pageData: PageData, includeSuiteSetup: Boolean): String {
            if (pageData.hasAttribute("Test")) {
                if (includeSuiteSetup) {
                    includePage(SuiteResponder.SUITE_SETUP_NAME, "-setup")
                }
                includePage("SetUp", "-setup")
            }
            buffer.append(pageData.content)
            if (pageData.hasAttribute("Test")) {
                includePage("TearDown", "-teardown")
                if (includeSuiteSetup) {
                    includePage(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown")
                }
            }
            pageData.content = buffer.toString()
            return pageData.getHtml()
        }

        private fun includePage(pageName: String, arg: String) {
            val suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage)
            suiteSetup?.let {
                val pagePath = it.getPageCrawler().getFullPath(suiteSetup)
                val pagePathName = PathParser.render(pagePath)
                buffer.append("!include $arg .$pagePathName\n")
            }
        }
    }


}