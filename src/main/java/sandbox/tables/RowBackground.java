/*
 * Example written in answer to the SO question:
 * http://stackoverflow.com/questions/39154089
 */
package sandbox.tables;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import sandbox.WrapToTest;
 
@WrapToTest
public class RowBackground {
 
    public class RowBackgroundEvent implements PdfPTableEvent {
        // the row number of the row that needs a background
        protected int row;
    
        // creates a background event for a specific row
        public RowBackgroundEvent(int row) {
            this.row = row;
        }
    
        /**
         * Draws the background of a row.
         */
        @Override
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights,
            int headerRows, int rowStart, PdfContentByte[] canvases) {
            float llx = widths[row][0];
            float lly = heights[row];
            float urx = widths[row][widths[row].length - 1];
            float ury = heights[row - 1];
            float h = ury - lly;
            PdfContentByte canvas = canvases[PdfPTable.BASECANVAS];
            canvas.saveState();
            canvas.arc(llx - h / 2, lly, llx + h / 2, ury, 90, 180);
            canvas.lineTo(urx, lly);
            canvas.arc(urx - h / 2, lly, urx + h / 2, ury, 270, 180);
            canvas.lineTo(llx, ury);
            canvas.setColorFill(BaseColor.LIGHT_GRAY);
            canvas.fill();
            canvas.restoreState();
        }
    }
    
    /** The resulting PDF file. */
    public static final String DEST = "results/tables/row_background.pdf";
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException
     * @throws    SQLException
     */
    public void createPdf(String filename) throws SQLException, DocumentException, IOException {
        // step 1
        Document document = new Document(PageSize.A4.rotate());
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        PdfPTableEvent event = new RowBackgroundEvent(3);
        PdfPTable table = new PdfPTable(7);
        table.setTableEvent(event);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 8; j++) {
                table.addCell(String.valueOf(j));
            }
        }
        document.add(table);
        // step 5
        document.close();
    }
 
    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException 
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException, DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RowBackground().createPdf(DEST);
    }
}
