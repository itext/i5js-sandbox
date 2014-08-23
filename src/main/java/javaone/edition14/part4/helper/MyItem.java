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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.Matrix;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

/**
 * Abstract class that is used as a superclass for specific item classes
 * such as TextItem, ImageItem, Line and Structure.
 */
public abstract class MyItem {
    /** Rectangle that defines the coordinates of an item. */
    protected Rectangle rectangle;
    /** Color that will be used to mark the item. */
    protected BaseColor color;

    /**
     * Creates an instance of the MyItem object
     * @param rectangle the coordinates of the item
     * @param color     the color that will be used to mark the item
     */
    protected MyItem(Rectangle rectangle, BaseColor color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Getter for the coordinates.
     * @return coordinates in the form of a Rectangle object
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Getter for the color.
     * @return a BaseColor object
     */
    public BaseColor getColor() {
        return color;
    }

    /**
     * Converts the Matrix containing the coordinates of an image as stored
     * in an ImageRenderInfo object into a Rectangle.
     * @param imageRenderInfo   Object that contains info about an image
     * @return coordinates in the form of a Rectangle object
     */
    public static Rectangle getRectangle(ImageRenderInfo imageRenderInfo) {
        Matrix ctm = imageRenderInfo.getImageCTM();
        return new Rectangle(ctm.get(6), ctm.get(7), ctm.get(6) + ctm.get(0), ctm.get(7) + ctm.get(4));
    }

    /**
     * Stores the start and end points and the ascent and descent info from
     * a text snippet into a Rectangle object.
     * @param textRenderInfo    Object that contains info about a text snippet
     * @return coordinates in the form of a Rectangle object
     */
    static Rectangle getRectangle(TextRenderInfo textRenderInfo) {
        float x0 = textRenderInfo.getBaseline().getStartPoint().get(0);
        float x1 = textRenderInfo.getBaseline().getEndPoint().get(0);
        float y0 = textRenderInfo.getDescentLine().getStartPoint().get(1);
        float y1 = textRenderInfo.getAscentLine().getEndPoint().get(1);
        return new Rectangle(x0, y0, x1, y1);
    }
}