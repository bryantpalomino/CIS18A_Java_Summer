//Class: Drink
//Purpose: The purpose of this class is it is an abstract class (Drink).
//			describing a generic type of drink that is available on this 
//			bar menu. It allows all subclasses to obtain the functionality
//			in this class + all of the other subclass information as well.
//			Each drink has a quantity associated with it, for how many 
//			are purchased.
public abstract class Drink implements DrinkAction 
{
	// The number of drinks bought of this type.
	protected int quantity;
	
	// Constructor
	// Used to specify the number of drinks purchased to 0.
	public Drink()
	{
		quantity = 0;
	}
	
	// Value Constructor
	// Used to set the number of drinks purchased to quant.
	public Drink(int quant)
	{
		setQuantity(quant);
	}
	
	// Set the Quantity of this drink to quant.
	public void setQuantity(int quant)
	{
		quantity = quant;
	}
	
	// Get the Quantity of this drink.
	public int getQuantity()
	{
		return quantity;
	}
	
	// Get the total cost for this specific drink (# of drinks x price).
	public double getTotal() 
	{
		return getPrice() * this.quantity;
	}
}
