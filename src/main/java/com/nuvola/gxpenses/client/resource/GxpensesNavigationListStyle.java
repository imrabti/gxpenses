package com.nuvola.gxpenses.client.resource;

import com.google.gwt.user.cellview.client.CellList;

public interface GxpensesNavigationListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/nuvola/gxpenses/client/resource/css/navigationListStyle.css"})
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}
