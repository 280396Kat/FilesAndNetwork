package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindFiles {
    private static List<String> nameJSONFiles;
    private static List<String> nameCSVFiles;

    public FindFiles(String path) {
        nameJSONFiles = new ArrayList<>();
        nameCSVFiles = new ArrayList<>();
        File file = new File(path);
        searchFiles(file);
    }

    public void searchFiles(File file) {
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            if (file1.isDirectory()) {
                searchFiles(file1);
            } else if (file1.isFile()) {
                addCollection(file1);
            }
        }
    }

    private void addCollection(File file) {
        if (file.getName().matches(".+\\.json$")) {
            nameJSONFiles.add(file.getAbsolutePath());
        } else if (file.getName().matches(".+\\.csv$")) {
            nameCSVFiles.add(file.getAbsolutePath());
        }
    }

    public List<String> getNameJSONFiles() {
        return nameJSONFiles;
    }

    public List<String> getNameCSVFiles() {
        return nameCSVFiles;
    }
}
