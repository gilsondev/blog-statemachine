package in.gilsondev.blog.domain;

import in.gilsondev.blog.test.EntityTestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogEntityTest extends EntityTestCase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private Validator validator;

    @Test
    public void shouldHaveLombokProperties() {
        Blog blog = createBlog();

        assertThat(blog.getId()).isEqualTo(1L);
        assertThat(blog.getName()).isEqualTo("Blog Test");
    }

    @Test
    public void shouldBeEntity() throws NoSuchFieldException {
        Blog blog = new Blog();

        assertThat(blog.getClass().isAnnotationPresent(Entity.class)).isTrue();
        assertThat(blog.getClass().getDeclaredField("id").isAnnotationPresent(Id.class)).isTrue();
    }

    @Test
    public void shouldValidateNameEmpty() {
        Blog blog = createBlog();
        blog.setName(null);

        Set<ConstraintViolation<Blog>> violations = this.validator.validate(blog);

        assertThat(violations.isEmpty()).isFalse();
    }

    private Blog createBlog() {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setName("Blog Test");
        return blog;
    }
}
