/*
 * This example was written by Bruno Lowagie.
 */
package sandbox.xmlworker;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.NoCustomContextException;
import com.itextpdf.tool.xml.Tag;
import com.itextpdf.tool.xml.WorkerContext;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Span;
import com.itextpdf.tool.xml.html.TagProcessorFactory;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.ctx.MapContext;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sandbox.WrapToTest;

/**
 * @author iText
 */
@WrapToTest
public class BarcodeTagExample {
    
    public static final String HTML = "resources/xml/barcode.html";
    public static final String DEST = "results/xmlworker/barcode.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BarcodeTagExample().createPdf(DEST);
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
        
        // CSS
        CSSResolver cssResolver =
                XMLWorkerHelper.getInstance().getDefaultCssResolver(false);
        
        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        TagProcessorFactory factory = Tags.getHtmlTagProcessorFactory();
        factory.addProcessor(
            new Span(){
                @Override
                public List<Element> end(WorkerContext ctx, Tag tag, List<Element> l) {
                    List<Element> list = new ArrayList<Element>(1);
                    list.add(getEanBarcodeChunk(ctx, tag.getAttributes()));
                    return list;
                }
            },
            "ean");
        htmlContext.setTagFactory(factory);
        htmlContext.autoBookmark(false);
        
        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        
        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new FileInputStream(HTML));
        
        // step 5
        document.close();
    }
    
    public Chunk getEanBarcodeChunk(WorkerContext ctx, Map<String, String> attributes) {
        MapContext mc;
        try {
            mc = (MapContext)ctx.get("com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline");
        } catch (NoCustomContextException ex) {
            throw new ExceptionConverter(ex);
        }
        PdfWriter writer = (PdfWriter) mc.get("WRITER");
        BarcodeEAN barcode = new BarcodeEAN();
        String type = attributes.get("type");
        if ("EAN8".equals(type))
            barcode.setCodeType(BarcodeEAN.EAN8);
        else
            barcode.setCodeType(BarcodeEAN.EAN13);
        barcode.setCode(attributes.get("value"));
        Image img = barcode.createImageWithBarcode(writer.getDirectContent(), null, null);
        return new Chunk(img, 0, 0, true);
    }
}
