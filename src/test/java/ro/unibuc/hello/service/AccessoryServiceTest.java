package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.unibuc.hello.data.AccessoryEntity;
import ro.unibuc.hello.data.AccessoryRepository;
import ro.unibuc.hello.dto.Accessory;
import ro.unibuc.hello.exception.EntityNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AccessoryServiceTest {

    String accessoryName = "Accessory name";
    String accessoryDescription = "Accessory description";
    String accessoryColour = "Accessory colour";
    String accessoryDriver = "Accessory driver";
    float accessoryPrice = 15;

    @Mock
    AccessoryRepository mockAccessoryRepository;

    @InjectMocks
    AccessoryService accessoryService = new AccessoryService();

    @Test
    void test_get_accessory() {
        String accessoryId = "0f2cca1e-d9dc-42f2-a8ee-12345678";

        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);
        accessoryService.addAccessory(accessory);

        when(mockAccessoryRepository.findById(accessoryId)).thenReturn(Optional.of(new AccessoryEntity(accessory.getId(), accessory.getName(), accessory.getDescription(), accessory.getColour(), accessory.getDriver(), accessory.getPrice())));

        Accessory newAccessory = accessoryService.getAccessory(accessoryId);
        Assertions.assertEquals(accessoryId, newAccessory.getId());
    }

    @Test
    void test_add_accessory() {
        String accessoryId = "new-id-for-add-test";

        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);
        accessoryService.addAccessory(accessory);

        when(mockAccessoryRepository.findById(accessoryId)).thenReturn(Optional.of(new AccessoryEntity(accessory.getId(), accessory.getName(), accessory.getDescription(), accessory.getColour(), accessory.getDriver(), accessory.getPrice())));

        Accessory newAccessory = accessoryService.getAccessory(accessoryId);
        Assertions.assertNotNull(newAccessory);
        Assertions.assertEquals(accessoryId, accessory.getId());
    }

    @Test
    void test_delete_accessory() {
        String accessoryId = "new-id-for-delete-test";

        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);
        accessoryService.addAccessory(accessory);

        when(mockAccessoryRepository.findById(accessoryId)).thenReturn(Optional.of(new AccessoryEntity(accessory.getId(), accessory.getName(), accessory.getDescription(), accessory.getColour(), accessory.getDriver(), accessory.getPrice())));
        Accessory newAccessory = accessoryService.getAccessory(accessoryId);
        Assertions.assertEquals(accessoryId, newAccessory.getId());

        accessoryService.deleteAccessory(accessoryId);

        when(mockAccessoryRepository.findById(accessoryId)).thenReturn(Optional.empty());
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> accessoryService.getAccessory(accessoryId),
                "Didn't throw."
        );
        Assertions.assertEquals("Entity: " + accessoryId + " was not found", thrown.getMessage());
    }
}