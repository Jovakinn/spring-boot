package com.mainacad.controller;

import com.mainacad.entity.Cart;
import com.mainacad.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.save(cart);

        if (savedCart != null) {
            return new ResponseEntity<Cart>(savedCart, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.update(cart);

        if (updatedCart != null) {
            return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping({"/{id}","/",""})
    public ResponseEntity getOneOrList(@PathVariable(required = false) Integer id) { // check without id
        if (id != null) {
            Cart cartFromDB = cartService.findOne(id);
            if (cartFromDB!= null){
                return new ResponseEntity<>(cartFromDB, HttpStatus.OK);
            }
        } else {
            List<Cart> carts = cartService.findAll();
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        cartService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
