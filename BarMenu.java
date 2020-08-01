import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Class: BarMenu
// Purpose: The purpose of this class is to display all of the bar
//			drinks available to the customer.  Uses JFrame/Java
//			Swing to show everything.
public class BarMenu extends JFrame implements ActionListener
{
	// The max number of possible drinks on the list.
	private final int MAX_DRINKS = 6;

	// The panels used for this display.
	// BarPanel shows the entire pane, mainPanel is for
	// the drinks and quantities, and buttonPanel is for
	// the buttons.
	private JPanel barPanel = new JPanel();
	private JPanel mainPanel;
	private JPanel buttonPanel;
	
	// The text inputs for each of the different drinks.
	private JTextField drinkTextInputs[];
	
	// The two buttons to clear all inputs
	// and submit the order.
	private JButton clearButton, submitButton;
	
	// Constructor
	// Creates the entire window for the JFrame.
	public BarMenu()
	{
		// Create the title for the JFrame.
		super("Bar Menu Application");
		
		// Display and create the panels to the screen.
		createMainPanel();
		createButtonPanel();
		add(barPanel);
		
		// Show the entire window to the screen.
		this.setSize(720, 310);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Create the main panel for the menu options.
	private void createMainPanel()
	{
		// Create the list of 6 drinks on the menu.
		drinkTextInputs = new JTextField[MAX_DRINKS];
		for(int i = 0; i < MAX_DRINKS; i++) {
			// Create each individual drink with initially 0 ordered.
			drinkTextInputs[i] = new JTextField();
			drinkTextInputs[i].setPreferredSize(new Dimension(60, 30));
			drinkTextInputs[i].setText("0");
		}
		
		// Create the main panel of information.
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(720, 220));
		
		// Add drinks to the main panel.
		for(int i = 0; i < MAX_DRINKS; i++) {
			// Create a drink.
			Drink drink = createDrink(i);
			
			// Setup the name and description for each drink.
			JLabel nameLabel = new JLabel(drink.getName());
			nameLabel.setPreferredSize(new Dimension(140, 30));
			JLabel descLabel = new JLabel(drink.getDescription());
			descLabel.setPreferredSize(new Dimension(480, 30));
			
			// Add the name, description, and text field for quantity to the panel.
			mainPanel.add(nameLabel);
			mainPanel.add(descLabel);
			mainPanel.add(drinkTextInputs[i]);
		}
		
		// Display all to this panel.
		barPanel.add(mainPanel);
	}

	// Create the button panel for the button options.
	private void createButtonPanel()
	{
		// Create the button panel.
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(720, 50));
		
		// Create the clear button.
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(140, 30));
		clearButton.addActionListener(this);
		buttonPanel.add(clearButton);

		// Create the submit button.
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(140, 30));
		submitButton.addActionListener(this);
		buttonPanel.add(submitButton);
		
		// Display all to this panel.
		barPanel.add(buttonPanel);
	}
	
	// Create a new drink object based on index.
	// Creates one of the 6 drinks, or null (if it is an invalid index). 
	private Drink createDrink(int index)
	{
		// Use switch to process all 6 possible choices.
		switch(index)
		{
			case 0: return new AfternoonDelight();
			case 1: return new AppleFlower();
			case 2: return new BlackBerryBliss();
			case 3: return new CocoRose();
			case 4: return new MioAmoreMargarita();
			case 5: return new UpAllNight();
		}
		
		// Invalid choice means null (no) drink object.
		return null;
	}

	// Listens for all actions performed by the user.
	// If clear or submit buttons are pressed, then do something.
	// Otherwise no action occurs.
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// If clear button was hit.
		if(e.getSource() == clearButton)
		{
			// Go through all drink quantities (text fields)
			// Set them to 0 to reset the quantities.
			for(int i = 0; i < MAX_DRINKS; i++) {
				drinkTextInputs[i].setText("0");
			}
		}
		// If submit button was hit.
		else if(e.getSource() == submitButton)
		{
			// Create a new order.
			Order drinkOrder = new Order();
			
			// Go through all inputed quantities.
			for(int i = 0; i < MAX_DRINKS; i++) {
				try
				{
					// Get the quantity by trying to parse it as an integer.
					int quantity = Integer.parseInt(drinkTextInputs[i].getText());
					
					// If an integer and it's more than 0, then add to the order list.
					if(quantity > 0) {
						// Add that drink with that quantity to the order list.
						Drink newDrink = createDrink(i);
						newDrink.setQuantity(quantity);
						drinkOrder.add(newDrink);
					}
					// If an integer but negative, then error.
					else if(quantity < 0) {
					    JOptionPane.showMessageDialog(null, "Error: Cannot Order Negative Quantity.");  
					    return;
					}
				}
				catch (Exception ex)
				{
					// If for some reason a non-integer is entered, then error.
				    JOptionPane.showMessageDialog(null, "Error: Invalid Quantity Given.");  
				    return;
				}
			}
			
			// If no drinks are ordered, then error.
			if(drinkOrder.getSize() == 0) {
				JOptionPane.showMessageDialog(null, "Error: No Drinks Ordered.");  
			}
			// Otherwise go to the display to purchase the drinks.
			else {
				new OrderDisplay(drinkOrder);
			}
		}
	}
}
