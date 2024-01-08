package compression;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextCompressorTest {
    @Test
    void testCompressText() {
        compression.TextCompressor compressor = new compression.TextCompressor("test_input.txt", "test_output.txt", "test_dictionary.txt");
        assertDoesNotThrow(() -> compressor.compressText());
        // Add more assertions here to check the contents of the output and dictionary files.
    }
}