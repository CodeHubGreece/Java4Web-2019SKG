package org.regeneration.rest.restless.repository;

import org.regeneration.rest.restless.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
