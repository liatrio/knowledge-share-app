package io.liatr.sampleappapi;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class PostTest {

    Long id = 12345678910L;
    String name = "jon";
    String title = "devops";
    String link = "devops.com";

    @Test
    public void toStringTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        assertEquals("Post(id=null, firstName=jon, title=null, link=null)", hc.toString());
    }

    @Test
    public void toStringTitleTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        hc.setFirstName(name);
        assertEquals("Post(id=null, firstName=jon, title=devops, link=null)", hc.toString());
    }

    @Test
    public void getClassTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        hc.getTitle().hashCode();
        
        assertEquals("class io.liatr.sampleappapi.Post", hc.getClass().toString());
    }



    @Test
    public void equalsTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        Post hc2 = new Post();
        hc2.setFirstName(name);
        assertTrue(hc.equals(hc2));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        Post hc2 = new Post();
        hc2.setFirstName(name);
        assertEquals(hc.hashCode(), hc2.hashCode());
    }

    @Test
    public void getIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        assertEquals(id, test);
    }

    @Test
    public void getBadIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        Long badVal = 12345678912L;
        assertNotEquals(badVal, test);
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
