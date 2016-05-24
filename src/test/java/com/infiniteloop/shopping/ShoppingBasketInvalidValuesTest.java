package com.infiniteloop.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import fruit.AppleImpl;
import fruit.BannanaImpl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.mockito.Mockito.*;

/**
 * Unit test for Shopping Basket.
 */
public class ShoppingBasketInvalidValuesTest  extends TestCase {
	
	ProductPricing productPricing;
	ShoppingBasket shoppingBasket;
	CalculationHelper calculator;
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ShoppingBasketInvalidValuesTest(String testName) {		
		super(testName);
		
		productPricing = mock(ProductPricing.class);
		shoppingBasket = new ShoppingBasket(new ValidationHelper());
		calculator = new CalculationHelper();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {			
		return new TestSuite(ShoppingBasketInvalidValuesTest.class);
	}

	/**
	 * 
	 * @throws ShoppingBasketException
	 */
	@Test 
	public void testCalculateEmpty() throws ShoppingBasketException {

		shoppingBasket.removeAll();
				
		// Test expected value
		assertTrue(shoppingBasket.calculateGross().getAmount().equals( Money.zero(CurrencyUnit.of("GBP")).getAmount()));
		// Test actual value
		assertTrue(shoppingBasket.calculateGross().getAmount().equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)));
	}
	
	/**
	 * This tests a scenario where an exception is thrown 
	 * for a negative total in the basket
	 * @throws ShoppingBasketException
	 */
	@Test(expected=ShoppingBasketException.class)
	public void testExceptionNegativeValues() {

		shoppingBasket.removeAll();
		
		when(productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME)).thenReturn(new BigDecimal("-1.20"));
		when(productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME)).thenReturn(new BigDecimal("-0.205"));
				
		AppleImpl apple1 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		AppleImpl apple2 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME));

		shoppingBasket.addItem(apple1);
		shoppingBasket.addItem(apple2);

		try {
			shoppingBasket.calculateGross().getAmount();
		} catch (ShoppingBasketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * This tests a scenario where an exception is thrown 
	 * for a total greater than the total value allowed in a basket
	 * @throws ShoppingBasketException
	 */
	@Test(expected=ShoppingBasketException.class)
	public void testExceptionMaxValue() {

		shoppingBasket.removeAll();
		
		when(productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME)).thenReturn(new BigDecimal("10000"));
		
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));

		shoppingBasket.addItem(bannana);
		shoppingBasket.addItem(bannana);
		
		try {
			shoppingBasket.calculateGross().getAmount();
		} catch (ShoppingBasketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
