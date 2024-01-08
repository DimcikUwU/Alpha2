package compression;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextDecompressor {
    private String inputFile;
    private String outputFile;
    private String dictionaryFile;

    public TextDecompressor(String inputFile, String outputFile, String dictionaryFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.dictionaryFile = dictionaryFile;
    }

    public void decompressText() throws IOException {
        Map<String, String> dictionary = new HashMap<>();
        String line;

        BufferedReader dictionaryReader = new BufferedReader(new FileReader(dictionaryFile));
        while ((line = dictionaryReader.readLine()) != null) {
            String[] entry = line.split(" -> ");
            dictionary.put(entry[1], entry[0]);
        }
        dictionaryReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            StringBuilder newLine = new StringBuilder();

            for (String word : words) {
                newLine.append(dictionary.getOrDefault(word, word)).append(" ");
            }

            writer.write(newLine.toString().trim() + "\n");
        }

        reader.close();
        writer.close();
    }
}