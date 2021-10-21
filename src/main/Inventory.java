package main;

public interface Inventory {

    /**
     * Add a book to the inventory
     *
     * @param book to add
     * @return true if the operation was successful otherwise false
     */
    boolean add(Book book);

    /**
     * Add a copy the of the book with the given bookId to the inventory
     *
     * @param bookId of the book to add a copy of
     * @return true if the operation was successful otherwise false
     */
    boolean addCopy(int bookId);

    /**
     * Sell a copy the of the book with the given bookId if its in the inventory
     *
     * @param bookId of the book to sell
     * @return true if the operation was successful otherwise false
     */
    boolean sell(int bookId);

    /**
     * Change price the of the book with the given bookId if its in the inventory
     *
     * @param bookId of the book to change price of
     * @param newPrice to set
     * @return true if the operation was successful otherwise false
     */
    boolean changePrice(int bookId, double newPrice);

    /**
     * Find a book with the given bookId in the inventory
     *
     * @param bookId of the book to find
     * @return Book if it was found null otherwise
     */
    Book find(int bookId);

    /**
     * Find a book with the given book name in the inventory
     *
     * @param bookName of the book to find
     * @return Book if it was found null otherwise
     */
    Book find(String bookName);
}
