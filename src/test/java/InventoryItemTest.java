/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    //these tests cover multiple requirements:
    //Requirements 5/6/7: The GUI SHALL have a control that allows the user to edit the name/value/serial number
    //as well as:
    //Requirements 1.1, 1.2, 1.3: Vaidation requirements for Item data
    InventoryItem item = new InventoryItem(
            "Shrek 2 DVD sealed first edition signed by the voice of Donkey",
            "$9000",
            "A-LLS-TAR-420");
    @Test
    void setName() {
        assertFalse(item.setName("e"));
        assertFalse(item.setName("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
        assertEquals("Shrek 2 DVD sealed first edition signed by the voice of Donkey", item.getName());
        assertTrue(item.setName("1st edition Charizard Pokemon Card"));
        assertEquals("1st edition Charizard Pokemon Card", item.getName());
    }

    @Test
    void setValue() {
        assertFalse(item.setValue("$-500"));
        assertFalse(item.setValue("-500"));
        assertEquals("$9,000.00", item.getValue());
        assertTrue(item.setValue("$100000.54"));
        assertTrue(item.setValue("100000.23452343"));
        assertEquals("$100,000.23",item.getValue());
    }

    @Test
    void setSerialNumber() {
        assertFalse(item.setSerialNumber("$-500"));
        assertFalse(item.setSerialNumber("A-LLSTAR-420"));
        assertEquals("A-LLS-TAR-420", item.getSerialNumber());
        assertTrue(item.setSerialNumber("F-UNN-YMO-NKE"));
        assertTrue(item.setSerialNumber("A-123-123-123"));
        assertEquals("A-123-123-123",item.getSerialNumber());
    }
}