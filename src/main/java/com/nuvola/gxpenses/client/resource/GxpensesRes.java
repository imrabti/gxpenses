package com.nuvola.gxpenses.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface GxpensesRes extends ClientBundle {
    @Source("com/nuvola/gxpenses/client/resource/css/generalStyle.css")
    GeneralStyle generalStyleCss();

    @Source("com/nuvola/gxpenses/client/resource/css/popupStyle.css")
    PopupStyle popupStyleCss();

    @Source("com/nuvola/gxpenses/client/resource/css/buttonStyle.css")
    ButtonStyle buttonStyleCss();

    @Source("com/nuvola/gxpenses/client/resource/css/suggestionBoxStyle.css")
    SuggestionBoxStyle suggestBoxStyleCss();

    @Source("com/nuvola/gxpenses/client/resource/css/datePickerStyle.css")
    DatePickerStyle datePickerStyle();

    @Source("com/nuvola/gxpenses/client/resource/css/progressStyle.css")
    ProgressBarStyle progressBarStyle();

    @Source("com/nuvola/gxpenses/client/resource/images/popupBack.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource popupBack();

    @Source("com/nuvola/gxpenses/client/resource/images/setting.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource settingIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/setting_white.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource settingIconWhite();

    @Source("com/nuvola/gxpenses/client/resource/images/next.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource nextIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/previous.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource previousIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/add.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource addIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/add_white.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource addIconWhite();

    @Source("com/nuvola/gxpenses/client/resource/images/transfert.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource transfertIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/remove.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource removeIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/transfert_white.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource transfertWhiteIcon();

    @Source("com/nuvola/gxpenses/client/resource/images/arrow_right.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource arrowRightImage();

    @Source("com/nuvola/gxpenses/client/resource/images/arrow_up.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource arrowUpImage();

    interface GeneralStyle extends CssResource {
        String emptyMessageStyle();

        String rightArrow();

        String upArrow();

        String accountBalance();

        String accountBalanceSelected();

        String budgetAllowed();

        String budgetAllowedSelected();

        String smallTitle();

        String bigTitle();

        String subTitle();

        String amountExpense();

        String amountIncome();

        String tag();

        String date();

        String payee();

        String tagWhite();

        String dateWhite();

        String payeeWhite();

        String amountWhite();

        String amountExpenseTrans();

        String amountIncomeTrans();

        String transactionListHeader();

        String transactionListFooter();

        String globalMsgInfo();

        String ajaxLoader();

        String textInput();

        String label();

        String formButtons();

        String errorBox();
    }

    interface PopupStyle extends CssResource {
        @ClassName("gwt-PopupPanel")
        String gwtPopupPanel();

        String buttonContainer();
    }

    interface ButtonStyle extends CssResource {
        String button();

        String small();

        String medium();

        String large();

        String green();

        String red();

        String gray();

        String toolBarbutton();

        String linkButton();

        String singleButton();

        String leftButton();

        String rightButton();

        String settingButton();

        String settingButtonText();

        String addButtonAlt();

        String addButtonAltText();

        String nextButton();

        String previousButton();

        String addButton();

        String transfertButton();

        String transfertButtonText();
    }

    interface SuggestionBoxStyle extends CssResource {
        @ClassName("gwt-SuggestBox")
        String gwtSuggestBox();

        @ClassName("gwt-SuggestBoxPopup")
        String gwtSuggestBoxPoup();
    }

    interface DatePickerStyle extends CssResource {
        @ClassName("gwt-DatePicker")
        String gwtDatePicker();
    }

    interface ProgressBarStyle extends CssResource {
        String smallProgress();

        String smallProgressSelected();

        String bigProgress();

        String bigProgressTotal();

        String green();

        String red();

        String orange();

        String gray();
    }
}
