package net.haidarali.Receptacle.exceptions;

public class PostParsingException extends RuntimeException {

    private final String postId;
    private final String postTitle;

    public PostParsingException(String postId, String postTitle) {
        super("Error parsing post");
        this.postId = postId;
        this.postTitle = postTitle;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

}
