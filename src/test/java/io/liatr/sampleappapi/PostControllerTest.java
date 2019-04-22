package io.liatr.sampleappapi;

import org.junit.Test;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    PostController mockPost;

    @Mock
    PostRepository mockPr;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = standaloneSetup(mockPost).build();
    }

    @Test
    public void getPostsResponse() throws Exception {
        this.mockMvc.perform(get("/posts"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void postPostsFirstName() throws Exception {
        this.mockMvc.perform(post("/post")
            .param("firstName", "jon")
            .param("title", "devops")
            .param("link", "jon.devops"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void deletePostsFirstName() throws Exception {
        this.mockMvc.perform(post("/post")
            .param("firstName", "jon")
            .param("title", "devops")
            .param("link", "jon.devops"))
            .andDo(print())
            .andExpect(status().isOk());
        this.mockMvc.perform(delete("/post")
            .param("id","1"))
            .andDo(print())
            .andExpect(status().isOk());;
    }
}
