package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
class CartDAOTest {
}