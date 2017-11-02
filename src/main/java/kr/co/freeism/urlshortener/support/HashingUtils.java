package kr.co.freeism.urlshortener.support;

import com.google.common.hash.Hashing;
import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@UtilityClass
public class HashingUtils {
    public static String hash(String input) {
        return Hashing.murmur3_32().hashString(input, Charset.defaultCharset()).toString();
    }
}
