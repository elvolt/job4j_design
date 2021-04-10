package ru.job4j.io.serialization;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "drive")
public class Drive {

    @XmlAttribute
    private String vendor;
    @XmlAttribute
    private int memoryInMB;
    @XmlAttribute
    private String type;

    public Drive() { }

    public Drive(String vendor, int memoryInMB, String type) {
        this.vendor = vendor;
        this.memoryInMB = memoryInMB;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Drive{"
                + "vendor='" + vendor + '\''
                + ", memoryInMB=" + memoryInMB
                + ", type='" + type + '\''
                + '}';
    }
}
