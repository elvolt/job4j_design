package ru.job4j.io.serialization;

public class Notebook {
    private String vendor;
    private CPU cpu;
    private Drive[] drives;
    private int vendorCode;
    private boolean isNew;

    public Notebook(String vendor, CPU cpu, Drive[] drives, int vendorCode, boolean isNew) {
        this.vendor = vendor;
        this.cpu = cpu;
        this.drives = drives;
        this.vendorCode = vendorCode;
        this.isNew = isNew;
    }

    public static void main(String[] args) {
        Drive drive1 = new Drive("Green", 512, "SSD");
        Drive drive2 = new Drive("Samsung", 1024, "HDD");
        Drive[] drives = new Drive[] {drive1, drive2};
        CPU cpu = new CPU("Inter", 3.2);
        Notebook notebook = new Notebook("DELL", cpu, drives, 1234567, true);
    }
}
