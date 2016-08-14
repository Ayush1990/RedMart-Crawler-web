package redmart.crm.product.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;

import redmart.crm.product.core.ProductService;
import redmart.crm.product.dao.CompetitorProductDAO;
import redmart.crm.product.dao.ProductDAO;
import redmart.crm.product.entity.BrandDetails;
import redmart.crm.product.entity.CompetitorProduct;
import redmart.crm.product.entity.Product;
import redmart.crm.product.exception.InvalidParameterException;
import redmart.crm.product.exception.SystemException;
import redmart.crm.product.utils.ApplicationConstants;
import redmart.crm.product.utils.ProductUtils;

/**
 * @author dipankar.saha
 *
 */
public class ProductServiceImpl implements ProductService {

	static Logger _logger = Logger
			.getLogger(ProductServiceImpl.class.getName());

	@Override
	public long getTotalMatchedProductCount(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalMatchedProductCount");
		CompetitorProductDAO dao = null;
		if (datastore != null) {
			dao = new CompetitorProductDAO(datastore);
		} else {
			dao = new CompetitorProductDAO();
		}
		List<redmart.crm.product.mongo.entity.CompetitorProduct> matchedProducts = dao
				.getTotalMatchedProducts();
		_logger.info("Exiting getTotalMatchedProductCount");
		return matchedProducts.size();
	}

	@Override
	public long getTotalUnmatchedProductCount(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalUnmatchedProductCount");
		long totalUnmatchedCount = getTotalProductCount(
				ApplicationConstants.PRODUCT_TYPE_REDMART, datastore)
				- getTotalMatchedProductCount(datastore);
		_logger.info("Exiting getTotalUnmatchedProductCount");
		return totalUnmatchedCount;
	}

	@Override
	public long getTotalProductCount(String productType, Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalProductCount");
		long totalCount = 0;
		if (productType.equals(ApplicationConstants.PRODUCT_TYPE_REDMART)) {
			totalCount = ProductUtils.getProductCount(datastore);
		}
		if (productType.equals(ApplicationConstants.PRODUCT_TYPE_COMPETITOR)) {
			totalCount = ProductUtils.getCompetitorProductCount(datastore);
		}
		_logger.info("Exiting getTotalProductCount");
		return totalCount;
	}

	@Override
	public long getTotalUnderpricedProduct(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalUnderpricedProduct");
		long totalCount = 0;
		Map<Integer, Double> productPriceMap = new HashMap<>();
		Map<Integer, Double> productPromoPriceMap = new HashMap<>();
		CompetitorProductDAO dao = null;
		if (datastore != null) {
			dao = new CompetitorProductDAO(datastore);
		} else {
			dao = new CompetitorProductDAO();
		}
		List<redmart.crm.product.mongo.entity.CompetitorProduct> competitorProductList = dao
				.getTotalMatchedProducts();
		ProductDAO productDao = null;
		if (datastore != null) {
			productDao = new ProductDAO(datastore);
		} else {
			productDao = new ProductDAO();
		}
		List<redmart.crm.product.mongo.entity.Product> productList = productDao
				.getAllProducts();
		for (redmart.crm.product.mongo.entity.Product product : productList) {
			productPriceMap.put(product.getId(), product.getPrice());
			productPromoPriceMap
					.put(product.getId(), product.getPromotePrice());
		}
		Double promoPrice;
		for (redmart.crm.product.mongo.entity.CompetitorProduct competitorProduct : competitorProductList) {
			if (competitorProduct.getOnPromo()) {
				promoPrice = Double.parseDouble(competitorProduct
						.getPromoPrice().replace("$", ""));
				if (promoPrice < productPromoPriceMap.get(competitorProduct
						.getMatchedProductId())) {
					++totalCount;
				}
			} else {
				if (competitorProduct.getPrice() < productPriceMap
						.get(competitorProduct.getMatchedProductId())) {
					++totalCount;
				}
			}
		}
		_logger.info("Exiting getTotalUnderpricedProduct");
		return totalCount;
	}

	@Override
	public long getTotalOverpricedProduct(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalOverpricedProduct");
		long totalCount = 0;
		Map<Integer, Double> productPriceMap = new HashMap<>();
		Map<Integer, Double> productPromoPriceMap = new HashMap<>();
		CompetitorProductDAO dao = null;
		if (datastore != null) {
			dao = new CompetitorProductDAO(datastore);
		} else {
			dao = new CompetitorProductDAO();
		}
		List<redmart.crm.product.mongo.entity.CompetitorProduct> competitorProductList = dao
				.getTotalMatchedProducts();
		ProductDAO productDao = null;
		if (datastore != null) {
			productDao = new ProductDAO(datastore);
		} else {
			productDao = new ProductDAO();
		}
		List<redmart.crm.product.mongo.entity.Product> productList = productDao
				.getAllProducts();
		for (redmart.crm.product.mongo.entity.Product product : productList) {
			productPriceMap.put(product.getId(), product.getPrice());
			productPromoPriceMap
					.put(product.getId(), product.getPromotePrice());
		}
		Double promoPrice;
		for (redmart.crm.product.mongo.entity.CompetitorProduct competitorProduct : competitorProductList) {
			if (competitorProduct.getOnPromo()) {
				promoPrice = Double.parseDouble(competitorProduct
						.getPromoPrice().replace("$", ""));
				if (promoPrice > productPromoPriceMap.get(competitorProduct
						.getMatchedProductId())) {
					++totalCount;
				}
			} else {
				if (competitorProduct.getPrice() > productPriceMap
						.get(competitorProduct.getMatchedProductId())) {
					++totalCount;
				}
			}
		}
		_logger.info("Exiting getTotalOverpricedProduct");
		return totalCount;
	}

