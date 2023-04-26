package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.ClothingItem;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.ClothingItemService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ClothesController {
    @Autowired
    private ClothingItemService clothingItemService;

    @Autowired
    MeterRegistry metricsRegistry;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Timed(value = "hello.exceptionHandle.time", description = "Time taken to handle exception")
    @Counted(value = "hello.exceptionHandle.count", description = "Times exception was handled")
    public ResponseEntity<String> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @PostMapping("/clothes")
    @Timed(value = "hello.post_clothes.time", description = "Time taken to insert clothes")
    @Counted(value = "hello.post_clothes.count", description = "Times clothes were inserted")
    public void insert(@RequestBody ClothingItem clothingItem, HttpServletResponse response) {
        clothingItemService.insert(clothingItem);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping("/clothes")
    @ResponseBody
    @Timed(value = "hello.get_clothes.time", description = "Time taken to return clothes")
    @Counted(value = "hello.get_clothes.count", description = "Times clothes were returned")
    public List<ClothingItem> getClothes(){
        return clothingItemService.getClothingItems();
    }

    @GetMapping("/clothes/{clothingItemId}")
    @ResponseBody
    @Timed(value = "hello.get_clothingItem.time", description = "Time taken to return a clothing item")
    @Counted(value = "hello.get_clothingItem.count", description = "Times clothing item was returned")
    public ClothingItem getClothingItem(@PathVariable String clothingItemId){
        return clothingItemService.getClothingItemById(clothingItemId);
    }

    @DeleteMapping("/clothes/{clothingItemId}")
    @Timed(value = "hello.delete_clothes.time", description = "Time taken to delete clothes")
    @Counted(value = "hello.delete_clothes.count", description = "Times clothes were deleted")
    public String delete(@PathVariable String clothingItemId) {
        return  this.clothingItemService.deleteItem(clothingItemId);
    }
}
