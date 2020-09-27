package ru.job4j.io.find;

import org.apache.commons.cli.*;

import java.io.File;

public class ArgsParser {
    private final String[] args;
    private String directory;
    private String fileName;
    private String findMode;
    private String output;
    private boolean helpActive = false;

    public ArgsParser(String[] args) {
        this.args = args;
        parse();
        valid();
    }

    public String getDirectory() {
        return directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFindMode() {
        return findMode;
    }

    public String getOutput() {
        return output;
    }

    public boolean isHelpActive() {
        return helpActive;
    }

    private void valid() {
        if (helpActive) {
            return;
        }
        if (getDirectory() == null || getFileName() == null
                || getOutput() == null || getFindMode() == null) {
            throw new IllegalArgumentException(
                    "Wrong arguments count. Use --help option to see usage");
        }
        File dir = new File(getDirectory());
        if (dir.isFile() || !dir.exists()) {
            throw new IllegalArgumentException(
                    "Wrong argument -d. Use --help option to see usage");
        }
    }

    private void parse() {
        Options options = new Options();
        Option directory = Option.builder("d")
                .longOpt("directory")
                .hasArg()
                .numberOfArgs(1)
                .desc("Directory for search")
                .build();
        Option name = Option.builder("n")
                .longOpt("name")
                .hasArg()
                .numberOfArgs(1)
                .desc("Name, mask, or RegExp")
                .build();
        Option output = Option.builder("o")
                .longOpt("output")
                .hasArg()
                .numberOfArgs(1)
                .desc("Path to file with results")
                .build();
        options.addOption(directory);
        options.addOption(name);
        options.addOption(output);
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(
                new Option("m", "mask mode", false, "Turn on search by mask.")
        );
        optionGroup.addOption(
                new Option("f", "full name mode", false, "Turn on search by full name.")
        );
        optionGroup.addOption(
                new Option("r", "regexp mode", false, "Turn on search by RegExp.")
        );
        options.addOptionGroup(optionGroup);
        options.addOption(new Option("h", "help", false, "Print this message"));
        String header = "Find files by full name, mask or RegExp and write them to file.";
        String footer = "Good luck!";
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);
            HelpFormatter formatter = new HelpFormatter();
            if (line.hasOption("h")) {
                this.helpActive = true;
                formatter.printHelp("finder", header, options, footer, true);
            }
            if (line.hasOption("d")) {
                this.directory = line.getOptionValue("directory");
            }
            if (line.hasOption("n")) {
                this.fileName = line.getOptionValue("name");
            }
            if (line.hasOption("o")) {
                this.output = line.getOptionValue("output");
            }
            if (line.hasOption("m")) {
                if (findMode != null) {
                    throw new IllegalArgumentException("Turn on only one mode");
                }
                this.findMode = "mask";
            }
            if (line.hasOption("f")) {
                if (findMode != null) {
                    throw new IllegalArgumentException("Turn on only one mode");
                }
                this.findMode = "full";
            }
            if (line.hasOption("r")) {
                if (findMode != null) {
                    throw new IllegalArgumentException("Turn on only one mode");
                }
                this.findMode = "RegExp";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
