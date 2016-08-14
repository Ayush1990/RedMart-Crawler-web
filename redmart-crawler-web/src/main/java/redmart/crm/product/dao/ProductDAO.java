/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redmart.crm.product.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import redmart.crm.product.mongo.entity.Product;
import redmart.crm.product.utils.ApplicationConstants;

/**
 *
 * @author dipankar.saha
 */
public class ProductDAO {
	
	static Logger _logger = Logger.getLogger(ProductDAO.class.getName());

	String host;
	Integer port;
	String databaseName;
	String userName;
	String password;
	Datastore datastore;

	public ProductDAO() {
		this.host = ApplicationConstants.MONGO_DB_HOST;
		this.port = ApplicationConstants.MONGO_DB_PORT;
		this.databaseName = ApplicationConstants.MONGO_DB_NAME;
		this.userName = ApplicationConstants.MONGO_DB_USER;
		this.password = ApplicationConstants.MONGO_DB_PWD;
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		MongoCredential credentia = MongoCredential.createCredential(userName,
				databaseName, password.toCharArray());
		credentialsList.add(credentia);
		ServerAddress addr = new ServerAddress(host, port);
		MongoClient mongoClient = new MongoClient(addr, credentialsList);
		Morphia morphia = new Morphia();
		morphia.mapPackage(ApplicationConstants.MONGO_PACKAGE);
		datastore = morphia.createDatastore(mongoClient, databaseName);
		datastore.ensureIndexes();
	}
	
	public ProductDAO(Datastore datastore) {
        this.datastore = datastore;
    }

	@SuppressWarnings("unused")
	public void saveProduct(List<Product> product) {
		_logger.info("Calling saveProduct");
		Iterable<Key<Product>> savedProduct = datastore.save(product);
		_logger.info("Exiting saveProduct");
	}

	public long getProductCount() {
		_logger.info("Calling getProductCount");
		DBCollection dbCollection = datastore.getCollection(Product.class);
		long count = dbCollection.count();
		_logger.info("Exiting getProductCount");
		return count;
	}

	public List<Product> getAllProducts() {
		_logger.info("Calling getAllProducts");
		Query<Product> query = datastore.createQuery(Product.class);
		List<Product> products = query.asList();
		_logger.info("Exiting getAllProducts");
		return products;

	}

}
