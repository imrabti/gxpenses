package com.nuvola.gxpenses.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.nuvola.gxpenses.client.resource.style.GeneralStyle;
import com.nuvola.gxpenses.client.resource.style.component.ButtonStyle;
import com.nuvola.gxpenses.client.resource.style.component.DatePickerStyle;
import com.nuvola.gxpenses.client.resource.style.component.PopupStyle;
import com.nuvola.gxpenses.client.resource.style.component.ProgressBarStyle;
import com.nuvola.gxpenses.client.resource.style.component.SuggestionBoxStyle;

public interface Resources extends ClientBundle {
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

    @Source("com/nuvola/gxpenses/client/resource/images/gxpenses_logo_big.png")
    @ImageOptions(repeatStyle = RepeatStyle.None)
    ImageResource gxpensesLogo();

    @Source("com/nuvola/gxpenses/client/resource/images/loginBack.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource entryPointBack();

    @Source("com/nuvola/gxpenses/client/resource/images/back.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource applicationBack();

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
}
