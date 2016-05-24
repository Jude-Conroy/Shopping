package com.infiniteloop.shopping;

import java.math.BigDecimal;
import org.junit.Test;

import fruit.AppleImpl;
import fruit.BannanaImpl;
import fruit.LemonImpl;
import fruit.OrangeImpl;
import fruit.PeachImpl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.mockito.Mockito.*;

/**
 * Unit test for Shopping Basket Valid Values Test.
 */
public class ShoppingBasketValidValuesTest  extends TestCase {
	
	private ProductPricing productPricing;
	private ShoppingBasket shoppingBasket;
	private CalculationHelper calculationHelper;
	private ProductPricing productPricingMock;
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ShoppingBasketValidValuesTest(String testName) {		
		super(testName);
		
		productPricing = new ProductPricing();
		shoppingBasket = new ShoppingBasket(new ValidationHelper());
		calculationHelper = new CalculationHelper();
		productPricingMock = mock(ProductPricing.class);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite() {			
		return new TestSuite(ShoppingBasketValidValuesTest.class);
	}
	
	@Test
	public void testCalculateApple() throws ShoppingBasketException {
		
		shoppingBasket.removeAll();
		
		ProductPricing productPricingMock = mock(ProductPricing.class);
		
		when(productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME)).thenReturn(new BigDecimal("0.22"));
			
		AppleImpl apple = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		
		shoppingBasket.addItem(apple);
		
		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(apple.getPrice()));
				
		assertTrue(calculated.equals(expected));
		// Should be "0.26"
		assertTrue(calculated.equals(new BigDecimal("0.26")));
	}

	@Test
	public void testCalculateApples() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		when(productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME)).thenReturn(new BigDecimal("1.205"));
		when(productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME)).thenReturn(new BigDecimal("0.205"));
				
		AppleImpl apple1 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.BREABURN_NAME));
		AppleImpl apple2 = new AppleImpl(ProductPricingData.APPLES_TYPE, productPricingMock.getProduct(ProductPricingData.APPLES_TYPE, ProductPricingData.GOLDEN_DELICIOUS_NAME));

		shoppingBasket.addItem(apple1);
		shoppingBasket.addItem(apple2);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(apple1.getPrice().add(apple2.getPrice())));

		assertTrue(calculated.equals(expected));
		// Should be 1.69
		assertTrue(calculated.equals(new BigDecimal("1.69")));
		
	}

	@Test
	public void testCalculateAppleBannanas() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		when(productPricingMock.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME)).thenReturn(new BigDecimal("0.49"));
		
		BannanaImpl bannana = new BannanaImpl(ProductPricingData.BANNANA_TYPE, productPricingMock.getProduct(ProductPricingData.BANNANA_TYPE, ProductPricingData.BANNANA_NAME));

		shoppingBasket.addItem(bannana);
		shoppingBasket.addItem(bannana);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();		
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(bannana.getPrice().add(bannana.getPrice())));
		
		// expected
		assertTrue(calculated.equals(expected));
		
		// Actual
		assertTrue(calculated.equals(new BigDecimal("1.18")));
	}

	@Test
	public void testCalculateLemons() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		LemonImpl lemon = new LemonImpl(ProductPricingData.LEMON_TYPE, productPricing.getProduct(ProductPricingData.LEMON_TYPE, ProductPricingData.LEMON_NAME));

		shoppingBasket.addItem(lemon);
		shoppingBasket.addItem(lemon);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();		
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(lemon.getPrice().add(lemon.getPrice())));
		
		assertTrue(calculated.equals(expected));
	}

	@Test
	public void testCalculateOranges() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		OrangeImpl orange = new OrangeImpl(ProductPricingData.ORANGES_TYPE, productPricing.getProduct(ProductPricingData.ORANGES_TYPE, ProductPricingData.SATSUMA_NAME));

		shoppingBasket.addItem(orange);
		shoppingBasket.addItem(orange);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();			
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(orange.getPrice().add(orange.getPrice())));
		
		assertTrue(calculated.equals(expected));
	}

	@Test
	public void testCalculatePeachs() throws ShoppingBasketException {

		shoppingBasket.removeAll();
		
		PeachImpl peach = new PeachImpl(ProductPricingData.PEACH_TYPE, productPricing.getProduct(ProductPricingData.PEACH_TYPE, ProductPricingData.PEACH_NAME));

		shoppingBasket.addItem(peach);
		shoppingBasket.addItem(peach);

		BigDecimal calculated = shoppingBasket.calculateGross().getAmount();
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(peach.getPrice().add(peach.getPrice())));
				
		assertTrue(calculated.equals(expected));
	}

	@Test
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
		BigDecimal expected = calculationHelper.returnRoundedValue(calculationHelper.vatAmountFromGross(apple.getPrice()
				.add(bannana.getPrice().add(orange.getPrice().add(lemon.getPrice().add(peach.getPrice()))))));
		
		assertTrue(calculated.equals(expected));
	}

}
