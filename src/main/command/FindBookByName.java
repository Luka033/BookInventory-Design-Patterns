package main.command;

import main.Inventory;

public class FindBookByName implements InventoryCommand {
    private final String bookName;

    public FindBookByName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public boolean execute(Inventory inventory) {
//        return inventory.find(this.bookName);
        return true;
    }
}
