package net.incandescently.wordlesolver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordQuery {

    private String wordStarts;
    private String wordEnds;
    private String wordContains;
    private String wordOmits;
    private List<String> wordleWords;

    public WordQuery() {
        this.wordleWords = new ArrayList<>();
        initializeWordleWords();
    }

    private void initializeWordleWords() {
        String basePath = new File("").getAbsolutePath();
        Path filePath = Path.of(basePath.concat("/src/main/resources/net/incandescently/wordlesolver/words.txt"));
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> englishWords = Files.readAllLines(filePath, charset);
            for(String word : englishWords) {
                if(word.length() == 5) {
                    this.wordleWords.add(word);
                }
            }
        } catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
        }
    }

    public List<String> filterByWordStarts(String wordStarts) {
        List<String> query = new ArrayList<>(this.wordleWords);
        int inputLength = wordStarts.length();
        if(inputLength < 1) {
            return query;
        }
        for (String word : this.wordleWords) {
            for (int i = 0; i < inputLength; i++) {
                if (word.charAt(i) != wordStarts.charAt(i)) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }

    public List<String> filterByWordEnds(String wordEnds) {
        List<String> query = new ArrayList<>(this.wordleWords);
        int inputLength = wordEnds.length();
        if(inputLength < 1) {
            return query;
        }
        for (String word : this.wordleWords) {
            for (int i=0; i < inputLength; i++) {
                if (word.charAt(4-i) != wordEnds.charAt(inputLength-1-i)) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }

    public List<String> filterByWordContains(String wordContains) {
        List<String> query = new ArrayList<>(this.wordleWords);
        int inputLength = wordContains.length();
        if (inputLength < 1) {
            return query;
        }
        boolean flag;
        for (String word : this.wordleWords) {
            flag = false;
            for (char c : wordContains.toCharArray()) {
                for (char d : word.toCharArray()) {
                    if (c == d) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    query.remove(word);
                    break;
                }
                flag = false;
            }
        }
        return query;
    }

    public List<String> filterByWordOmits(String wordOmits) {
        List<String> query = new ArrayList<>(this.wordleWords);
        int inputLength = wordOmits.length();
        if (inputLength < 1) {
            return query;
        }
        boolean flag;
        for (String word : this.wordleWords) {
            for (char c : wordOmits.toCharArray()) {
                flag = false;
                for (char d : word.toCharArray()) {
                    if (c == d) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }
}
