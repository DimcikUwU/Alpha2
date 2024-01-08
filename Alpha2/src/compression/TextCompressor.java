package compression;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextCompressor {
    private String inputFile;
    private String outputFile;
    private String dictionaryFile;

    public TextCompressor(String inputFile, String outputFile, String dictionaryFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.dictionaryFile = dictionaryFile;
    }

    public void compressText() throws IOException {
        Map<String, String> dictionary = new HashMap<>();
        String line;
        int abbreviationIndex = 1;

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        BufferedWriter dictionaryWriter = new BufferedWriter(new FileWriter(dictionaryFile));

        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            StringBuilder newLine = new StringBuilder();

            for (String word : words) {
                if (word.length() > 4 && !dictionary.containsKey(word)) {
                    String abbreviation = " " + abbreviationIndex++;
                    dictionary.put( word, abbreviation);
                    dictionaryWriter.write(word + " -> " + abbreviation + "\n");
                }

                newLine.append(dictionary.getOrDefault(word, word)).append(" ");
            }

            writer.write(newLine.toString().trim() + "\n");
        }

        reader.close();
        writer.close();
        dictionaryWriter.close();
    }
}