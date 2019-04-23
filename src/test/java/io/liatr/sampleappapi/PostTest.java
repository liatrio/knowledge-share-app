package io.liatr.sampleappapi;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
public class PostTest {

    Long id = 12345678910L;
    String name = "jon";
    String title = "devops";
    String link = "devops.com";

    @Test
    public void getIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        assertEquals(id, test);
    }

    @Test
    public void setIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        assertEquals(id, test);
    }

    @Test
    public void setNameTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        String test = hc.getFirstName();
        assertEquals(name, test);
    }

    @Test
    public void getNameTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        String test = hc.getFirstName();
        assertEquals(name, test);
    }

    @Test
    public void setTitleTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        String test = hc.getTitle();
        assertEquals(title, test);
    }

    @Test
    public void getTitleTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        String test = hc.getTitle();
        assertEquals(title, test);
    }

    @Test
    public void setLinkTest() throws Exception {
        Post hc = new Post();
        hc.setLink(link);
        String test = hc.getLink();
        assertEquals(link, test);
    }

    @Test
    public void getLinkTest() throws Exception {
        Post hc = new Post();
        hc.setLink(link);
        String test = hc.getLink();
        assertEquals(link, test);
    }
}
