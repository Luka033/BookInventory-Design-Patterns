package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Memento for the {@link BookInventory} class that is Serializable to allow saving
 * a state in order to restore it at a later time
 */
public class Memento implements Serializable {
    final List<Book> mementoBookInventory;
    final int mementoBookId;

    public Memento(List<Book> bookInventory, int bookId) {
        mementoBookInventory = new ArrayList<>(bookInventory);
        mementoBookId = bookId;
    }

    public List<Book> getMementoBookInventory() {
        return mementoBookInventory;
    }

    public int getMementoBookId() {
        return mementoBookId;
    }
}
