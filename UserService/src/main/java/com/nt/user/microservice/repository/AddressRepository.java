package com.nt.user.microservice.repository;

import com.nt.user.microservice.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
  List<Address> findByUserId(Integer userId);
  void deleteById(Integer id);
  boolean existsById(Integer id);
}



