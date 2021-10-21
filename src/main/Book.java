package main;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private final String name;
    private double price;
    private int id = -1;
    private int quantity = 1;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = getId();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity > 0)
            this.quantity--;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book)obj;
        return other.getName().equals(this.getName()) &&
                other.getId() == this.getId() &&
                other.getPrice() == this.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, price);
    }
}
