package org.regeneration.rest.restless;

import org.regeneration.rest.restless.model.Book;
import org.regeneration.rest.restless.model.User;
import org.regeneration.rest.restless.repository.BookRepository;
import org.regeneration.rest.restless.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadData {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   BookRepository bookRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User("Bill Gates", "user", passwordEncoder.encode("user"), "USER");
            User admin = new User("Jeffn Bezos", "admin", passwordEncoder.encode("admin"), "ADMIN");
            userRepository.save(user);
            userRepository.save(admin);

            List<Book> books = Arrays.asList(
                    new Book("The Grapes of Wrath", "0143125508"),
                    new Book("Symposium", "0872200760"),
                    new Book("Pride and Prejudice", "0486284735"),
                    new Book("The Great Gatsby", "9780141182636")
            );
            bookRepository.saveAll(books);
        };

    }
}
