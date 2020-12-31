package todoList.util;
import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
    public static String getHash(String originalString) {
        return DigestUtils.sha256Hex(originalString);
    }
}
