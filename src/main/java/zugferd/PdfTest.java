/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zugferd;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AFRelationshipValue;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.xmp.PdfAXmpWriter;
import com.itextpdf.xmp.XMPException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import zugferd.InvoiceData;
import zugferd.pojo.Invoice;
import zugferd.pojo.PojoFactory;
import zugferd.xml.BASICInvoice;
import zugferd.xml.BASICInvoiceDOM;

/**
 *
 * @author iText
 */
public class PdfTest {
    public static final String DEST = "results/zugferd/Invoice%05d.pdf";
    public static final String ICC = "resources/data/sRGB_CS_profile.icm";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException, ParserConfigurationException, SQLException, SAXException, TransformerException, XMPException, ParseException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PojoFactory factory = PojoFactory.getInstance();
        List<Invoice> invoices = factory.getInvoices();
        InvoiceData invoiceData = new InvoiceData();
        PdfTest app = new PdfTest();
        for (Invoice invoice : invoices) {
            app.createPdf(String.format(DEST, invoice.getId()),
                    invoiceData.importInvoice(invoice));
        }
        factory.close();
    }
    
    public void createPdf(String dest, BASICInvoice invoice) throws ParserConfigurationException, SAXException, TransformerException, IOException, DocumentException, XMPException, ParseException {
        
        
        // step 1
        Document document = new Document();
        // step 2
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(dest), PdfAConformanceLevel.ZUGFeRDBasic);
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.createXmpMetadata();
        writer.getXmpWriter().setProperty(PdfAXmpWriter.zugferdSchemaNS, PdfAXmpWriter.zugferdDocumentFileName, "ZUGFeRD-invoice.xml");
        // step 3
        document.open();
        // step 4
        ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
        
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED);
        Font font12 = new Font(bf, 12);
        Font font14 = new Font(bf, 14);
        
        Paragraph p;
        p = new Paragraph(invoice.getName(), font14);
        document.add(p);
        p = new Paragraph(convertDate(invoice.getDateTime(), "yyyyMMdd", "MMM dd, yyyy"), font12);
        document.add(p);
        
        PdfPTable table = new PdfPTable(2);
        document.add(table);
        
        BASICInvoiceDOM dom = new BASICInvoiceDOM();
        dom.importData(invoice);
        PdfDictionary parameters = new PdfDictionary();
        parameters.put(PdfName.MODDATE, new PdfDate());
        PdfFileSpecification fileSpec = writer.addFileAttachment(
                "ZUGFeRD invoice", dom.exportDoc(), null,
                "ZUGFeRD-invoice.xml", "application/xml",
                AFRelationshipValue.Alternative, parameters);
        PdfArray array = new PdfArray();
        array.add(fileSpec.getReference());
        writer.getExtraCatalog().put(PdfName.AF, array);
        
        // step 5
        document.close();
    }
    
    public PdfPCell getParty(Font f) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
    }
    
    public String convertDate(String date, String origFormat, String newFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(origFormat);
        Date d = sdf.parse(date);
        sdf.applyPattern(newFormat);
        return sdf.format(d);
        
    }
}
