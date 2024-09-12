package com.nt.user.microservice.repository;

import com.nt.user.microservice.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Address} entities.
 * Provides methods for performing CRUD operations and custom queries on the Address table.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

  /**
   * Finds a list of addresses associated with a given user ID.
   *
   * @param userId the ID of the user whose addresses are to be found.
   * @return a list of addresses associated with the given user ID.
   */
  List<Address> findByUserId(Integer userId);

  /**
   * Deletes an address by its ID.
   *
   * @param id the ID of the address to be deleted.
   */
  void deleteById(Integer id);

  /**
   * Checks if an address exists by its ID.
   *
   * @param id the ID of the address to check.
   * @return true if the address exists, false otherwise.
   */
  boolean existsById(Integer id);

  /**
   * Finds all addresses associated with a given user ID.
   *
   * @param userId the ID of the user whose addresses are to be found.
   * @return a list of all addresses associated with the given user ID.
   */
  List<Address> findAllByUserId(Integer userId);
}
