package io.liatr.sampleappapi;

import lombok.*;


import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
public class Post {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String firstName;
    private @NonNull String title;
    private @NonNull String link;

//    public Post(@NonNull String firstName, @NonNull String title, @NonNull String link) {
//        this.firstName = firstName;
//        this.title = title;
//        this.link = link;
//    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
