package redmart.crm.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import redmart.crm.product.mongo.entity.CompetitorProduct;
import redmart.crm.product.utils.ApplicationConstants;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
*
* @author dipankar.saha
*/
public class CompetitorProductDAO {
	
	static Logger _logger = Logger.getLogger(CompetitorProductDAO.class.getName());

	String host;
	Integer port;
	String databaseName;
	String userName;
	String password;
	Datastore datastore;

	public CompetitorProductDAO() {
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
	
	public CompetitorProductDAO(Datastore datastore) {
        this.datastore = datastore;
    }

	@SuppressWarnings("unused")
	public void saveCompetitorProduct(List<CompetitorProduct> product) {
		_logger.info("Calling saveCompetitorProduct");
		Iterable<Key<CompetitorProduct>> savedProduct = datastore.save(product);
		_logger.info("Exiting saveCompetitorProduct");
	}
	
	public long getProductCount(){
		_logger.info("Calling getProductCount");
		DBCollection dbCollection = datastore.getCollection(CompetitorProduct.class);
		long count = dbCollection.count();
		_logger.info("Exiting getProductCount");
		return count;
	}
	
	public List<CompetitorProduct> getAllProducts(){
		_logger.info("Calling getAllProducts");
		Query<CompetitorProduct> query = datastore.createQuery(CompetitorProduct.class);
		List<CompetitorProduct> products = query.asList();
		_logger.info("Exiting getAllProducts");
		return products;
	}
	
	public List<CompetitorProduct> getTotalMatchedProducts(){
		_logger.info("Calling getTotalMatchedProducts");
		Query<CompetitorProduct> query = datastore.createQuery(CompetitorProduct.class)
				                                  .filter("matchedProductId !=",null);
		List<CompetitorProduct> products = query.asList();
		_logger.info("Exiting getTotalMatchedProducts");
		return products;
	}
	
	public List<CompetitorProduct> getTotalUnMatchedProducts(){
		_logger.info("Calling getTotalUnMatchedProducts");
		Query<CompetitorProduct> query = datastore.createQuery(CompetitorProduct.class)
				                                  .filter("matchedProductId ==",null);
		List<CompetitorProduct> products = query.asList();
		_logger.info("Exiting getTotalUnMatchedProducts");
		return products;
	}

}
