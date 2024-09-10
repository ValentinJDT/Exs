package test.target

import test.Test
import test.TestClass
import java.net.URI

class UriTest: TestClass() {

    @Test
    fun `get uri local`() {

        URI("file:///C:/Users/v.jeandot/Desktop/profiles/")
        // No working
    }

}
