package com.infiniteloop.shopping;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import fruit.AppleImpl;
import fruit.BannanaImpl;
import fruit.LemonImpl;
import fruit.OrangeImpl;
import fruit.PeachImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for Large Shopping Basket.
 */
public class ShoppingBasketLargeTest extends TestCase {
	
	private ProductPricing productPricing; 
	private CalculationHelper calculator;
	private ShoppingBasket shoppingBasket;
		
	/**
	 * Create the test case	 *
	 * @param testName name of the test case
	 */
	public ShoppingBasketLargeTest(String testName) {
		super(testName);
		
		productPricing = new ProductPricing(); 
		calculator = new CalculationHelper();
				
		shoppingBasket = new ShoppingBasket(new ValidationHelper());
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ShoppingBasketLargeTest.class);
	}
	
	public void testCalculateApple() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing .getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<5000;i++){
			shoppingBasket.addItem(apple);
			expected = expected.add(apple.getPrice());
		}		
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));

		assertTrue(calculated.equals(expected));
	}
	
	public void testCalculateAppleBannana() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<5000;i++){
			shoppingBasket.addItem(bannana);
			expected = expected.add(bannana.getPrice());
		}	
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));
		
		assertTrue(calculated.equals(expected));
	}

	public void testCalculateLemon() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		LemonImpl lemon = new LemonImpl(ProductPricingData.LEMON_TYPE, productPricing.getProduct(ProductPricingData.LEMON_TYPE, ProductPricingData.LEMON_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<5000;i++){
			shoppingBasket.addItem(lemon);
			expected = expected.add(lemon.getPrice());
		}		

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));
		
		assertTrue(calculated.equals(expected));		
	}

	public void testCalculateOrange() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		OrangeImpl orange = new OrangeImpl(ProductPricingData.ORANGES_TYPE, productPricing.getProduct(ProductPricingData.ORANGES_TYPE, ProductPricingData.SATSUMA_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<5000;i++){
			shoppingBasket.addItem(orange);
			expected = expected.add(orange.getPrice());
		}
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));
		
		assertTrue(calculated.equals(expected));	
	}
	
	public void testCalculatePeach() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		PeachImpl peach = new PeachImpl(ProductPricingData.PEACH_TYPE, productPricing.getProduct(ProductPricingData.PEACH_TYPE, ProductPricingData.PEACH_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<5000;i++){
			shoppingBasket.addItem(peach);
			expected = expected.add(peach.getPrice());
		}
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));
		
		assertTrue(calculated.equals(expected));		
	}

	/**
	 * This test ignores the upper limit on the number of 10000 in the shopping basket
	 * to test for large values and performance
	 * @throws ShoppingBasketException
	 */	
	public void testCalculateAllMultiples() throws ShoppingBasketException {
		
		ValidationHelper validator = mock(ValidationHelper.class);		
		shoppingBasket = new ShoppingBasket(validator);
		
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricing.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME));
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricing.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));
		OrangeImpl orange = new OrangeImpl(ProductPricingData.ORANGES_TYPE, productPricing.getProduct(ProductPricingData.ORANGES_TYPE, ProductPricingData.SATSUMA_NAME));
		LemonImpl lemon = new LemonImpl(ProductPricingData.LEMON_TYPE, productPricing.getProduct(ProductPricingData.LEMON_TYPE, ProductPricingData.LEMON_NAME));
		PeachImpl peach =  new PeachImpl(ProductPricingData.PEACH_TYPE, productPricing.getProduct(ProductPricingData.PEACH_TYPE, ProductPricingData.PEACH_NAME));

		BigDecimal expected = BigDecimal.ZERO;
		
		for(int i=0;i<10000;i++){
			shoppingBasket.addItem(apple);
			expected = expected.add(apple.getPrice());
		}
		
		for(int i=0;i<100000;i++){
			shoppingBasket.addItem(bannana);
			expected = expected.add(bannana.getPrice());
		}
		
		for(int i=0;i<1000000;i++){
			shoppingBasket.addItem(orange);
			expected = expected.add(orange.getPrice());
		}
		
		for(int i=0;i<10000000;i++){
			shoppingBasket.addItem(lemon);
			expected = expected.add(lemon.getPrice());
		}
		
		for(int i=0;i<100000000;i++){
			shoppingBasket.addItem(peach);
			expected = expected.add(peach.getPrice());
		}		
				
		when(validator.testBasketValue(expected)).thenReturn(expected);
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		expected = calculator.returnRoundedValue(calculator.vatAmountFromGross(expected));
		
		assertTrue(calculated.equals(expected));		
	}
}
