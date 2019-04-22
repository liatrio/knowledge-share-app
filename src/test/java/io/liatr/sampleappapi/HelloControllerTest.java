package io.liatr.sampleappapi;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DemoApplication demoApplication;

    @Test
    public void getHelloResponse() throws Exception {
        this.mockMvc.perform(get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World"));
    }

}
