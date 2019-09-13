package com.mainacad.controller;

import com.mainacad.entity.Order;
import com.mainacad.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);

        if (savedOrder != null) {
            return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.update(order);

        if (updatedOrder != null) {
            return new ResponseEntity(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/{id}","/",""})
    public ResponseEntity getOneOrList(@PathVariable(required = false) Integer id) { // check without id
        if (id != null) {
            Order orderFromDB = orderService.findOne(id);
            if (orderFromDB != null){
                return new ResponseEntity<>(orderFromDB, HttpStatus.OK);
            }
        } else {
            List<Order> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
