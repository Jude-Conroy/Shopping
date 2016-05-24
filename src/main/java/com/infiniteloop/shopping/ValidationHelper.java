package com.infiniteloop.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.CurrencyUnit;

public class ValidationHelper {

	/*
	 * This is a type of currency. Obviously if multiple currencies were to be
	 * used then this is not te right place to declare it.
	 */
	protected final CurrencyUnit currency = CurrencyUnit.of("GBP");

	/*
	 * VAT
	 */
	protected final BigDecimal vatRate = BigDecimal.valueOf(0.20);

	/*
	 *  Calculate the gross amount
	 */
	protected BigDecimal testBasketValue(BigDecimal grossAmount) {
		
		// First test if the basket is negative
		if(grossAmount.compareTo(BigDecimal.ZERO) < 0 ) {
			
			//TODO Something has gone wrong - Raise an event to notify ESB of error
			
			// We do not want to be paying customers to shop
			return BigDecimal.ZERO;
		}
		
		// Lets stick an arbitrary upper limit on the maximum amount allowed in the basket
		if(grossAmount.compareTo(new BigDecimal("10000")) > 0 ) {
			
			//TODO Something has gone wrong - Raise an event to notify ESB of error
			
			// lets reset the basket to zero
			return BigDecimal.ZERO;
		}
		
		
		// Otherwise return the value
		return grossAmount;
	}

	/*
	 *  Calculate the net amount
	 */
	protected BigDecimal vatAmountFromNet(BigDecimal netAmount) {
		return netAmount.multiply((vatRate.add(BigDecimal.ONE)));
	}

	/*
	 * This method takes a decimal and then rounds it and returns 
	 * the value as a string
	 */
	protected String returnRoundedString(BigDecimal numberToConvert) {
		return returnRoundedValue(numberToConvert).toPlainString();
	}

	/*
	 * This method takes a BigDecimal value and rounds it to scale 2 and
	 * returns the rounded with a HALF_UP value 0.005 = 0.01
	 */
	protected BigDecimal returnRoundedValue(BigDecimal numberToConvert) {
		return numberToConvert.setScale(2, RoundingMode.HALF_UP);
	}

}
