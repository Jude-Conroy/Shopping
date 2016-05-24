package com.infiniteloop.shopping;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import fruit.AppleImpl;
import fruit.BannanaImpl;
import fruit.LemonImpl;
import fruit.OrangeImpl;
import fruit.PeachImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for ShoppingBasket.
 */
public class ShoppingBasketTest extends TestCase {
	
	ProductPricing productPricing; 
	ShoppingBasket shoppingBasket;
	CalculationHelper calculationHelper;
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ShoppingBasketTest(String testName) {
		super(testName);
		
		productPricing = new ProductPricing();
		shoppingBasket = new ShoppingBasket(new ValidationHelper());
		calculationHelper = new CalculationHelper();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ShoppingBasketTest.class);
	}

	public void testCalculateEmpty() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		CurrencyUnit gbp = CurrencyUnit.of("GBP");
		Money expected = Money.zero(gbp);
		
		assertTrue(shoppingBasket.calculateGross().getAmount().equals(expected.getAmount()));
	}
	
	public void testCalculateRemoving() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		shoppingBasket.addItem(apple);
		shoppingBasket.removeItem(apple);
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(BigDecimal.ZERO));

		assertTrue(calculated.equals(expected));
	}
	
	public void testCalculateAdd3Removing1() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple1 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		AppleImpl apple2 = new AppleImpl(ProductPricingData.APPLES_TYPE, new BigDecimal("1.11"));
		AppleImpl apple3 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		
		shoppingBasket.addItem(apple1);
		shoppingBasket.addItem(apple2);
		shoppingBasket.addItem(apple3);
		
		shoppingBasket.removeItem(apple2);
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(apple1.getPrice().add(apple3.getPrice())));

		//expected
		assertTrue(calculated.equals(expected));
		//test actual
		assertTrue(calculated.equals(new BigDecimal("1.01")));
	}

	public void testCalculateApple() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		shoppingBasket.addItem(apple);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(apple.getPrice()));

		//expected
		assertTrue(calculated.equals(expected));
		//test actual
		assertTrue(calculated.equals(new BigDecimal("0.50")));
		
	}

	public void testCalculateApplex2() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple1 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		AppleImpl apple2 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME));

		shoppingBasket.addItem(apple1);
		shoppingBasket.addItem(apple2);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(apple1.getPrice().add(apple2.getPrice())));

		assertTrue(calculated.equals(expected));
	}

	public void testCalculateAppleBannana() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));

		shoppingBasket.addItem(bannana);
		shoppingBasket.addItem(bannana);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(bannana.getPrice().add(bannana.getPrice())));

		assertTrue(calculated.equals(expected));
	}

	public void testCalculateLemon() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		LemonImpl lemon = new LemonImpl(ProductPricingData.LEMON_TYPE, productPricing.getProduct(ProductPricingData.LEMON_TYPE, ProductPricingData.LEMON_NAME));

		shoppingBasket.addItem(lemon);
		shoppingBasket.addItem(lemon);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(lemon.getPrice().add(lemon.getPrice())));

		assertTrue(calculated.equals(expected));
	}

	public void testCalculateOrange() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		OrangeImpl orange = new OrangeImpl(ProductPricingData.ORANGES_TYPE, productPricing.getProduct(ProductPricingData.ORANGES_TYPE, ProductPricingData.SATSUMA_NAME));

		shoppingBasket.addItem(orange);
		shoppingBasket.addItem(orange);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(orange.getPrice().add(orange.getPrice())));

		assertTrue(calculated.equals(expected));
	}

	public void testCalculatePeach() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		PeachImpl peach = new PeachImpl(ProductPricingData.PEACH_TYPE, productPricing.getProduct(ProductPricingData.PEACH_TYPE, ProductPricingData.PEACH_NAME));

		shoppingBasket.addItem(peach);
		shoppingBasket.addItem(peach);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(peach.getPrice().add(peach.getPrice())));

		assertTrue(calculated.equals(expected));
	}
	
	public void testCalculateAppleDiscount() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		shoppingBasket.setDiscount(new BigDecimal("0.10"));
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		shoppingBasket.addItem(apple);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(apple.getPrice().subtract(shoppingBasket.getDiscount())));

		//expected
		assertTrue(calculated.equals(expected));
		//test actual
		assertTrue(calculated.equals(new BigDecimal("0.38")));
		
	}


	public void testCalculateAll() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME));
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));
		OrangeImpl orange = new OrangeImpl(ProductPricingData.ORANGES_TYPE, productPricing.getProduct(ProductPricingData.ORANGES_TYPE, ProductPricingData.SATSUMA_NAME));
		LemonImpl lemon = new LemonImpl(ProductPricingData.LEMON_TYPE, productPricing.getProduct(ProductPricingData.LEMON_TYPE, ProductPricingData.LEMON_NAME));
		PeachImpl peach =  new PeachImpl(ProductPricingData.PEACH_TYPE, productPricing.getProduct(ProductPricingData.PEACH_TYPE, ProductPricingData.PEACH_NAME));

		shoppingBasket.addItem(apple);
		shoppingBasket.addItem(bannana);
		shoppingBasket.addItem(orange);
		shoppingBasket.addItem(lemon);
		shoppingBasket.addItem(peach);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatGross(apple.getPrice()
				.add(bannana.getPrice().add(orange.getPrice().add(lemon.getPrice().add(peach.getPrice()))))));

		assertTrue(calculated.equals(expected));
	}

}
