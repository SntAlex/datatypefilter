package org.alexgolikov.file.writer.contract;

import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.resultdata.ServiceResult;

public interface FilesWritable {
    ServiceResult writeToFiles(String path, String prefix, Boolean isAdding, TypeData typeData);
}
