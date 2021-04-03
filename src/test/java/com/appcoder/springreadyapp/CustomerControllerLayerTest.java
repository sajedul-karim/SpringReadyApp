package com.appcoder.springreadyapp;


import com.appcoder.springreadyapp.controller.CustomerController;
import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.exception.CustomerNotFoundException;
import com.appcoder.springreadyapp.security.JwtTokenProviderService;
import com.appcoder.springreadyapp.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({CustomerController.class})
public class CustomerControllerLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtTokenProviderService jwtTokenProviderService;

    @MockBean
    CustomerService customerService;


    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void save_customer_success() throws Exception {

        Customer customer = new Customer("sajedul", "karim", "01737186095");

        when(customerService.saveUpdateCustomer(any())).thenReturn(Boolean.TRUE);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/saveCustomer")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Customer Save/update Done"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void save_customer_failed() throws Exception {

        Customer customer = new Customer("sajedul", "karim", "01737186095");

        when(customerService.saveUpdateCustomer(any())).thenReturn(Boolean.FALSE);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/saveCustomer")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Customer Save/update failed"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void customer_fetch_in_db_success() throws Exception {

        List<Customer> customerList = Arrays.asList(
                new Customer("sajedul", "karim", "01737186095"),
                new Customer("shawon", "nirob", "01737186096"),
                new Customer("aayan", "karim", "01737186097")
        );

        when(customerService.fetchAllCustomer()).thenReturn(customerList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/fetchAllCustomer"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}, {}]"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void customer_fetch_customer_by_mobileNo_exception_success() throws Exception {

        String searchedMobileNo = "01737186095";

        when(customerService.findCustomerByMobileNumberException(searchedMobileNo)).thenThrow(new RuntimeException("No data found"));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/fetchCustomerByMobileNumberException?mobileNumber=" + searchedMobileNo))
                .andDo(print())
                .andExpect(status().is(404))
                .andReturn();
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
