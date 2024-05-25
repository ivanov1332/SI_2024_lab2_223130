package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void testEveryBranch() {
        // Test case 1: allItems is null
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());

        // Test case 2: allItems is an empty list, payment is 0
        List<Item> allItems2 = Arrays.asList();
        assertTrue(SILab2.checkCart(allItems2, 0));

        // Test case 3: One valid item, payment is enough
        List<Item> allItems3 = Arrays.asList(new Item("item", "012345", 100, 0));
        assertTrue(SILab2.checkCart(allItems3, 100));

        // Test case 4: One valid item with discount, payment is enough
        List<Item> allItems4 = Arrays.asList(new Item("item", "012345", 350, 0.1f));
        assertTrue(SILab2.checkCart(allItems4, 320));

        // Test case 5: One item with invalid barcode
        List<Item> allItems5 = Arrays.asList(new Item("item", "01a345", 100, 0));
        exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(allItems5, 100);
        });
        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    void testMultipleConditions() {
        // Test case 1: item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'
        List<Item> allItems1 = Arrays.asList(new Item("item", "012345", 350, 0.1f));
        assertTrue(SILab2.checkCart(allItems1, 320));

        // Test case 2: item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) != '0'
        List<Item> allItems2 = Arrays.asList(new Item("item", "112345", 350, 0.1f));
        assertFalse(SILab2.checkCart(allItems2, 320));

        // Test case 3: item.getPrice() > 300 && item.getDiscount() == 0 && item.getBarcode().charAt(0) == '0'
        List<Item> allItems3 = Arrays.asList(new Item("item", "012345", 350, 0.0f));
        assertFalse(SILab2.checkCart(allItems3, 350));

        // Test case 4: item.getPrice() > 300 && item.getDiscount() == 0 && item.getBarcode().charAt(0) != '0'
        List<Item> allItems4 = Arrays.asList(new Item("item", "112345", 350, 0.0f));
        assertFalse(SILab2.checkCart(allItems4, 350));

        // Test case 5: item.getPrice() <= 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'
        List<Item> allItems5 = Arrays.asList(new Item("item", "012345", 250, 0.1f));
        assertTrue(SILab2.checkCart(allItems5, 275));

        // Test case 6: item.getPrice() <= 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) != '0'
        List<Item> allItems6 = Arrays.asList(new Item("item", "112345", 250, 0.1f));
        assertTrue(SILab2.checkCart(allItems6, 275));

        // Test case 7: item.getPrice() <= 300 && item.getDiscount() == 0 && item.getBarcode().charAt(0) == '0'
        List<Item> allItems7 = Arrays.asList(new Item("item", "012345", 250, 0.0f));
        assertTrue(SILab2.checkCart(allItems7, 250));

        // Test case 8: item.getPrice() <= 300 && item.getDiscount() == 0 && item.getBarcode().charAt(0) != '0'
        List<Item> allItems8 = Arrays.asList(new Item("item", "112345", 250, 0.0f));
        assertTrue(SILab2.checkCart(allItems8, 250));
    }
}