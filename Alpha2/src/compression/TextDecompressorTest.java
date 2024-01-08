package compression;



        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextDecompressorTest {
    @Test
    void testDecompressText() {
        TextDecompressor decompressor = new TextDecompressor("test_output.txt", "test_decompressed.txt", "test_dictionary.txt");
        assertDoesNotThrow(() -> decompressor.decompressText());
        // Add more assertions here to check the contents of the decompressed file.
    }
}