package example2

/**
 * Use Intention-Revealing Names
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.18'
 **/
object Example2 {
    val theList = ArrayList<Array<Int>>()

    fun getThem(): ArrayList<Array<Int>> {
        val list1 = ArrayList<Array<Int>>()
        for (x in theList) {
            if (x[0] == 4)
                list1.add(x)
        }
        return list1
    }
}