package tests.fb;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.fb.objects.Post;

import java.io.File;
import java.util.List;

public class Posts extends Login{

    @BeforeMethod
    public void openPage() {
        pages.fb.Pages.open("SimonGerman600");
    }

    @Test
    public void getNewPosts() {

        // Set file paths, out/ directory needs to be present
        String pathHistoricalPostsFile = "out/postsHistorical.txt";
        String pathNewPostsFile = "out/postsNew.txt";

        // Read previously scanned posts
        List<Post> oldPosts = pages.fb.Posts.readPostsFromFileToList(pathHistoricalPostsFile);

        // Delete new posts file for a new one to be created
        FileUtils.deleteQuietly(new File(pathNewPostsFile));

        for (int i = 1; i < 6; i++) {

            // Get each post element
            WebElement postElement = pages.fb.Posts.getPostElement(i);

            // Get values from the post element
            String postBodyText = pages.fb.Posts.getPostBodyText(postElement);
            Integer postHashCode = postBodyText.hashCode();
            String postImageSrc = pages.fb.Posts.getPostImageSrc(postElement);

            // Create post object
            Post post = new Post(postHashCode, postBodyText, postImageSrc);

            // Check if post was previously scanned
            // If this is a fresh post:
            if (!pages.fb.Posts.postByHashExistsInList(postHashCode, oldPosts)) {

                // Save post to both files (new and historical posts)
                pages.fb.Posts.writePostToFile(post, pathNewPostsFile);
                pages.fb.Posts.writePostToFile(post, pathHistoricalPostsFile);

                // Create path for post image, directory out/images/ must be present
                String imagePath = String.format("out/images/img%s.png", post.hash);

                // Create image file
                pages.fb.Posts.createImageFileFromSource(imagePath, post.imgSrc);

            }
        }
    }
}
