package org.alexgolikov.file.reader;

import org.alexgolikov.file.reader.contract.FileReadable;
import org.alexgolikov.file.reader.contract.FilesReadable;
import org.alexgolikov.resultdata.ServiceValueResult;

import java.util.ArrayList;
import java.util.List;

public class FilesReader implements FilesReadable {
    private final FileReadable fileReader;

    public FilesReader(FileReadable fileReader) {
        this.fileReader = fileReader;
    }

    public ServiceValueResult<List<String>> readAllFiles(List<String> files) {
        List<String> result = new ArrayList<>();

        for (String filename : files) {
            ServiceValueResult<List<String>> fileDataResult = fileReader.read("", filename);

            if (!fileDataResult.isSuccessful()) {
                return fileDataResult;
            }

            result.addAll(fileDataResult.getValue());
        }

        return new ServiceValueResult<>(result);
    }
}