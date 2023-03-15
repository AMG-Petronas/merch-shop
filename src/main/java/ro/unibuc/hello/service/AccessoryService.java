package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.AccessoryEntity;
import ro.unibuc.hello.data.AccessoryRepository;
import ro.unibuc.hello.dto.Accessory;
import ro.unibuc.hello.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;

    public List<Accessory> getAccessories() throws EntityNotFoundException {
        List<AccessoryEntity> entities = accessoryRepository.findAll();

        if (entities.isEmpty()) {
            throw new EntityNotFoundException("Accessory");
        }
        return entities.stream()
                .map(entity -> new Accessory(entity.id, entity.name, entity.description, entity.colour, entity.driver, entity.price))
                .collect(Collectors.toList());
    }

    public boolean addAccessory(Accessory accessory) {
        Optional<AccessoryEntity> accessoryEntityFromDb = accessoryRepository.findById(accessory.getId());

        if (accessoryEntityFromDb.isEmpty()) {
            AccessoryEntity accessoryEntity = new AccessoryEntity(accessory.getId(), accessory.getName(), accessory.getDescription(), accessory.getColour(), accessory.getDriver(), accessory.getPrice());
            accessoryRepository.insert(accessoryEntity);
            return true;
        }
        return false;
    }

    public boolean deleteAccessory(String id) {
        Optional<AccessoryEntity> accesoryEntity = accessoryRepository.findById(id);

        if (!accesoryEntity.isEmpty()) {
            accessoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
