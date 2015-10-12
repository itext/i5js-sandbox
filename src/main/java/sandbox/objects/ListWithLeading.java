/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29566115/adding-veritcal-spacing-itextsharp-in-list
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class ListWithLeading {

    public static final String DEST = "results/objects/list_with_leading.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListWithLeading().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font font = new Font(FontFamily.HELVETICA, 12);
        List list1 = new List(12);
        list1.setListSymbol("\u2022");
        list1.add(new ListItem("Value 1", font));
        list1.add(new ListItem("Value 2", font));
        list1.add(new ListItem("Value 3", font));
        document.add(list1);
        List list2 = new List(12);
        list2.setListSymbol("\u2022");
        list2.add(new ListItem(30, "Value 1", font));
        list2.add(new ListItem(30, "Value 2", font));
        list2.add(new ListItem(30, "Value 3", font));
        list2.setIndentationLeft(60);
        document.add(list2);
        document.close();
    }
}
