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
import com.itextpdf.text.pdf.parser.ImageRenderInfo;

/**
 * Subclass of the MyItem class that is used to store the coordinates
 * of an image.
 */
public class ImageItem extends MyItem {
    /** Images will be marked in this color. */
    public static final BaseColor IMG_COLOR = BaseColor.RED;
    
    /**
     * Creates an ImageItem based on an ImageRenderInfo object.
     * @param imageRenderInfo Object containing info about an image
     */
    public ImageItem(ImageRenderInfo imageRenderInfo) {
        super(getRectangle(imageRenderInfo), IMG_COLOR);
    }
}
