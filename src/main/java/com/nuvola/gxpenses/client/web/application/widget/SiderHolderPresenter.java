package com.nuvola.gxpenses.client.web.application.widget;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.nuvola.gxpenses.client.event.SetVisibleSiderEvent;

public class SiderHolderPresenter extends PresenterWidget<SiderHolderPresenter.MyView>
        implements SetVisibleSiderEvent.SetVisibleSiderHandler {

    public interface MyView extends View {
    }

    public static final Object TYPE_SetSiderContent = new Object();

    @Inject
    public SiderHolderPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
    }

    @Override
    public void onVisibleSider(SetVisibleSiderEvent event) {
        clearSlot(TYPE_SetSiderContent);
        setInSlot(TYPE_SetSiderContent, event.getSider());
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(SetVisibleSiderEvent.getType(), this);
    }

}
