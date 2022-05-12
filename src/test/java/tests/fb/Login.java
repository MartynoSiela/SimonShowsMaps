package tests.fb;

import org.testng.annotations.BeforeMethod;
import tests.BaseTest;

public class Login extends BaseTest {

    @BeforeMethod
    public void login() {
        pages.fb.Login.open();
        pages.fb.Login.closeCookiesModal();
        pages.fb.Login.inputEmail("shakatronas@gmail.com");
        pages.fb.Login.inputPassword("nqm_HQY.nft7juc.mku");
        pages.fb.Login.clickLogin();
        pages.fb.Login.waitForPageLoad();
    }
}
