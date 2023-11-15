package de.fakultaet.user;

import java.time.LocalDate;
import java.util.Base64;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BlogUserControllerTest {

    private BlogUser testUser1 = new BlogUser("John", "Testing", "john@email.de", "johnuser", hashPassword("12345"),
            LocalDate.of(1987, 1, 1));
    private BlogUser testUser2 = new BlogUser("Johannes", "Schmidt", "js@gmx.de", "jsuser", hashPassword("12345"),
            LocalDate.of(1990, 2, 3));

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    BlogUserRepository blogUserRepo;

    public String hashPassword(String plaintext) {
        byte[] passwordBytes = plaintext.getBytes();
        return Base64.getEncoder().withoutPadding().encodeToString(passwordBytes);
    }

    @BeforeEach
    public void setUpBeforeEach() {
        this.blogUserRepo.save(this.testUser1);
        this.blogUserRepo.save(this.testUser2);
    }

    @AfterEach
    public void tearDownAfterEach() {
        this.blogUserRepo.deleteAll();
    }

    @Test
    public void testTestSetup() {
        String userName = "johnuser";
        String password = "12345";
        byte[] passwordBytes = password.getBytes();
        String passwordHash = Base64.getEncoder().withoutPadding().encodeToString(passwordBytes);
        Assertions.assertEquals(2, this.blogUserRepo.count());
        Assertions.assertEquals("john@email.de", this.blogUserRepo.findByUserName(userName).get().getEmail());
    }

    @Test
    public void testLogin() {

        String url = "http://localhost:8072/users/login?userName=" + this.testUser1.getUserName() + "&passwordHash="
                + this.testUser1.getPasswordHash();
        System.out.println(url);

        ResponseEntity<BlogUserDTO> response = this.restTemplate.getForEntity(url, BlogUserDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(this.testUser1.getUserName(), response.getBody().userName());

    }
}
