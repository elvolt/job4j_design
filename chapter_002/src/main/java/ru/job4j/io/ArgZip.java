package ru.job4j.io;

import org.apache.commons.cli.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArgZip {

    private final String[] args;
    private String directory;
    private String outputZip;
    private List<String> excludeExtensions = new ArrayList<>();

    public ArgZip(String[] args) {
        this.args = args;
        parse();
        valid();
    }

    public boolean valid() {
        if (directory() == null || exclude() == null || output() == null) {
            throw new IllegalArgumentException("Wrong arguments count");
        }
        File dir = new File(directory());
        if (dir.isFile() || !dir.exists()) {
            throw new IllegalArgumentException("Wrong argument -d");
        }
        if (!output().endsWith(".zip")) {
            throw new IllegalArgumentException("Wrong argument -o");
        }
        return true;
    }

    public String directory() {
        return directory;
    }

    public List<String> exclude() {
        return excludeExtensions;
    }

    public String output() {
        return outputZip;
    }

    private void parse() {
        Options options = new Options();
        Option directory = Option.builder("d")
                .longOpt("directory")
                .hasArg()
                .numberOfArgs(1)
                .required(true)
                .build();
        Option output = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .numberOfArgs(1)
                .required(true)
                .build();
        Option exclude = Option.builder("e")
                .longOpt("exclude")
                .hasArg()
                .hasArgs()
                .valueSeparator('\u0020')
                .required(true)
                .build();
        options.addOption(directory);
        options.addOption(output);
        options.addOption(exclude);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("d")) {
                this.directory = line.getOptionValue("directory");
            }
            if (line.hasOption("o")) {
                this.outputZip = line.getOptionValue("output");
            }
            if (line.hasOption("e")) {
                this.excludeExtensions = List.of(line.getOptionValues("exclude"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}