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
        val encoder = Encoder("1104")
        val encoder2 = Encoder("0411")
        encoder.encode("files/in.txt", "files/temp.txt")
        assertFileContent("files/temp.txt", "Ya}h~(1S~v}`?")
        encoder.encode("files/temp.txt", "files/out.txt")
        assertFileContent("files/out.txt", "Hello, World.")
        encoder2.encode("files/temp.txt", "files/out2.txt")
        assertFileContent("files/out2.txt", "]pyyz95Bzgyq;")
        File("files/temp.txt").delete()
    }

}