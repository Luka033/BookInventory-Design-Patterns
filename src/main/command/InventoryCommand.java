package main.command;

import main.Inventory;

import java.io.Serializable;

public interface InventoryCommand extends Serializable {
    boolean execute(Inventory inventory);
}
