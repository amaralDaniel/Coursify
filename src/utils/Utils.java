package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class Utils {

    public static String getCookie(HttpServletRequest req, String key) {
        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();

        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if(cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
