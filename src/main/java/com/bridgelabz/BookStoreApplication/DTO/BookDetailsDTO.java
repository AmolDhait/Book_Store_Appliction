package com.bridgelabz.BookStoreApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {
    //private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private int bookPrice;
    private int bookQuantity;
}
