package com.rafdev.prova.blog.api.entity;

import com.github.slugify.Slugify;
import com.rafdev.prova.blog.api.enums.EPostStatus;
import com.rafdev.prova.blog.api.listener.PostListener;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(PostListener.class)
@Table(name = "tab_posts")
public class Post extends AbstractBaseEntity {

    @NotBlank(message = "The title cannot be blank")
    @Size.List ({
            @Size(min = 3, message = "The title must be at least {min} characters"),
            @Size(max = 100, message = "The title must be less than {max} characters")
    })
    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    @NotBlank(message = "The content cannot be blank")
    @Size.List ({
            @Size(min = 10, message = "The content must be at least {min} characters")
    })
    @Column
    private String content;

    @URL(message = "Invalid image url")
    @Column
    private String imageUrl;

    @Column
    private LocalDateTime publishedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EPostStatus status = EPostStatus.DRAFT;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Category.class, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tab_post_tags",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(targetEntity = Comment.class, mappedBy = "post", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public Post() {
    }

    public Post(String title, String content, String imageUrl, LocalDateTime publishedAt, User user,
                Category category, Set<Tag> tags) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
        this.user = user;
        this.category = category;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public EPostStatus getStatus() {
        return status;
    }

    public void setStatus(EPostStatus status) {
        this.status = status;
    }

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(title);
    }

    public Boolean isPublished() {
        return status == EPostStatus.PUBLISHED && publishedAt != null;
    }

    public Boolean isArchived() {
        return status == EPostStatus.ARCHIVED && publishedAt == null;
    }

    public Boolean isDraft() {
        return status == EPostStatus.DRAFT && publishedAt == null;
    }
}
