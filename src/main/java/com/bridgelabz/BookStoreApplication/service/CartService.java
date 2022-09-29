package com.bridgelabz.BookStoreApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreApplication.DTO.CartDTO;
import com.bridgelabz.BookStoreApplication.model.BookDetails;
import com.bridgelabz.BookStoreApplication.model.CartData;
import com.bridgelabz.BookStoreApplication.model.UserRegistrationData;
import com.bridgelabz.BookStoreApplication.repository.BookDetailsRepository;
import com.bridgelabz.BookStoreApplication.repository.CartRepository;
import com.bridgelabz.BookStoreApplication.repository.UserRegistrationRepository;
import com.bridgelabz.BookStoreApplication.tokenutil.TokenUtil;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookDetailsRepository bookrepo;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    UserRegistrationRepository userRepository;

    @Override
    public CartData addToCart(String token, int bookId, CartDTO cartdto) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isPresent = userRepository.findById(id);
        Optional<BookDetails> bookDetails = bookrepo.findById(bookId);
        if (isPresent.isPresent()&& bookDetails.isPresent()) {
            CartData cartData = new CartData();
            cartData.setQuantity(cartdto.getQuantity());
            CartData cart = new CartData(isPresent.get(), bookDetails.get(), cartData.getQuantity());
             cartRepository.save(cart);
             return cart;
        } else {
            return null;
        }
    }

    @Override
    public void removeCart(int cartId) {
        cartRepository.deleteById(cartId);

    }

    @Override
    public CartData updateCartQuentity(String token, int cartId, int quantity) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isPresent = userRepository.findById(id);
        if (isPresent.isPresent()) {
            CartData cart = cartRepository.getById(cartId);
            cart.setQuantity(quantity);
            return cartRepository.save(cart);
        } else {
            return null;
        }

    }

    @Override
    public List<CartData> getAllCart(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isPresent = userRepository.findById(id);
        if (isPresent.isPresent()) {
            List<CartData> cartProduct = cartRepository.findAll();
            return cartProduct;
        } else {
            return null;
        }

    }
}