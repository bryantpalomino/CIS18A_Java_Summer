//Class: OrderAction
//Purpose: The purpose of this class is it describes the actions
//			that are common between all orders.  All orders can
//			operate by adding, getting a drink, finding the subtotal
//			and finding the total price of the order.
public interface OrderAction 
{
	public void add(Drink drink);
	public Drink getDrink(int i);
	public double getSubTotal();
	public double getTotal(double tip);
}
