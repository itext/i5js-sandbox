/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/30214147/arabic-characters-from-html-content-to-pdf-using-itext
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import sandbox.WrapToTest;

/**
 *
 * @author iText
 */
@WrapToTest
public class ParseHtml7 {
    public static final String DEST = "results/xmlworker/arabic.pdf";
    public static final String HTML = "resources/xml/arabic.html";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtml7().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        // Styles
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register("resources/fonts/NotoNaskhArabic-Regular.ttf");
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        // Pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline pdf = new ElementHandlerPipeline(elements, null);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        
        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new FileInputStream(HTML), Charset.forName("UTF-8"));
        
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        for (Element e : elements) {
            cell.addElement(e);
        }
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }
}
