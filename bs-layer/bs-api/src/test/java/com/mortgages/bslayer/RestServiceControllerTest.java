package com.mortgages.bslayer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgages.bslayer.entity.MortgageApplication;
import com.mortgages.bslayer.request.NewApplicationsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by rameshkale on 18/08/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { BootApplication.class })
@WebAppConfiguration
public class RestServiceControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private
    MockHttpServletRequestBuilder post;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getApplications() throws Exception {

        MockHttpServletRequestBuilder post = post("/mortgages/applications")
                .contentType("application/json");

        NewApplicationsRequest newApplicationsRequest = new NewApplicationsRequest();

        MortgageApplication mortgageApplication  = new MortgageApplication();
        mortgageApplication.setId("M1");
        mortgageApplication.setVersion(1);
        mortgageApplication.setOfferId("OFF1");
        mortgageApplication.setOfferDate("12/12/2020");
        mortgageApplication.setProductId("PRD1");
        mortgageApplication.setExpired(true);
        mortgageApplication.setCreatedDate("12/11/2019");

        newApplicationsRequest.setData(mortgageApplication);

        post.content(objectMapper.writeValueAsString(newApplicationsRequest));
        mockMvc.perform(post).andExpect(status().isOk());


        MockHttpServletRequestBuilder get = get("/mortgages/applications?sort=")
                .contentType("application/json");
        MvcResult mvcResult = mockMvc.perform(get).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        JsonNode data = jsonNode.get("data").get(0);

        assertTrue(data.get("id").textValue().equals("M1"));
        assertTrue(data.get("version").intValue() == 1);
        assertTrue(data.get("offerId").textValue().equals("OFF1"));
        assertTrue(data.get("offerDate").textValue().equals("12/12/2020"));
        assertTrue(data.get("createdDate").textValue().equals("12/11/2019"));
        assertTrue(data.get("productId").textValue().equals("PRD1"));
        assertTrue(data.get("expired").booleanValue() == true);
        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }
}