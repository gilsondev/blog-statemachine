package in.gilsondev.blog.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    private Long id;

    @OneToMany
    private List<Post> posts;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;
}
