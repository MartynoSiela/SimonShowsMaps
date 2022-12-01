package pages.fb;

import pages.Common;
import pages.Constants;

public class Login {

    public static void open() {
        Common.openUrl(Constants.Facebook.Login.url);
    }

    public static void closeCookiesModal() {
        try {
            Common.waitForElementToBeVisible(Constants.Facebook.Login.buttonCookiesEssential);
            Common.clickElement(Constants.Facebook.Login.buttonCookiesEssential);
        } catch (Exception e) {
            System.out.println("Cookies modal not found, trying to continue...");
        }
    }

    public static void inputEmail(String email) {
        Common.sendKeys(Constants.Facebook.Login.inputEmail, email);
        System.out.println("Email entered");
    }

    public static void inputPassword(String password) {
        Common.sendKeys(Constants.Facebook.Login.inputPassword, password);
        System.out.println("Password entered");
    }

    public static void clickLogin() {
        Common.clickElement(Constants.Facebook.Login.buttonLogin);
        System.out.println("Login clicked");
    }

    public static void waitForPageLoad() {
        Common.waitForElementToBeVisible(Constants.Facebook.Login.textWelcomeHeading);
//        try {
//            Common.waitForElementToBeVisible(Constants.Facebook.Login.textWelcomeHeading);
//        } catch (Exception e) {
//            // TODO fix an issue where the build is stuck on github actions if password is incorrect
//            System.out.println("User name not found, trying to continue...");
//        }
    }
}
