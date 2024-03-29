package org.alexgolikov.file.reader.contract;

import org.alexgolikov.shared.model.ServiceValueResult;

import java.util.List;

public interface FileReadable {
    ServiceValueResult<List<String>> read(String path, String filename);
}
