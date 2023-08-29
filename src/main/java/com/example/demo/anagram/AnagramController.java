package com.example.demo.anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anagrams")
public class AnagramController {
    private final Anagram anagram;

    @Autowired
    public AnagramController(Anagram anagram) {
        this.anagram = anagram;
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkAnagramForTwoWords(
            @RequestParam("firstWord") String firstWord,
            @RequestParam("secondWord") String secondWord) {

        boolean result = anagram.checkIfTwoStringsAreAnagramAndKeepTrack(firstWord, secondWord);
        return ResponseEntity.ok(result ? "Provided strings are anagram" : "Provided strings are not anagram");
    }

    @GetMapping("/anagrams/all")
    public ResponseEntity<String> getAllAnagramsForWord(
            @RequestParam("word") String word) {

        String anagrams = anagram.getAnagrams(word).toString();
        return ResponseEntity.ok("Anagrams for provided string are: " + anagrams);
    }
}
