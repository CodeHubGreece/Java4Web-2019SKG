package org.regeneration.rest.restless.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.regeneration.rest.restless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testNamedQueryFindUserByUsername() {
        User user = new User("Bill Gates", "user", "user", "USER");
        repository.save(user);
        User foundUser = repository.findUserByUsername("UsEr");

        assertNotNull(foundUser, "no user with username AdMiN found");
        assertEquals("user", foundUser.getUsername());
    }

    @Test
    public void testNamedQueryFindUserByUsernameAdminNotFound() {
        User admin = new User("Jeffn Bezos", "admin", "admin", "ADMIN");
        repository.save(admin);
        User foundUser = repository.findUserByUsername("admin");

        assertNull(foundUser, "ADMIN role user found");
    }

}