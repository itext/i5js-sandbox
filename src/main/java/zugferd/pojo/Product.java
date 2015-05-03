/*
 * Part of a set of classes based on a sample database.
 */
package zugferd.pojo;

/**
 * Plain Old Java Object containing info about a Product.
 * @author Bruno Lowagie (iText Software)
 */
public class Product {
    protected int id;
    protected String name;
    protected double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t(").append(id).append(")\t").append(name).append("\t$").append(price);
        return sb.toString();
    }
}
