package com.bridgelabz.BookStoreApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApplication.DTO.CartDTO;
import com.bridgelabz.BookStoreApplication.model.CartData;


@Service
public interface ICartService {

    CartData addToCart(String token, int bookId, CartDTO cartdto);

    void removeCart(int cartId);

    CartData updateCartQuentity(String token, int cartId, int quantity);

    List<CartData> getAllCart(String token);

}