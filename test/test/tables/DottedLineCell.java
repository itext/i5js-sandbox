package test.tables;

import test.GenericTest;

public class DottedLineCell extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.tables.DottedLineCell");
	}
	
    @Override
    protected String getCmpPdf() {
        return "./test/results/tables/cmp_dotted_line_cell.pdf";
    }
}
