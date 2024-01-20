package org.alexgolikov;

import org.alexgolikov.cli.OptionsParser;
import org.alexgolikov.cli.contract.CommandLineParsable;
import org.alexgolikov.cli.model.FileWriterConfiguration;
import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.cli.model.StatisticsConfiguration;
import org.alexgolikov.configuration.ConfigurationJsonParser;
import org.alexgolikov.configuration.contract.ConfigurationParsable;
import org.alexgolikov.configuration.model.OptionsConfiguration;
import org.alexgolikov.file.reader.FileReader;
import org.alexgolikov.file.reader.FilesReader;
import org.alexgolikov.file.reader.contract.FilesReadable;
import org.alexgolikov.file.writer.FilesWriter;
import org.alexgolikov.file.writer.TypeListFileWriter;
import org.alexgolikov.file.writer.contract.FilesWritable;
import org.alexgolikov.filter.DataTypeFilter;
import org.alexgolikov.filter.contract.TypeFilterable;
import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.shared.model.ServiceResult;
import org.alexgolikov.shared.model.ServiceValueResult;
import org.alexgolikov.statistics.StatisticsHandler;
import org.alexgolikov.statistics.contract.Statistical;
import org.alexgolikov.statistics.model.StatisticsFull;
import org.alexgolikov.statistics.model.StatisticsShort;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILENAME = "appsettings.json";

    public static void main(String[] args) {
        ConfigurationParsable configurationJsonParser = new ConfigurationJsonParser();
        ServiceValueResult<OptionsConfiguration> optionsResult = configurationJsonParser.retrieveParserOptions(FILENAME);

        closeIfServiceError(optionsResult);

        CommandLineParsable parser = new OptionsParser();
        ServiceValueResult<ParsedData> parsedDataResult = parser.parse(optionsResult.getValue(), args);

        closeIfServiceError(parsedDataResult);

        FilesReadable filesReader = new FilesReader(new FileReader());
        ServiceValueResult<List<String>> fileDataResult = filesReader.readAllFiles(new ArrayList<>(parsedDataResult.getValue().getInputFiles()));

        closeIfServiceError(fileDataResult);

        TypeFilterable typeFilter = new DataTypeFilter();
        ServiceValueResult<TypeData> typeDataResult = typeFilter.filterToTypes(fileDataResult.getValue());

        closeIfServiceError(typeDataResult);

        FileWriterConfiguration writerConfiguration = parsedDataResult.getValue().getFileWriterConfiguration();
        FilesWritable filesWriter = new FilesWriter(new TypeListFileWriter());
        ServiceResult writtenFilesResult = filesWriter.writeToFiles(writerConfiguration.getPath(), writerConfiguration.getPrefix(), writerConfiguration.isAdding(), typeDataResult.getValue());

        closeIfServiceError(writtenFilesResult);

        StatisticsConfiguration statisticsConfiguration = parsedDataResult.getValue().getStatisticsConfiguration();

        if (statisticsConfiguration.isStatisticEnabled()) {
            Statistical statisticService = new StatisticsHandler();
            ServiceValueResult<StatisticsShort> statisticsResult = statisticService.analyse(statisticsConfiguration.isFullData(), typeDataResult.getValue());

            closeIfServiceError(statisticsResult);

            showStatistics(statisticsConfiguration.isFullData(), statisticsResult.getValue());
        }
    }

    private static void closeIfServiceError(ServiceResult result) {
        if (!result.isSuccessful()) {
            System.out.println(result.getErrorMessage());
            System.exit(0);
        }
    }

    private static void showStatistics(Boolean isFullData, StatisticsShort statistic) {
        System.out.printf("Integers amount: %s%n", statistic.getIntegersCount());
        System.out.printf("Floats amount: %s%n", statistic.getDoublesCount());
        System.out.printf("Strings amount: %s%n", statistic.getStringsCount());

        if (isFullData && statistic instanceof StatisticsFull) {
            StatisticsFull statisticsFull = (StatisticsFull) statistic;

            System.out.printf("Minimal number: %s%n", statisticsFull.getStatisticFullData().getMinimalValue());
            System.out.printf("Maximum number: %s%n", statisticsFull.getStatisticFullData().getMaximumValue());
            System.out.printf("Sum of numbers: %s%n", statisticsFull.getStatisticFullData().getSumValue());
            System.out.printf("Average of numbers: %s%n", statisticsFull.getStatisticFullData().getAverageValue());
            System.out.printf("Size of shortest string: %s%n", statisticsFull.getStatisticFullData().getShortestStringSize());
            System.out.printf("Size of longest string: %s%n", statisticsFull.getStatisticFullData().getLongestStringSize());
        }
    }
}