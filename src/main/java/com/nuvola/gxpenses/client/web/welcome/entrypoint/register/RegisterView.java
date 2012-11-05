package com.nuvola.gxpenses.client.web.welcome.entrypoint.register;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class RegisterView extends ViewWithUiHandlers<RegisterUiHandlers> implements RegisterPresenter.MyView {

    public interface Binder extends UiBinder<Widget, RegisterView> {
    }

    @Inject
    public RegisterView(final Binder uiBinder,
                        final UiHandlersStrategy<RegisterUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }

}
