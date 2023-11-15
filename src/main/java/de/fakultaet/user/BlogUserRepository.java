package de.fakultaet.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
    public Optional<BlogUser> findByUserName(String userName);
}
