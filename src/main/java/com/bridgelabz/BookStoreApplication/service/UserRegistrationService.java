package com.bridgelabz.BookStoreApplication.service;

import com.bridgelabz.BookStoreApplication.repository.UserRegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.BookStoreApplication.DTO.LoginDTO;
import com.bridgelabz.BookStoreApplication.DTO.ResponseDTO;
import com.bridgelabz.BookStoreApplication.DTO.UserRegistrationDTO;
import com.bridgelabz.BookStoreApplication.exception.UserRegistrationException;
import com.bridgelabz.BookStoreApplication.model.UserRegistrationData;
import com.bridgelabz.BookStoreApplication.repository.UserRegistrationRepository;
import com.bridgelabz.BookStoreApplication.tokenutil.TokenUtil;

    @Service
    public class UserRegistrationService implements IUserRegistration {
        @Autowired
        private UserRegistrationRepository repository;
        @Autowired
        ModelMapper modelmapper;
        @Autowired
        private TokenUtil tokenutil;

        public List<UserRegistrationData> getAllUsers() {
            return repository.findAll();
        }

        public ResponseDTO updateUserData(int userId, @Valid UserRegistrationDTO userdto) {
            UserRegistrationData empData = this.getUserDataById(userId);

            Optional<UserRegistrationData> isEmployeePresent = repository.findById(userId);
            if (isEmployeePresent.isPresent()) {
                empData.updateUser(userdto);
                repository.save(isEmployeePresent.get());
                return new ResponseDTO("Contact Succefully Updated", empData);
            } else {
                throw new UserRegistrationException("Id is not valid!!");
            }

        }

        public ResponseDTO deleteUserData(int userId) {
            Optional<UserRegistrationData> isContactPresent = repository.findById(userId);
            if (isContactPresent.isPresent()) {
                repository.delete(isContactPresent.get());
                return new ResponseDTO("Contact Succefully deleted", userId);

            } else {
                throw new UserRegistrationException("Contact is not preset!!");
            }

        }

        public UserRegistrationData getUserDataById(int userId) {
            Optional<UserRegistrationData> isEmployeePresent = repository.findById(userId);
            if (isEmployeePresent.isPresent()) {
                return repository.findById(userId)
                        .orElseThrow(() -> new UserRegistrationException("employee ID Not Found"));
            } else
                throw new UserRegistrationException("Not Valid id");
        }

        @Override
        public ResponseDTO createUserData(@Valid UserRegistrationDTO userdto) {
            Optional<UserRegistrationData> isPresent = repository.findByEmailId(userdto.getEmailId());
            if (isPresent.isPresent()) {
                throw new UserRegistrationException("User Already Added");
            } else {
                UserRegistrationData user = new UserRegistrationData();
                user.createUser(userdto);
                repository.save(user);
                return new ResponseDTO("user created successfully", user);
            }
        }

        @Override
        public ResponseDTO loginValidation(LoginDTO logindto) {
            String token;
            Optional<UserRegistrationData> isPresent = repository.findByEmailId(logindto.getEmailId());
            if (isPresent.isPresent()) {
                String pass = isPresent.get().getPassword();
                if (pass.equals(logindto.getPassword())) {
                    token = tokenutil.createToken(isPresent.get().getUserId());
                    return new ResponseDTO("Employee is found", token);
                } else {
                    throw new UserRegistrationException("Password is Wrong");
                }
            } else {
                throw new UserRegistrationException("Email ID or password is wrong");
            }
        }

        public Boolean verifyUser(String token) {
            int id = tokenutil.decodeToken(token);
            Optional<UserRegistrationData> isPresent = repository.findById(id);
            if (isPresent.isPresent()) {
                isPresent.get().setVerify(true);
                repository.save(isPresent.get());
                return true;
            } else {
                return false;
            }
        }

        @Override
        public UserRegistrationData forgotPassword(String emailId, String password) {
            Optional<UserRegistrationData> isPresent = repository.findByEmailId(emailId);
            if (isPresent.isPresent()) {
                isPresent.get().setPassword(password);
                return repository.save(isPresent.get());
            } else {
                throw new UserRegistrationException("Invalid Email") ;
            }
        }
    }

