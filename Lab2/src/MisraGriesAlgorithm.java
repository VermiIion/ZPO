import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MisraGriesAlgorithm {
    public static void main(String[] args) {
        // Parametry wejściowe
        int k = 100; // ustalony parametr k

        // Słowniki D1 i D2
        Map<String, Integer> D1 = new HashMap<>();
        Map<String, Integer> D2 = new HashMap<>();

        // Pobierz plik i przetwórz go słowo po słowie
        String fileName = "/english.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", " ") // Usuń znaki niebędące literami
                        .toLowerCase()                // Zamień na małe litery
                        .split("\\s+");               // Podziel na słowa
                for (String word : words) {
                    if (word.length() >= 3) {
                        processWord(word, D1, D2, k);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Wyświetl wynikowe słowa z D2
        D2.entrySet().stream()
                .filter(entry -> entry.getValue() > 29_250_532 / k)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private static void processWord(String word, Map<String, Integer> D1, Map<String, Integer> D2, int k) {
        // Przebieg pierwszy
        if (D1.size() < k - 1) {
            if (D1.containsKey(word)) {
                D1.put(word, D1.get(word) + 1);
            } else {
                D1.put(word, 1);
            }
        } else {
            if (D1.containsKey(word)) {
                D1.put(word, D1.get(word) + 1);
            } else {
                for (Map.Entry<String, Integer> entry : D1.entrySet()) {
                    entry.setValue(entry.getValue() - 1);
                    if (entry.getValue() == 0) {
                        D1.remove(entry.getKey());
                    }
                }
            }
        }

        // Przebieg drugi
        if (D1.containsKey(word)) {
            D2.put(word, D2.getOrDefault(word, 0) + 1);
        }
    }
}