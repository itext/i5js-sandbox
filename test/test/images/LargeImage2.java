package test.images;

import test.GenericTest;

public class LargeImage2  extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.images.LargeImage1");
	}

    @Override
    protected String getCmpPdf() {
        return "./test/results/images/cmp_large_image2.pdf";
    }
}
