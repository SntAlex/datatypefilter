package org.alexgolikov;

import org.alexgolikov.services.ServiceValueResult;
import org.alexgolikov.services.cli.OptionsParser;
import org.alexgolikov.services.cli.contract.OptionParsable;
import org.alexgolikov.services.cli.options.Options;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        OptionParsable parser = new OptionsParser();
        ServiceValueResult<Options> optionsResult = parser.parse(Arrays.asList(args));

        if (!optionsResult.isSuccessful()) {
            System.out.println(optionsResult.getErrorMessage());
        }
    }
}