package in.gilsondev.blog.domain;

import in.gilsondev.blog.builder.AuthorBuilder;
import in.gilsondev.blog.builder.PostBuilder;
import in.gilsondev.blog.test.EntityTestCase;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorEntityTest extends EntityTestCase {

    @Test
    public void shouldHaveLombokProperties() {
        Author author = createAuthor();

        assertThat(author.getId()).isEqualTo(1L);
        assertThat(author.getPosts()).isNotNull();
        assertThat(author.getName()).isEqualTo("Author Test");
        assertThat(author.getEmail()).isEqualTo("author@mail.com");
    }

    @Test
    public void shouldBeEntity() throws NoSuchFieldException {
        Author author = new Author();
        assertThat(author.getClass().isAnnotationPresent(Entity.class)).isTrue();
        assertThat(author.getClass().getDeclaredField("id").isAnnotationPresent(Id.class)).isTrue();
    }

    @Test
    public void shouldNotAcceptNameNull() {
        Author author = createAuthor();
        author.setName(null);

        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptEmailNull() {
        Author author = createAuthor();
        author.setEmail(null);

        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptInvalidEmail() {
        Author author = createAuthor();
        author.setEmail("mail$test.com");

        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        assertThat(violations.isEmpty()).isFalse();
    }

    private Author createAuthor() {
        Post post = new PostBuilder().withAuthor(null).build();
        List<Post> posts = Collections.singletonList(post);

        Author author = new AuthorBuilder()
                .withId(1L)
                .withName("Author Test")
                .withEmail("author@mail.com")
                .build();

        author.setPosts(posts);

        return author;
    }
}
