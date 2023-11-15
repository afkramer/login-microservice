package de.fakultaet.user;

import java.time.LocalDate;

public record BlogUserDTO(String firstName, String lastName, String email, String userName, String passwordHash,
        LocalDate birthDate) {
    public BlogUserDTO(BlogUser blogUser) {
        this(blogUser.getFirstName(), blogUser.getLastName(), blogUser.getEmail(), blogUser.getUserName(),
                blogUser.getPasswordHash(), blogUser.getBirthDate());
    }
}
