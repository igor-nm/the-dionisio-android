package com.the.dionisio.apk.client.Util.View;

import com.the.dionisio.apk.client.util.view.TextHelp;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jonathan on 6/5/17.
 */

public class TextHelpTest {

    TextHelp textHelp = new TextHelp();


    @Test
    public void aaaTest() {
        assertEquals("aaa", textHelp.format("aaa", "aaa"));
    }

    @Test
    public void aaATest() {
        assertEquals("aaA", textHelp.format("aaa", "aaA"));
    }

    @Test
    public void aAaTest() {
        assertEquals("aAa", textHelp.format("aaa", "aAa"));
    }

    @Test
    public void AAATest() {
        assertEquals("AAA", textHelp.format("aaa", "AAA"));
    }

    @Test
    public void AAaTest() {
        assertEquals("AAa", textHelp.format("aaa", "AAa"));
    }

    @Test
    public void AaaTest() {
        assertEquals("Aaa", textHelp.format("aaa", "Aaa"));
    }

    @Test
    public void AaATest() {
        assertEquals("AaA", textHelp.format("aaa", "AaA"));
    }


    @Test
    public void aaaSentenceTest() {
        assertEquals("text text text", textHelp.format("Text TEXT text", "aaa"));
    }

    @Test
    public void aaASentenceTest() {
        assertEquals("texT texT texT", textHelp.format("Text TEXT text", "aaA"));
    }

    @Test
    public void aAaSentenceTest() {
        assertEquals("tExT tExT tExT", textHelp.format("Text TEXT text", "aAa"));
    }

    @Test
    public void AAASentenceTest() {
        assertEquals("TEXT TEXT TEXT", textHelp.format("Text TEXT text", "AAA"));
    }

    @Test
    public void AaASentenceTest() {
        assertEquals("TeXt TeXt TeXt", textHelp.format("Text TEXT text", "AaA"));
    }

    @Test
    public void AaaSentenceTest() {
        assertEquals("Text Text Text", textHelp.format("Text TEXT text", "Aaa"));
    }


    @Test
    public void textNullErrorTest() {
        assertEquals("text error", textHelp.format(null, "AaA"));
    }

    @Test
    public void textEmptyErrorTest() {
        assertEquals("text error", textHelp.format("", "AaA"));
    }

    @Test
    public void patternNullErrorTest() {
        assertEquals("pattern error", textHelp.format("aaa", null));
    }

    @Test
    public void patternEmptyErrorTest() {
        assertEquals("pattern error", textHelp.format("aaa", ""));
    }

    @Test
    public void patternLengthErrorTest() {
        assertEquals("pattern error", textHelp.format("aaa", "aA"));
    }


}