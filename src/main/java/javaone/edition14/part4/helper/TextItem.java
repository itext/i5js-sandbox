/*
 * This code sample was written in the context of
 *
 * JavaOne 2014: PDF is dead. Long live PDF... and Java!
 * Tutorial Session by Bruno Lowagie and Raf Hens
 *
 * Copyright 2014, iText Group NV
 */
package javaone.edition14.part4.helper;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import java.util.HashMap;

/**
 * Subclass of the MyItem class that is used to store the coordinates
 * of a text snippet.
 */
public class TextItem extends MyItem {
    /** Color to be used to mark text that is part of an artifact. */
    static final BaseColor artifactColor = BaseColor.GRAY;
    /** Colors to be used to mark text based on the font that is used. */
    static final HashMap<TextStyle, BaseColor> textStyles = new HashMap<TextStyle, BaseColor>();
    static {
        textStyles.put(new TextStyle("FranklinGothic", 10.5f), BaseColor.ORANGE);
        textStyles.put(new TextStyle("FranklinGothic", 8f), BaseColor.GREEN);
        textStyles.put(new TextStyle("NewBaskerville", 10f), BaseColor.BLUE);
        textStyles.put(new TextStyle("Courier", 9.5f), BaseColor.BLUE);
    }
    /** Position of the baseline of the text. */
    float baseline;

    /**
     * Creates a TextItem based on a TextRenderInfo object.
     * @param textRenderInfo    the TextRenderInfo object
     * @param top               the Y coordinate of the top margin
     */
    public TextItem(TextRenderInfo textRenderInfo, float top) {
        super(getRectangle(textRenderInfo), getColor(textRenderInfo, top));
        baseline = textRenderInfo.getBaseline().getStartPoint().get(1);
    }

    /**
     * Getter for the base line
     * @return the Y coordinate of the text snippet
     */
    public float getBaseline() {
        return baseline;
    }

    /**
     * Determines the color that will mark the text snippet based on the
     * position of the snippet (in case it's an artifact) or it's style
     * (font name and size).
     * @param textRenderInfo    the TextRenderInfo object
     * @param top               the Y position of the top margin
     * @return a color that will be used to mark the text snippet
     */
    static BaseColor getColor(TextRenderInfo textRenderInfo, float top) {
        if (textRenderInfo.getBaseline().getStartPoint().get(1) > top)
                return artifactColor;
        TextStyle ts = new TextStyle(textRenderInfo);
        return textStyles.get(ts);
    }
}
