

import compression.TextDecompressor;

import java.io.*;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length < 4) {
            LOGGER.severe("Please provide the operation (compress or decompress), input file, output file, and dictionary file as arguments.");
            return;
        }

        String operation = args[0];
        String inputFile = args[1];
        String outputFile = args[2];
        String dictionaryFile = args[3];

        try {
            FileHandler fileHandler = new FileHandler("log/TextProcessor.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);

            validateFile(inputFile);
            if ("compress".equals(operation)) {
                LOGGER.info("Starting compression...");
                compression.TextCompressor compressor = new compression.TextCompressor(inputFile, outputFile, dictionaryFile);
                compressor.compressText();
                LOGGER.info("Compression completed.");
            } else if ("decompress".equals(operation)) {
                validateFile(dictionaryFile);
                LOGGER.info("Starting decompression...");
                TextDecompressor decompressor = new TextDecompressor(inputFile, outputFile, dictionaryFile);
                decompressor.decompressText();
                LOGGER.info("Decompression completed.");
            } else {
                LOGGER.severe("Invalid operation: " + operation);
            }
        } catch (IOException e) {
            LOGGER.severe("An error occurred: " + e.getMessage());
        }
    }

    private static void validateFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new IOException("Cannot read the file: " + fileName);
        }
        if (!fileName.endsWith(".txt")) {
            throw new IOException("File is not a .txt file: " + fileName);
        }
    }
}
