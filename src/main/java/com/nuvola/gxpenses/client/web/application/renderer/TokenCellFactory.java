package com.nuvola.gxpenses.client.web.application.renderer;

import com.google.gwt.cell.client.ActionCell.Delegate;

public interface TokenCellFactory {
    TokenCell create(Delegate<String> delegate);
}
