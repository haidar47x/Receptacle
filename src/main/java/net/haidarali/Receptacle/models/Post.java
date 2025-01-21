package net.haidarali.Receptacle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public final class Post {

    @Id
    private String permaLink;

    private String id;
    private String subreddit;
    private String subredditId;
    private String title;
    private String content;
    private String author;
    private String authorUrl;
    private String domain;
    private String url;
    private LocalDateTime dateCreated;
    private long comments;
    private long score;
    private boolean isSelf;
    private boolean isVideo;
    private boolean isMediaOnly;
    private boolean isRedditMediaDomain;
    private boolean isOriginalContent;
    private boolean isSeen;

    public Post() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
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

    public String getUser() {
        return author;
    }

    public void setUser(String author) {
        this.author = author;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public String getPermaLink() {
        return permaLink;
    }

    public void setPermaLink(String permaLink) {
        this.permaLink = permaLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public boolean isRedditMediaDomain() {
        return isRedditMediaDomain;
    }

    public void setRedditMediaDomain(boolean redditMediaDomain) {
        isRedditMediaDomain = redditMediaDomain;
    }

    public boolean isMediaOnly() {
        return isMediaOnly;
    }

    public void setMediaOnly(boolean mediaOnly) {
        this.isMediaOnly = mediaOnly;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public boolean isOriginalContent() {
        return isOriginalContent;
    }

    public void setOriginalContent(boolean originalContent) {
        isOriginalContent = originalContent;
    }
}
