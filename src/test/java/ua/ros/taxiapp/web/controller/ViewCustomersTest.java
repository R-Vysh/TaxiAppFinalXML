
package ua.ros.taxiapp.web.controller;



import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.any;

import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import ua.ros.taxiapp.services.CustomerService;

public class ViewCustomersTest {

  MockMvc mockMvc;


  @Mock
  CustomerService customerService;
  
  Integer id = 1;
  
//  @Before
//  public void setup() {
//    MockitoAnnotations.initMocks(this);
//
//    this.mockMvc = standaloneSetup(controller)
//            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
//  }

  
//  @Test
//  public void thatViewCustomerUsesHttpOK() throws Exception {
//
//    when(customerService.findCustomerById(any(Integer.class))).thenReturn(
//            RestDataFixtures.standardCustomer());
//
//    this.mockMvc.perform(
//            get("/findCustomer/{id}", id)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
//  }

//  @Test
//  public void thatViewCustomerRendersCorrectly() throws Exception {
//
//    when(customerService.findCustomerById(any(Integer.class))).thenReturn(
//            RestDataFixtures.standardCustomer());
//
//    this.mockMvc.perform(
//            get("/findCustomer/{id}", id)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
//            .andExpect(jsonPath("$.key").value(key.toString()));
//  }
}
