package com.mortgage.dslayer;

/**
 * Created by rameshkale on 18/08/20.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgages.BootApplication;
import com.mortgages.dslayer.exceptions.BusinessException;
import com.mortgages.dslayer.http.entity.MortgageApplication;
import com.mortgages.dslayer.http.request.NewApplicationsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by rameshkale on 18/08/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { BootApplication.class })
@WebAppConfiguration
public class BusinessProcessFunctionalTest {
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
    public void insertLesserVersionMortgageApplication() throws Exception {

        // Prepare Data
        MvcResult mvcResult = createMortgageApplication("M1", 2, "12/04/2020", "24/02/2018");
        assertTrue(mvcResult.getResponse().getStatus() == HttpStatus.OK.value());

        mvcResult = createMortgageApplication("M15", 1, "12/05/2020", "01/02/2019");
        assertTrue(mvcResult.getResponse().getStatus() == HttpStatus.OK.value());

        //Replace existing version
        mvcResult = createMortgageApplication("M15", 2, "12/04/2020", "01/02/2019");
        assertTrue(mvcResult.getResponse().getStatus() == HttpStatus.OK.value());

        //try submitting lower version record.
        mvcResult = createMortgageApplication("M11", 1, "12/01/2019", "22/01/2019");
        assertTrue(mvcResult.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        assertTrue(jsonNode.get("code").textValue().equals(BusinessException.BusinessError.ERROR_102.toString()));

    }
    @Test
    public void insertSixMonthOlderMortgageApplication() throws Exception{

        // prepare data and try submitting offer six month older.
        MvcResult mvcResult = createMortgageApplication("M11", 1, "12/01/2019", "22/01/2019");
        assertTrue(mvcResult.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        assertTrue(jsonNode.get("code").textValue().equals(BusinessException.BusinessError.ERROR_102.toString()));
    }

    private MvcResult createMortgageApplication(String id, int version, String offerDate, String createdDate) throws Exception {
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
        MvcResult mvcResult = mockMvc.perform(post).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        System.out.println("BusinessProcessFunctionalTest.createMortgageApplication ---- " + jsonNode.toPrettyString());
        return mvcResult;
    }
}