package de.fakultaet.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Base64;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BlogUserControllerTest {

    @Autowired
    BlogUserRepository blogUserRepo;

    @BeforeEach
    public void setUp() {
        // BlogUserControllerTest test = new BlogUserControllerTest();

        String password = "12345";
        byte[] passwordBytes = password.getBytes();
        String passwordHash = Base64.getEncoder().withoutPadding().encodeToString(passwordBytes);
        this.blogUserRepo.save(
                new BlogUser("John", "Testing", "john@email.de", "johnuser", passwordHash, LocalDate.of(1987, 1, 1)));

        this.blogUserRepo.save(
                new BlogUser("Johannes", "Schmidt", "js@gmx.de", "jsuser", passwordHash, LocalDate.of(1990, 2, 3)));
    }

    @AfterAll
    public static void tearDown() {

    }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testLogin() {
        String userName = "testUser";
        String password = "12345";
        byte[] passwordBytes = password.getBytes();
        String passwordHash = Base64.getEncoder().withoutPadding().encodeToString(passwordBytes);

        ResponseEntity<BlogUserDTO> response = this.restTemplate
                .getForEntity("/users/login?userName=" + userName + "&passwordHash=" + passwordHash, BlogUserDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userName, response.getBody().userName());

    }
}