	@Override
	public long getTotalSamepricedProduct(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getTotalSamepricedProduct");
		long totalCount = 0;
		Map<Integer, Double> productPriceMap = new HashMap<>();
		Map<Integer, Double> productPromoPriceMap = new HashMap<>();
		CompetitorProductDAO dao = null;
		if(datastore != null){
			dao = new CompetitorProductDAO(datastore);	
		}else{
			dao = new CompetitorProductDAO();
		}
		
		List<redmart.crm.product.mongo.entity.CompetitorProduct> competitorProductList = dao
				.getTotalMatchedProducts();
		ProductDAO productDao = null;
		if(datastore != null){
			productDao = new ProductDAO(datastore);	
		}else{
			productDao = new ProductDAO();
		}
		
		List<redmart.crm.product.mongo.entity.Product> productList = productDao
				.getAllProducts();
		for (redmart.crm.product.mongo.entity.Product product : productList) {
			productPriceMap.put(product.getId(), product.getPrice());
			productPromoPriceMap
					.put(product.getId(), product.getPromotePrice());
		}
		Double promoPrice;
		for (redmart.crm.product.mongo.entity.CompetitorProduct competitorProduct : competitorProductList) {
			if (competitorProduct.getOnPromo()) {
				promoPrice = Double.parseDouble(competitorProduct
						.getPromoPrice().replace("$", ""));
				if (promoPrice == productPromoPriceMap.get(competitorProduct
						.getMatchedProductId())) {
					++totalCount;
				}
			} else {
				if (competitorProduct.getPrice() == productPriceMap
						.get(competitorProduct.getMatchedProductId())) {
					++totalCount;
				}
			}
		}
		_logger.info("Exiting getTotalSamepricedProduct");
		return totalCount;
	}

