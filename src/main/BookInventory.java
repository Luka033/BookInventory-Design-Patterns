package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * BookInventory for {@link Book} with the ability to add, addCopy, sell, and changePrice
 * of books. This class is Serializable in order for it to be saved in a {@link Memento} to
 * allow restoration to previous state in case of a crash.
 */
public class BookInventory implements Inventory, Serializable {
    private List<Book> bookInventory;
    private int bookIdCounter;

    public BookInventory() {
        this.bookInventory = new ArrayList<>();
        this.bookIdCounter = 1;
    }

    /**
     * {@inheritDoc}
     */
    public boolean add(Book book) {
        if (book == null) return false;
        Book existingBook = find(book.getName());
        if (existingBook == null) {
            book.setId(bookIdCounter);
            bookIdCounter++;
            bookInventory.add(book);
            System.out.println("New Book Added: " + book.getName());
        } else {
            addCopy(existingBook.getId());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addCopy(int bookId) {
        Book existingBook = find(bookId);
        if (existingBook != null) {
            existingBook.incrementQuantity();
            System.out.println("New Copy Added: " + existingBook.getName());
            return true;
        }
        System.out.println("This book is not in our inventory!");
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean sell(int bookId) {
        Book existingBook = find(bookId);
        if (existingBook != null && existingBook.getQuantity() > 0) {
            existingBook.decrementQuantity();
            System.out.println(existingBook.getName() + " sold for $" + existingBook.getPrice());
            return true;
        }
        System.out.println("This book is out of stock or not in our inventory!");
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean changePrice(int bookId, double newPrice) {
        Book existingBook = find(bookId);
        if (existingBook != null) {
            existingBook.setPrice(newPrice);
            System.out.println("Changed price of " + existingBook.getName() + " to " + newPrice);
            return true;
        }
        System.out.println("This book is not in our inventory!");
        return false;
    }

    /**
     * Searches for a book in the current inventory by name
     *
     * @param bookName name of the book to find
     * @return Book if it was found null otherwise
     */
    public Book find(String bookName) {
        return bookInventory.stream()
                .filter(book -> bookName.equals(book.getName()))
                .findAny()
                .orElse(null);
    }

    /**
     * Searches for a book in the current inventory by id
     *
     * @param bookId name of the book to find
     * @return Book if it was found null otherwise
     */
    public Book find(int bookId) {
        return bookInventory.stream()
                .filter(book -> bookId == book.getId())
                .findAny()
                .orElse(null);
    }

    /**
     * Creates a new Memento object containing this
     *
     * @return Memento object containing this
     */
    public Memento save() {
        return new Memento(this.bookInventory, this.bookIdCounter);
    }

    /**
     * Restores this object to the given state
     *
     * @param objMemento containing a previous state of this
     */
    public void restore(Object objMemento) {
        Memento memento = (Memento) objMemento;
        this.bookInventory = memento.getMementoBookInventory();
        this.bookIdCounter = memento.getMementoBookId();
    }

    @Override
    public String toString() {
        return bookInventory.toString();
    }

}
