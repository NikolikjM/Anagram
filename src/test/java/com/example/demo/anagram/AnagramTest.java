package com.example.demo.anagram;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
class AnagramTest {

    private Anagram anagram;
    private final String A = "hahah";
    private final String B = "ahahh";
    private final String C = "hehehe";
    private final String D = "haahh";

    @BeforeEach
    void setUp() {
        anagram = new Anagram();
    }

    @Test
    void testAnagramFunctions() {
        // f1 check
        Assertions.assertTrue(anagram.checkIfTwoStringsAreAnagramAndKeepTrack(A,B));
        Assertions.assertFalse(anagram.checkIfTwoStringsAreAnagramAndKeepTrack(A,C));
        Assertions.assertTrue(anagram.checkIfTwoStringsAreAnagramAndKeepTrack(A,D));

        // f2 check
        Set<String> anagramsOfA = anagram.getAnagrams(A);
        Set<String> anagramsOfB = anagram.getAnagrams(B);
        Set<String> anagramsOfC = anagram.getAnagrams(C);
        Set<String> anagramsOfD = anagram.getAnagrams(D);
        log.info("anagramsOfA : {}", anagramsOfA);
        log.info("anagramsOfB : {}", anagramsOfB);
        log.info("anagramsOfC : {}", anagramsOfC);
        log.info("anagramsOfD : {}", anagramsOfD);

        Assertions.assertTrue(anagramsOfA.containsAll(Set.of(B, D)));
        Assertions.assertEquals(2, anagramsOfA.size());

        Assertions.assertTrue(anagramsOfB.containsAll(Set.of(A, D)));
        Assertions.assertEquals(2, anagramsOfB.size());

        Assertions.assertTrue(anagramsOfC.isEmpty());

        Assertions.assertTrue(anagramsOfD.containsAll(Set.of(A, B)));
        Assertions.assertEquals(2, anagramsOfD.size());
    }
}