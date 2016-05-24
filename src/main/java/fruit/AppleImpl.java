package fruit;

import java.math.BigDecimal;

import com.infiniteloop.shopping.BasketItem;
import com.infiniteloop.shopping.ItemTypes;

public class AppleImpl implements BasketItem
{
	private final BigDecimal price;
	private final String name;
	
	public AppleImpl(String name, BigDecimal price)	{
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getType() {
		return ItemTypes.fruit;
	}	
}
