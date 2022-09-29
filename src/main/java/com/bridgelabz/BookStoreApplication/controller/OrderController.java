package com.bridgelabz.BookStoreApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.BookStoreApplication.DTO.OrderDTO;
import com.bridgelabz.BookStoreApplication.DTO.ResponseDTO;
import com.bridgelabz.BookStoreApplication.model.OrderData;
import com.bridgelabz.BookStoreApplication.repository.OrderRepository;
import com.bridgelabz.BookStoreApplication.service.IOrderService;

@RestController
@RequestMapping("/orderData")
public class OrderController {
    @Autowired
    private IOrderService orderservice;
    @Autowired
    private OrderRepository orderrepo;

    @PostMapping("/order")
    ResponseEntity<ResponseDTO> createOrderData(@RequestHeader(name = "token") String token,
                                                @RequestBody OrderDTO ordrdto, @RequestParam int bookId) {
        OrderData orderData = null;
        orderData = orderservice.createOrderData(token, ordrdto, bookId);
        ResponseDTO respDTO = new ResponseDTO("order Created :", orderData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{orderId}")
    ResponseEntity<ResponseDTO> createOrderData(@RequestHeader(name = "token") String token,
                                                @PathVariable int orderId) {
        OrderData orderData = null;
        orderData = orderservice.cancelOrder(token, orderId);
        ResponseDTO respDTO = new ResponseDTO("order has benn canceled for:", orderData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);

    }

    @GetMapping("/allOrders")
    ResponseEntity<ResponseDTO> getAllOrder() {
        List<OrderData> orderdata = orderservice.FindAllOrders();
        ResponseDTO response = new ResponseDTO("Retrieved all orders ", orderdata);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @GetMapping("/getUser")

    ResponseEntity<ResponseDTO> getAllCartsByUser(@RequestHeader(name = "token") String token) {
        List<OrderData> allItemsForUser = orderservice.getAllOrdersOfUser(token);
        ResponseDTO response = new ResponseDTO(" Get All orders for user ", allItemsForUser);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}