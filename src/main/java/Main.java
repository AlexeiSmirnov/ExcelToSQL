import Parsers.ExcelParser;
import Parsers.FileParser;
import SQL.TableActions;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = "Nalog/To_load";

        FileParser fileParser = new FileParser(path);
        List<File> list = fileParser.getExcelFilesList();
        ExcelParser excelParser = new ExcelParser();
        List<List<String>> result;
        TableActions tableActions = new TableActions();
        tableActions.createTable();
        for (File file : list)
        {
            excelParser.parse(file);
            result = excelParser.getResultTable();
            tableActions.addValues(result);
        }
    }
}
