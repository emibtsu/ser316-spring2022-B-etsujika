package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junit.framework.AssertionFailedError;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class costTest {

	private Class<Cart> classUnderTest;

	@SuppressWarnings("unchecked")
	public costTest(Object classUnderTest) {
		this.classUnderTest = (Class<Cart>) classUnderTest;
	}

	// Define all classes to be tested
	@Parameterized.Parameters
	public static Collection<Object[]> cartClassUnderTest() {
		Object[][] classes = {
				{Cart.class}
		};
		return Arrays.asList(classes);
	}

	private Cart createCart(int age) throws Exception {
		Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
		return constructor.newInstance(age);
	}

	// A sample Cart

	Cart cart1;
	Cart cart2;
	Cart cart3;
	Cart cart4;
	Cart cart5;
	Cart cart6;
	Cart cart7;
	double cart1Expected;
	double cart2Expected;
	double cart3Expected;
	double cart4Expected;
	double cart5Expected;
	double cart6Expected;

	@org.junit.Before
	public void setUp() throws Exception {

		// all carts should be set up like this

		// cart created with an age 40 shopper
		cart1 = createCart(40);
		for (int i = 0; i < 2; i++) {
			cart1.addItem(new Alcohol());
		}
		for(int i = 0; i < 3; i++) {
			cart1.addItem(new Dairy());
		}
		for(int i = 0; i < 4; i++) {
			cart1.addItem(new Meat());
		}

		cart1Expected = 69;

		//

		cart2 = createCart(40);

		for(int i = 0; i < 4; i++) {
			cart2.addItem(new Produce());
		}

		cart2Expected = 7;

		// cart created with an age 20 shopper
		cart3 = createCart(20);
		cart3Expected = 0;

		//cart created with alcohol and frozen food
		cart4 = createCart(40);
		for (int i = 0; i <=1; i++) {
			cart4.addItem(new Alcohol());
		}
		for(int i = 0; i <= 1; i++) {
			cart1.addItem(new FrozenFood());
		}
		cart4Expected = 16;

		cart5 = createCart(5);
		for (int i = 0; i < 2; i++) {
			cart5.addItem(new Alcohol());
		}
		for(int i = 0; i < 3; i++) {
			cart5.addItem(new Dairy());
		}
		for(int i = 0; i < 4; i++) {
			cart5.addItem(new Meat());
		}
		cart5Expected = 69;

		cart6 = createCart(30);
		for(int i=0; i<=2; i++)
		{
			cart6.addItem(new Produce());
		}
		cart6.addItem(new Alcohol());
		cart6.addItem(new FrozenFood());

		cart6Expected = 15;

		cart7= createCart(500);
	}

	// sample test
	@Test
	public void calcCostCart1() throws UnderAgeException {
		double amount = cart1.calcCost();
		assertEquals(cart1Expected, amount, 0.01);
	}

	@Test
	public void amountSavedCart1() throws UnderAgeException {
		double amount = cart1.amountSaved();
		assertEquals(6, amount, 0.01);
	}

	@Test
	public void calcCostCart4() throws UnderAgeException {
		double amount = cart4.calcCost();
		assertEquals(cart4Expected, amount, 0.01);
	}

	@Test 
	public void calcCostCart5() throws UnderAgeException {
		try {
			double amount = cart5.calcCost();
			assertEquals(cart5Expected, amount, 0.01);
		}
		catch(UnderAgeException tooYoung)
		{
			System.out.println(tooYoung);
		}
	}

	@Test
	public void calcCostCart6() throws UnderAgeException {
		double amount = cart6.calcCost();
		assertEquals(cart6Expected, amount, 0.01);
	}

	@Test
	public void amountSavedCart4() throws UnderAgeException {
		Cart cart = cart4;
		double amount = cart.amountSaved();
		assertEquals(0, amount, 0.01);
	}

	@Test
	public void calcCostCart2() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2Expected, amount, 0.01);
	}

	@Test
	public void amountSavedCart2() throws UnderAgeException {
		double amount = cart2.amountSaved();
		assertEquals(1, amount, 0.01);
	}


	@Test
	public void amountSavedCart6() throws UnderAgeException {
		double amount = cart6.amountSaved();
		assertEquals(4, amount, 0.01);
	}

	@Test
	public void impossibleAgeOld() throws UnderAgeException {
		cart7.addItem(new Produce());

	}

	@Test 
	public void RemoveInvalidItem()
	{
		Cart original = cart3;
		cart3.removeItem(new Produce());
		assertEquals(cart3, original);
	}
	@Test
	public void RemoveAddedItem()
	{
		Cart original = cart1;
		Product apple = new Produce();
		cart1.addItem(apple);
		cart1.removeItem(apple);
		assertEquals(cart1, original);
	}
	@Test
	public void UnderAge() throws UnderAgeException
	{
		try {
		cart3.addItem(new Alcohol());
		cart3.amountSaved();
		}
		catch(UnderAgeException tooYoung)
		{
			System.out.println(tooYoung);
		}
	}

	@Test 
	public void ColoradoTax()throws Exception 
	{
		assertEquals(cart1.getTax(10, "CO"), .70 , .01);
	}

	@Test 
	public void CaliforniaTax()throws Exception 
	{
		assertEquals(cart1.getTax(10, "CA"), .90 , .01);
	}

	@Test 
	public void NewYorkTax() throws Exception 
	{
		assertEquals(cart1.getTax(10, "NY"), .70 , .01);
	}

	@Test 
	public void DefaultTax()throws Exception  
	{
		try {
			assertEquals(cart1.getTax(10, "WA"), 10 , .01);
		}
		catch(Exception noTaxForDefault)
		{
		}
	}

	@Test 
	public void emptyCartEmpty()
	{
		cart3.removeItem(new Produce());
	}
}
