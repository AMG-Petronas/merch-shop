package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.Accessory;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.AccessoryService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Timed(value = "hello.exceptionHandle.time", description = "Times taken to handle exceptions")
    @Counted(value = "hello.exceptionHandle.count", description = "Times exceptions were handled")
    public ResponseEntity<String> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @GetMapping("/accessory")
    @ResponseBody
    @Timed(value = "hello.get_accesories.time", description = "Times taken to return accesories")
    @Counted(value = "hello.get_accesories.count", description = "Times accesories were returned")
    public List<Accessory> getAccessories() {
        return accessoryService.getAccessories();
    }

    @GetMapping("/accessory/{id}")
    @ResponseBody
    @Timed(value = "hello.get_accessory.time", description = "Times taken to return an accessory item")
    @Counted(value = "hello.get_accessory.count", description = "Times accessories entities were returned")
    public Accessory getAccessory(@PathVariable String id) { return accessoryService.getAccessory(id); }

    @PostMapping("/accessory")
    @Timed(value = "hello.add_accessory.time", description = "Times taken to insert accessories")
    @Counted(value = "hello.add_accessory.count", description = "Times accessories were inserted")
    public void addAccessory(@RequestBody Accessory accessory, HttpServletResponse response) {
        boolean wasAccessoryAdded = accessoryService.addAccessory(accessory);
        response.setStatus(wasAccessoryAdded ? HttpStatus.OK.value():  HttpStatus.CONFLICT.value());
    }

    @DeleteMapping("/accessory/{id}")
    @Timed(value = "hello.delete_accessory.time", description = "Times taken to delete accessories")
    @Counted(value = "hello.delete_accessory.count", description = "Times accessories were deleted")
    public void deleteAccessory(@PathVariable String id, HttpServletResponse response) {
        boolean wasAccessoryDeleted = accessoryService.deleteAccessory(id);
        response.setStatus(wasAccessoryDeleted ? HttpStatus.OK.value():  HttpStatus.NOT_FOUND.value());
    }
}
