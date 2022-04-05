package main.java;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

public class Cart {

	protected int userAge;
	public List<Product> cart;
	public int cartStorage;
	/**
	 * Calculates the final cost after all savings and tax has been applied. Also checks
	 * that the user is of age to purchase alcohol if it is in their cart at checkout. Sales tax is always AZ tax.
	 *
	 * Calculation is based off of the following prices and deals:
	 * Dairy -> $3
	 * Meat -> $10
	 * Produce -> $2 or 3 for $5
	 * Alcohol -> $8
	 * Frozen Food -> $5
	 * Alcohol + Frozen Food -> $10
	 *
	 * If there is an alcohol product in the cart and the user is under 21, then an
	 * UnderAgeException should be thrown.
	 *
	 * @return double totalCost
	 * @throws UnderAgeException
	 */
	public double calcCost() throws UnderAgeException {
		double totalCost = 0;
		// getting the total subTotal cost from the cart
		for(int i=0; i<cart.size(); i++)
		{
			totalCost = totalCost + cart.get(i).getCost();
		}
		//subtracting the amount saved
		totalCost = totalCost - amountSaved();
		return totalCost;	
		}

	// calculates how much was saved in the current shopping cart based on the deals, returns the saved amount
	// throws exception if alcohol is bought from underage person
	// TODO: Create node graph for this method in assign 4: create white box tests and fix the method, reach at least 98% coverage
	public int amountSaved() throws UnderAgeException {
		int subTotal = 0;
		int costAfterSavings = 0;

		double produce_counter = 0;
		int alcoholCounter = 0;
		int frozenFoodCounter = 0;
		int dairyCounter = 0;

		for(int i = 0; i < cart.size(); i++) {
			subTotal += cart.get(i).getCost();
			costAfterSavings =costAfterSavings+cart.get(i).getCost();

			if (cart.get(i).getClass().toString().equals(Produce.class.toString())) {
				produce_counter++;
// everytime 3 are purchased it takes off a dollar, not for every after 3 produce
				if (produce_counter % 3 == 0) {
					costAfterSavings -= 1;
					produce_counter = 0;
				}
			}
			else if (cart.get(i).getClass().toString().equals(Alcohol.class.toString())) {
				alcoholCounter++;
				if (userAge < 21) {
					throw new UnderAgeException("The User is not of age to purchase alcohol!");
				}
			}
			else if (cart.get(i).getClass().toString().equals(FrozenFood.class.toString())) {
				frozenFoodCounter++;
			}
			else if (cart.get(i).getClass().toString().equals(Dairy.class.toString()))
			{
				dairyCounter++;
			}
			if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
				costAfterSavings = costAfterSavings - 3;
				alcoholCounter--;
				frozenFoodCounter--;
			}
		}

		return subTotal - costAfterSavings;
	}

	// Gets the tax based on state and the total
	@SuppressWarnings("deprecation")
	public double getTax(double totalBT, String twoLetterUSStateAbbreviation) throws Exception {
		double newTotal = 0;
		switch (twoLetterUSStateAbbreviation) {
		case "AZ":
			newTotal = totalBT * .08;
			break;
		case "CA":
			newTotal = totalBT * .09;
			break;
		case "NY":
			newTotal = totalBT * .1;
		case "CO":
			newTotal = totalBT * .07;
			break;
		default:
			throw new Exception("Incorrect state " + twoLetterUSStateAbbreviation );
		}
		return newTotal;
	}

	public void addItem(Product productToAdd) {
		cart.add(productToAdd);
	}

	public boolean RemoveItem(Product productToRemove)
	{
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).equals(productToRemove)) {
				cart.remove(i);
				return true;
			}
		}
		return false;
	}

	public Cart(int age) {
		userAge = age;
		cart = new ArrayList<Product>();
	}

}
