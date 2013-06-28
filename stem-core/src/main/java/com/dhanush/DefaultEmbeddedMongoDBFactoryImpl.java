package com.dhanush;

import java.io.IOException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * An implementation of {@link MongoDbFactory} that will be started in emdedded
 * mode
 * 
 * @author Dhanush Gopinath
 * 
 * @version 1.0.0
 */
public class DefaultEmbeddedMongoDBFactoryImpl implements
		EmbeddedMongoDBFactory {

	private String databaseName;
	private MongodExecutable mongodExecutable;
	private DB db;

	public DefaultEmbeddedMongoDBFactoryImpl(String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public DB getDb() throws DataAccessException {
		return getDb(databaseName);
	}

	@Override
	public DB getDb(String dbName) throws DataAccessException {
		// return if db already exists
		if (db != null && db.getName().equals(dbName)) {
			return db;
		}
		try {
			int port = 12345;
			MongodConfig mongodConfig = new MongodConfig(Version.Main.V2_4,
					port, Network.localhostIsIPv6());
			MongodStarter runtime = MongodStarter.getDefaultInstance();
			mongodExecutable = runtime.prepare(mongodConfig);
			mongodExecutable.start();
			Mongo mongo = new Mongo("localhost", port);
			db = mongo.getDB(dbName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return db;
	}

	@Override
	public void stopDB(String databaseName) {
		if (db != null && mongodExecutable != null)
			mongodExecutable.stop();
	}

}
