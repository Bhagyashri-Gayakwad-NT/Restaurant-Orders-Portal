package com.nt.user.microservice.repository;

import com.nt.user.microservice.entites.WalletBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link WalletBalance} entities.
 * Provides methods for performing CRUD operations and custom queries on the WalletBalance table.
 */
@Repository
public interface WalletBalanceRepository extends JpaRepository<WalletBalance, Integer> {

  /**
   * Finds the {@link WalletBalance} associated with a specific user ID.
   *
   * @param userId the ID of the user whose wallet balance is to be found.
   * @return the {@link WalletBalance} associated with the given user ID, or null if no such wallet balance exists.
   */
  WalletBalance findByUserId(Integer userId);

  /**
   * Deletes the {@link WalletBalance} associated with a specific user ID.
   *
   * @param userId the ID of the user whose wallet balance is to be deleted.
   */
  void deleteByUserId(Integer userId);

}
