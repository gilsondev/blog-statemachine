package in.gilsondev.blog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    private Long id;

    @ManyToOne
    private Author author;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String slug;

    @NotNull
    @NotEmpty
    private String body;

    private PostStatusEnum status = PostStatusEnum.DRAFT;

    @Setter(AccessLevel.NONE)
    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime published;

    private LocalDateTime removed;
}
