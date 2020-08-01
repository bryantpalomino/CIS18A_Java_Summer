// Class: MioAmoreMargarita
// Purpose: The purpose of this class is it is a subclass of the Drink class
//  		describing one type of drink that is available on this bar menu.
//			It inherits from Drink and has the same functionality as in 
//			the DrinkAction interface.
public class MioAmoreMargarita extends Drink
{
	// Get the name of this drink.
	@Override
	public String getName() 
	{
		return "Mio Amore Margarita";
	}

	// Get the description (ingredients) in this drink.
	@Override
	public String getDescription() 
	{
		return "Agave, Aperol, Avion Silver Tequila, Basil, Lime, Strawberry";
	}

	// Get the price of this drink.
	@Override
	public double getPrice() 
	{
		return 10.95;
	}
}
