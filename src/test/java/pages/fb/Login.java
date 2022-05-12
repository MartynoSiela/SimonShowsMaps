package pages.fb;

import pages.Common;
import pages.Constants;

public class Login {

    public static void open() {
        Common.openUrl(Constants.Facebook.Login.url);
    }

    public static void closeCookiesModal() {
        Common.clickElement(Constants.Facebook.Login.buttonCookiesEssential);
    }

    public static void inputEmail(String email) {
        Common.sendKeys(Constants.Facebook.Login.inputEmail, email);
    }

    public static void inputPassword(String password) {
        Common.sendKeys(Constants.Facebook.Login.inputPassword, password);
    }

    public static void clickLogin() {
        Common.clickElement(Constants.Facebook.Login.buttonLogin);
    }

    public static void waitForPageLoad() {
        Common.waitForElementToBeVisible(Constants.Facebook.Login.textWelcomeHeading);
    }
}
