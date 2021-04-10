package ru.job4j.io.serialization;

public class Drive {
    private String vendor;
    private int memoryInMB;
    private String type;

    public Drive(String vendor, int memoryInMB, String type) {
        this.vendor = vendor;
        this.memoryInMB = memoryInMB;
        this.type = type;
    }
}
