package test.target

import rsakeys.RsaKeyProperties
import test.After
import test.Before
import test.Test
import test.TestClass
import test.assertTrue
import java.nio.file.Path
import java.sql.SQLOutput

class GenerateKeysTest : TestClass() {


    @Test(order = 1)
    fun `generate key pair`() {
        assertTrue(RsaKeyProperties.initKeys())
    }

    @Test(order = 2)
    fun `validate private and public keys`() {
        val pubKey = RsaKeyProperties.publicKey.get()
        val privKey = RsaKeyProperties.privateKey.get()

        val keyPairMatches = privKey.modulus.equals(pubKey.modulus)

        assertTrue(keyPairMatches)
    }

    @After
    fun `remove key files`() {
        Path.of("certs\\").toFile().deleteRecursively()
    }

}