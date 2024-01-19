package org.alexgolikov.file.writer;

import org.alexgolikov.file.writer.contract.TypeListFileWritable;
import org.alexgolikov.shared.model.ServiceResult;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TypeListFileWriter implements TypeListFileWritable {
    @Override
    public ServiceResult writeToFile(String fullPath, Boolean isAdding, List<String> data) {
        if (data.isEmpty()) {
            return new ServiceResult();
        }

        Path path = Paths.get(fullPath);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, isAdding ? StandardOpenOption.APPEND : StandardOpenOption.CREATE)) {
            for (String str : data) {
                writer.write(str.concat("\n"));
            }
        } catch (IOException ex) {
            return new ServiceResult(ex, String.format("File \"%s\" cannot be written. If you added \"-a\" option, please check, that file should be existing.", fullPath));
        }

        return new ServiceResult();
    }
}
