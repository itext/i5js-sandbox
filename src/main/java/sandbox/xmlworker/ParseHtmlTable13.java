/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/41341398/itextsharp-add-css-style-or-a-css-file
 */
package sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParseHtmlTable13 {
    public static final String DEST = "results/xmlworker/html_table_13.pdf";
    public static final String CSS = "th { background-color: #C0C0C0; font-size: 16pt; } "
        + "td { font-size: 10pt; }";
    public static final String HTML = "<html><body><table  class='table-bordered'>"
        + "<thead><tr><th>Customer Name</th><th>Customer's Address</th> </tr></thead>"
        + "<tbody><tr><td> XYZ </td><td> Bhubaneswar </td></tr>"
        + "<tr><td> MNP </td><td> Cuttack </td></tr></tbody>"
        + "</table></body></html>";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtmlTable13().createPdf(DEST);
    }
        
    /**
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        
        
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
        cssResolver.addCss(cssFile);
        
        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        
        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        
        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(HTML.getBytes()));
        document.close();
    }
}
