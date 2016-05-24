package com.infiniteloop.shopping;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import org.joda.money.Money;

class ShoppingBasket extends CalculationHelper {
		
	/**
	 * Class variables
	 */
	private ArrayList<BasketItem> items;
	private BigDecimal discount;
	private ValidationHelper validator;
				
	/**
	 * Constructor for shopping basket
	 */
	protected ShoppingBasket(ValidationHelper valid)	{		
		items = new ArrayList<BasketItem>();
		discount = BigDecimal.ZERO;
		validator = valid;
	}

	/**
	 * Returns all items
	 * @return
	 */
	protected ArrayList<BasketItem> getItems() {
		return items;
	}

	/**
	 * Adds and individual item
	 * @param value
	 */
	protected void addItem(BasketItem value) {
		items.add(value);
	}
	
	/**
	 * Removes and individual item
	 * @param value
	 */
	protected void removeItem(BasketItem value) {
		items.remove(value);
	}
	
	/**
	 * Clears the collection
	 */
	protected void removeAll() {
		items.clear();
	}
	
	/**
	 * Discount amount
	 * @param value
	 */
	protected void setDiscount(BigDecimal value) {
		discount = value;
	}
		
	/**
	 * Calculate Gross from running total
	 * @throws ShoppingBasketException 
	 */
	protected Money calculateGross() throws ShoppingBasketException {	
			
		// Zero the basket
			BigDecimal basketTotal = BigDecimal.ZERO;
			
			// Add the contents to a ruinningTotal
			for(BasketItem item : items){											
				basketTotal = basketTotal.add(item.getPrice());				
			}
			
			/*
			 *Calculate the discounted amount
			 */
			basketTotal = calculateDiscount(basketTotal);
			
			/*
			 * Validate total rules
			 */
			BigDecimal validated = validateTotal(basketTotal);
			
			// round and return
			return Money.parse(currency + " " + returnRoundedString(vatAmountFromGross(validated)));
		}

	/**
	 * Calculate total after discount applied
	 * @param basketTotal
	 * @return
	 */
	protected BigDecimal calculateDiscount(BigDecimal basketTotal) {
		// Subtract any discounts
		basketTotal = basketTotal.subtract(discount, MathContext.UNLIMITED);
		return basketTotal;
	}

	/**
	 * Validate the basket total with a couple of rules
	 * @param basketTotal
	 * @return
	 * @throws ShoppingBasketException
	 */
	protected BigDecimal validateTotal(BigDecimal basketTotal) throws ShoppingBasketException {

		// validate
		BigDecimal validated = validator.testBasketValue(basketTotal);
		
		/*
		 * If the total is zero something has gone wrong so empty the basket
		 */
		if(basketTotal.compareTo(BigDecimal.ZERO) != 0 && validated.equals(BigDecimal.ZERO))
		{			
			removeAll();
			throw new ShoppingBasketException("There has been an unexpected problem with your shopping basket. Please start again.");
		}
		
		return validated;
	}	
	
	/**
	 * Calculate Net from Gross
	 * @param grossAmount
	 * @return
	 */
	protected Money calculateNet(BigDecimal grossAmount) {	
					
			// round and return
			return Money.parse(currency + " " + returnRoundedString(vatAmountFromNet(grossAmount)));
		}	
}
