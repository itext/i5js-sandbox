package sandbox;

import com.itextpdf.testutils.CompareTool;
import com.itextpdf.testutils.ITextTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public abstract class SandboxTest extends ITextTest {

    private String errorMessage;
    private String differenceImagePrefix = "difference";

    private void addError(String error) {
        if (error != null && error.length() > 0) {
            if (errorMessage == null)
                errorMessage = "";
            else
                errorMessage += "\n";

            errorMessage += error;
        }
    }

    @Override
    protected void comparePdf(String outPdf, String cmpPdf) throws Exception {
        if (cmpPdf == null || cmpPdf.length() == 0) return;
        CompareTool compareTool = new CompareTool(outPdf, cmpPdf);
        String outPath = "./target/" + new File(outPdf).getParent();
        new File(outPath).mkdirs();
        addError(compareTool.compare(outPdf, cmpPdf, outPath, differenceImagePrefix));
        addError(compareTool.compareDocumentInfo(outPdf, cmpPdf));
        addError(compareTool.compareLinks(outPdf, cmpPdf));

        if (errorMessage != null) Assert.fail(errorMessage);
    }

    public void makePdf() throws Exception {
        makePdf(getOutPdf());
    }

    @Before
    public void setup() {
        String testDir = this.getClass().getPackage().getName();
        testDir = testDir.substring(testDir.lastIndexOf('.') + 1);
        File dir = new File(String.format("./results/%s", testDir));
        dir.mkdirs();
    }

    @Test(timeout = 60000)
    public void test() throws Exception {
        super.runTest();
    }
}
