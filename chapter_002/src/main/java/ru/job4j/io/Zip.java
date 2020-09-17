package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target, File root) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target))
        )) {
            for (File source : sources) {
                if (source.isFile()) {
                    ZipEntry entry = new ZipEntry(
                            source.getAbsolutePath().substring(
                                    root.getAbsolutePath().length() - root.getName().length()
                            )
                    );
                    zout.putNextEntry(entry);
                    try (BufferedInputStream out = new BufferedInputStream(
                            new FileInputStream(source))
                    ) {
                        zout.write(out.readAllBytes());
                    }
                }
                if (source.isDirectory() && source.listFiles() != null) {
                    packFiles(List.of(source.listFiles()), target, root);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}