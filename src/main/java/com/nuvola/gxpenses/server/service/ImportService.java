package com.nuvola.gxpenses.server.service;

import com.nuvola.gxpenses.shared.type.ImportFileType;

public interface ImportService {
    void importFile(ImportFileType fileType, String fileContent);
}
