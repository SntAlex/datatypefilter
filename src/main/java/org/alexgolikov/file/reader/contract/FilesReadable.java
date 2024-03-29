package org.alexgolikov.file.reader.contract;

import org.alexgolikov.shared.model.ServiceValueResult;

import java.util.List;

public interface FilesReadable {
    ServiceValueResult<List<String>> readAllFiles(List<String> files);
}
