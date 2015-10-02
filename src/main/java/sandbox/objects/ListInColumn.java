/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/29277611/itextsharp-continuing-ordered-list-on-second-page-with-a-number-other-than-1
 */
package sandbox.objects;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sandbox.WrapToTest;

@WrapToTest
public class ListInColumn {

    public static final String SRC = "resources/pdfs/pages.pdf";
    public static final String DEST = "results/objects/list_in_column.pdf";
    
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListInColumn().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        reader.selectPages("1-2");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        List list = new List(List.ORDERED);
        for (int i = 0; i < 10; i++) {
            list.add("This is a list item. It will be repeated a number of times. "
                    + "This is done only for test purposes. "
                    + "I want a list item that is distributed over several lines.");
        }
        ColumnText ct = new ColumnText(stamper.getOverContent(1));
        ct.addElement(list);
        Rectangle rect = new Rectangle(250, 400, 500, 806);
        ct.setSimpleColumn(rect);
        int status = ct.go();
        if (ColumnText.hasMoreText(status)) {
            ct.setCanvas(stamper.getOverContent(2));
            ct.setSimpleColumn(rect);
            ct.go();
        }
        stamper.close();
    }
}
