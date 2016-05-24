package com.infiniteloop.shopping;

import java.math.BigDecimal;

import fruit.AppleImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for ShoppingBasketEdgeTest.
 */
public class ShoppingBasketEdgeTest extends TestCase {
	
	private ShoppingBasket shoppingBasket;
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ShoppingBasketEdgeTest(String testName) {
		super(testName);
				
		shoppingBasket = new ShoppingBasket(new ValidationHelper());
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ShoppingBasketEdgeTest.class);
	}

	
	public void testCalculateMax() throws ShoppingBasketException {
				
		BigDecimal expected = new BigDecimal("9999");		
		
		shoppingBasket.addItem(new AppleImpl("", new BigDecimal("9999")));
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		
		assertFalse(calculated.equals(expected));		
	}	
	
	public void testCalculateMin() throws ShoppingBasketException {
		
		BigDecimal expected = new BigDecimal("0");		
		
		shoppingBasket.addItem(new AppleImpl("", new BigDecimal("0")));
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		
		assertFalse(calculated.equals(expected));		
	}	
}
