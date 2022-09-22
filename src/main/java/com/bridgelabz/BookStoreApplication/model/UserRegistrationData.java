package com.bridgelabz.BookStoreApplication.model;

import com.bridgelabz.BookStoreApplication.DTO.UserRegistrationDTO;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name ="userregistration" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column
    private String kyc;

    @Column(name = "emailId")
    private String emailId;

    @Column
    private String password;

    @Column
    private LocalDate dob;

    @Column(name = "registerDate")
    private LocalDate registerDate;

    @Column(name = "verify")
    private Boolean verify;

    @Column(name = "updatedDate")
    private LocalDate updatedDate;


    public void createUser(UserRegistrationDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.kyc = userDTO.getKyc();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.dob = userDTO.getDob();
        this.registerDate = userDTO.getRegisterDate();
        this.verify = userDTO.getVerify();
        this.updatedDate = userDTO.getUpdatedDate();


    }

    public void updateUser(UserRegistrationDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.kyc = userDTO.getKyc();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.dob = userDTO.getDob();
        this.registerDate = userDTO.getRegisterDate();
        this.verify = userDTO.getVerify();
        this.updatedDate = userDTO.getUpdatedDate();


    }
}
