import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// Class: OrderDisplay
// Purpose: The purpose of this class is to display all of the information
//			for the order and get the credit card information from the user
//			so that they can purchase the order.
public class OrderDisplay extends JFrame implements ActionListener 
{
	// The order passed from the bar menu.
	private Order order;
	
	// All panels used for this JFrame.
	// Main panel for order information, ccPanel for credit card information,
	// and buttonPanel for user input.
	private JPanel orderDetailPanel = new JPanel();
	private JPanel mainPanel;
	private JPanel ccPanel;
	private JPanel buttonPanel;
	
	// All drink inputs (quantity) and costs.
	private JTextField drinkTextInputs[];
	private JTextField drinkTotalCosts[];
	
	// Fields for credit card information.
	private JTextField nameInput, ccNumInput, addrInput, cvvInput, expDateInput;
	
	// All buttons for this operation.
	private JButton clearButton, submitButton;
	
	// Constructor
	// Uses the given order and displays all information to the screen.
	public OrderDisplay(Order ord)
	{
		// Set title of this JFrame.
		super("Order Details");
		
		// Store order information into this object.
		order = ord;
		
		// Setup the JFrame with information.
		createMainPanel();
		createCreditCardPanel();
		createButtonPanel();
		add(orderDetailPanel);
		
		// Show the entire window to the screen.
		// Depending on the number of drinks, the height of the window is larger for more drinks.
		this.setSize(360, (order.getSize() + 2) * 35 + 260);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Create the main panel of information
	// this is for the drinks ordered.
	private void createMainPanel()
	{
		// Get the drinks inputs and costs (quantities and sub-total cost).
		drinkTextInputs = new JTextField[order.getSize() + 1];
		drinkTotalCosts = new JTextField[order.getSize() + 1];
		for(int i = 0; i < order.getSize(); i++) {
			// Show the quantities of the drinks ordered.
			drinkTextInputs[i] = new JTextField();
			drinkTextInputs[i].setPreferredSize(new Dimension(60, 30));
			drinkTextInputs[i].setText(order.getDrink(i).getQuantity() + "");
			drinkTextInputs[i].setEditable(false);
			
			// Show the total price of the drinks ordered.
			drinkTotalCosts[i] = new JTextField();
			drinkTotalCosts[i].setPreferredSize(new Dimension(60, 30));
			drinkTotalCosts[i].setText("$" + String.format("%,.2f", order.getDrink(i).getTotal()));
			drinkTotalCosts[i].setEditable(false);
		}
		
		// Show the tip amount.
		drinkTextInputs[order.getSize()] = new JTextField();
		drinkTextInputs[order.getSize()].setPreferredSize(new Dimension(60, 30));
		drinkTextInputs[order.getSize()].setText("0.00");
		
		// Show the total price, (sub-totals + tip).
		drinkTotalCosts[order.getSize()] = new JTextField();
		drinkTotalCosts[order.getSize()].setPreferredSize(new Dimension(60, 30));
		drinkTotalCosts[order.getSize()].setText("$" + String.format("%,.2f", order.getTotal(0.0)));
		drinkTotalCosts[order.getSize()].setEditable(false);
		
		// All the user to change information on the tip amount.
		// Update the total cost as it changes.
		drinkTextInputs[order.getSize()].getDocument().addDocumentListener(new DocumentListener() {
			
			// Update when the user changes something.
		    public void update(DocumentEvent e) {
		    	
		    	// Try to parse the amount given in the tip.
		    	double amt = 0;
		    	try
		    	{
			    	// If it is non-negative then set it to amt, otherwise it is set to 0.0.
		    		amt = Double.parseDouble(drinkTextInputs[order.getSize()].getText());
		    		if(amt < 0.0) {
		    			amt = 0.0;
		    		}
		    	}
		    	catch(Exception ex) { }
		    	
		    	// Setup the new total cost.
				drinkTotalCosts[order.getSize()].setText("$" + String.format("%,.2f", order.getTotal(amt)));
		    }

		    // None of these have to change, just listen to an update.
			public void changedUpdate(DocumentEvent e) { update(e); }
			public void insertUpdate(DocumentEvent e) { update(e); }
			public void removeUpdate(DocumentEvent e) { update(e); }
		});
		
		// Setup the main panel.
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(275, (order.getSize() + 1) * 35 + 5));
		
		// Go through all drinks in the order.
		for(int i = 0; i < order.getSize(); i++) {
			// Place the name, quantity, and total cost together on the same line.
			JLabel nameLabel = new JLabel(order.getDrink(i).getName());
			nameLabel.setPreferredSize(new Dimension(140, 30));
			mainPanel.add(nameLabel);
			mainPanel.add(drinkTextInputs[i]);
			mainPanel.add(drinkTotalCosts[i]);
		}

		// Show the tip amount, the inputed amount and the total cost on the same line.
		JLabel tipLabel = new JLabel("Tip Amount: ");
		tipLabel.setPreferredSize(new Dimension(140, 30));
		mainPanel.add(tipLabel);
		mainPanel.add(drinkTextInputs[order.getSize()]);
		mainPanel.add(drinkTotalCosts[order.getSize()]);
		
		// Show all details.
		orderDetailPanel.add(mainPanel);
	}

