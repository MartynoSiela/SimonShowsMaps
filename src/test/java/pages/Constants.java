package pages;

import org.openqa.selenium.By;

public class Constants {

    public static class Facebook {

        public static class Login {
            public static String url = "https://www.facebook.com/";
            public static By buttonCookiesEssential = By.xpath("//*[@data-testid='cookie-policy-manage-dialog-accept-button']");
            public static By inputEmail = By.xpath("//*[@data-testid='royal_email']");
            public static By inputPassword = By.xpath("//*[@data-testid='royal_pass']");
            public static By buttonLogin = By.xpath("//*[@data-testid='royal_login_button']");
            public static By textWelcomeHeading = By.xpath("//*[contains(text(), 'Welcome to Facebook, Misterino')]");
        }

        public static class Posts {
            public static By postByPosition(Integer position) { return By.xpath(String.format("//*[@aria-posinset=%d]", position)); }
            public static By postBodyText = By.xpath("./div/div/div/div/div/div/div/div[3]/div[1]//span");
            public static By postImageSrc = By.xpath("./div/div/div/div/div/div/div/div[3]/div[2]//img");
        }
    }
}
