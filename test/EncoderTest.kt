import org.junit.Test

import org.junit.Assert.*
import java.io.File

class EncoderTest {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun encode() {
        val encoder = Encoder("key")
        encoder.encode("files/in.txt", "files/temp.txt", "key")
        val encoder2 = Encoder("key")
        encoder2.encode("files/temp.txt", "files/out.txt", "key")
        assertFileContent("files/out.txt", "Hello, World.")
        File("files/temp.txt").delete()
    }

}