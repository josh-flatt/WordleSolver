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

    public WordQuery(String wordStarts, String wordEnds, String wordContains, String wordOmits, Character[] letters) {
        this.wordleWords = new ArrayList<>();
        initializeWordleWords();
        List<String> starts = filterByWordStarts(wordStarts, wordleWords);
        List<String> ends = filterByWordEnds(wordEnds, starts);
        List<String> contains = filterByWordContains(wordContains, ends);
        List<String> omits = filterByWordOmits(wordOmits, contains);
        List<String> result = filterByLetterPlacement(letters, omits);
        wordleWords = result;
    }

    public List<String> getResult() {
        return wordleWords;
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

    public List<String> filterByWordStarts(String wordStarts, List<String> wordList) {
        List<String> query = new ArrayList<>(wordList);
        int inputLength = wordStarts.length();
        if(inputLength < 1) {
            return query;
        }
        for (String word : wordList) {
            for (int i = 0; i < inputLength; i++) {
                if (word.charAt(i) != wordStarts.charAt(i)) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }

    public List<String> filterByWordEnds(String wordEnds, List<String> wordList) {
        List<String> query = new ArrayList<>(wordList);
        int inputLength = wordEnds.length();
        if(inputLength < 1) {
            return query;
        }
        for (String word : wordList) {
            for (int i=0; i < inputLength; i++) {
                if (word.charAt(4-i) != wordEnds.charAt(inputLength-1-i)) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }

    public List<String> filterByWordContains(String wordContains, List<String> wordList) {
        List<String> query = new ArrayList<>(wordList);
        int inputLength = wordContains.length();
        if (inputLength < 1) {
            return query;
        }
        boolean flag;
        for (String word : wordList) {
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

    public List<String> filterByWordOmits(String wordOmits, List<String> wordList) {
        List<String> query = new ArrayList<>(wordList);
        int inputLength = wordOmits.length();
        if (inputLength < 1) {
            return query;
        }
        boolean flag;
        for (String word : wordList) {
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

    private List<String> filterByLetterPlacement(Character[] letters, List<String> wordList) {
        List<String> query = new ArrayList<>(wordList);
        boolean flag;
        for (String word : wordList) {
            for (int i=0; i < 5; i++) {
                if (letters[i] == '\u0000') {
                    continue;
                }
                if (letters[i] != word.charAt(i)) {
                    query.remove(word);
                    break;
                }
            }
        }
        return query;
    }
}
