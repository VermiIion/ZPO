import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        String fileName = "english.200MB.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", " ") // Usuń znaki niebędące literami
                        .toLowerCase()                // Zamień na małe litery
                        .split("\\s+");               // Podziel na słowa
                for (String word : words) {
                    if (word.length() >= 3) {
                        // Przetwarzaj słowa o długości co najmniej 3 litery
                        // tutaj możesz dodać kod do dalszej analizy lub przetwarzania
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
