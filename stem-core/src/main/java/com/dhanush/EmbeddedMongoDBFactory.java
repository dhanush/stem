
package com.dhanush;

import org.springframework.data.mongodb.MongoDbFactory;


/**
 *An interface that extends {@link MongoDbFactory}.
 *
 * Add methods in case of any specific needs
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
public interface EmbeddedMongoDBFactory extends MongoDbFactory {
	
	void stopDB(String databaseName);
}
