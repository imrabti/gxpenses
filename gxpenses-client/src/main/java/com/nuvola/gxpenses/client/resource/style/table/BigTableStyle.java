package com.nuvola.gxpenses.client.resource.style.table;

import com.google.gwt.user.cellview.client.DataGrid;

public interface BigTableStyle extends DataGrid.Resources {
    @Source({DataGrid.Style.DEFAULT_CSS, "com/nuvola/gxpenses/client/resource/css/bigTableStyle.css"})
    TableStyle dataGridStyle();

    interface TableStyle extends DataGrid.Style {
    }
}
