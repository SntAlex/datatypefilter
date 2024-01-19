package org.alexgolikov.file.writer.contract;

import org.alexgolikov.resultdata.ServiceResult;

import java.util.List;

public interface TypeListFileWritable {
    ServiceResult writeToFile(String fullPath, Boolean isAdding, List<String> data);
}
