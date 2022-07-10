package ua.goit.HW13;

import java.util.Objects;

public class Comment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;

    public Integer getPostId() {
        return postId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(postId, comment.postId) && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
