package io.liatr.sampleappapi;

import org.junit.Test;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.MockitoAnnotations.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    HelloController mockHello;

    @MockBean
    DemoApplication demoApplication;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = standaloneSetup(mockHello).build();
    }

    @Test
    public void getHelloResponse() throws Exception {
        this.mockMvc.perform(get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World!!!!"));
    }

    @Test
    public void helloTest() throws Exception {
        HelloController hc = new HelloController();
        String test = hc.hello();
        assertEquals("Hello World!!!!", test);
    }

}
