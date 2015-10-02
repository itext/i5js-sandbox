/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27844914/align-justified-for-itext-list-item
 */
package sandbox.objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class ListAlignment {

    public static final String DEST = "results/objects/list_alignment.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListAlignment().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        String text = "test 1 2 3 ";
        for (int i = 0; i < 5; i++) {
            text = text + text;
        }
        List list = new List(List.UNORDERED);
        ListItem item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        text = "a b c align ";
        for (int i = 0; i < 5; i++) {
            text = text + text;
        }
        item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        text = "supercalifragilisticexpialidocious ";
        for (int i = 0; i < 3; i++) {
            text = text + text;
        }
        item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        document.add(list);
        document.close();
    }
}
