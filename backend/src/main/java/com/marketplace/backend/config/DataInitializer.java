package com.marketplace.backend.config;

import com.marketplace.backend.domain.entities.Category;
import com.marketplace.backend.domain.entities.Faculty;
import com.marketplace.backend.domain.entities.User;
import com.marketplace.backend.repositories.iCategoryRepository;
import com.marketplace.backend.repositories.iFacultyRepository;
import com.marketplace.backend.repositories.iUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final iFacultyRepository FacultyRepository;
    private final iUserRepository UserRepository;
    private final iCategoryRepository CategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (FacultyRepository.count() == 0 && UserRepository.count() == 0 && CategoryRepository.count() == 0) {
            Category category = new Category();
            category.setName("Libros");
            category.setDescription("Libros");
            CategoryRepository.save(category);

            Category category1 = new Category();
            category1.setName("Tecnologia");
            category1.setDescription("Tecnologia");
            CategoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Servicios");
            category2.setDescription("Servicios");
            CategoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Entretenimiento");
            category3.setDescription("Entretenimiento");
            CategoryRepository.save(category3);

            Category category4 = new Category();
            category4.setName("Comida");
            category4.setDescription("Comida");
            CategoryRepository.save(category4);

            Category category5 = new Category();
            category5.setName("Ropa");
            category5.setDescription("Ropa");
            CategoryRepository.save(category5);

            Category category6 = new Category();
            category6.setName("Otros");
            category6.setDescription("Otros");
            CategoryRepository.save(category6);

            Faculty faculty = new Faculty();
            faculty.setFacultyName("Administrador");
            FacultyRepository.save(faculty);

            User admin = new User();
            admin.setName("admin");
            admin.setUsername("admin@uca.edu.sv");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPhoneNumber("79653723");
            admin.setRating(0.0);
            admin.setReviewsCount(0);
            admin.setRole("ADMIN");

            faculty.addUser(admin);
            UserRepository.save(admin);

            Faculty faculty1 = new Faculty();
            faculty1.setFacultyName("Facultad de Ciencias Sociales y Humanidades");
            FacultyRepository.save(faculty1);

            Faculty faculty2 = new Faculty();
            faculty2.setFacultyName("Facultad de Ciencias Económicas y Empresariales");
            FacultyRepository.save(faculty2);

            Faculty faculty3 = new Faculty();
            faculty3.setFacultyName("Facultad de Ingeniería y Arquitectura");
            FacultyRepository.save(faculty3);

            Faculty faculty4 = new Faculty();
            faculty4.setFacultyName("Facultad de Postgrados");
            FacultyRepository.save(faculty4);

        }
    }
}
