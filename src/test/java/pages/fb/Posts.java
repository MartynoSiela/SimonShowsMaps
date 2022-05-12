package pages.fb;

import org.openqa.selenium.WebElement;
import pages.Common;
import pages.Constants;
import pages.fb.objects.Post;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Posts {

    public static WebElement getPostElement(Integer positionPost) {
        while (true)
            try {
                return Common.getElement(Constants.Facebook.Posts.postByPosition(positionPost));
            } catch(Exception e) {
                Common.scrollDownByPixels(0, 5000);
                Common.waitForMillis(Duration.ofMillis(100));
            }
    }

    public static String getPostBodyText(WebElement post) {
        return post.findElement(Constants.Facebook.Posts.postBodyText).getText();
    }

    public static String getPostImageSrc(WebElement post) {

        int retries = 3;
        int retry = 0;
        while (retry <= retries) {
            try {
                return post.findElement(Constants.Facebook.Posts.postImageSrc).getAttribute("src");
            } catch (Exception e) {
                if (retry == retries) {
                    return "ImageNotFound";
                } else {
                    retry++;
                    Common.waitForMillis(Duration.ofMillis(500));
                }
            }
        }
        return "ImageNotFound";
    }

    public static void createImageFileFromSource(String path, String source) {
        try {
            BufferedImage bf;
            bf = ImageIO.read(new URL(source));
            File image = new File(path);
            ImageIO.write(bf, "png", image);
        } catch(IOException e) {
            System.out.println("Something ain't right :/" + e.getMessage());
        }
    }

    public static Boolean postByHashExistsInList(Integer postHash, List<Post> list) {
        return list.stream().anyMatch(post -> post.hash.equals(postHash));
    }

    public static List<Post> readPostsFromFileToList(String path) {
        List<Post> postsList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(String.valueOf(path));
             InputStreamReader is = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(is))
        {
            // Loop through lines in the file
            String line;
            while ((line = br.readLine()) != null) {

                // Split lines by specified delimiter
                String[] splits;
                splits = line.split(utils.Constants.lineSplitDelimiterRegex);

                // Parse splits to Post object
                Integer postHash = Integer.parseInt(splits[0]);
                String postBodyText = splits[1];
                String postImageSrc = splits[2];
                Post post = new Post(postHash, postBodyText, postImageSrc);

                // Add post to list
                postsList.add(post);
            }
        } catch (IOException e) {
            System.out.println("Something ain't right :/" + e.getMessage());
        }
        return postsList;
    }

    public static void writePostToFile(Post post, String path) {

        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            // Remove delimiter (if present) from body text
            // Remove new lines
            String d = utils.Constants.lineSplitDelimiter;
            String formattedBodyText = post.bodyText.
                    replaceAll(d, "").
                    replaceAll(System.getProperty("line.separator"), "").
                    replaceAll("\n", "");

            // Write Post object as a line with property values split by delimiter
            out.println(String.format("%d%s%s%s%s", post.hash, d, formattedBodyText, d, post.imgSrc));
        } catch (IOException e) {
            System.out.println("Something ain't right :/" + e.getMessage());
        }
    }
}
