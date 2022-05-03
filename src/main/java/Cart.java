package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
    //SER316 TASK 2 SPOTBUGS FIX

    /**
     * Calculates the final cost after all savings and tax has been applied. Also
     * checks that the user is of age to purchase alcohol if it is in their cart at
     * checkout. Sales tax is always AZ tax. 
     * Calculation is based off of the following prices and deals: 
     * Dairy -> $3 Meat
     * -> $10 Produce -> $2 or 3 for $5 Alcohol -> 
     * $8 Frozen Food -> $5 Alcohol +
     * Frozen Food -> $10, 
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost total cost returned.
     * @throws UnderAgeException expection for under 21.
     */
    public double calcCost() throws UnderAgeException {
        double totalCost = 0;
        // getting the total subTotal cost from the cart
        for (int i = 0; i < cart.size(); i++) {
            totalCost = totalCost + cart.get(i).getCost();
        }
        // subtracting the amount saved
        totalCost = totalCost - amountSaved();
        return totalCost;
    }

    /** Method to calculate amount saved.
     * 
     * @return returns amount saved.
     * @throws UnderAgeException exception for underage.
     */
    public int amountSaved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;
        int produceCounter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        String itemName = "";
        //SER316 TASK 2 SPOTBUGS FIX
        for (int i = 0; i < cart.size(); i++) {
            itemName = cart.get(i).getClass().toString();
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();

            if (itemName.equals(Produce.class.toString())) {
                produceCounter++;
                // everytime 3 are purchased it takes off a dollar
                costAfterSavings += produceSavings(produceCounter);
            } else if (itemName.equals(Alcohol.class.toString())) {
                checkAge(userAge);
                alcoholCounter++;
            } else if (itemName.equals(FrozenFood.class.toString())) {
                frozenFoodCounter++;
                costAfterSavings += alchoholAndFrozenFoodDeal(alcoholCounter, frozenFoodCounter);
            }
        }
        return subTotal - costAfterSavings;
    }

    /** Method to calculate if produce savings apply.
     * 
     * @param produceCounter counter for number of produce items.
     * @return return savings.
     */
    public int produceSavings(int produceCounter) {
        if (produceCounter % 3 == 0) {
            return -1;
        }
        return 0;

    }

    /** Method to check users age.
     * 
     * @param age int of age of user.
     * @throws UnderAgeException  excpetion if under 21.
     */
    public void checkAge(int age) throws UnderAgeException {
        if (age < 21) {
            throw new UnderAgeException("The User is not of "
                    + "age to purchase alcohol!");
        }

    }

    /**
     * 
     * @param alcoholCounter
     * @param frozenFoodCounter
     * @return
     */
    public int alchoholAndFrozenFoodDeal(int alcoholCounter, int frozenFoodCounter) {
        if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
            alcoholCounter--;
            frozenFoodCounter--;
            return -3;
        }
        return 0;
    }

    /** Method to get the tax.
     * 
     * @param totalB the total amount subtotal.
     * @param twoLetterStateAbbreviation the state abbreviation.
     * @return returns the new total.
     * @throws Exception  for no existing state.
     */
    public double getTax(double totalB, String twoLetterStateAbbreviation) throws Exception {
        double newTotal = 0;
        if (twoLetterStateAbbreviation.equals("AZ")) {
            newTotal = totalB * .08;
        } else if (twoLetterStateAbbreviation.equals("CA")) {

            newTotal = totalB * .09;
        } else if (twoLetterStateAbbreviation.equals("NY")) {

            newTotal = totalB * .1;
        } else if (twoLetterStateAbbreviation.equals("CO")) {

            newTotal = totalB * .07;
        } else {
            throw new Exception("Incorrect state " + twoLetterStateAbbreviation);
        }
        return newTotal;
    }

    /** Method to add item.
     * 
     * @param productToAdd input of product added to cart.
     */
    public void addItem(Product productToAdd) {
        cart.add(productToAdd);
    }

    /** Method to remove item.
     * 
     * @param productToRemove input of product to remove.
     * @return returns a boolean if it was removed.
     */
    public boolean removeItem(Product productToRemove) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).equals(productToRemove)) {
                cart.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Constructor for class.
     * 
     * @param age input of shoppers age.
     */
    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}