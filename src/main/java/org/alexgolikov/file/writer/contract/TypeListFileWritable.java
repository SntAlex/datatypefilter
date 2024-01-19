package org.alexgolikov.file.writer.contract;

import org.alexgolikov.shared.model.ServiceResult;

import java.util.List;

public interface TypeListFileWritable {
    ServiceResult writeToFile(String fullPath, Boolean isAdding, List<String> data);
}
