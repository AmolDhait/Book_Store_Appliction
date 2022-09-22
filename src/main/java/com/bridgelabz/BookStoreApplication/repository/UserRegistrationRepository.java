package com.bridgelabz.BookStoreApplication.repository;
import java.util.Optional;

import com.bridgelabz.BookStoreApplication.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer> {

    Optional<UserRegistrationData> findByEmailId(String emailId);

}
