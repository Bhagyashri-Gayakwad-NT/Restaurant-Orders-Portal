package com.nt.user.microservice.service;

import com.nt.user.microservice.indto.WalletBalanceDTO;

public interface WalletBalanceService {
  WalletBalanceDTO getBalance(Long userId);
  void addBalance(Long userId, double amount);
}

