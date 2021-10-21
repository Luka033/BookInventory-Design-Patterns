package main.command;

import main.Book;
import main.Inventory;

public class AddBookCommand implements InventoryCommand {
    private final Book book;

    public AddBookCommand(Book book) {
        this.book = book;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.add(this.book);
    }
}
