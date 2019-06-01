package ua.training.model.entity;

public class TaxableItem {
    private long id;
    private long idPerson;
    private String name;
    private long price;
    private int quantity;

    public TaxableItem() {
    }

    public TaxableItem(long id, long idPerson, String name, long price, int quantity) {
        this.id = id;
        this.idPerson = idPerson;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