	@Override
	public boolean importRedMartData(String filePath)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling importRedMartData");
		Product product = null;
		String cvsSplitBy = ",";
		List<Product> products = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {

				if (count != 0) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					product = new Product();
					product.setId(Integer.parseInt(data[0]));
					product.setTitle(data[1]);
					product.setPrice(Double.parseDouble(data[2]));
					product.setPromotePrice(Double.parseDouble(data[3]));
					product.setBrand(data[4]);
					product.setCategoryName1(data[5]);
					product.setCategoryName2(data[6]);
					products.add(product);
				}
				++count;

			}

		} catch (IOException e) {
			_logger.error(e.getMessage());
		}

		ProductDAO dao = new ProductDAO();
		List<redmart.crm.product.mongo.entity.Product> productList = ProductUtils
				.convertProduct(products);
		dao.saveProduct(productList);
		_logger.info("Exiting importRedMartData");
		return true;

	}

	@Override
	public boolean importRedMartFile(String content, String fileName,
			Datastore datastore) throws InvalidParameterException,
			SystemException {
		_logger.info("Calling importRedMartData");
		Product product = null;
		String cvsSplitBy = ",";
		List<Product> products = new ArrayList<>();
		String tmpPath = System.getProperty("java.io.tmpdir");
		String filePath = tmpPath + File.separator + fileName;
		try {
			File file = new File(tmpPath + File.separator + fileName);
			FileUtils.writeStringToFile(file, content);
		} catch (Exception e) {
			_logger.error(e.getMessage());
		}
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {

				if (count != 0) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					product = new Product();
					product.setId(Integer.parseInt(data[0]));
					product.setTitle(data[1]);
					product.setPrice(Double.parseDouble(data[2]));
					product.setPromotePrice(Double.parseDouble(data[3]));
					product.setBrand(data[4]);
					product.setCategoryName1(data[5]);
					product.setCategoryName2(data[6]);
					products.add(product);
				}
				++count;

			}

		} catch (IOException e) {
			_logger.error(e.getMessage());
		}

		ProductDAO dao = null;
		if (datastore != null) {
			dao = new ProductDAO(datastore);
		} else {
			dao = new ProductDAO();
		}
		List<redmart.crm.product.mongo.entity.Product> productList = ProductUtils
				.convertProduct(products);
		dao.saveProduct(productList);
		_logger.info("Exiting importRedMartData");
		return true;

	}

	@Override
	public boolean importCompetitorFile(String content, String fileName,
			Datastore datastore) throws InvalidParameterException,
			SystemException {
		_logger.info("Calling importCompetitorFile");
		CompetitorProduct product = null;
		String cvsSplitBy = ",";
		List<CompetitorProduct> products = new ArrayList<>();
		String tmpPath = System.getProperty("java.io.tmpdir");
		String filePath = tmpPath + File.separator + fileName;
		try {
			File file = new File(tmpPath + File.separator + fileName);
			FileUtils.writeStringToFile(file, content);
		} catch (Exception e) {
			_logger.error(e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {

				if (count != 0) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					try {
						product = new CompetitorProduct();

						product.setTitle(data[0]);
						if (data[1] != " " || data[1] != null || data[1] != "") {
							try {
								product.setMatchedProductId(Integer
										.parseInt(data[1]));
							} catch (NumberFormatException e) {

							}
						}
						product.setBrand(data[2]);
						product.setInStock(Boolean.parseBoolean(data[3]));
						product.setPrice(Double.parseDouble(data[4].replace(
								"$", "")));
						product.setOnPromo(Boolean.parseBoolean(data[5]));
						product.setPromoPrice(data[6]);

						product.setCategoryName1(data[7]);
						product.setCategoryName2(data[8]);
						products.add(product);
					} catch (NumberFormatException
							| ArrayIndexOutOfBoundsException e) {

					}

				}
				++count;

			}

		} catch (IOException e) {
			_logger.error(e.getMessage());
		}

		CompetitorProductDAO dao = null;
		if (datastore != null) {
			dao = new CompetitorProductDAO(datastore);
		} else {
			dao = new CompetitorProductDAO();
		}

		List<redmart.crm.product.mongo.entity.CompetitorProduct> productList = ProductUtils
				.convertCompetitorProduct(products);
		dao.saveCompetitorProduct(productList);
		_logger.info("Exiting importCompetitorFile");
		return true;
	}

	@Override
	public boolean importCompetitorData(String filePath)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling importCompetitorData");
		CompetitorProduct product = null;
		String cvsSplitBy = ",";
		List<CompetitorProduct> products = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {

				if (count != 0) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					try {
						product = new CompetitorProduct();

						product.setTitle(data[0]);
						if (data[1] != " " || data[1] != null || data[1] != "") {
							try {
								product.setMatchedProductId(Integer
										.parseInt(data[1]));
							} catch (NumberFormatException e) {

							}
						}
						product.setBrand(data[2]);
						product.setInStock(Boolean.parseBoolean(data[3]));
						product.setPrice(Double.parseDouble(data[4].replace(
								"$", "")));
						product.setOnPromo(Boolean.parseBoolean(data[5]));
						product.setPromoPrice(data[6]);

						product.setCategoryName1(data[7]);
						product.setCategoryName2(data[8]);
						products.add(product);
					} catch (NumberFormatException
							| ArrayIndexOutOfBoundsException e) {

					}

				}
				++count;

			}

		} catch (IOException e) {
			_logger.error(e.getMessage());
		}

		CompetitorProductDAO dao = new CompetitorProductDAO();
		List<redmart.crm.product.mongo.entity.CompetitorProduct> productList = ProductUtils
				.convertCompetitorProduct(products);
		dao.saveCompetitorProduct(productList);
		_logger.info("Exiting importCompetitorData");
		return true;
	}

	@Override
	public List<BrandDetails> getBrandsWisePrice(Datastore datastore)
			throws InvalidParameterException, SystemException {
		_logger.info("Calling getBrandsWisePrice");

		List<BrandDetails> details = new ArrayList<>();
		Map<String, Double> products = ProductUtils.getProductBrands(datastore);
		Map<String, Double> competitorProducts = ProductUtils
				.getCompetitorProductBrands(datastore);
		Map<String, String> brandPriceMap = new HashMap<>();
		Set<String> brandList = products.keySet();
		boolean flag = true;
		for (String brand : brandList) {
			for (String competitorProductBrand : competitorProducts.keySet()) {
				if (brand.equals(competitorProductBrand)) {
					if (products.get(brand) > competitorProducts
							.get(competitorProductBrand)) {
						brandPriceMap.put(brand,
								ApplicationConstants.OVER_PRICED);
						flag = false;

					}
					if (products.get(brand) < competitorProducts
							.get(competitorProductBrand)) {
						brandPriceMap.put(brand,
								ApplicationConstants.UNDER_PRICED);
						flag = false;

					}
					if (products.get(brand) == competitorProducts
							.get(competitorProductBrand)) {

						brandPriceMap.put(brand, ApplicationConstants.OPTIMAL);
						flag = false;
					}
				}
			}
			if (flag) {
				if (brand != null) {
					if (!brand.isEmpty()) {
						brandPriceMap.put(brand,
								ApplicationConstants.NOT_COMPARABLE);
						flag = true;
					}
				}
			}
		}
		details = ProductUtils.populateBrandDetails(brandPriceMap);
		_logger.info("Exiting getBrandsWisePrice");
		return details;
	}

}
