package com.nuvola.gxpenses.client.util;

import com.google.gwt.editor.client.Editor;

public interface EditorView<T> extends Editor<T> {
    public void edit(T object);

    public T get();
}
