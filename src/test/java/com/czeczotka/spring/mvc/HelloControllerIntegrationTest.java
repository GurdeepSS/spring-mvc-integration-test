package com.czeczotka.spring.mvc;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:mvc-dispatcher-servlet.xml")
public class HelloControllerIntegrationTest {
    
    private final static String HELLO = "/hello";
    
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test public void 
    successfulGetRequest() {
        given().
                mockMvc(mockMvc).
        when().
                get(HELLO).
        then().
                statusCode(HttpServletResponse.SC_OK);
    }   
    
    @Test public void
    unsuccessfulPostRequest() {
        given().
                mockMvc(mockMvc).
        when().
                post(HELLO).
        then().
                statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}