	// Create all information from the credit card
	// that needs to be added by the user.
	private void createCreditCardPanel()
	{
		// A credit card panel.
		ccPanel = new JPanel();
		ccPanel.setPreferredSize(new Dimension(350, 200));

		// Name and information.
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setPreferredSize(new Dimension(110, 30));
		ccPanel.add(nameLabel);

		nameInput = new JTextField("");
		nameInput.setPreferredSize(new Dimension(220, 30));
		ccPanel.add(nameInput);
		
		// Credit Card number and information.
		JLabel ccNoLabel = new JLabel("CC Number: ");
		ccNoLabel.setPreferredSize(new Dimension(110, 30));
		ccPanel.add(ccNoLabel);

		ccNumInput = new JTextField("");
		ccNumInput.setPreferredSize(new Dimension(220, 30));
		ccPanel.add(ccNumInput);
		
		// Billing Address and information.
		JLabel addrLabel = new JLabel("Billing Address: ");
		addrLabel.setPreferredSize(new Dimension(110, 30));
		ccPanel.add(addrLabel);

		addrInput = new JTextField("");
		addrInput.setPreferredSize(new Dimension(220, 30));
		ccPanel.add(addrInput);
		
		// CVV Number and information.
		JLabel cvvNoLabel = new JLabel("CVV Number: ");
		cvvNoLabel.setPreferredSize(new Dimension(110, 30));
		ccPanel.add(cvvNoLabel);

		cvvInput = new JTextField("");
		cvvInput.setPreferredSize(new Dimension(220, 30));
		ccPanel.add(cvvInput);
		
		// Expiration Date and information.
		JLabel expDateLabel = new JLabel("Exp Date: ");
		expDateLabel.setPreferredSize(new Dimension(110, 30));
		ccPanel.add(expDateLabel);
		
		expDateInput = new JTextField("");
		expDateInput.setPreferredSize(new Dimension(220, 30));
		ccPanel.add(expDateInput);
		
		// Show the entire list of details of the credit card.
		orderDetailPanel.add(ccPanel);
	}
	
	// Create all buttons for this JFrame.
	// Allow clearing all fields and submit button.
	private void createButtonPanel()
	{
		// Panel for buttons.
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(350, 50));
		
		// Clear button to return all text fields to 0.
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(140, 30));
		clearButton.addActionListener(this);
		buttonPanel.add(clearButton);

		// Submit the order for checking and ordering.
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(140, 30));
		submitButton.addActionListener(this);
		buttonPanel.add(submitButton);
		
		// Show entire list of buttons.
		orderDetailPanel.add(buttonPanel);
	}

	// Listens for all actions performed by the user.
	// If clear or submit buttons are pressed, then do something.
	// Otherwise no action occurs.
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Clear button actions.
		// Set the tip amount to 0.0.
		if(e.getSource() == clearButton)
		{
			drinkTextInputs[order.getSize()].setText("0.00");
		}
		// Submit button action.
		// Check information and see if it is valid.
		// If so then the order is placed, otherwise an error appears.
		else if(e.getSource() == submitButton)
		{
			try
			{
				// Try to produce the tip.
				double tip = Double.parseDouble(drinkTextInputs[order.getSize()].getText());
				
				// If the tip is negative then error.
				if(tip < 0) {
					JOptionPane.showMessageDialog(null, "Error: Cannot Leave A Negative Tip.");  
					return;
				}
			}
			catch (Exception ex)
			{
				// If the tip is non-double then error.
			    JOptionPane.showMessageDialog(null, "Error: Invalid Tip Given.");  
			    return;
			}
			
			// If any credit card information is missing, then error.
			if(nameInput.getText().equals("") || ccNumInput.getText().equals("") || addrInput.getText().equals("") || cvvInput.getText().equals("") || expDateInput.getText().equals("")) {
			    JOptionPane.showMessageDialog(null, "Error: Missing Valid CC Information.");  
			    return;
			}
			// Otherwise successfully place the order.
			else {
				JOptionPane.showMessageDialog(null, "Success: Order Placed");
				dispose();
			}
		}
	}
}
