package com.mainacad.dao;

import com.mainacad.dao.connection.ConnectionFactory;
import com.mainacad.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Setter
@Getter
@Repository
public class ItemDAO {

    @Autowired
    private ConnectionFactory connectionFactory;

    public Item save(Item item){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(item);
        item.setId(id);

        session.getTransaction().commit();
        session.close();

        return item;
    }


    public Item update(Item item){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(item);

        session.getTransaction().commit();
        session.close();

        return item;
    }

    public Item findOne(Integer id){
        try (Session session = connectionFactory.getSessionFactory().openSession();){
            session.getTransaction().begin();

            Item itemFromDB = session.get(Item.class, id);
            session.getTransaction().commit();

            return itemFromDB;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> findAll(){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        String sql = "SELECT * FROM items";
        List<Item> result = session.createNativeQuery(sql, Item.class).getResultList();

        session.close();
        return result;
    }

    public void delete(Item item){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(item);

        session.getTransaction().commit();
        session.close();
    }
}