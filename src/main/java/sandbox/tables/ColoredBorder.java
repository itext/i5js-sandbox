/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/35073619
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ColoredBorder {
    public static final String DEST = "results/tables/colored_border.pdf";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColoredBorder().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table;
        table = new PdfPTable(2);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Cell 1"));
        cell.setUseVariableBorders(true);
        cell.setBorderColorTop(BaseColor.RED);
        cell.setBorderColorBottom(BaseColor.BLUE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell 2"));
        cell.setBorderWidthLeft(5);
        cell.setBorderColorLeft(BaseColor.GREEN);
        cell.setBorderWidthTop(8);
        cell.setBorderColorTop(BaseColor.YELLOW);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell 3"));
        cell.setUseVariableBorders(true);
        cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
        cell.setBorderColorLeft(BaseColor.RED);
        cell.setBorderColorBottom(BaseColor.BLUE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell 4"));
        cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
        cell.setUseBorderPadding(true);
        cell.setBorderWidthLeft(5);
        cell.setBorderColorLeft(BaseColor.GREEN);
        cell.setBorderWidthTop(8);
        cell.setBorderColorTop(BaseColor.YELLOW);
        table.addCell(cell);
        document.add(table);
        document.close();
    }

}
