/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27500586/itext-page-number-in-header-within-pdf-a
 */
package sandbox.pdfa;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PdfA1A {
    
    class Header extends PdfPageEventHelper {
        Font font;
        PdfTemplate t;
        Image total;
        
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            t = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(t);
                total.setRole(PdfName.ARTIFACT);
                font =  new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            } catch (IOException ioe) {
                throw new ExceptionConverter(ioe);
            }
        }
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(3);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.addCell(new Phrase("Test", font));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), font));
                PdfPCell cell = new PdfPCell(total);
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                PdfContentByte canvas = writer.getDirectContent();
                canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
                table.writeSelectedRows(0, -1, 36, 30, canvas);
                canvas.endMarkedContentSequence();
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
        
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1), font),
                2, 2, 0);
        }
    }
    
    public static final String DEST = "results/pdfa/pdf_a1a.pdf";
    public static final String DATA = "resources/data/united_states.csv";
    public static final String FONT = "resources/fonts/OpenSans-Regular.ttf";
    public static final String BOLD = "resources/fonts/OpenSans-Bold.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfA1A().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Font font = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Font bold = new Font(BaseFont.createFont(BOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new Document(PageSize.A4.rotate());
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(dest), PdfAConformanceLevel.PDF_A_1A);
        writer.createXmpMetadata();
        writer.setTagged();
        writer.setPageEvent(new Header());
        document.open();
        document.addLanguage("en-us");
        File file = new File("resources/data/sRGB_CS_profile.icm");
        ICC_Profile icc = ICC_Profile
                .getInstance(new FileInputStream(file));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
        PdfDictionary parameters = new PdfDictionary();
        parameters.put(PdfName.MODDATE, new PdfDate());
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        BufferedReader br = new BufferedReader(new FileReader(DATA));
        String line = br.readLine();
        process(table, line, bold);
        table.setHeaderRows(1);
        while ((line = br.readLine()) != null) {
            process(table, line, font);
        }
        br.close();
        document.add(table);
        document.close();
    }
    
    public void process(PdfPTable table, String line, Font font) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            table.addCell(new Phrase(tokenizer.nextToken(), font));
        }
    }
}