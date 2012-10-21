package com.nuvola.gxpenses.client.web.welcome.entrypoint.login;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    @Inject
    public LoginView(final Binder uiBinder,
                     final UiHandlersStrategy<LoginUiHandlers> uiHandlers) {
        super(uiHandlers);

        initWidget(uiBinder.createAndBindUi(this));
    }

}
