package com.infiniteloop.shopping;

import java.math.BigDecimal;
import java.util.HashMap;

public class ProductPricing extends ProductPricingData{

	private final HashMap<String, BigDecimal> apples 	= new HashMap<>();
	private final HashMap<String, BigDecimal> bannana = new HashMap<>();
	private final HashMap<String, BigDecimal> orange 	= new HashMap<>();
	private final HashMap<String, BigDecimal> lemon 	= new HashMap<>();
	private final HashMap<String, BigDecimal> peach 	= new HashMap<>();

	public ProductPricing() {
		apples.put(BREABURN_NAME, new BigDecimal("0.42"));
		apples.put(GOLDEN_DELICIOUS_NAME, new BigDecimal("0.39"));
		apples.put(COOKING_NAME, new BigDecimal("0.68"));
		bannana.put(BANNANA_NAME, new BigDecimal("0.122"));
		orange.put(SATSUMA_NAME, new BigDecimal("0.28"));
		lemon.put(LEMON_NAME, new BigDecimal("0.18"));
		peach.put(PEACH_NAME, new BigDecimal("0.173"));
	}

	public BigDecimal getProduct(String type, String name) {
		switch (type) {
		case APPLES_TYPE:
			return apples.get(name);

		case BANNANA_TYPE:
			return bannana.get(type);

		case ORANGES_TYPE:
			return orange.get(type);

		case LEMON_TYPE:
			return lemon.get(type);

		case PEACH_TYPE:
			return peach.get(type);

		default:
			return new BigDecimal("0");
		}
	}
}
