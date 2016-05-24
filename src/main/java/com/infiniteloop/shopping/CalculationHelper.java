package com.infiniteloop.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.CurrencyUnit;

public class CalculationHelper {

	/*
	 * This is a type of currency. Obviously if multiple currencies were to be
	 * used then this is not the right place to declare it.
	 */
	protected final CurrencyUnit currency = CurrencyUnit.of("GBP");

	/*
	 * VAT
	 */
	protected final BigDecimal vatRate = BigDecimal.valueOf(1.20);

	/*
	 *  Calculate the gross amount
	 */
	protected BigDecimal vatAmountFromNet(BigDecimal grossAmount) {
		return grossAmount.divide(vatRate);
	}

	/*
	 *  Calculate the net amount
	 */
	protected BigDecimal vatAmountFromGross(BigDecimal netAmount) {
		return netAmount.multiply(vatRate);
	}

	/*
	 * This method takes a decimal and then rounds it and returns the value as a
	 * string
	 */
	protected String returnRoundedString(BigDecimal numberToConvert) {
		return returnRoundedValue(numberToConvert).toPlainString();
	}

	/*
	 * This method takes a BigDecimal value and rounds it as per below and
	 * returns the rounded BigDecimal Value
	 */
	protected BigDecimal returnRoundedValue(BigDecimal numberToConvert) {
		return numberToConvert.setScale(2, RoundingMode.HALF_UP);
	}

}
