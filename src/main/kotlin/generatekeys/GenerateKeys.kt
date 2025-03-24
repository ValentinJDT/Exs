package generatekeys

import java.nio.file.Files.createFile
import java.nio.file.Path
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.util.Base64
import kotlin.io.path.writeText

fun rsaKeyPair(): KeyPair? {
    val generator: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(2048)
    return generator.generateKeyPair()
}

fun String.chunked(size: Int): List<String> {
    val nChunks = length / size
    return (0 until nChunks).map { substring(it * size, (it + 1) * size) }
}

fun KeyPair.toFiles(publicKeyPath: Path, privateKeyPath: Path) {
    val publicKeyString = Base64.getEncoder().encodeToString(this.public.encoded).chunked(64).joinToString("\n")
    val privateKeyString = Base64.getEncoder().encodeToString(this.private.encoded).chunked(64).joinToString("\n")

    createFile(publicKeyPath).writeText("-----BEGIN PUBLIC KEY-----\n$publicKeyString\n-----END PUBLIC KEY-----")
    createFile(privateKeyPath).writeText("-----BEGIN PRIVATE KEY-----\n$privateKeyString\n-----END PRIVATE KEY-----")
}

