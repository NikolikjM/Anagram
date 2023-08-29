package com.example.demo.anagram;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AnagramControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testAnagramsCheck() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/anagrams/check")
                        .param("firstWord", "hahah")
                        .param("secondWord", "ahahh")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Provided strings are anagram"));
    }

    @Test
    @Order(2)
    public void testAnagramsCheckNotAnagrams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/anagrams/check")
                        .param("firstWord", "hahah")
                        .param("secondWord", "hehehe")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Provided strings are not anagram"));
    }

    @Test
    @Order(3)
    public void testGetAnagrams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/anagrams/anagrams/all")
                        .param("word", "hahah")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Anagrams for provided string are: [ahahh]"));
    }
}
