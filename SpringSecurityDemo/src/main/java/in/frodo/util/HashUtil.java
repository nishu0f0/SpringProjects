package in.frodo.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/*import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;*/

public class HashUtil {

  private static SecureRandom random = new SecureRandom();

  /*public static String decodeBase64(String encodedString) {
    String decodedString = StringUtils.newStringUtf8(Base64.decodeBase64(encodedString));
    return decodedString;
  }

  public static String encodeBase64(String inputString) {
    String encodedString = Base64.encodeBase64String(StringUtils.getBytesUtf8(inputString));
    return encodedString;
  }

  public static String generateSHA256(String passwordString) {
    String hashSHA256 = DigestUtils.sha256Hex(passwordString);
    return hashSHA256;
  }

  public static String generateMD5(String msg) {
    String hashMD5 = DigestUtils.md5Hex(msg);
    return hashMD5;
  }*/

  public static String generateRandomString(int length) {
    byte[] byteArray = new byte[length];
    random.setSeed(Double.toString(Math.random()).getBytes());
    String randomString = new BigInteger(1000, random).toString(32);
    return randomString.substring(0, length);
  }

}
