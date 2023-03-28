package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.unibuc.hello.dto.Accessory;
import ro.unibuc.hello.service.AccessoryService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
public class AccessoryControllerTest {

    String accessoryName = "Accessory name";
    String accessoryDescription = "Accessory description";
    String accessoryColour = "Accessory colour";
    String accessoryDriver = "Accessory driver";
    float accessoryPrice = 15;

    @Mock
    private AccessoryService accessoryService;

    @InjectMocks
    private AccessoryController accessoryControllerMock;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accessoryControllerMock).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void test_get_accessory() throws Exception {
        String accessoryId = "accessory-id-for-controller-get-by-id";
        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);

        when(accessoryService.getAccessory(accessoryId)).thenReturn(accessory);

        // Act
        MvcResult result = mockMvc.perform(get("/accessory/" + accessoryId)
                .content(objectMapper.writeValueAsString(accessory))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(accessory));
    }

    @Test
    void test_add_accessory() throws Exception {
        String accessoryId = "accessory-id-for-controller-add";
        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);

        when(accessoryService.addAccessory(accessory)).thenReturn(true);

        // Act
        MvcResult result = mockMvc.perform(get("/accessory/" + accessoryId)
                        .content(objectMapper.writeValueAsString(accessory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    void test_delete_accessory() throws Exception {
        String accessoryId = "accessory-id-for-controller-delete";
        Accessory accessory = new Accessory(accessoryId, accessoryName, accessoryDescription, accessoryColour, accessoryDriver, accessoryPrice);

        when(accessoryService.deleteAccessory(accessoryId)).thenReturn(true);

        // Act
        MvcResult result = mockMvc.perform(get("/accessory/" + accessoryId)
                        .content(objectMapper.writeValueAsString(accessory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    }
}