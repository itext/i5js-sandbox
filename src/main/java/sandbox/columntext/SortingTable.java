/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/38761938
 */
package sandbox.columntext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class SortingTable {
        
    public static final String DEST = "results/columntext/optionaltables.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SortingTable().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        ArrayList<PdfLayer> options = new ArrayList<PdfLayer>();
        PdfLayer radiogroup = PdfLayer.createTitle("Table", writer);
        PdfLayer radio1 = new PdfLayer("column1", writer);
        radio1.setOn(true);
        options.add(radio1);
        radiogroup.addChild(radio1);
        PdfLayer radio2 = new PdfLayer("column2", writer);
        radio2.setOn(false);
        options.add(radio2);
        radiogroup.addChild(radio2);
        PdfLayer radio3 = new PdfLayer("column3", writer);
        radio3.setOn(false);
        options.add(radio3);
        radiogroup.addChild(radio3);
        writer.addOCGRadioGroup(options);
        
        PdfContentByte canvas = writer.getDirectContent();
        ColumnText ct = new ColumnText(canvas);
        
        for (int i = 1; i < 4; i++) {
            canvas.beginLayer(options.get(i - 1));
            ct.setSimpleColumn(new Rectangle(36, 36, 559, 806));
            ct.addElement(createTable(i, options));
            ct.go();
            canvas.endLayer();
        }
        
        // step 5
        document.close();
    }
    
    public PdfPTable createTable(int c, List<PdfLayer> options) {
        PdfPTable table = new PdfPTable(3);
        for (int j = 1; j < 4; j++) {
            table.addCell(createCell(j, options));
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                table.addCell(createCell(i, j, c));
            }
        }
        return table;
    }
    
    public PdfPCell createCell(int c, List<PdfLayer> options) {
        Chunk chunk = new Chunk("Column " + c);
        ArrayList<Object> list = new ArrayList<Object>();
        list.add("ON");
        list.add(options.get(c - 1));
        PdfAction action = PdfAction.setOCGstate(list, true);
        chunk.setAction(action);
        return new PdfPCell(new Phrase(chunk));
    }
    
    public PdfPCell createCell(int i, int j, int c) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph(String.format("row %s; column %s", i, j)));
        if (j == c) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        return cell;
    }
}
