package net.haidarali.Receptacle.services;

import jakarta.transaction.Transactional;
import net.haidarali.Receptacle.exceptions.PostParsingException;
import net.haidarali.Receptacle.models.Post;
import net.haidarali.Receptacle.repositories.IngestRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PostIngestionService {

    private final Logger logger = Logger.getLogger(PostIngestionService.class.getName());
    private final IngestRepository ingestRepository;

    public PostIngestionService(IngestRepository ingestRepository) {
        this.ingestRepository = ingestRepository;
    }

    @Transactional
    public void ingestPosts(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String jsonEntry;
            List<Post> posts = new ArrayList<>();
            int batchSize = 1024;
            while ((jsonEntry = reader.readLine()) != null) {

                JSONObject jsonPost = new JSONObject(jsonEntry);
                Post post = mapJsonToPost(jsonPost);
                if (post != null) {
                    posts.add(post);
                }

                if (posts.size() >= batchSize) {
                    ingestRepository.saveAll(posts);
                    posts.clear();
                }
            }

            if (!posts.isEmpty()) {
                ingestRepository.saveAll(posts);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    private Post mapJsonToPost(JSONObject jsonPost) throws PostParsingException {
        try {
            Post post = new Post();
            post.setId(jsonPost.getString("id"));
            post.setSubreddit(jsonPost.getString("subreddit"));
            post.setSubredditId(jsonPost.getString("subreddit_id"));
            post.setTitle(jsonPost.getString("title"));
            post.setAuthor(jsonPost.getString("author"));
            post.setAuthorUrl(String.format("https://reddit.com/u/%s", post.getAuthor()));

            long timestamp = jsonPost.getLong("created_utc");

            if (timestamp == 0) {
                log(new RuntimeException("Invalid timestamp"), jsonPost);
                throw new PostParsingException(jsonPost.getString("title"), jsonPost.getString("id"));
            }

            Instant instant = Instant.ofEpochSecond(timestamp);
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            post.setDateCreated(localDateTime);

            post.setPermaLink(jsonPost.getString("permalink"));

            post.setDomain("unavailable");
            if (jsonPost.has("domain") && !jsonPost.isNull("domain")) {
                post.setDomain(jsonPost.getString("domain"));
            }

            post.setUrl("unavailable");
            if (jsonPost.has("url") && !jsonPost.isNull("url")) {
                post.setUrl(jsonPost.getString("url"));
            }

            if (post.getDomain().equals("unavailable") && post.getUrl().equals("unavailable")) {
                return null;
            }

            post.setSelf(false);
            if (jsonPost.has("is_self")) {
                post.setSelf(jsonPost.getBoolean("is_self"));
            }

            post.setVideo(false);
            if (jsonPost.has("is_video")) {
                post.setVideo(jsonPost.getBoolean("is_video"));
            }

            post.setMediaOnly(false);
            if (jsonPost.has("media_only")) {
                post.setMediaOnly(jsonPost.getBoolean("media_only"));
            }

            post.setRedditMediaDomain(false);
            if (jsonPost.has("is_media_reddit_domain")) {
                post.setRedditMediaDomain(jsonPost.getBoolean("is_media_reddit_domain"));
            }

            post.setOriginalContent(false);
            if (jsonPost.has("is_original_content")) {
                post.setOriginalContent(jsonPost.getBoolean("is_original_content"));
            }

            post.setComments(jsonPost.getLong("num_comments"));
            post.setScore(jsonPost.getLong("score"));
            post.setSeen(false);

            return post;
        } catch (Exception e) {
            log(e, jsonPost);
            throw new PostParsingException(jsonPost.getString("title"), jsonPost.getString("id"));
        }
    }

    private void log(Exception e, JSONObject jsonPost) {
        logger.log(Level.SEVERE, String.format("Error processing post with ID: %s and Title: %s",
                jsonPost.optString("id"),
                jsonPost.optString("title")), e);
    }
}
