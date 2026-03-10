package model;

import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FileResult {

    private String name;
    private String path;
    private long size;
    private String modified;

    public FileResult(String name, Path path, long size, long modifiedTime) {

        this.name = name;
        this.path = path.toString();
        this.size = size;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .withZone(ZoneId.systemDefault());

        this.modified = formatter.format(Instant.ofEpochMilli(modifiedTime));
    }

    public String getName() { return name; }
    public String getPath() { return path; }
    public long getSize() { return size; }
    public String getModified() { return modified; }
}