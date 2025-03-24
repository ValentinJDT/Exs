package test.target

import generatekeys.rsaKeyPair
import generatekeys.toFiles
import test.Test
import test.TestClass
import test.assertNotNull
import java.nio.file.Path

class GenerateKeysTest : TestClass() {

    @Test
    fun `generate rsa key pair`() {
        assertNotNull(rsaKeyPair())
    }

    @Test
    fun `generate rsa key files`() {
        rsaKeyPair()?.toFiles(
            Path.of("C:\\Users\\v.jeandot\\Desktop\\rsa-test\\public_key.pem"),
            Path.of("C:\\Users\\v.jeandot\\Desktop\\rsa-test\\private_key.pem")
        )
    }

}