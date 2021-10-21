package main;

/**
 * Program to create a {@link BookInventory} using design patterns
 * CS-635
 *
 * @author Luka Jozic
 */
public class Main {
    public static void main(String[] args) {
        BookInventory inventory = new BookInventory();
        Inventory inventoryDecorator = new InventoryDecorator(inventory);

//        inventoryDecorator.restoreState();
//        System.out.println(inventory);

        inventoryDecorator.add(new Book("Book1", 29.99));
        System.out.println(inventory);
//
//        inventoryDecorator.addCopy(1);
//        System.out.println(inventory);
//
//        inventoryDecorator.addCopy(1);
//        System.out.println(inventory);
//
//        inventoryDecorator.add(new Book("Book2", 25.99));
//        System.out.println(inventory);
//
//        inventoryDecorator.add(new Book("Book3", 19.99));
//        System.out.println(inventory);
//
//        inventoryDecorator.sell(1);
//        System.out.println(inventory);
//
//        inventoryDecorator.sell(1);
//        System.out.println(inventory);
//
//        inventoryDecorator.add(new Book("Book4", 29.99));
//        System.out.println(inventory);
//
//        inventoryDecorator.add(new Book("Book5", 25.99));
//        System.out.println(inventory);
//
//        inventoryDecorator.add(new Book("Book6", 19.99));
//        System.out.println(inventory);
//
//        inventoryDecorator.sell(3);
//        System.out.println(inventory);
//
//        inventoryDecorator.changePrice(2, 9.99);
//        System.out.println(inventory);




    }
}
