package de.fakultaet.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BlogUserService {

    @Autowired
    BlogUserRepository blogRepo;

    public BlogUserDTO login(@RequestParam String userName, @RequestParam String passwordHash) {
        Optional<BlogUser> optional = this.blogRepo.findByUserName(userName);

        if (!optional.isPresent()) {
            System.err.println("not present");
            return null;
        }

        if (optional.get().getPasswordHash().equals(passwordHash)) {
            System.err.println("password stimmt");
            return new BlogUserDTO(optional.get());
        } else {
            System.err.println("password stimmt nicht");
            return null;
        }
    }

    public BlogUserDTO register(BlogUserDTO blogUserDTO) {
        return new BlogUserDTO(this.blogRepo.save(new BlogUser(blogUserDTO)));
    }

    public ResponseEntity<List<BlogUserDTO>> getAll() {
        return new ResponseEntity<List<BlogUserDTO>>(
                this.blogRepo.findAll().stream().map(u -> new BlogUserDTO(u)).toList(), HttpStatus.OK);
    }

}
