// Class: AfternoonDelight
// Purpose: The purpose of this class is it is a subclass of the Drink class
//  		describing one type of drink that is available on this bar menu.
//			It inherits from Drink and has the same functionality as in 
//			the DrinkAction interface.
public class AfternoonDelight extends Drink
{
	// Get the name of this drink.
	@Override
	public String getName() 
	{
		return "Afternoon Delight";
	}

	// Get the description (ingredients) in this drink.
	@Override
	public String getDescription() 
	{
		return "Aloe Vera, Cucumber, Grey Goose Vodka, Lemon, Selzer";
	}

	// Get the price of this drink.
	@Override
	public double getPrice() 
	{
		return 10.95;
	}
}
