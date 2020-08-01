// Class: CocoRose
// Purpose: The purpose of this class is it is a subclass of the Drink class
//  		describing one type of drink that is available on this bar menu.
//			It inherits from Drink and has the same functionality as in 
//			the DrinkAction interface.
public class CocoRose extends Drink
{
	// Get the name of this drink.
	@Override
	public String getName() 
	{
		return "Coco Rose";
	}

	// Get the description (ingredients) in this drink.
	@Override
	public String getDescription() 
	{
		return "Brown Sugar, Chocolate Bitters, Coco Puff Infused Four Roses Bourbon";
	}

	// Get the price of this drink.
	@Override
	public double getPrice() 
	{
		return 14.95;
	}
}
