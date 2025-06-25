package validator;

import util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MemberValidator {

    // 이메일
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    // 비밀번호
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

    public static List<String> validateMember(String email,String password){

        List<String> error = new ArrayList<>();

        /* 이메일 형식이 올바른지 */
        if(email == null || email.trim().isEmpty()){
            error.add("이메일을 입력해주세요.");
        }else if(!EMAIL_PATTERN.matcher(email).matches()){
            error.add("@ 포함한 올바른 이메일형식을 입력해주세요");
        }else if(SecurityUtil.containSQLInjection(email)){
            error.add("올바른 이메일 형식이 아닙니다.");
        }

        /* 비밀번호 형식이 올바른지 */
        if(password == null || password.trim().isEmpty()){
            error.add("비밀번호를 입력해주세요.");
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            error.add("비밀번호 형식은 영문자 1개 이상, "+
                    "숫자 1개 이상, " +
                    "특수문자 1개 이상, " +
                    "8자 이상 입니다");
        }else if(SecurityUtil.containSQLInjection(password)){
            error.add("올바른 비밀번호 형식이 아닙니다.");
        }
        return error;
    }
}