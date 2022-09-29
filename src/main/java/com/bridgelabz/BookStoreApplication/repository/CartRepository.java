package com.bridgelabz.BookStoreApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.BookStoreApplication.model.CartData;

@Repository
public interface CartRepository extends JpaRepository<CartData, Integer> {
    List<CartData> findAll();
}