package pages.fb.objects;

public class Post {

    public Integer hash;
    public String bodyText;
    public String imgSrc;

    public Post(Integer hash, String text, String imgSrc) {
        this.hash = hash;
        this.bodyText = text;
        this.imgSrc = imgSrc;
    }
}

