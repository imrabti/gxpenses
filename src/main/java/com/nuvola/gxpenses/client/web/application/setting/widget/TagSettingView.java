package com.nuvola.gxpenses.client.web.application.setting.widget;

import com.google.common.base.Strings;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.nuvola.gxpenses.client.mvp.ViewWithUiHandlers;
import com.nuvola.gxpenses.client.mvp.uihandler.UiHandlersStrategy;
import com.nuvola.gxpenses.client.resource.style.list.TagListStyle;
import com.nuvola.gxpenses.client.web.application.setting.renderer.TagCellFactory;

import java.util.List;

public class TagSettingView extends ViewWithUiHandlers<TagSettingUiHandlers> implements TagSettingPresenter.MyView {

    public interface Binder extends UiBinder<Widget, TagSettingView> {
    }

    @UiField(provided = true)
    CellList<String> tagsList;
    @UiField
    TextBox tag;

    private final ListDataProvider<String> dataProvider;
    private final SingleSelectionModel<String> selectionModel;

    private List<String> currentTagsList;

    @Inject
    public TagSettingView(final Binder uiBinder,
                          final TagListStyle tagListStyle,
                          final TagCellFactory tagCellFactory,
                          final UiHandlersStrategy<TagSettingUiHandlers> uiHandlers) {
        super(uiHandlers);

        dataProvider = new ListDataProvider<String>();
        selectionModel = new SingleSelectionModel<String>();

        tagsList = new CellList<String>(tagCellFactory.create(setupRemoveAction()), tagListStyle);
        tagsList.setSelectionModel(selectionModel);
        dataProvider.addDataDisplay(tagsList);

        initWidget(uiBinder.createAndBindUi(this));

        tag.getElement().setAttribute("placeholder", "New tag...");
    }

    @Override
    public void setData(List<String> tags) {
        currentTagsList = tags;
        dataProvider.getList().clear();
        dataProvider.getList().addAll(currentTagsList);
        dataProvider.refresh();
    }

    @UiHandler("addTag")
    void onAddTagClicked(ClickEvent event) {
        if (!Strings.isNullOrEmpty(tag.getText())) {
            currentTagsList.add(tag.getText());
            setData(currentTagsList);
            tag.setText("");
        }
    }

    @UiHandler("tag")
    void onTagKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            if (!Strings.isNullOrEmpty(tag.getText())) {
                currentTagsList.add(tag.getText());
                setData(currentTagsList);
                tag.setText("");
            }
        }
    }

    @UiHandler("save")
    void onSave(ClickEvent event) {
        getUiHandlers().saveTags(currentTagsList);
    }

    private ActionCell.Delegate<String> setupRemoveAction() {
        return new ActionCell.Delegate<String>() {
            @Override
            public void execute(String object) {
                currentTagsList.remove(object);
                setData(currentTagsList);
            }
        };
    }

}
