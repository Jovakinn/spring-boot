package com.mainacad.dao;

import com.mainacad.dao.connection.ConnectionFactory;
import com.mainacad.entity.Cart;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Setter
@Repository
public class CartDAO {

    @Autowired
    private ConnectionFactory connectionFactory;

    public Cart save(Cart cart){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(cart);
        cart.setId(id);

        session.getTransaction().commit();
        session.close();

        return cart;
    }


    public Cart update(Cart cart){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(cart);

        session.getTransaction().commit();
        session.close();

        return cart;
    }

    public Cart findOne(Integer id){
        try (Session session = connectionFactory.getSessionFactory().openSession();){
            session.getTransaction().begin();

            Cart cartFromDB = session.get(Cart.class, id);
            session.getTransaction().commit();

            return cartFromDB;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Cart> findAll(){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        String sql = "SELECT * FROM carts";
        List<Cart> result = session.createNativeQuery(sql, Cart.class).getResultList();

        session.close();
        return result;
    }

    public void delete(Cart cart){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(cart);

        session.getTransaction().commit();
        session.close();
    }
}
