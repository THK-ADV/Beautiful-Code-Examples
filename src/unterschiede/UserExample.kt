package unterschiede

import data.PhoneNumber

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.24'
 **/

object UserExample {

    class User (val nameString: String, var ageVariable: Int, val mPhoneString: PhoneNumber)

}