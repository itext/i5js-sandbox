/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29752875/generate-pdf-from-java-code-that-dynamically-continue-file-template-with-itext
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class SimpleTable8 {
    public static final String DEST = "results/tables/simple_table8.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable8().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfReader reader = new PdfReader("resources/pdfs/header.pdf");
        PdfImportedPage header = writer.getImportedPage(reader, 1);
        PdfPCell cell = new PdfPCell(Image.getInstance(header));
        cell.setColspan(3);
        table.addCell(cell);
        for (int row = 1; row <= 50; row++) {
            for (int column = 1; column <= 3; column++) {
                table.addCell(String.format("row %s, column %s", row, column));
            }
        }
        reader = new PdfReader("resources/pdfs/footer.pdf");
        PdfImportedPage footer = writer.getImportedPage(reader, 1);
        cell = new PdfPCell(Image.getInstance(footer));
        cell.setColspan(3);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
