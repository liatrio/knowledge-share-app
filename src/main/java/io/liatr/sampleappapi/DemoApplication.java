package io.liatr.sampleappapi;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = {
        "io.liatr.sampleappapi"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    ApplicationRunner init(PostRepository repository) {
        return args -> {
            Stream.of("story1", "story2", "story3").forEach(story -> {
                Post post = new Post();
                post.setFirstName("Ben");
                post.setTitle(story);
                post.setLink("http://some-link.com");
                repository.save(post);
            });

            repository.findAll().forEach(System.out::println);
        };

    }
}