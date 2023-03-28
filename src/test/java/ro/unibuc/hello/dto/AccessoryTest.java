package ro.unibuc.hello.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class AccessoryTest {
    Accessory accessory = new Accessory("0f2cca1e-d9dc-42f2-a8ee-65f029f139f2", "Accessory name", "Accessory description", "Accessory colour", "Accessory driver", 15);

    @Test
    void test_id() {
        Assertions.assertEquals("0f2cca1e-d9dc-42f2-a8ee-65f029f139f2", accessory.getId());
    }
    @Test
    void test_id_setter() {
        accessory.setId("new id");
        Assertions.assertEquals("new id", accessory.getId());
    }
    @Test
    void test_name() {
        Assertions.assertEquals("Accessory name", accessory.getName());
    }
    @Test
    void test_name_setter() {
        accessory.setName("new name");
        Assertions.assertEquals("new name", accessory.getName());
    }
    @Test
    void test_description() {
        Assertions.assertEquals("Accessory description", accessory.getDescription());
    }
    @Test
    void test_description_setter() {
        accessory.setDescription("new description");
        Assertions.assertEquals("new description", accessory.getDescription());
    }
    @Test
    void test_colour() {
        Assertions.assertEquals("Accessory colour", accessory.getColour());
    }
    @Test
    void test_colour_setter() {
        accessory.setColour("new colour");
        Assertions.assertEquals("new colour", accessory.getColour());
    }
    @Test
    void test_driver() {
        Assertions.assertEquals("Accessory driver", accessory.getDriver());
    }
    @Test
    void test_driver_setter() {
        accessory.setDriver("lewis hamilton");
        Assertions.assertEquals("lewis hamilton", accessory.getDriver());
    }
    @Test
    void test_price() {
        Assertions.assertEquals(15, accessory.getPrice());
    }
    @Test
    void test_price_setter() {
        accessory.setPrice(14.99F);
        Assertions.assertEquals(14.99F, accessory.getPrice());
    }
}
