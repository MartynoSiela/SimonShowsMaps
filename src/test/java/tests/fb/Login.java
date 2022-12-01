package tests.fb;

import org.testng.annotations.BeforeMethod;
import tests.BaseTest;

public class Login extends BaseTest {

    @BeforeMethod
    public void login() {
        pages.fb.Login.open();
        pages.fb.Login.closeCookiesModal();
        pages.fb.Login.inputEmail("shakatronas@gmail.com");
        pages.fb.Login.inputPassword("apY6f3A@CgfXj8*nUrcTq4DXN");
        pages.fb.Login.clickLogin();
        pages.fb.Login.waitForPageLoad();
    }
}
