//package com.mainacad.dao;
//
//import com.mainacad.ApplicationRunner;
//import com.mainacad.entity.Item;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringJUnitConfig(ApplicationRunner.class)
//@ActiveProfiles("dev")
//class ItemDAOTest {
//
//    @Autowired
//    ItemDAO itemDAO;
//
//    @Test
//    public void getConnectionFactory() {
//        assertNotNull(itemDAO.getConnectionFactory());
//        assertTrue(itemDAO.getConnectionFactory().getClass().getSimpleName().equals("PostgresFactory"));
//    }
//    @Test
//    public void testSaveAndDelete(){
//        Item item = new Item();
//        item.setPrice(10000);
//        item.setName("Machina");
//        item.setInitPrice(2222);
//        item.setArticle("Perf");
//
//        Item testItem = itemDAO.save(item);
//
//
//        assertNotNull(testItem);
//        assertNotNull(testItem.getId());
//
//        itemDAO.delete(item);
//        assertNull(itemDAO.findOne(testItem.getId()));
//    }
//
//    @Test
//    public void testGet(){
//        Item item = new Item();
//        item.setPrice(10000);
//        item.setName("Machina");
//        item.setInitPrice(2222);
//        item.setArticle("Perf");
//
//        Item savedItem = itemDAO.save(item);
//
//        Item testItem = itemDAO.findOne(savedItem.getId());
//
//        assertNotNull(testItem);
//        assertNotNull(testItem.getId());
//
//        itemDAO.delete(item);
//    }
//
//    @Test
//    public void testUpdate(){
//        Item item = new Item();
//        item.setPrice(10000);
//        item.setName("Machina");
//        item.setInitPrice(2222);
//        item.setArticle("Perf");
//
//        Item savedItem = itemDAO.save(item);
//
//        assertNotNull(savedItem);
//
//        Item modifItem = new Item();
//        modifItem.setPrice(3300);
//        modifItem.setName("Perddd");
//        modifItem.setInitPrice(2222);
//        modifItem.setArticle("Perf");
//
//        Item testItem = itemDAO.update(modifItem);
//
//        assertNotNull(testItem);
//
//        Item dbItem = itemDAO.findOne(savedItem.getId());
//
//        assertEquals(dbItem.getArticle(), "Perf");
//        assertEquals(dbItem.getInitPrice(), 2222);
//        assertEquals(dbItem.getName(), "Perddd");
//        assertEquals(dbItem.getPrice(), 3300);
//
//        itemDAO.delete(dbItem);
//    }
//
//    @Test
//    public void testGetAll(){
//        Item item = new Item();
//        item.setPrice(10000);
//        item.setName("Machina");
//        item.setInitPrice(2222);
//        item.setArticle("Perf");
//
//        Item modifItem = new Item();
//        modifItem.setPrice(3300);
//        modifItem.setName("Perddd");
//        modifItem.setInitPrice(2222);
//        modifItem.setArticle("Perf");
//
//        Item savedItem1 = itemDAO.save(item);
//        Item savedItem2 = itemDAO.save(modifItem);
//
//        List<Item> items = itemDAO.findAll();
//
//        assertNotNull(items);
//        assertTrue(items.size() >= 2);
//
//        itemDAO.delete(savedItem1);
//        itemDAO.delete(savedItem2);
//    }
//}