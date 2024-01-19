package org.alexgolikov.cli;

import org.alexgolikov.cli.contract.CommandLineParsable;
import org.alexgolikov.cli.model.FileWriterConfiguration;
import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.cli.model.StatisticConfiguration;
import org.alexgolikov.resultdata.ServiceValueResult;
import org.apache.commons.cli.*;

import java.util.HashSet;
import java.util.List;

public class OptionsParser implements CommandLineParsable {
    @Override
    public ServiceValueResult<ParsedData> parse(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            FileWriterConfiguration fileWriterConfiguration = new FileWriterConfiguration(
                    cmd.hasOption("p") ? cmd.getOptionValue("p") : "",
                    cmd.hasOption("o") ? cmd.getOptionValue("o") : "",
                    cmd.hasOption("a")
            );

            StatisticConfiguration statisticConfiguration = cmd.hasOption("s") || cmd.hasOption("f") ?
                    new StatisticConfiguration(cmd.hasOption("f")) :
                    new StatisticConfiguration();

            List<String> files = cmd.getArgList();

            ParsedData parsedData = new ParsedData(new HashSet<>(files), fileWriterConfiguration, statisticConfiguration);

            return new ServiceValueResult<>(parsedData);
        } catch (ParseException ex) {
            return new ServiceValueResult<>(ex, ex.getMessage());
        }
    }
}
