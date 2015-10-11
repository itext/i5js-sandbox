/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28590487/adding-table-to-existing-pdf-on-the-same-page-itext
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class AddExtraTable {

    public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/add_extra_table.pdf";
    
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddExtraTable().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        Rectangle pagesize = reader.getPageSize(1);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.setField("Name", "Jennifer");
        form.setField("Company", "iText's next customer");
        form.setField("Country", "No Man's Land");
        PdfPTable table = new PdfPTable(2);
        table.addCell("#");
        table.addCell("description");
        table.setHeaderRows(1);
        table.setWidths(new int[]{ 1, 15 });
        for (int i = 1; i <= 150; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("test " + i);
        }
        ColumnText column = new ColumnText(stamper.getOverContent(1));
        Rectangle rectPage1 = new Rectangle(36, 36, 559, 540);
        column.setSimpleColumn(rectPage1);
        column.addElement(table);
        int pagecount = 1;
        Rectangle rectPage2 = new Rectangle(36, 36, 559, 806);
        int status = column.go();
        while (ColumnText.hasMoreText(status)) {
            status = triggerNewPage(stamper, pagesize, column, rectPage2, ++pagecount);
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }
    
    public int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        return column.go();
    }
}