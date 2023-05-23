package no.emiljensen.hbecommerce.checkout;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutService checkoutService;

    @Test
    void testCheckout_ValidRequest_ReturnsTotalAmount() throws Exception {
        List<String> ids = List.of("001", "002", "003");
        int totalAmount = 500;

        Mockito.when(checkoutService.getCheckoutPrice(ids)).thenReturn(totalAmount);

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"001\", \"002\", \"003\"]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"price\":500}"));
    }

    @Test
    void testCheckout_EmptyRequest_ReturnsZero() throws Exception {
        List<String> ids = Collections.emptyList();
        int totalAmount = 0;

        Mockito.when(checkoutService.getCheckoutPrice(ids)).thenReturn(totalAmount);

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"price\":0}"));
    }

    @Test
    void testCheckout_NullRequest_ReturnsZero() throws Exception {
        int totalAmount = 0;

        Mockito.when(checkoutService.getCheckoutPrice(null)).thenReturn(totalAmount);

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("null"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
