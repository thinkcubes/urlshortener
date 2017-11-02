package kr.co.freeism.urlshortener.service;

import kr.co.freeism.urlshortener.repository.UrlMap;
import kr.co.freeism.urlshortener.repository.UrlMapRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlMapServiceTest {
    private static final String URL = "http://localhost";
    private static final String HASH_VALUE = "296beed8";

    @InjectMocks
    private UrlMapService dut;
    @Mock
    private UrlMapRepository urlMapRepository;

    @Test
    public void getHashValue() {
        givenActionUrlMapSaved();

        String result = dut.getHashValue(URL);

        verifyCallUrlMapSaved();
        verifyHashValue(result);
    }

    @Test
    public void getUrl() {
        givenUrlMap();

        String result = dut.getUrl(HASH_VALUE);

        verifyUrl(result);
    }

    @Test(expected = RuntimeException.class)
    public void getUrlIfNullUrlMap() {
        givenNullUrlMap();

        dut.getUrl(HASH_VALUE);
    }

    private void givenNullUrlMap() {
        when(urlMapRepository.findByHashValue(HASH_VALUE)).thenReturn(null);
    }

    private void givenUrlMap() {
        when(urlMapRepository.findByHashValue(HASH_VALUE)).thenReturn(mockUrlMap());
    }

    private void givenActionUrlMapSaved() {
        doNothing().when(urlMapRepository).saveIfNotExisted(URL, HASH_VALUE);
    }

    private void verifyCallUrlMapSaved() {
        verify(urlMapRepository, times(1)).saveIfNotExisted(URL, HASH_VALUE);
    }

    private void verifyHashValue(String result) {
        assertThat(result, is(HASH_VALUE));
    }

    private void verifyUrl(String result) {
        assertThat(result, is(URL));
    }

    private UrlMap mockUrlMap() {
        return UrlMap.builder()
            .url(URL)
            .hashValue(HASH_VALUE)
            .build();
    }
}