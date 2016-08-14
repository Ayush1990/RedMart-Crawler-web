package redmart.crm.product.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;

import redmart.crm.product.dao.CompetitorProductDAO;
import redmart.crm.product.dao.ProductDAO;
import redmart.crm.product.entity.BrandDetails;
import redmart.crm.product.entity.CompetitorProduct;
import redmart.crm.product.entity.Product;

/**
 * @author dipankar.saha
 *
 */
public class ProductUtils {

	static Logger _logger = Logger.getLogger(ProductUtils.class.getName());

	public static long getProductCount(Datastore datastore) {
		_logger.info("Calling getProductCount");
		ProductDAO dao = null;
		if (datastore != null) {
			dao = new ProductDAO(datastore);
		} else {
			dao = new ProductDAO();
		}
		long totalCount = dao.getProductCount();
		_logger.info("Exiting getProductCount");
		return totalCount;
	}

	public static long getCompetitorProductCount(Datastore datastore) {
		_logger.info("Calling getCompetitorProductCount");
		CompetitorProductDAO dao = null;
		if (datastore != null) {
			dao = new CompetitorProductDAO(datastore);
		} else {
			dao = new CompetitorProductDAO();
		}
		long totalCount = dao.getProductCount();
		_logger.info("Exiting getCompetitorProductCount");
		return totalCount;
	}

	public static List<redmart.crm.product.mongo.entity.Product> convertProduct(
			List<Product> products) {
		_logger.info("Calling convertProduct");
		List<redmart.crm.product.mongo.entity.Product> productList = new ArrayList<>();

		redmart.crm.product.mongo.entity.Product mongoProduct = null;
		for (Product product : products) {
			mongoProduct = new redmart.crm.product.mongo.entity.Product();
			mongoProduct.setId(product.getId());
			mongoProduct.setTitle(product.getTitle());
			mongoProduct.setBrand(product.getBrand());
			mongoProduct.setCategoryName1(product.getCategoryName1());
			mongoProduct.setCategoryName2(product.getCategoryName2());
			mongoProduct.setPrice(product.getPrice());
			mongoProduct.setPromotePrice(product.getPromotePrice());
			productList.add(mongoProduct);
		}
		_logger.info("Exiting convertProduct");
		return productList;
	}

	public static List<redmart.crm.product.mongo.entity.CompetitorProduct> convertCompetitorProduct(
			List<CompetitorProduct> products) {
		_logger.info("Calling convertCompetitorProduct");
		List<redmart.crm.product.mongo.entity.CompetitorProduct> productList = new ArrayList<>();

		redmart.crm.product.mongo.entity.CompetitorProduct mongoProduct = null;

		for (CompetitorProduct product : products) {
			mongoProduct = new redmart.crm.product.mongo.entity.CompetitorProduct();
			mongoProduct.setTitle(product.getTitle());
			mongoProduct.setMatchedProductId(product.getMatchedProductId());
			mongoProduct.setBrand(product.getBrand());
			mongoProduct.setInStock(product.getInStock());
			mongoProduct.setPrice(product.getPrice());
			mongoProduct.setOnPromo(product.getOnPromo());
			mongoProduct.setPromoPrice(product.getPromoPrice());
			mongoProduct.setCategoryName1(product.getCategoryName1());
			mongoProduct.setCategoryName2(product.getCategoryName2());
			productList.add(mongoProduct);
		}
		_logger.info("Exiting convertCompetitorProduct");
		return productList;

	}

	public static Map<String, Double> getProductBrands(Datastore datastore) {
		_logger.info("Calling getProductBrands");
		Set<String> brandList = new HashSet<>();
		Map<String, Double> brandMap = new HashMap<>();
		Map<String, Integer> brandCountMap = new HashMap<>();
		Map<String, Double> brandPriceAvgMap = new HashMap<>();
		ProductDAO productDao = null;
		if(datastore != null){
			productDao = new ProductDAO(datastore);	
		}else{
			productDao = new ProductDAO();
		}
		
		List<redmart.crm.product.mongo.entity.Product> productList = productDao
				.getAllProducts();
		for (redmart.crm.product.mongo.entity.Product product : productList) {
			brandList.add(product.getBrand().toLowerCase().trim());
			if (brandMap.get(product.getBrand().toLowerCase().trim()) != null) {
				brandMap.put(product.getBrand().toLowerCase().trim(),
						brandMap.get(product.getBrand().toLowerCase().trim())
								+ product.getPrice());
			} else {
				brandMap.put(product.getBrand().toLowerCase().trim(),
						product.getPrice());
			}
			if (brandCountMap.get(product.getBrand().toLowerCase().trim()) != null) {
				brandCountMap.put(
						product.getBrand().toLowerCase().trim(),
						brandCountMap.get(product.getBrand().toLowerCase()
								.trim()) + 1);
			} else {
				brandCountMap.put(product.getBrand().toLowerCase().trim(), 1);
			}

		}
		double price;
		for (String brand : brandList) {
			price = brandMap.get(brand) / brandCountMap.get(brand);
			brandPriceAvgMap.put(brand, price);
		}
		_logger.info("Exiting getProductBrands");
		return brandPriceAvgMap;

	}

	public static Map<String, Double> getCompetitorProductBrands(Datastore datastore) {
		_logger.info("Calling getCompetitorProductBrands");
		Set<String> brandList = new HashSet<>();
		Map<String, Double> brandMap = new HashMap<>();
		Map<String, Integer> brandCountMap = new HashMap<>();
		Map<String, Double> brandPriceAvgMap = new HashMap<>();
		CompetitorProductDAO dao = null;
		if(datastore != null){
			dao = new CompetitorProductDAO(datastore);
		}else{
			dao = new CompetitorProductDAO();
		}
		List<redmart.crm.product.mongo.entity.CompetitorProduct> competitorProductList = dao
				.getTotalMatchedProducts();
		for (redmart.crm.product.mongo.entity.CompetitorProduct product : competitorProductList) {
			brandList.add(product.getBrand().toLowerCase().trim());
			if (brandMap.get(product.getBrand().toLowerCase().trim()) != null) {
				brandMap.put(product.getBrand().toLowerCase().trim(),
						brandMap.get(product.getBrand().toLowerCase().trim())
								+ product.getPrice());
			} else {
				brandMap.put(product.getBrand().toLowerCase().trim(),
						product.getPrice());
			}
			if (brandCountMap.get(product.getBrand().toLowerCase().trim()) != null) {
				brandCountMap.put(
						product.getBrand().toLowerCase().trim(),
						brandCountMap.get(product.getBrand().toLowerCase()
								.trim()) + 1);
			} else {
				brandCountMap.put(product.getBrand().toLowerCase().trim(), 1);
			}
		}
		double price;
		for (String brand : brandList) {
			price = brandMap.get(brand) / brandCountMap.get(brand);
			brandPriceAvgMap.put(brand, price);
		}
		_logger.info("Exiting getCompetitorProductBrands");
		return brandPriceAvgMap;
	}

	public static List<BrandDetails> populateBrandDetails(
			Map<String, String> brandPriceMap) {
		_logger.info("Calling populateBrandDetails");
		List<BrandDetails> brandDetails = new ArrayList<>();
		BrandDetails details = null;
		for (String brand : brandPriceMap.keySet()) {
			if (brand != null || brand != "" || brand != " ") {
				details = new BrandDetails();
				details.setBrands(brand);
				details.setPricingPosition(brandPriceMap.get(brand));
				brandDetails.add(details);
			}
		}
		_logger.info("Exiting populateBrandDetails");
		return brandDetails;

	}

}
