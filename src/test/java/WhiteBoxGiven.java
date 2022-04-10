package test.java;

import main.java.Alcohol;
import main.java.Cart;
import main.java.Dairy;
import main.java.FrozenFood;
import main.java.Meat;
import main.java.Produce;
import main.java.UnderAgeException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;

import static org.junit.Assert.*;

public class WhiteBoxGiven {

    Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart(45);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTaxAZ() throws Exception{
        assertEquals(4.0, cart.getTax(50, "AZ"), .01);
    }
    
    @Test
    public void getTaxCA() throws Exception{
        assertEquals(4.5, cart.getTax(50, "CA"), .01);
    }
    
    @Test
    public void getTaxCO() throws Exception {
        assertEquals(3.5, cart.getTax(50, "CO"), .01);
    }
    
    @Test
    public void getTaxNY() throws Exception {
        assertEquals(3.5, cart.getTax(50, "NY"), .01);
    }
    
    @Test
    public void getTaxDefault() throws Exception{
    	try {
        assertEquals(0, cart.getTax(50, "WA"), .01);
    	} 
    	catch(Exception noTaxForDefault)
		{
			
		}
    }
    
    @Test
    public void produceTwo() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	assertEquals(cart.calcCost(),4, 0.01);
    	
    }
    
    @Test
    public void produceThree() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	assertEquals(cart.calcCost(),5, 0.01);
    }
    
    @Test
    public void produceFour() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	assertEquals(cart.calcCost(),7, 0.01);
    }
    
    @Test
    public void produceSix() throws UnderAgeException{
    	Cart cart = new Cart(40);
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	cart.addItem(new Produce());
    	assertEquals(cart.calcCost(),10, 0.01);
    }
    
    @Test
    public void alcoholAge21() throws UnderAgeException {
    	Cart cart = new Cart(21);
    	cart.addItem(new Alcohol());
    	assertEquals(cart.calcCost(), 8.0, 0.01);
    }
    
    @Test
    public void alcoholUnderAge() {
    	Cart cart = new Cart(18);
    	cart.addItem(new Alcohol());
    	try {
			assertEquals(cart.calcCost(), 8.0, 0.01);
		} catch (UnderAgeException e) {
		}
    }
    
    @Test
    public void alcoholOverAge() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Alcohol());
    	assertEquals(cart.calcCost(), 8.0, 0.01);
    }
    @Test
    public void purchaseFrozenFood() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new FrozenFood());
    	assertEquals(cart.calcCost(), 5.0, 0.01);
    }
    @Test
    public void buyDairy() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Dairy());
    	assertEquals(cart.calcCost(), 3.0, 0.01);
    }
    
    @Test
    public void buyMeat() throws UnderAgeException {
    	Cart cart = new Cart(40);
    	cart.addItem(new Meat());
    	assertEquals(cart.calcCost(), 10.0, 0.01);
    }
    
    @Test 
    public void oneToOneAlcoholAndFrozenFood() throws UnderAgeException
    {
    
    	Cart cart = new Cart(40);
    	cart.addItem(new Alcohol());
    	cart.addItem(new FrozenFood());
    	assertEquals(cart.calcCost(), 10.0, 0.01);
    }
    
    @Test 
    public void twoToTwoAlcoholAndFrozenFood() throws UnderAgeException
    {
    	Cart cart = new Cart(40);
    	cart.addItem(new Alcohol());
    	cart.addItem(new Alcohol());
    	cart.addItem(new FrozenFood());
    	cart.addItem(new FrozenFood());
    	assertEquals(cart.calcCost(), 20.0, 0.01);
    }
    

    @Test 
    public void oneToTwoAlcoholAndFrozenFood() throws UnderAgeException
    {
    	Cart cart = new Cart(40);
    	cart.addItem(new Alcohol());
    	cart.addItem(new Alcohol());
    	cart.addItem(new FrozenFood());
    	assertEquals(cart.calcCost(), 18.0, 0.01);
    }
    
    @Test 
    public void TwoToOneAlcoholAndFrozenFood() throws UnderAgeException
    {
    	Cart cart = new Cart(40);
    	cart.addItem(new Alcohol());
    	cart.addItem(new FrozenFood());
    	cart.addItem(new FrozenFood());
    	assertEquals(cart.calcCost(), 15.0, 0.01);
    }
    
    @Test
    public void removeExistingItem()
    {
    	Cart cart = new Cart(40);
    	Cart original = cart;
    	Produce Apple = new Produce();
    	cart.addItem(Apple);
    	Assert.assertTrue(cart.removeItem(Apple));
    	
    }
    
    @Test
    public void removeNonexistingItem()
    {
    	Cart cart = new Cart(40);
    	Cart original = cart;
    	Produce Apple = new Produce();
    	Produce Pear = new Produce();
    	cart.addItem(new Produce());
    	Assert.assertFalse(cart.removeItem(Pear));
    }
    
}