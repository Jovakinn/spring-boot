package com.mainacad.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import com.mainacad.dao.connection.ConnectionFactory;
import com.mainacad.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Setter
@Getter
@Repository
public class UserDAO{

    @Autowired
	private ConnectionFactory connectionFactory;
	
	public User save(User user){
		Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(user);
        user.setId(id);
        session.getTransaction().commit();
        session.close();
        return user;
	}

    public User update(User user){
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }
	
	public User findOne(Integer id){
	    try (Session session = connectionFactory.getSessionFactory().openSession();){
            session.getTransaction().begin();
            User userFromDB = session.get(User.class, id);
            session.getTransaction().commit();
            return userFromDB;
	    } catch (Exception e){
	        e.printStackTrace();
        }
	    return null;
	}

	public List<User> findAll(){
		Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        String sql = "SELECT * FROM users";
        List<User> result = session.createNativeQuery(sql, User.class).getResultList();

        session.close();
        return result;
	}
	
	public void delete(User user) {
		Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
	}
}
