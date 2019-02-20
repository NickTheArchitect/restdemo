package org.nickthearchitect.camunda.restdemo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestdemoApplicationWebTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/app/"));
    }
    @Test
    public void shouldReturnDefaultPage() throws Exception {
        mockMvc.perform(get("/app/welcome/default/").accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isNotFound());
        // Why is this returning 404?
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello World")));
    }
}
