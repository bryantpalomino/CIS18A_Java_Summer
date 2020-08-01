import java.util.ArrayList;

//Class: Order
//Purpose: The purpose of this class is to hold the entire order
//			of drinks for this one operation.
public class Order implements OrderAction 
{
	// A list of drinks in the order.
	// Uses a packaged ArrayList to use its class
	// functionality.
	private ArrayList<Drink> order;
	
	// Constructor
	// Creates a new empty list of drinks in the order.
	public Order() {
		order = new ArrayList<Drink>();
	}
	
	// Gets the size of the order (number of types of drinks).
	public int getSize() {
		return order.size();
	}
	
	// Add a drink to the order list.
	@Override
	public void add(Drink drink) {
		order.add(drink);
	}

	// Get a drink in the i-th location in the order list.
	@Override
	public Drink getDrink(int i) {
		return order.get(i);
	}

	// Get the subtotal for the entire total.
	// The subtotal is the total price of all drinks (price x quantity)
	// for the order before the tip is included.
	@Override
	public double getSubTotal() {
		int i = 0;
		double total = 0.0;
		
		// Go through all orders in the list.
		// Get the total (price x quantity) and add to total.
		while(i < order.size()) {
			total += order.get(i).getTotal();
			i++;
		}
		return total;
	}

	// Get the total for the entire order with tip included.
	@Override
	public double getTotal(double tip) {
		return getSubTotal() + tip;
	}
	
}
