package com.nt.user.microservice.repository;

import com.nt.user.microservice.entites.WalletBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletBalanceRepository extends JpaRepository<WalletBalance, Integer> {
  WalletBalance findByUserId(Integer userId);
  void deleteByUserId(Integer userId);
}


