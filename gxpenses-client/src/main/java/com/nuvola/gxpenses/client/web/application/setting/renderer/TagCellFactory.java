package com.nuvola.gxpenses.client.web.application.setting.renderer;

import com.google.gwt.cell.client.ActionCell;

public interface TagCellFactory {
    TagCell create(ActionCell.Delegate<String> delegate);
}
