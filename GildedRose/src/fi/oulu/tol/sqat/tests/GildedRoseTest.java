package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {
	// Source for print testing https://www.baeldung.com/java-testing-system-out-println
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;


	@Before
	public void setUpStreams() {
		//set up stream for testing printstream
	    System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStreams() {
		// restore print stream
	    System.setOut(originalOut);
	}
	
	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void updateHandSulfatTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 20, quality);
	}
	
	@Test
	public void updateHandWithNegativeSellInSulfatTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 20, quality);
	}
	
	
	@Test
	public void decreaseQualityTwiceAsFastTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 18, quality);
	}
	
	@Test
	public void DecreaseTimeQualityBrieTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int brieQualtity = 10;
		int brieTimeOnce = 20;
		int brieTimeDouble = 3;
		inn.setItem(new Item("Aged Brie", brieTimeOnce, brieQualtity));
		for (int i = 0; i < (brieTimeOnce + brieTimeDouble); i++) {
			inn.oneDay();
		}
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality has increased by 26
		assertEquals("Failed quality for Aged Brie", 36, quality);
	}
	
	@Test
	public void DecreaseTimeWithQualityGreater50BrieTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int brieQualtity = 51;
		int brieTime = -2;
		inn.setItem(new Item("Aged Brie", brieTime, brieQualtity));
		inn.oneDay();
	
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality stays the same
		assertEquals("Failed quality for Aged Brie", 51, quality);
	}
		
	@Test
	public void DecreaseTimeWithQualityEqual50BrieTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int brieQualtity = 50;
		int brieTime = 10;
		inn.setItem(new Item("Aged Brie", brieTime, brieQualtity));
		inn.oneDay();
	
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality stays the same
		assertEquals("Failed quality for Aged Brie", 50, quality);
	}
	
	@Test
	public void DecreaseTimeWithQualityEqual50NegativeSellInBrieTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int brieQualtity = 50;
		int brieTime = -10;
		inn.setItem(new Item("Aged Brie", brieTime, brieQualtity));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality stays the same
		assertEquals("Failed quality for Aged Brie", 50, quality);
	}
	
	
	
	@Test
	public void decreaseTimeUntilQualityNegativeTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 1, 1));
		inn.oneDay();
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 0, quality);

	}
	
	@Test
	public void QualityGreaterThanFiftyTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 100, 300));
		inn.oneDay();
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 300, quality);

	}
	
	@Test
	public void decreaseTimeBrieWithQuantityGreaterFiftyTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 21, quality);
		
	}
	
	@Test
	public void decreaseTimeBackstageTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int backStageQualtity = 10;
		int backStageTime = 15;
		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", backStageTime, backStageQualtity));
		List<Item> items = inn.getItems();

		for (int i = 0; i < 5; i++) {
			inn.oneDay();
		}
		int quality = items.get(0).getQuality();
		
		//assert quality has increased to 15
		assertEquals("Failed quality for Backstage passes", 15, quality);
		for (int i = 0; i < 5; i++) {
			inn.oneDay();
		}
		quality = items.get(0).getQuality();
		
		//assert quality has increased to 25
		assertEquals("Failed quality for Backstage passes", 25, quality);

		for (int i = 0; i < 5; i++) {
			inn.oneDay();
		}
		quality = items.get(0).getQuality();

		//assert quality has increased to 40
		assertEquals("Failed quality for Backstage passes", 40, quality);
		
		inn.oneDay();
		inn.oneDay();

		quality = items.get(0).getQuality();

		//assert quality has decreased to 0
		assertEquals("Failed quality for Backstage passes", 0, quality);
	}
	
	
	@Test
	public void decreaseTimeBackstageWithQuantityGreaterFortyNineAndBackstageTimeTen() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int backStageQualtity = 49;
		int backStageTime = 10;
		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", backStageTime, backStageQualtity));
		List<Item> items = inn.getItems();
		inn.oneDay();
	
		int quality = items.get(0).getQuality();
		
		//assert quality has increased to 15
		assertEquals("Failed quality for Backstage passes", 50, quality);
		
	}
	
	@Test
	public void decreaseTimeBackstageWithQuantityGreaterFortyNineAndBackstageTimeFive() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int backStageQualtity = 49;
		int backStageTime = 5;
		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", backStageTime, backStageQualtity));
		List<Item> items = inn.getItems();
		inn.oneDay();
	
		int quality = items.get(0).getQuality();
		
		//assert quality has increased to 15
		assertEquals("Failed quality for Backstage passes", 50, quality);
		
	}
	
	@Test
	public void mainTest() {
		//create an inn
		GildedRose inn = new GildedRose();
		GildedRose.main(null);
		List<Item> items = inn.getItems();
		int listLength = items.size();
		Item item = items.get(0);
		// check if list size is 6 (side effect)
		assertEquals("Failed size of list", 6, listLength);
		//assert quality has decreased to 19
		assertEquals("Failed quality for Backstage passes", 19, item.getQuality());
		assertEquals("OMGHAI!", outContent.toString().trim());
		
	}
	
	@Test
	public void skipLoopTest() {
		//create an inn
		GildedRose inn = new GildedRose();
		inn.oneDay();
		int listLength = inn.getItems().size();

		// check if list size is 6 (side effect)
		assertEquals("Failed size of list", 0, listLength);
		
	}
	
	@Test
	public void twoLoopsTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int backStageQualtity = 1;
		int backStageTime = 200;
		
		for (int i = 0; i < 2; i++) {
			inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", backStageTime, backStageQualtity));

		}		
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int listLength = inn.getItems().size();
		
		//assert size of items has increased to 2
		assertEquals("Failed size of list", 2, listLength);
		
	}
	
	@Test
	public void hundredLoopsTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		int backStageQualtity = 1;
		int backStageTime = 200;
		
		for (int i = 0; i < 100; i++) {
			inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", backStageTime, backStageQualtity));

		}		
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int listLength = inn.getItems().size();
		
		//assert size of items has increased to 100
		assertEquals("Failed size of list", 100, listLength);
		
	}
}
