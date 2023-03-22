package ro.unibuc.hello.service;//package ro.unibuc.hello.service;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.unibuc.hello.data.ClothingItemEntity;
import ro.unibuc.hello.data.ClothingItemRepository;
import ro.unibuc.hello.dto.ClothingItem;
import ro.unibuc.hello.exception.EntityNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;
@FixMethodOrder(MethodSorters.NAME_ASCENDING )
@ExtendWith(SpringExtension.class)
class ClothingItemServiceTest {

    ClothingItem mockClothingItem = new ClothingItem("testIdService",
            "testitle","testdesc","testmaterial","testsize",
            23);
    @Mock
    ClothingItemRepository mockClothingItemRepository;

    @InjectMocks
    ClothingItemService clothingItemService = new ClothingItemService();


    @Test
    void test_1_insert(){
        String clothingItemInsertRes = clothingItemService.insert(mockClothingItem);

        Assertions.assertEquals("Item added successfully", clothingItemInsertRes);
    }

    @Test
    void test_2_get_by_id(){

        clothingItemService.insert(mockClothingItem);

        when(mockClothingItemRepository.findById("testIdService")).thenReturn(Optional.of(new ClothingItemEntity(mockClothingItem.getId(), mockClothingItem.getTitle(), mockClothingItem.getDescription(), mockClothingItem.getMaterial(), mockClothingItem.getSize(), mockClothingItem.getPrice())));

        ClothingItem getClothingItemById  = clothingItemService.getClothingItemById("testIdService");

        Assertions.assertEquals("testIdService", getClothingItemById.getId());
    }

    @Test
    void test_3_delete_by_id(){
        String clothingItemInsertRes = clothingItemService.deleteItem("testIdService");

        Assertions.assertEquals("Item deleted successfully", clothingItemInsertRes);
    }

}