package ro.unibuc.hello.dto;

public class Accessory {

    private String id;
    private String name;
    private String description;
    private String colour;
    private String driver;
    private float price;

    public Accessory() {}

    public Accessory(String id, String name, String description, String colour, String driver, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.driver = driver;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
