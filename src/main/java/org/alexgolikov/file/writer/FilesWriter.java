package org.alexgolikov.file.writer;

import org.alexgolikov.file.writer.contract.FilesWritable;
import org.alexgolikov.file.writer.contract.TypeListFileWritable;
import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceResult;

import java.util.Objects;

public class FilesWriter implements FilesWritable {
    private final String integersFileName = "integers.txt";
    private final String floatsFileName = "floats.txt";
    private final String stringsFileName = "strings.txt";

    private final TypeListFileWritable typeListFileWriter;

    public FilesWriter(TypeListFileWritable typeListFileWriter) {
        this.typeListFileWriter = typeListFileWriter;
    }

    @Override
    public ServiceResult writeToFiles(String path, String prefix, Boolean isAdding, TypeData typeData) {
        if (Objects.equals(path, "")) {
            path = ".";
        }

        String pathWithPrefix = path.concat("/").concat(prefix);

        ServiceResult integersResult = typeListFileWriter.writeToFile(pathWithPrefix.concat(integersFileName), isAdding, typeData.getIntegers());

        if (!integersResult.isSuccessful()) {
            return integersResult;
        }

        ServiceResult floatsResult = typeListFileWriter.writeToFile(pathWithPrefix.concat(floatsFileName), isAdding, typeData.getDoubles());

        if (!floatsResult.isSuccessful()) {
            return floatsResult;
        }

        ServiceResult stringsResult = typeListFileWriter.writeToFile(pathWithPrefix.concat(stringsFileName), isAdding, typeData.getStrings());

        if (!stringsResult.isSuccessful()) {
            return stringsResult;
        }

        return new ServiceResult();
    }
}
