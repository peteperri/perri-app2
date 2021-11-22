/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest {

    InventoryList testList = new InventoryList();

    @Test
    //test for requirement 2: the GUI shall have a control that allows the user to add a new inventory item
    void addItem() {
        assertTrue(testList.addItem("Shrek 2 DVD", "$400", "A-123-123-122"));
        assertEquals(1, testList.getInventory().size());
    }

    @Test
    //test for requirement 3: the GUI shall have a control that allows the user to remove a single inventory item
    void removeItem() {
        testList.addItem("Shrek 3 DVD", "$2", "A-123-123-124");
        testList.removeItem(testList.getInventory().get(0));
        assertEquals(0, testList.getInventory().size());

    }

    @Test
    //test for requirement 4: the GUI shall have a control that allows the user to remove every single inventory item
    void clearList() {
        testList.addItem("Shrek 2 DVD", "$400", "A-123-123-123");
        testList.addItem("Shrek 3 DVD", "$2", "A-123-123-124");
        assertEquals(2, testList.getInventory().size());
        testList.clearList();
        assertEquals(0, testList.getInventory().size());
    }
}