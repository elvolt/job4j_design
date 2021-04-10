package ru.job4j.io.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "notebook")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notebook {
    @XmlAttribute
    private String vendor;
    private CPU cpu;
    @XmlElementWrapper(name = "drives")
    @XmlElement(name = "drive")
    private Drive[] drives;
    @XmlAttribute
    private int vendorCode;
    @XmlAttribute
    private boolean isNew;

    public Notebook() { }

    public Notebook(String vendor, CPU cpu, Drive[] drives, int vendorCode, boolean isNew) {
        this.vendor = vendor;
        this.cpu = cpu;
        this.drives = drives;
        this.vendorCode = vendorCode;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Notebook{"
                + "vendor='" + vendor + '\''
                + ", cpu=" + cpu
                + ", drives=" + Arrays.toString(drives)
                + ", vendorCode=" + vendorCode
                + ", isNew=" + isNew
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Drive drive1 = new Drive("Green", 512, "SSD");
        Drive drive2 = new Drive("Samsung", 1024, "HDD");
        Drive[] drives = new Drive[] {drive1, drive2};
        CPU cpu = new CPU("Intel", 3.2);
        Notebook notebook = new Notebook("DELL", cpu, drives, 1234567, true);

        JAXBContext context = JAXBContext.newInstance(Notebook.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(notebook, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Notebook result = (Notebook) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
