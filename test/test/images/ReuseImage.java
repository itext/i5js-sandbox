package test.images;

import test.GenericTest;

public class ReuseImage  extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.images.ReuseImage");
	}

    @Override
    protected String getCmpPdf() {
        return "cmpfiles/images/cmp_image_on_A4.pdf";
    }
}
