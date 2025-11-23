package com.marketplace.backend.repositories;

import com.marketplace.backend.domain.entities.Faculty;
import com.marketplace.backend.domain.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface iUserRepository extends iGenericRepository<User, UUID> {

    User findByUsername(String username);
    List<User> findUsersByRole(String role);
    List<User> findUsersByNameContainingIgnoreCase(String name);
    List<User> findUsersByFaculty(Faculty faculty);
    List<User> findUsersByRating(Double rating);
}
