package com.mainacad.dao;

import com.mainacad.dao.connection.ConnectionFactory;
import com.mainacad.entity.Order;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Setter
@Repository
public class OrderDAO {

    @Autowired
    private ConnectionFactory connectionFactory;

    public Order save(Order order){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(order);
        order.setId(id);

        session.getTransaction().commit();
        session.close();

        return order;
    }

    public Order update(Order order){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(order);

        session.getTransaction().commit();
        session.close();

        return order;
    }

    public Order findOne(Integer id){
        try (Session session = connectionFactory.getSessionFactory().openSession();){
            session.getTransaction().begin();
            Order orderFromDB = session.get(Order.class, id);

            session.getTransaction().commit();

            return orderFromDB;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> findAll(){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        String sql = "SELECT * FROM orders";
        List<Order> result = session.createNativeQuery(sql, Order.class).getResultList();

        session.close();
        return result;
    }

    public void delete(Order user) {
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }
}
