package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClothingItemTest {

    ClothingItem clothingItem = new ClothingItem("testId","Test title", "test description",
            "test material", "test small size",23);

    ClothingItem emptyClothingItem = new ClothingItem();
    @Test
    void test_empty_id(){
        Assertions.assertSame(null, emptyClothingItem.getId());
    }

    @Test
    void test_id(){
        Assertions.assertSame("testId", clothingItem.getId());
    }
    @Test
    void test_title(){
        Assertions.assertEquals("Test title", clothingItem.getTitle());
    }
    @Test
    void test_description(){
        Assertions.assertEquals("test description", clothingItem.getDescription());
    }
    @Test
    void test_material(){
        Assertions.assertEquals("test material", clothingItem.getMaterial());
    }

    @Test
    void test_size(){
        Assertions.assertEquals("test small size", clothingItem.getSize());
    }

    @Test
    void test_price(){
        Assertions.assertEquals(23, clothingItem.getPrice());
    }

    @Test
    void test_set_id(){
        clothingItem.setId("setidTest");
        Assertions.assertSame("setidTest", clothingItem.getId());
    }

    @Test
    void test_set_title(){
        clothingItem.setTitle("New title");
        Assertions.assertEquals("New title", clothingItem.getTitle());
    }
    @Test
    void test_set_description(){
        clothingItem.setDescription("New description");
        Assertions.assertEquals("New description", clothingItem.getDescription());
    }
    @Test
    void test_set_material(){
        clothingItem.setMaterial("New material");
        Assertions.assertEquals("New material", clothingItem.getMaterial());
    }

    @Test
    void test_set_size(){
        clothingItem.setSize("New size");
        Assertions.assertEquals("New size", clothingItem.getSize());
    }

    @Test
    void test_set_price(){
        clothingItem.setPrice(18);
        Assertions.assertEquals(18, clothingItem.getPrice());
    }

}