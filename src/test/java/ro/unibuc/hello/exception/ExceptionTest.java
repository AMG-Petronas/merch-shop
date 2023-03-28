package ro.unibuc.hello.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionTest {

    EntityNotFoundException empty_exception = new EntityNotFoundException();
    EntityNotFoundException exception = new EntityNotFoundException("entity");

    @Test
    void empty_exception(){
        Assertions.assertSame("No entities found", empty_exception.getMessage());
    }

    @Test
    void exception(){
        Assertions.assertEquals(String.format("Entity: %s was not found", "entity"), exception.getMessage());
    }

}