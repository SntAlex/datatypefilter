package org.alexgolikov.file.reader;

import org.alexgolikov.file.reader.contract.FileReadable;
import org.alexgolikov.resultdata.ServiceValueResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements FileReadable {

    @Override
    public ServiceValueResult<List<String>> read(String path, String filename) {
        String fileFullPath = path.concat("/").concat(filename);

        List<String> result = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException ex) {
            return new ServiceValueResult<>(ex, String.format("File \"%s\" cannot be opened", fileFullPath));
        }

        return new ServiceValueResult<>(result);
    }
}
