package com.dhanush;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhanush.EmbeddedMongoDBFactory;
import com.mongodb.DB;

/**
 * Unit test to test Embedded Mongo Db Server
 * 
 * @author Dhanush Gopinath
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/spring-mongo-context.xml" })
public class EmbeddedMongoDBFactoryTest{
	
	@Autowired
	EmbeddedMongoDBFactory embeddedMongoDBFactory;
	
	@Test
	public void testGetDB() {
		DB db = embeddedMongoDBFactory.getDb();
		assertNotNull(db);
		assertEquals("test", db.getName());
	}

}
