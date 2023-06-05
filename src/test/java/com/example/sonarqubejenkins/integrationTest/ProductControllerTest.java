package com.example.sonarqubejenkins.integrationTest;

import com.example.sonarqubejenkins.controller.ProductController;
import com.example.sonarqubejenkins.dto.ProductDto;
import com.example.sonarqubejenkins.service.interfaces.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    private Long idProduct;
    private ProductDto productDto;
    @BeforeEach
    void initVariables(){
     productDto= ProductDto.builder()
                .name("tomate")
                .price(70L)
                .build();
     idProduct = 1L;
    }

    @Test
    void createProduct() throws Exception {
;
        when(productService.createProduct(productDto)).thenReturn(productDto);

        ResultActions resultActions = mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto))
        );

        resultActions.andExpect(
                MockMvcResultMatchers.jsonPath(
                        "$.name", CoreMatchers.is(productDto.getName())));
        resultActions.andExpect(
                MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(70)));
    }

    @Test
    void getProducts() throws Exception {
        ProductDto productDto2 = ProductDto.builder()
                .name("pomme")
                .price(90L)
                .build();
        when(productService.getProducts()).thenReturn(Arrays.asList(productDto,productDto2));

        ResultActions resultActions = mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON)
        );

       resultActions.andExpect(MockMvcResultMatchers.status().isOk());
       resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    void getProductById() throws Exception {
        when(productService.getProductById(idProduct)).thenReturn(productDto);
        ResultActions resultActions = mockMvc.perform(get("/products/"+idProduct)
                .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())),
                MockMvcResultMatchers.jsonPath("$.price",CoreMatchers.is(70))
        );

    }

    @Test
    void updateProduct() throws Exception {
        when(productService.updateProduct(idProduct,productDto)).thenReturn(productDto);

        ResultActions resultActions = mockMvc.perform(put("/products/"+idProduct)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto))
        );

        resultActions.andExpectAll(
          MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(productDto.getName())),
                MockMvcResultMatchers.jsonPath("$.price",CoreMatchers.is(70))
        );

    }

    @Test
    void deleteProduct() throws Exception {
        when(productService.deleteProduct(idProduct)).thenReturn(true);

        ResultActions resultActions = mockMvc.perform(delete("/products/"+idProduct)
                .contentType(MediaType.APPLICATION_JSON)
        );

        String booleanString = resultActions.andReturn().getResponse().getContentAsString();
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        assertTrue(Boolean.parseBoolean(booleanString));
    }
}