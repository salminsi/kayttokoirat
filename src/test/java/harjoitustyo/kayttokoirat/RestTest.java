package harjoitustyo.kayttokoirat;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import harjoitustyo.kayttokoirat.domain.Dog;
import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // kaikki koirat
    @Test
    public void testGETAllDogs() throws Exception {
        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk());
    }

    // rotu
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    public void testGETBreedById() throws Exception {
        mockMvc.perform(get("/breeds/1"))
                .andExpect(status().isOk());
    }

    // lisää koira
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    public void testPOSTDog() throws Exception {
        Dog dog = new Dog("Testikoira", "Uros", 2019, "Marja Mustikka", "Toko", "TK1");

        mockMvc.perform(post("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dog)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dogname").value("Testikoira"));
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    public void testDeleteDogUser() throws Exception {
        mockMvc.perform(delete("/dogs/1"));
        mockMvc.perform(get("/dogs/1"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertNotEquals("[null]", content);
                });
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    public void testDeleteDogAdmin() throws Exception {
        mockMvc.perform(delete("/dogs/1"));
        mockMvc.perform(get("/dogs/1"))
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertEquals("null", content);
                });
    }

}
