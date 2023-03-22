package ro.unibuc.hello.controller;

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
    public ResponseEntity<String> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @GetMapping("/accessory")
    @ResponseBody
    public List<Accessory> getAccessories() {
        return accessoryService.getAccessories();
    }

    @PostMapping("/accessory")
    public void addAccessory(@RequestBody Accessory accessory, HttpServletResponse response) {
        boolean wasAccessoryAdded = accessoryService.addAccessory(accessory);
        response.setStatus(wasAccessoryAdded ? HttpStatus.OK.value():  HttpStatus.CONFLICT.value());
    }

    @DeleteMapping("/accessory/{id}")
    public void deleteAccessory(@PathVariable String id, HttpServletResponse response) {
        boolean wasAccessoryDeleted = accessoryService.deleteAccessory(id);
        response.setStatus(wasAccessoryDeleted ? HttpStatus.OK.value():  HttpStatus.NOT_FOUND.value());
    }
}
