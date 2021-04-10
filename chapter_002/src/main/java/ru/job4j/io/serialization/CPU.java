package ru.job4j.io.serialization;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "cpu")
public class CPU {

    @XmlAttribute
    private String vendor;
    @XmlAttribute
    private double frequencyInMHz;

    public CPU() { }

    public CPU(String vendor, double frequencyInMHz) {
        this.vendor = vendor;
        this.frequencyInMHz = frequencyInMHz;
    }

    @Override
    public String toString() {
        return "CPU{"
                + "vendor='" + vendor + '\''
                + ", frequencyInMHz=" + frequencyInMHz
                + '}';
    }
}
