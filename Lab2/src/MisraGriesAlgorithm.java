import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MisraGriesAlgorithm {
    public static void main(String[] args) {
        int k = 100;
        long startTime = System.currentTimeMillis();

        Map<String, Integer> D1 = new HashMap<>();
        Map<String, Integer> D2 = new HashMap<>();
        int totalWords = 0;

        String fileName = "english200MB";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalWords += processLine(line, D1, D2, k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = totalWords;

        D2.entrySet().stream()
                .filter(entry -> entry.getValue() > n / k)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Czas wykonania: " + executionTime + " ms");
    }

    static int processLine(String line, Map<String, Integer> D1, Map<String, Integer> D2, int k) {
        String[] words = line.replaceAll("[^a-zA-Z ]", " ")
                .toLowerCase()
                .split("\\s+");
        int wordCount = 0;
        for (String word : words) {
            if (word.length() >= 3) {
                processWord(word, D1, D2, k);
                wordCount++;
            }
        }
        return wordCount;
    }

    static void processWord(String word, Map<String, Integer> D1, Map<String, Integer> D2, int k) {
        D1.merge(word, 1, Integer::sum);
        if (D1.size() == k) {
            Iterator<Map.Entry<String, Integer>> iterator = D1.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                entry.setValue(entry.getValue() - 1);
                if (entry.getValue() == 0) {
                    iterator.remove();
                }
            }
        }
        if (D1.containsKey(word)) {
            D2.put(word, D2.getOrDefault(word, 0) + 1);
        }
    }
}
