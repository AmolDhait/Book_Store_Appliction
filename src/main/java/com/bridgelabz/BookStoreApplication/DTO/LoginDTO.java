package com.bridgelabz.BookStoreApplication.DTO;


import lombok.Data;

@Data
public class LoginDTO {
    public String emailId;
    public String password;
    public LoginDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
}