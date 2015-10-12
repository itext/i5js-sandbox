/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27956833/adding-comment-on-pdf-layer-created-using-itextsharp-in-adobe-reader
 */
package sandbox.stamper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import sandbox.WrapToTest;

@WrapToTest
public class AddOCG {

    public static final String SRC = "resources/pdfs/hello.pdf";
    public static final String DEST = "results/stamper/hello_ocg.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddOCG().manipulatePdf(SRC, DEST);
    }


    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Map<String, PdfLayer> layers = stamper.getPdfLayers();
        PdfWriter writer = stamper.getWriter();
        PdfContentByte cb = stamper.getOverContent(1);
        
        PdfLayer nested = new PdfLayer("Nested layers", writer);
        PdfLayer nested_1 = new PdfLayer("Nested layer 1", writer);
        PdfLayer nested_2 = new PdfLayer("Nested layer 2", writer);
        nested.addChild(nested_1);
        nested.addChild(nested_2);
        writer.lockLayer(nested_2);
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "nested layers"), 50, 775, 0);
        cb.endLayer();
        cb.beginLayer(nested_1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "nested layer 1"), 100, 800, 0);
        cb.endLayer();
        cb.beginLayer(nested_2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "nested layer 2"), 100, 750, 0);
        cb.endLayer();

        PdfLayer group = PdfLayer.createTitle("Grouped layers", writer);
        PdfLayer layer1 = new PdfLayer("Group: layer 1", writer);
        PdfLayer layer2 = new PdfLayer("Group: layer 2", writer);
        group.addChild(layer1);
        group.addChild(layer2);
        cb.beginLayer(layer1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "layer 1 in the group"), 50, 700, 0);
        cb.endLayer();
        cb.beginLayer(layer2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "layer 2 in the group"), 50, 675, 0);
        cb.endLayer();

        PdfLayer radiogroup = PdfLayer.createTitle("Radio group", writer);
        PdfLayer radio1 = new PdfLayer("Radiogroup: layer 1", writer);
        radio1.setOn(true);
        PdfLayer radio2 = new PdfLayer("Radiogroup: layer 2", writer);
        radio2.setOn(false);
        PdfLayer radio3 = new PdfLayer("Radiogroup: layer 3", writer);
        radio3.setOn(false);
        radiogroup.addChild(radio1);
        radiogroup.addChild(radio2);
        radiogroup.addChild(radio3);
        ArrayList<PdfLayer> options = new ArrayList<PdfLayer>();
        options.add(radio1);
        options.add(radio2);
        options.add(radio3);
        writer.addOCGRadioGroup(options);
        cb.beginLayer(radio1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 1"), 50, 600, 0);
        cb.endLayer();
        cb.beginLayer(radio2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 2"), 50, 575, 0);
        cb.endLayer();
        cb.beginLayer(radio3);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 3"), 50, 550, 0);
        cb.endLayer();

        PdfLayer not_printed = new PdfLayer("not printed", writer);
        not_printed.setOnPanel(false);
        not_printed.setPrint("Print", false);
        cb.beginLayer(not_printed);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(
                "PRINT THIS PAGE"), 300, 700, 90);
        cb.endLayer();

        PdfLayer zoom = new PdfLayer("Zoom 0.75-1.25", writer);
        zoom.setOnPanel(false);
        zoom.setZoom(0.75f, 1.25f);
        cb.beginLayer(zoom);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Only visible if the zoomfactor is between 75 and 125%"), 30,
                530, 90);
        cb.endLayer();
        
        stamper.close();
        reader.close();
    }

}
