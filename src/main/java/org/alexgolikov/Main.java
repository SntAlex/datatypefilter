package org.alexgolikov;

import org.alexgolikov.cli.OptionsParser;
import org.alexgolikov.cli.contract.CommandLineParsable;
import org.alexgolikov.cli.model.FileWriterConfiguration;
import org.alexgolikov.cli.model.ParsedData;
import org.alexgolikov.cli.model.StatisticConfiguration;
import org.alexgolikov.configuration.ConfigurationJsonParser;
import org.alexgolikov.configuration.contract.ConfigurationParsable;
import org.alexgolikov.file.reader.FileReader;
import org.alexgolikov.file.reader.FilesReader;
import org.alexgolikov.file.reader.contract.FilesReadable;
import org.alexgolikov.file.writer.FilesWriter;
import org.alexgolikov.file.writer.TypeListFileWriter;
import org.alexgolikov.file.writer.contract.FilesWritable;
import org.alexgolikov.filter.DataTypeFilter;
import org.alexgolikov.filter.contract.TypeFilterable;
import org.alexgolikov.filter.model.TypeData;
import org.alexgolikov.resultdata.ServiceResult;
import org.alexgolikov.resultdata.ServiceValueResult;
import org.alexgolikov.statistic.StatisticHandler;
import org.alexgolikov.statistic.contract.Statistical;
import org.alexgolikov.statistic.model.StatisticFull;
import org.alexgolikov.statistic.model.StatisticShort;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILENAME = "appsettings.json";

    public static void main(String[] args) {
        ConfigurationParsable configurationJsonParser = new ConfigurationJsonParser();

        ServiceValueResult<Options> optionsResult = configurationJsonParser.retrieveParserOptions(FILENAME);

        if (!optionsResult.isSuccessful()) {
            System.out.println(optionsResult.getErrorMessage());
            return;
        }

        CommandLineParsable parser = new OptionsParser();
        ServiceValueResult<ParsedData> parsedDataResult = parser.parse(optionsResult.getValue(), args);

        if (!parsedDataResult.isSuccessful()) {
            System.out.println(parsedDataResult.getErrorMessage());
            return;
        }

        FilesReadable filesReader = new FilesReader(new FileReader());
        ServiceValueResult<List<String>> fileDataResult = filesReader.readAllFiles(new ArrayList<>(parsedDataResult.getValue().getInputFiles()));

        if (!fileDataResult.isSuccessful()) {
            System.out.println(fileDataResult.getErrorMessage());
            return;
        }

        TypeFilterable typeFilter = new DataTypeFilter();
        ServiceValueResult<TypeData> typeDataResult = typeFilter.filterToTypes(fileDataResult.getValue());

        if (!typeDataResult.isSuccessful()) {
            System.out.println(typeDataResult.getErrorMessage());
            return;
        }

        FileWriterConfiguration writerConfiguration = parsedDataResult.getValue().getFileWriterConfiguration();
        FilesWritable filesWriter = new FilesWriter(new TypeListFileWriter());
        ServiceResult writtenFilesResult = filesWriter.writeToFiles(writerConfiguration.getPath(), writerConfiguration.getPrefix(), writerConfiguration.isAdding(), typeDataResult.getValue());

        if (!writtenFilesResult.isSuccessful()) {
            System.out.println(writtenFilesResult.getErrorMessage());
            return;
        }

        StatisticConfiguration statisticConfiguration = parsedDataResult.getValue().getStatisticConfiguration();

        if (statisticConfiguration.isStatisticEnabled()) {
            Statistical statisticService = new StatisticHandler();
            ServiceValueResult<StatisticShort> statisticResult = statisticService.analyse(statisticConfiguration.isFullData(), typeDataResult.getValue());

            if (!statisticResult.isSuccessful()) {
                System.out.println(statisticResult.getErrorMessage());
                return;
            }

            showStatistic(statisticConfiguration.isFullData(), statisticResult.getValue());
        }
    }

    private static void showStatistic(Boolean isFullData, StatisticShort statistic) {
        System.out.printf("Integers amount: %s%n", statistic.getIntegersCount());
        System.out.printf("Floats amount: %s%n", statistic.getDoublesCount());
        System.out.printf("Strings amount: %s%n", statistic.getStringsCount());

        if (isFullData && statistic instanceof StatisticFull) {
            StatisticFull statisticFull = (StatisticFull) statistic;

            System.out.printf("Minimal number: %s%n", statisticFull.getStatisticFullData().getMinimalValue());
            System.out.printf("Maximum number: %s%n", statisticFull.getStatisticFullData().getMaximumValue());
            System.out.printf("Sum of numbers: %s%n", statisticFull.getStatisticFullData().getSumValue());
            System.out.printf("Average of numbers: %s%n", statisticFull.getStatisticFullData().getAverageValue());
            System.out.printf("Size of shortest string: %s%n", statisticFull.getStatisticFullData().getShortestStringSize());
            System.out.printf("Size of longest string: %s%n", statisticFull.getStatisticFullData().getLongestStringSize());
        }
    }
}