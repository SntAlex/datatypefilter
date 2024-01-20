package org.alexgolikov.cli;

import org.alexgolikov.cli.contract.CommandLineParsable;
import org.alexgolikov.cli.model.FileWriterConfiguration;
import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.cli.model.StatisticsConfiguration;
import org.alexgolikov.configuration.model.OptionConfiguration;
import org.alexgolikov.configuration.model.OptionsConfiguration;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.apache.commons.cli.*;

import java.util.HashSet;
import java.util.List;

public class OptionsParser implements CommandLineParsable {

    @Override
    public ServiceValueResult<ParsedData> parse(OptionsConfiguration configuration, String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            Options options = buildOptions(configuration.getAvailableOptions());

            CommandLine cmd = parser.parse(options, args);

            FileWriterConfiguration fileWriterConfiguration = new FileWriterConfiguration(
                    cmd.hasOption(configuration.getMappedOptions().getPrefix()) ? cmd.getOptionValue(configuration.getMappedOptions().getPrefix()) : "",
                    cmd.hasOption(configuration.getMappedOptions().getPath()) ? cmd.getOptionValue(configuration.getMappedOptions().getPath()) : "",
                    cmd.hasOption(configuration.getMappedOptions().getAdding())
            );

            StatisticsConfiguration statisticsConfiguration = cmd.hasOption(configuration.getMappedOptions().getShortStatistics()) || cmd.hasOption(configuration.getMappedOptions().getFullStatistics()) ?
                    new StatisticsConfiguration(cmd.hasOption(configuration.getMappedOptions().getFullStatistics())) :
                    new StatisticsConfiguration();

            List<String> files = cmd.getArgList();

            if (files.isEmpty()) {
                return new ServiceValueResult<>("One or more files to filter should be used");
            }

            ParsedData parsedData = new ParsedData(new HashSet<>(files), fileWriterConfiguration, statisticsConfiguration);

            return new ServiceValueResult<>(parsedData);
        } catch (ParseException ex) {
            return new ServiceValueResult<>(ex, ex.getMessage());
        }
    }

    private Options buildOptions(List<OptionConfiguration> optionConfigurations) {
        Options options = new Options();

        for (OptionConfiguration optionConfiguration : optionConfigurations) {
            options.addOption(new Option(optionConfiguration.getOption(), optionConfiguration.getAnyAdditionalData(), optionConfiguration.getDescription()));
        }

        return options;
    }
}
