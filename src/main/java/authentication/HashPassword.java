package authentication;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HashPassword {
    public static String getHashPassword(String username, String password){
        try{
            SecretKeySpec key = new SecretKeySpec((username).getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
            byte[] bytes = mac.doFinal(password.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for(byte symbol: bytes){
                stringBuilder.append(Integer.toString((symbol & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString().toUpperCase();
        }
        catch (Exception exception){
            return null;
        }
    }
}