//Class: DrinkAction
//Purpose: The purpose of this class is it describes the actions
//			that are common between all drinks.  All drinks can
//			operate by getting the name, description, price, 
//			and the total made (which is dependent on the
//			product's price).
public interface DrinkAction {
	public String getName();
	public String getDescription();
	public double getPrice();
	public double getTotal();
}
