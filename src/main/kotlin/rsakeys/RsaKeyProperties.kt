package rsakeys

import com.iknova.gl.core.common.CachedProperty
import com.iknova.gl.core.common.cached
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Files.createFile
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import kotlin.io.path.writeText

private val OUTPUT_DIR = File(Paths.get("").toAbsolutePath().toFile(), "certs/")
private val PUBLIC_KEY_FILE = File(OUTPUT_DIR, "public.pem")
private val PRIVATE_KEY_FILE = File(OUTPUT_DIR, "private.pem")

object RsaKeyProperties {

    private val LOGGER = LoggerFactory.getLogger(RsaKeyProperties::class.java)

    val publicKey: CachedProperty<RSAPublicKey> = cached {
        val key = String(Files.readAllBytes(PUBLIC_KEY_FILE.toPath()), Charset.defaultCharset())

        val publicKeyPEM: String = key.lines().toMutableList().apply {
            // Remove the first and last lines which are PEM headers/footers
            removeFirstOrNull()
            removeLastOrNull()
        }.joinToString("")

        val encoded = Base64.getDecoder().decode(publicKeyPEM)

        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(encoded)

        return@cached keyFactory.generatePublic(keySpec) as RSAPublicKey
    }

    val privateKey: CachedProperty<RSAPrivateKey> = cached {
        val key = String(Files.readAllBytes(PRIVATE_KEY_FILE.toPath()), Charset.defaultCharset())

        val privateKeyPEM: String = key.lines().toMutableList().apply {
            // Remove the first and last lines which are PEM headers/footers
            removeFirstOrNull()
            removeLastOrNull()
        }.joinToString("")

        val encoded = Base64.getDecoder().decode(privateKeyPEM)

        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = PKCS8EncodedKeySpec(encoded)

        return@cached keyFactory.generatePrivate(keySpec) as RSAPrivateKey
    }

    /**
     * Initialize RSA keys for JWT token signing.
     * If the keys do not exist, they will be generated and saved to the specified files.
     */
    fun initKeys(): Boolean? {
        /** Generate RSA keys to sign JWT tokens if they don't exists. */
        return generateRsaKeyPair()?.toFiles()?.also {
            if (it) LOGGER.info("RSA private and public keys does not exists. New keys have been created in \"${OUTPUT_DIR.absolutePath}\".")
        }
    }
}


/** Generate RSA key pair for the JWT token signature. */
private fun generateRsaKeyPair(): KeyPair? {
    val generator: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(2048)
    return generator.generateKeyPair()
}

/** Save keys in KeyPair instance as files */
private fun KeyPair.toFiles(publicKeyPath: File = PUBLIC_KEY_FILE, privateKeyPath: File = PRIVATE_KEY_FILE): Boolean {
    val encoder = Base64.getEncoder()

    if (!OUTPUT_DIR.exists())
        OUTPUT_DIR.mkdirs()

    if (!publicKeyPath.exists() && !privateKeyPath.exists()) {
        val publicKeyString = encoder.encodeToString(this.public.encoded).chunked(64).joinToString("\n")
        createFile(publicKeyPath.toPath()).writeText("-----BEGIN PUBLIC KEY-----${System.lineSeparator()}$publicKeyString${System.lineSeparator()}-----END PUBLIC KEY-----")

        val privateKeyString = encoder.encodeToString(this.private.encoded).chunked(64).joinToString("\n")
        createFile(privateKeyPath.toPath()).writeText("-----BEGIN PRIVATE KEY-----${System.lineSeparator()}$privateKeyString${System.lineSeparator()}-----END PRIVATE KEY-----")

        return true
    }

    return false
}