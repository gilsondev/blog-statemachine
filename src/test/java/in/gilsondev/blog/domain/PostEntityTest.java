package in.gilsondev.blog.domain;

import in.gilsondev.blog.builder.AuthorBuilder;
import in.gilsondev.blog.builder.PostBuilder;
import in.gilsondev.blog.config.ValidatorConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(LocalDateTime.class)
@ContextConfiguration(classes = {ValidatorConfiguration.class})
public class PostEntityTest {
    @Autowired
    private Validator validator;

    @Test
    public void shouldHaveLombokProperties() {
        // Criando mock para a data e hora atual usando Powermock
        mockStatic(LocalDateTime.class);
        LocalDateTime now = LocalDateTime.now();
        when(LocalDateTime.now()).thenReturn(now);

        Post post = createPost();

        assertThat(post.getId()).isEqualTo(1L);
        assertThat(post.getAuthor()).isNotNull();
        assertThat(post.getTitle()).isEqualTo("Post Title");
        assertThat(post.getSlug()).isEqualTo("post-title");
        assertThat(post.getBody()).isEqualTo("Body");
        assertThat(post.getStatus()).isEqualTo(PostStatusEnum.DRAFT);
        assertThat(post.getCreated()).isNotNull();
        assertThat(post.getPublished()).isEqualTo(now);
        assertThat(post.getRemoved()).isEqualTo(now);
    }

    @Test
    public void shouldBeEntity() throws NoSuchFieldException {
        Post post = new Post();

        assertThat(post.getClass().isAnnotationPresent(Entity.class)).isTrue();
        assertThat(post.getClass().getDeclaredField("id").isAnnotationPresent(Id.class)).isTrue();
    }

    @Test
    public void shouldNotAcceptTitleNull() {
        Post post = createPost();
        post.setTitle(null);

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptTitleEmpty() {
        Post post = createPost();
        post.setTitle("");

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptSlugNull() {
        Post post = createPost();
        post.setSlug(null);

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptSlugEmpty() {
        Post post = createPost();
        post.setSlug("");

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptBodyNull() {
        Post post = createPost();
        post.setBody(null);

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    public void shouldNotAcceptBodyEmpty() {
        Post post = createPost();
        post.setBody("");

        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.isEmpty()).isFalse();
    }

    private Post createPost() {

        Post post = new PostBuilder()
                .withId(1L)
                .withAuthor(new AuthorBuilder().build())
                .withTitle("Post Title")
                .withSlug("post-title")
                .withBody("Body")
                .withPublished(LocalDateTime.now())
                .withRemoved(LocalDateTime.now())
                .build();

        return post;
    }
}
