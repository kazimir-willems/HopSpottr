package hopspottr.luca.com.hopspottr.util;

/**
 * Created by Arthur on 8/14/2017.
 */

public class URLManager {
    private static String SERVER_IP = "http://hpspttr.co/development/hopspottr/";

    public static String getSingUpURL() {
        return SERVER_IP + "api_signup.php";
    }
}
