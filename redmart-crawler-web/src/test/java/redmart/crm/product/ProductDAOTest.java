package redmart.crm.product;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import redmart.crm.product.core.ProductService;
import redmart.crm.product.core.impl.ProductServiceImpl;
import redmart.crm.product.exception.InvalidParameterException;
import redmart.crm.product.exception.SystemException;
import redmart.crm.product.utils.ApplicationConstants;

import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class ProductDAOTest {

	public ProductDAOTest() {

	}

	private static final String DATABASE_NAME = "test";
	private MongodProcess mongod;
	private MongodExecutable mongodExecutable;
	private final int port = 12345;
	private final String host = "localhost";
	private static final String IMPORT_FILE_NAME = "redmart.csv";

	@BeforeMethod
	public void beforeEach() throws Exception {
		MongodStarter starter = MongodStarter.getDefaultInstance();
		@SuppressWarnings("deprecation")
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.LEGACY)
				.net(new Net(port, Network.localhostIsIPv6())).build();
		mongodExecutable = starter.prepare(mongodConfig);
		mongod = mongodExecutable.start();
	}

	@AfterMethod
	public void afterEach() throws Exception {
		if (this.mongod != null) {
			this.mongod.stop();
			this.mongodExecutable.stop();
		}
	}

	@Test
	public void shouldCreateNewObjectInEmbeddedMongoDb() throws IOException {
		boolean isImported = false;
		Morphia morphia = new Morphia();
		morphia.mapPackage(ApplicationConstants.MONGO_PACKAGE);
		MongoClient mongoClient = new MongoClient(host, port);
		Datastore datastore = morphia.createDatastore(mongoClient,
				DATABASE_NAME);

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(IMPORT_FILE_NAME);
		String content = IOUtils.toString(inputStream, null);
		ProductService service = new ProductServiceImpl();
		try {
			isImported = service.importRedMartFile(content, IMPORT_FILE_NAME,
					datastore);
		} catch (InvalidParameterException | SystemException e) {
			Assert.assertEquals(isImported, false);
		}

		Assert.assertEquals(isImported, true);
	}

}
