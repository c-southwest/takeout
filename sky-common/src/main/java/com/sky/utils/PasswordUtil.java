package com.sky.utils;

import com.sky.constant.PasswordConstant;
import org.springframework.util.DigestUtils;

public class PasswordUtil {
    public static String myPasswordSalt(String username, String password){
        String username_tmp1 = username.replace('a', 'g');
        String username_tmp2 = username_tmp1.replace('k', 'j');
        String username_salt = new StringBuilder(username_tmp2).reverse().toString();
        return DigestUtils.md5DigestAsHex((username_salt + password).getBytes());
    }
}
