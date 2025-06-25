package util;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class SecurityUtil {
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");
    private static final Pattern SCRIPT_PATTERN = Pattern.compile("(?i)<script[^>]*>.*?</scirpt>");
    /* SQL INJECTION 방지 */
    private static final Pattern SQL_INJECTION_PATTERN =
            Pattern.compile("(?i).*(union|select|insert|update|delete|drop|alter|exec|execute)");

    public static String escapeHTML(String input){
        if(input == null) return null;

        /* XSS 방지*/
        return input.replace("&", "&amp;")
                .replace("<","&lt;")
                .replace(">","&gt;")
                .replace("\"","&quot;")
                .replace("'","&#x27;")
                .replace("/","&#x2F;");
    }

    /* script 삭제 */
    public static String removeScript(String input){
        if(input == null) return null;
        return SCRIPT_PATTERN.matcher(input).replaceAll("");
    }

    /* SQL INJECTION 확인*/
    public static boolean containSQLInjection(String input){
        if (input == null) return false;
        return  SQL_INJECTION_PATTERN.matcher(input).matches();
    }

    /* 토큰 생성 */
    public static String generateToken(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }

    /* Script, HTML 방지 */
    public static String sanitizeInput(String input){
        if(input == null) return null;

        input = removeScript(input);

        input = escapeHTML(input);

        input = input.trim();

        return input;
    }
}