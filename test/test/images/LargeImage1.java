package test.images;

import test.GenericTest;

public class LargeImage1 extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.images.LargeImage1");
	}

    @Override
    protected String getCmpPdf() {
        return "cmpfiles/images/cmp_large_image1.pdf";
    }
}
