package org.alexgolikov.services.cli;

import org.alexgolikov.services.ServiceValueResult;
import org.alexgolikov.services.cli.configuration.OptionConfigurationsBuilder;
import org.alexgolikov.services.cli.contract.OptionParsable;
import org.alexgolikov.services.cli.exceptions.DuplicateFileException;
import org.alexgolikov.services.cli.exceptions.DuplicateOptionException;
import org.alexgolikov.services.cli.options.Options;

import java.util.List;
import java.util.Map;

public class OptionsParser implements OptionParsable {

    //TODO: segregate logic to help methods
    @Override
    public ServiceValueResult<Options> parse(List<String> parsingData) {
        Map<String, Boolean> availableConfigurations = getAvailableConfigurations();

        Options options = new Options();

        for (int i = 0; i < parsingData.size(); i++) {
            String option = parsingData.get(i);

            if (availableConfigurations.containsKey(option)) {

                try {
                    options.addAdditionalOption(option);
                } catch (DuplicateOptionException ex) {
                    return new ServiceValueResult<>(ex.getMessage());
                }

                Boolean isAnyAdditionalData = availableConfigurations.get(option);

                if (isAnyAdditionalData) {
                    i++;
                    String additionalValue = parsingData.get(i); //TODO: check for error
                    options.insertAdditionalData(option, additionalValue); //TODO: check for exception
                }

                continue;
            }

            try {
                options.addFile(option);
            } catch (DuplicateFileException ex) {
                return new ServiceValueResult<>(ex.getMessage());
            }
        }

        return new ServiceValueResult<>(options);
    }

    private Map<String, Boolean> getAvailableConfigurations() {
        return new OptionConfigurationsBuilder()
                .addConfiguration("-a", false)
                .addConfiguration("-s", false)
                .addConfiguration("-f", false)
                .addConfiguration("-o", true)
                .addConfiguration("-p", true)
                .getResult();
    }
}
