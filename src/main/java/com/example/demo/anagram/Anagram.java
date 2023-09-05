package com.example.demo.anagram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class Anagram {
    private final Map<String, Set<String>> anagramMap = new HashMap<>();

    public boolean checkIfTwoStringsAreAnagramAndKeepTrack(String s1, String s2) {

        if (s1.length() != s2.length()) {
            log.warn("{} does not have same length as {} so it can't be an anagram", s1, s2);
            return false;
        }

        boolean anagramFlag = true;
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : s1.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!charFrequency.containsKey(c)) {
                log.warn("{} has different characters then {} so it can't be an anagram", s1, s2);
                anagramFlag = false;
                break;
            }

            charFrequency.put(c, charFrequency.get(c) - 1);

            if (charFrequency.get(c) == 0) {
                charFrequency.remove(c);
            }
        }
        if (anagramFlag) {
            log.info("{} is anagram to {}, adding to map for tracking", s1, s2);
            addAnagram(s1, s2);
        }

        return anagramFlag;
    }

    public Set<String> getAnagrams(String word) {
        return anagramMap.getOrDefault(word, new HashSet<>());
    }

    private void addAnagram(String s1, String s2) {
        // add new anagrams to the map
        anagramMap.computeIfAbsent(s1, k -> new HashSet<>()).add(s2);
        anagramMap.computeIfAbsent(s2, k -> new HashSet<>()).add(s1);

        // get the sets of anagrams for s1 and s2
        Set<String> allAnagrams = new HashSet<>();
        allAnagrams.addAll(anagramMap.getOrDefault(s1, new HashSet<>()));
        allAnagrams.addAll(anagramMap.getOrDefault(s2, new HashSet<>()));

        // iterate all the anagrams to add the new anagram words to them
        allAnagrams.forEach(anagram -> {
            Set<String> values = anagramMap.computeIfAbsent(anagram, k -> new HashSet<>());
            values.addAll(allAnagrams);
            // we don't want to add self
            values.remove(anagram);
        });
    }
}


