package in.gilsondev.blog.builder;

import in.gilsondev.blog.domain.Author;

public class AuthorBuilder {
    private Author author;

    public AuthorBuilder() {
        this.author = new Author();
        this.author.setId(1L);
        this.author.setName("Author Test");
        this.author.setEmail("author.test@mail.com");
    }

    public AuthorBuilder withId(Long id) {
        this.author.setId(id);
        return this;
    }

    public AuthorBuilder withName(String name) {
        this.author.setName(name);
        return this;
    }

    public AuthorBuilder withEmail(String email) {
        this.author.setEmail(email);
        return this;
    }

    public Author build() {
        return this.author;
    }
}
