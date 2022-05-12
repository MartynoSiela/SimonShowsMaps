package pages.fb;

import pages.Common;
import pages.Constants;

public class Pages {

    public static void open(String name) {
        Common.openUrl(Constants.Facebook.Login.url + name);
    }
}
