package main.command;

import main.Inventory;

public class ChangePriceCommand implements InventoryCommand {
    private final int bookId;
    private final double newPrice;

    public ChangePriceCommand(int bookId, double newPrice) {
        this.bookId = bookId;
        this.newPrice = newPrice;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.changePrice(this.bookId, newPrice);
    }
}
