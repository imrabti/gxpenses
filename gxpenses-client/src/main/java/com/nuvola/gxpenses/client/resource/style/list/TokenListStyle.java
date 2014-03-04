package com.nuvola.gxpenses.client.resource.style.list;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;

public interface TokenListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/nuvola/gxpenses/client/resource/css/tokenListStyle.css"})
    ListStyle cellListStyle();

    @Source("com/nuvola/gxpenses/client/resource/images/remove_token.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource removeToken();

    @Source("com/nuvola/gxpenses/client/resource/images/remove_token_hover.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource removeTokenHover();

    interface ListStyle extends CellList.Style {
    }
}
