package com.bridgelabz.BookStoreApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApplication.DTO.LoginDTO;
import com.bridgelabz.BookStoreApplication.DTO.ResponseDTO;
import com.bridgelabz.BookStoreApplication.DTO.UserRegistrationDTO;
import com.bridgelabz.BookStoreApplication.model.UserRegistrationData;

@Service
public interface IUserRegistration {
    public ResponseDTO createUserData(UserRegistrationDTO userdto);

    public List<UserRegistrationData> getAllUsers();

    public ResponseDTO updateUserData(int userId, UserRegistrationDTO userdto);

    public ResponseDTO deleteUserData(int userId);

    public UserRegistrationData getUserDataById(int userId);

    public ResponseDTO loginValidation(LoginDTO logindto);

    public Boolean verifyUser(String token);

    UserRegistrationData forgotPassword(String emailId, String password);

}
