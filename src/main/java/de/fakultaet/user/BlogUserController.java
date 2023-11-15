package de.fakultaet.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class BlogUserController {

    @GetMapping
    public ResponseEntity<BlogUserDTO> login(String userName, String passwordHash) {
        return null;
    }
}
