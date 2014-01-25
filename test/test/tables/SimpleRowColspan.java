/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/20016630/how-to-create-a-table-in-a-generated-pdf-using-itextsharp
 * 
 * We create a table using rowspan and colspan.
 */
package test.tables;

import test.GenericTest;

public class SimpleRowColspan extends GenericTest {

	@Override
	public void setup() {
		setKlass("sandbox.tables.SimpleRowColspan");
	}

    @Override
    protected String getCmpPdf() {
        return "cmpfiles/tables/cmp_simple_rowspan_colspan.pdf";
    }
}
