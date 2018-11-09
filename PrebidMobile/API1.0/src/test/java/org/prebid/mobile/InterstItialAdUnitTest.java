package org.prebid.mobile;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prebid.mobile.testutils.BaseSetup;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = BaseSetup.testSDK)
public class InterstItialAdUnitTest {
    @Test
    public void testInterstitialAdUnitCreation() throws Exception {
        InterstitialAdUnit adUnit = new InterstitialAdUnit("12345");
        assertEquals("12345", FieldUtils.readField(adUnit, "configId", true));
        assertEquals(AdType.INTERSTITIAL, FieldUtils.readField(adUnit, "adType", true));
    }

    @Test
    public void testSetUserKeyword() throws Exception {
        InterstitialAdUnit adUnit = new InterstitialAdUnit("12345");
        adUnit.setUserKeyword("key", "value");
        adUnit.setUserKeyword("key1", null);
        @SuppressWarnings("unchecked")
        ArrayList<String> keywords = (ArrayList<String>) FieldUtils.readField(adUnit, "keywords", true);
        assertEquals(2, keywords.size());
        assertEquals("key=value", keywords.get(0));
        assertEquals("key1", keywords.get(1));
        adUnit.setUserKeyword("key", "value2");
        assertEquals(3, keywords.size());
        assertEquals("key=value", keywords.get(0));
        assertEquals("key1", keywords.get(1));
        assertEquals("key=value2", keywords.get(2));
        adUnit.removeUserKeyword("key");
        assertEquals(1, keywords.size());
        assertEquals("key1", keywords.get(0));
        adUnit.removeUserKeywords();
        assertEquals(0, keywords.size());
    }

    @Test
    public void testSetUserKeywords() throws Exception {
        InterstitialAdUnit adUnit = new InterstitialAdUnit("123456");
        adUnit.setUserKeyword("key1", "value1");
        String[] values = {"value1", "value2"};
        adUnit.setUserKeywords("key2", values);
        @SuppressWarnings("unchecked")
        ArrayList<String> keywords = (ArrayList<String>) FieldUtils.readField(adUnit, "keywords", true);
        assertEquals(2, keywords.size());
        assertEquals("key2=value1", keywords.get(0));
        assertEquals("key2=value2", keywords.get(1));
        adUnit.setUserKeywords("key1", values);
        assertEquals(2, keywords.size());
        assertEquals("key1=value1", keywords.get(0));
        assertEquals("key1=value2", keywords.get(1));
    }
}