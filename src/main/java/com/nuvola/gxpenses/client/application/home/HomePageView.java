package com.nuvola.gxpenses.client.application.home;

import com.nuvola.core.client.mvp.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @Inject
    public HomePageView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }
}
