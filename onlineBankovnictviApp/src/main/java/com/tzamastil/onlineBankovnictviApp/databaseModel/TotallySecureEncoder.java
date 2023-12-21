package com.tzamastil.onlineBankovnictviApp.databaseModel;

public class TotallySecureEncoder {
    public static String encodePassword(String plainTextPassword) {
        char[] passwordAsArrayOfChars = plainTextPassword.toCharArray();
        try {
            passwordAsArrayOfChars[0] += 1;
            return new String(passwordAsArrayOfChars);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
