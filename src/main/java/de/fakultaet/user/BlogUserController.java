package de.fakultaet.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class BlogUserController {

    @Autowired
    BlogUserService userService;

    @GetMapping
    public ResponseEntity<List<BlogUserDTO>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/login")
    public ResponseEntity<BlogUserDTO> login(String userName, String passwordHash) {
        BlogUserDTO userDTO = this.userService.login(userName, passwordHash);

        if (userDTO == null) {
            return new ResponseEntity<BlogUserDTO>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<BlogUserDTO>(userDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<BlogUserDTO> register(@RequestBody BlogUserDTO blogUserDTO) {
        return new ResponseEntity<BlogUserDTO>(this.userService.register(blogUserDTO), HttpStatus.CREATED);
    }
}
