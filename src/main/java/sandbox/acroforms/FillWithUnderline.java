/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28579382/underline-portion-of-text-using-itextsharp
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class FillWithUnderline {

    public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/field_underlined.pdf";
    

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillWithUnderline().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setFormFlattening(true);
        AcroFields form = stamper.getAcroFields();
        FieldPosition pos = form.getFieldPositions("Name").get(0);
        ColumnText ct = new ColumnText(stamper.getOverContent(pos.page));
        ct.setSimpleColumn(pos.position);
        ElementList elements = XMLWorkerHelper.parseToElementList("<div>Bruno <u>Lowagie</u></div>", null);
        for (Element element : elements) {
            ct.addElement(element);
        }
        ct.go();
        stamper.close();
    }
}
