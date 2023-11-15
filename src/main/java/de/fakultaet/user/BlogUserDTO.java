package de.fakultaet.user;

import java.time.LocalDate;

public record BlogUserDTO(String firstName, String lastName, String email, String userName, LocalDate birthDate) {

}
