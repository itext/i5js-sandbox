/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19873263/how-to-increase-the-width-of-pdfptable-in-itext-pdf
 * 
 * We create a table with two columns and two cells.
 * This way, we can add two images next to each other.
 */
package test.tables;

import test.GenericTest;

public class FullPageTable extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.tables.FullPageTable");
	}
	
    @Override
    protected String getCmpPdf() {
        return "./test/results/tables/cmp_full_page_table.pdf";
    }
}
