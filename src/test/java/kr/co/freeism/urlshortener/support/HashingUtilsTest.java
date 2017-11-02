package kr.co.freeism.urlshortener.support;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
public class HashingUtilsTest {
    private static final String INPUT = "http://localhost";

    @Test
    public void hash() {
        String result = HashingUtils.hash(INPUT);

        assertThat(result, not(nullValue()));
    }

    @Test
    public void hashRepeatedly() {
        String result = HashingUtils.hash(INPUT);

        assertThat(result, is(HashingUtils.hash(INPUT)));
    }
}