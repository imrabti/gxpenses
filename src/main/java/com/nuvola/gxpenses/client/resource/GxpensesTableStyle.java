package com.nuvola.gxpenses.client.resource;

import com.google.gwt.user.cellview.client.CellTable;

public interface GxpensesTableStyle extends CellTable.Resources {
    @Source({CellTable.Style.DEFAULT_CSS, "com/nuvola/gxpenses/client/resource/css/tableStyle.css"})
    TableStyle cellTableStyle();

    interface TableStyle extends CellTable.Style {
    }
}
