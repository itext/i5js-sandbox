/**
 * This example is written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/32582927/itext-pdfwriter-writing-table-header-if-the-few-table-rows-go-to-new-page
 */
package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class HeaderRowRepeated {
    public static final String DEST = "results/tables/repeat_header_row.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HeaderRowRepeated().createPdf(DEST);
    }    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // table with 2 columns:
        PdfPTable table = new PdfPTable(2);
        // header row:
        table.addCell("Key");
        table.addCell("Value");
        table.setHeaderRows(1);
        table.setSkipFirstHeader(true);
        // many data rows:
        for (int i = 1; i < 51; i++) {
            table.addCell("key: " + i);
            table.addCell("value: " + i);
        }
        document.add(table);
        document.close();
    }
}
