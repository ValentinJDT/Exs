package test.target

import editlong.MyLongEdit
import test.Test
import test.TestClass

class EditTest: TestClass() {

    @Test
    fun `edit from other`() {

        MyLongEdit().run()


    }

}
