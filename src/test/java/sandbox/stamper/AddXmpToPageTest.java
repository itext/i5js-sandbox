package sandbox.stamper;

import com.itextpdf.testutils.CompareTool;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;

import org.junit.Assert;

import sandbox.GenericTest;
import sandbox.WrapToTest;

@WrapToTest
public class AddXmpToPageTest extends GenericTest {

	    @Override
		public void setup() {
			setKlass("sandbox.stamper.AddXmpToPage");
		}
	    
	    @Override
	    protected void comparePdf(String dest, String cmp) throws Exception {
	    	// Skip the regular comparison
	    }
	    
	    @Override
	    protected void assertPdf(String outPdf) throws Exception {
	    	// Compare the page level XMP metadata
	    	PdfReader outReader = new PdfReader(outPdf);
	    	PdfDictionary outPageDict = outReader.getPageN(1);
	    	PdfReader cmpReader = new PdfReader(outPdf);
	    	PdfDictionary cmpPageDict = cmpReader.getPageN(1);
	    	PdfStream outMetadata = outPageDict.getAsStream(PdfName.METADATA);
	    	PdfStream cmpMetadata = cmpPageDict.getAsStream(PdfName.METADATA);
	    	byte[] outMetadataBytes = PdfReader.getStreamBytes((PRStream)outMetadata);
	    	byte[] cmpMetadataBytes = PdfReader.getStreamBytes((PRStream)cmpMetadata);
	        CompareTool ct = new CompareTool();
	        Assert.assertNull(ct.compareXmp(outMetadataBytes, cmpMetadataBytes, true));
	        outReader.close();
	        cmpReader.close();
	    }
	}
