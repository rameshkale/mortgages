package com.mortgage.dslayer;

/**
 * Created by rameshkale on 18/08/20.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgages.BootApplication;
import com.mortgages.dslayer.http.entity.MortgageApplication;
import com.mortgages.dslayer.http.request.NewApplicationsRequest;
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

import static org.junit.Assert.assertTrue;
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
public class DataSortFunctionalTest {
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

    }
    @Test
    public void testInsertAndSortApplications() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();


        insertMortgageApplication("M31",1,"12/05/2020","24/02/2018");
        insertMortgageApplication("M33",1,"12/04/2020","22/01/2019");
        insertMortgageApplication("M35",1,"11/04/2020","01/02/2019");
        insertMortgageApplication("M36",1,"10/04/2020","23/06/2020");
        insertMortgageApplication("M37",1,"12/05/2020","12/02/2019");
        insertMortgageApplication("M31",1,"12/06/2020","23/05/2020");

        JsonNode jsonNode = executeWithSort("OfferDateAsc");
        assertTrue(jsonNode.get("offerDate").textValue().equals("10/04/2020"));

        jsonNode = executeWithSort("OfferDateDesc");
        assertTrue(jsonNode.get("offerDate").textValue().equals("12/06/2020"));

        jsonNode = executeWithSort("CreatedDateAsc");
        assertTrue(jsonNode.get("createdDate").textValue().equals("24/02/2018"));

        jsonNode = executeWithSort("CreatedDateDesc");
        assertTrue(jsonNode.get("createdDate").textValue().equals("23/06/2020"));

        jsonNode = executeWithSort("");
        assertTrue(jsonNode.get("offerDate").textValue().equals("12/05/2020"));
    }
    private JsonNode executeWithSort(String sortColumn) throws Exception
    {
        MockHttpServletRequestBuilder get = get("/mortgages/applications?sort="+sortColumn)
                .contentType("application/json");
        MvcResult mvcResult = mockMvc.perform(get).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        JsonNode data = jsonNode.get("data").get(0);

        System.out.println("contentAsString = " + jsonNode.toPrettyString());
        return data;
    }

    private MortgageApplication insertMortgageApplication(String id, int version, String offerDate, String createdDate) throws Exception {
        NewApplicationsRequest newApplicationsRequest = new NewApplicationsRequest();


        MortgageApplication mortgageApplication = new MortgageApplication();

        mortgageApplication = new MortgageApplication();
        mortgageApplication.setId(id);
        mortgageApplication.setVersion(version);
        mortgageApplication.setOfferId("OFF1");
        mortgageApplication.setOfferDate(offerDate);
        mortgageApplication.setProductId("PRD1");
        mortgageApplication.setExpired(true);
        mortgageApplication.setCreatedDate(createdDate);

        newApplicationsRequest.setData(mortgageApplication);

        post = post("/mortgages/applications")
                .contentType("application/json");
        post.content(objectMapper.writeValueAsString(newApplicationsRequest));
        mockMvc.perform(post).andExpect(status().isOk());

        return mortgageApplication;
    }
}