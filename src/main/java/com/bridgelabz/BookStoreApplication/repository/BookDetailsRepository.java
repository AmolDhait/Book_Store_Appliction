package com.bridgelabz.BookStoreApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.BookStoreApplication.model.BookDetails;

public interface BookDetailsRepository  extends JpaRepository<BookDetails, Integer> {

    Optional<BookDetails> findById(int bookid);

}