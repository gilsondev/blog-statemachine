package in.gilsondev.blog.builder;

import in.gilsondev.blog.domain.Author;
import in.gilsondev.blog.domain.Post;
import in.gilsondev.blog.domain.PostStatusEnum;

import java.time.LocalDateTime;

public class PostBuilder {
    private Post post;

    public PostBuilder() {
        this.post = new Post();
        this.post.setId(1L);
        this.post.setTitle("Post Title");
        this.post.setSlug("post-title");
        this.post.setBody("Post Content");
        this.post.setPublished(LocalDateTime.now());
        this.post.setRemoved(LocalDateTime.now());
    }

    public PostBuilder withId(Long id) {
        this.post.setId(id);
        return this;
    }

    public PostBuilder withTitle(String title) {
        this.post.setTitle(title);
        return this;
    }

    public PostBuilder withSlug(String slug) {
        this.post.setSlug(slug);
        return this;
    }

    public PostBuilder withBody(String body) {
        this.post.setBody(body);
        return this;
    }

    public PostBuilder withPublished(LocalDateTime published) {
        this.post.setPublished(published);
        return this;
    }

    public PostBuilder withRemoved(LocalDateTime removed) {
        this.post.setRemoved(removed);
        return this;
    }

    public PostBuilder withStatus(PostStatusEnum postStatusEnum) {
        this.post.setStatus(postStatusEnum);
        return this;
    }

    public Post build() {
        return this.post;
    }

    public PostBuilder withAuthor(Author author) {
        this.post.setAuthor(author);
        return this;
    }
}
