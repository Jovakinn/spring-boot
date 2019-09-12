//package com.mainacad.dao;
//
//import com.mainacad.ApplicationRunner;
//import com.mainacad.entity.Cart;
//import com.mainacad.entity.Item;
//import com.mainacad.entity.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * This test is working incorrect because of relation to another bean,
// * to create Order we need other beans but Spring told us, that
// * it is NullPointerException...
// */
//
//
//@SpringJUnitConfig(ApplicationRunner.class)
//@ActiveProfiles("dev")
//class OrderDAOTest {
//
//    @Autowired
//    OrderDAO orderDAO;
//    ItemDAO itemDAO;
//    CartDAO cartDAO;
//
//
//    @Test
//    public void testSaveAndDelete(){
//        Cart cart = new Cart();
//        cartDAO.save(cart);
//
//        Item item = new Item();
//        itemDAO.save(item);
//
//        Order order = new Order();
//        order.setAmount(1);
//        order.setCart(cart);
//        order.setItem(item);
//
//        Order testOrder = orderDAO.save(order);
//
//        assertNotNull(testOrder);
//        assertNotNull(testOrder.getId());
//
//        orderDAO.delete(order);
//        assertNull(orderDAO.findOne(testOrder.getId()));
//    }
//
//    @Test
//    public void testGet(){
//        Cart cart = new Cart();
//        cartDAO.save(cart);
//
//        Item item = new Item();
//        itemDAO.save(item);
//
//        Order order = new Order();
//        order.setAmount(1);
//        order.setCart(cart);
//        order.setItem(item);
//
//        Order savedOrder = orderDAO.save(order);
//
//        Order testOrder = orderDAO.findOne(savedOrder.getId());
//
//        assertNotNull(testOrder);
//        assertNotNull(testOrder.getId());
//
//        orderDAO.delete(order);
//    }
//
//}