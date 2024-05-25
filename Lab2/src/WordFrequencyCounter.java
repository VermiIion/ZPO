import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyCounter {

    public static void main(String[] args) {

        String[] words = readAndProcessWordsFromFile("english.txt");

        int k = 10;
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        wordFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() >= words.length / k)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private static String[] readAndProcessWordsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", " ")
                        .toLowerCase()
                        .split("\\s+");
                for (String word : words) {
                    if (word.length() >= 3) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}