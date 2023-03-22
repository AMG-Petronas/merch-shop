package ro.unibuc.hello.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepository extends MongoRepository<AccessoryEntity, String> {
    List<AccessoryEntity> findAll();
}
