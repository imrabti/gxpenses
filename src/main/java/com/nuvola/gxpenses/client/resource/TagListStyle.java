package com.nuvola.gxpenses.client.resource;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;

public interface TagListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/nuvola/gxpenses/client/resource/css/tagListStyle.css"})
    ListStyle cellListStyle();

    @Source("com/nuvola/gxpenses/client/resource/images/remove_small.png")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource removeSmall();

    interface ListStyle extends CellList.Style {
    }
}
