/*
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/35011232
 */
package sandbox.barcodes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class BarcodeInTable {
    public static final String DEST = "results/barcodes/barcode_in_table.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BarcodeInTable().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        
        String code = "675-FH-A12";
        
        PdfContentByte cb = writer.getDirectContent();
        
        PdfPTable table = new PdfPTable(2);
        
        table.addCell("Change baseline:");
        Barcode128 code128 = new Barcode128();
        code128.setBaseline(-1);
        code128.setSize(12);
        code128.setCode(code);
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        PdfPCell cell = new PdfPCell(code128Image);
        table.addCell(cell);
        
        table.addCell("Add text and bar code separately:");
        code128 = new Barcode128();
        code128.setFont(null);
        code128.setCode(code);
        code128.setCodeType(Barcode128.CODE128);
        code128Image = code128.createImageWithBarcode(cb, null, null);
        cell = new PdfPCell();
        cell.addElement(new Phrase("PO #: " + code));
        cell.addElement(code128Image);
        table.addCell(cell);
        
        document.add(table);
        
        document.close();
    }
    
}
