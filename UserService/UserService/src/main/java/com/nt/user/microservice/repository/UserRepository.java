package com.nt.user.microservice.repository;

import com.nt.user.microservice.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Provides methods for performing CRUD operations and custom queries on the User table.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Finds a user by their email address.
   *
   * @param email the email address of the user to be found.
   * @return an {@link Optional} containing the user if found,
   * or an empty {@link Optional} if no user exists with the given email.
   */
  Optional<User> findByEmail(String email);
}
