package org.alexgolikov.file.writer.contract;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceResult;

public interface FilesWritable {
    ServiceResult writeToFiles(String path, String prefix, Boolean isAdding, TypeData typeData);
}
