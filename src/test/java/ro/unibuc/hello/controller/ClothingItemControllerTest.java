package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.unibuc.hello.dto.ClothingItem;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.ClothingItemService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ClothingItemControllerTest {

    @Mock
    private ClothingItemService clothingItemService;

    @InjectMocks
    private ClothesController clothesController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clothesController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void test_get_by_id() throws Exception {
        ClothingItem mockClothingItem = new ClothingItem("testIdService",
                "testitle","testdesc","testmaterial","testsize",
                23);

        when(clothingItemService.getClothingItemById("testIdService")).thenReturn(mockClothingItem);

        // Act
        MvcResult result = mockMvc.perform(get("/clothes/testIdService")
                .content(objectMapper.writeValueAsString(mockClothingItem))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(mockClothingItem));
    }

    @Test
    void test_delete() throws Exception {

        when(clothingItemService.deleteItem("testIdService")).thenReturn("Item deleted successfully");

        // Act
        MvcResult result = mockMvc.perform(delete("/clothes/testIdService")
                        .content("Item deleted successfully")
                        .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), "Item deleted successfully");
    }
}