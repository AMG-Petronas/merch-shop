package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

public class AccessoryEntity {

    @Id
    public String id;
    public String name;
    public String description;
    public String colour;
    public String driver;
    public float price;

    public AccessoryEntity() {}
    public AccessoryEntity(String id, String name, String description, String colour, String driver, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.driver = driver;
        this.price = price;
    }
}
