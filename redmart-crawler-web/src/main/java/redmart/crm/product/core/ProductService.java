package redmart.crm.product.core;

import java.util.List;

import org.mongodb.morphia.Datastore;

import redmart.crm.product.entity.BrandDetails;
import redmart.crm.product.exception.InvalidParameterException;
import redmart.crm.product.exception.SystemException;

/**
 * @author dipankar.saha
 *
 */
public interface ProductService {

	/**
	 * 
	 * @param productType
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalProductCount(String productType,Datastore datastore)
			throws InvalidParameterException, SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalMatchedProductCount(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalUnmatchedProductCount(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalUnderpricedProduct(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalOverpricedProduct(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	long getTotalSamepricedProduct(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	List<BrandDetails> getBrandsWisePrice(Datastore datastore) throws InvalidParameterException,
			SystemException;

	/**
	 * @param datastore
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	boolean importCompetitorData(String filePath)
			throws InvalidParameterException, SystemException;

	/**
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	boolean importRedMartData(String filePath)
			throws InvalidParameterException, SystemException;

	/**
	 * 
	 * @param content
	 * @param fileName
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	boolean importRedMartFile(String content, String fileName,Datastore datastore)
			throws InvalidParameterException, SystemException;
	
	/**
	 * 
	 * @param content
	 * @param fileName
	 * @param datastore
	 * @return
	 * @throws {@link InvalidParameterException}
	 * @throws {@link SystemException}
	 */
	boolean importCompetitorFile(String content, String fileName,Datastore datastore)
			throws InvalidParameterException, SystemException;

}
