import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MisraGriesAlgorithmTest {

    @Test
    public void testWordCount() throws IOException {
        String fileName = "english200MB";
        long wordCount = countWordsInFile(fileName);
        assertEquals(39_177_225, wordCount);
    }

    @Test
    public void testFilteredWordCount() throws IOException {
        String fileName = "english200MB";
        long filteredWordCount = countFilteredWordsInFile(fileName);
        assertEquals(29_250_532, filteredWordCount);
    }

    @Test
    public void testProcessLine() {
        Map<String, Integer> D1 = new HashMap<>();
        Map<String, Integer> D2 = new HashMap<>();
        String line = "Ten tekst ma w sobie 8 wyrazow, ktore sa dluzsze lub rowne 3";

        MisraGriesAlgorithm.processLine(line, D1, D2, 100);

        assertEquals(8, D1.size());
    }

    @Test
    public void testProcessWord() {
        Map<String, Integer> D1 = new HashMap<>();
        Map<String, Integer> D2 = new HashMap<>();
        String word = "example";

        MisraGriesAlgorithm.processWord(word, D1, D2, 100);
        assertEquals(1, D1.get("example"));
        assertEquals(1, D2.get("example"));
    }

    private long countWordsInFile(String fileName) throws IOException {
        long wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
        }
        return wordCount;
    }

    private long countFilteredWordsInFile(String fileName) throws IOException {
        long filteredWordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", " ")
                        .toLowerCase()
                        .split("\\s+");
                for (String word : words) {
                    if (word.length() >= 3) {
                        filteredWordCount++;
                    }
                }
            }
        }
        return filteredWordCount;
    }
}
