package group4462.payment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountAdminControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAccountsIntegrationTest() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }
}