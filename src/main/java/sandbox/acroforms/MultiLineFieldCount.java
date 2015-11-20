/*
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/33808187/get-row-count-of-a-multiline-acrofiled
 */
package sandbox.acroforms;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PRIndirectReference;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class MultiLineFieldCount {
    
    public static final String SRC = "resources/pdfs/multiline.pdf";
    public static final String DEST = "results/acroforms/multiline.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultiLineFieldCount().manipulatePdf(SRC, DEST);
    }
        
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        StringBuilder sb = new StringBuilder();
        for (String name : form.getFields().keySet()) {
            int n = getInformation(form, name);
            for (int i = 0; i < n; i++) {
                sb.append(" *");
            }
            String filler = sb.toString();
            form.setField(name, name + filler);
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }
    
    public int getInformation(AcroFields form, String name) {
        form.getFieldItem(name);
        AcroFields.Item item = form.getFieldItem(name);
        PdfDictionary dict = item.getMerged(0);
        PdfString da = dict.getAsString(PdfName.DA);
        Object[] da_values = AcroFields.splitDAelements(da.toUnicodeString());
        if (da_values == null) {
            System.out.println("No default appearance");
        }
        BaseFont bf = null;
        String font = (String)da_values[AcroFields.DA_FONT];
        if (font != null) {
            PdfDictionary dr = dict.getAsDict(PdfName.DR);
            if (dr != null) {
                PdfDictionary fontDict = dr.getAsDict(PdfName.FONT);
                bf = BaseFont.createFont((PRIndirectReference)fontDict.get(new PdfName(font)));
            }
        }
        if (bf == null) {
            System.out.println("No BaseFont");
        }
        else {
            System.out.println("Basefont: " + bf.getPostscriptFontName());
            System.out.println("Size: " + da_values[AcroFields.DA_SIZE]);
            Float size = (Float)da_values[AcroFields.DA_SIZE];
            if (size == 0)
                return 1000;
            Rectangle rect = form.getFieldPositions(name).get(0).position;
            float factor = bf.getFontDescriptor(BaseFont.BBOXURY, 1) - bf.getFontDescriptor(BaseFont.BBOXLLY, 1);
            int rows = Math.round(rect.getHeight() / (size * factor) + 0.5f);
            int columns = Math.round(rect.getWidth() / bf.getWidthPoint(" *", size) + 0.5f);
            System.out.println("height: " + rect.getHeight() + "; width: " + rect.getWidth());
            System.out.println("rows: " + rows + "; columns: " + columns);
            return rows * columns;
        }
        return 1000;
    }
}
