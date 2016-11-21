package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public static void setCookie(HttpServletResponse resp, String key, String value) {
        Cookie sessionTokenCookie = new Cookie(key, value);
        sessionTokenCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
        resp.addCookie(sessionTokenCookie);
    }

    public static void removeCookie(HttpServletResponse resp, String key) {
        Cookie sessionTokenCookie = new Cookie(key, "null");
        sessionTokenCookie.setMaxAge(0); //Store cookie for 1 year
        resp.addCookie(sessionTokenCookie);
    }
}
