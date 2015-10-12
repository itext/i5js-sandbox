/**
 * Example written by Ramesh in the context of a question on SO:
 * http://stackoverflow.com/questions/29345454/itext-avoid-row-splitting-in-table
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class TableSplitTest {
    public static final String DEST = "results/tables/split_test.pdf";
     
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableSplitTest().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.setMargins(15, 15, 55, 35);
        document.open();
        String[] header = new String[] { "Header1", "Header2", "Header3",
            "Header4", "Header5" };
	String[] content = new String[] { "column 1", "column 2",
            "some Text in column 3", "Test data ", "column 5" };
        PdfPTable table = new PdfPTable(header.length);
	table.setHeaderRows(1);
	table.setWidths(new int[] { 3, 2, 4, 3, 2 });
        table.setWidthPercentage(98);
        table.setSpacingBefore(15);
        table.setSplitLate(false);
	for (String columnHeader : header) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
            headerCell.setPadding(8);
            table.addCell(headerCell);
	}
        for (int i = 0; i < 15; i++) {
            int j = 0;
            for (String text : content) {
                if (i == 13 && j == 3) {
                    text = "Test data \n Test data \n Test data";
                }
                j++;
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
        }
        document.add(table);
        document.add(new Phrase("\n"));
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(98);
        separator.setLineColor(BaseColor.LIGHT_GRAY);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        for (int k = 0; k < 5; k++) {
            Paragraph info = new Paragraph("Some title", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));
            info.setSpacingBefore(12f);
            document.add(info);
            table = new PdfPTable(header.length);
            table.setHeaderRows(1);
            table.setWidths(new int[] { 3, 2, 4, 3, 2 });
            table.setWidthPercentage(98);
            table.setSpacingBefore(15);
            table.setSplitLate(false);
            for (String columnHeader : header) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(8);
                table.addCell(headerCell);
            }
            for (String text : content) {
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
            document.add(table);
            separator = new LineSeparator();
            separator.setPercentage(98);
            separator.setLineColor(BaseColor.LIGHT_GRAY);
            linebreak = new Chunk(separator);
            document.add(linebreak);
        }
        document.close();
    }
    
}
