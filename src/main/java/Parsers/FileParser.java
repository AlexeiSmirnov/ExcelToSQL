package Parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private List<File> excelFilesList = new ArrayList<>();

    public FileParser(String path) {

        File dir = new File(path);

        for (File file : dir.listFiles()) {
            if (file.isFile() & file.getName().matches(".+.xls[x]?")) {
                excelFilesList.add(file);
            }
        }
    }

    public List<File> getExcelFilesList() {
        return excelFilesList;
    }
}
