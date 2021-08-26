package Parsers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    private Workbook workbook;
    private List<List<String>> resultTable;


    public void parse(File file) {
        if (file.getName().matches(".+.xls")) {
            try (FileInputStream fileXls = new FileInputStream(file)) {
                workbook = new HSSFWorkbook(fileXls);
                doSomethnig(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try (FileInputStream fileXlsx = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fileXlsx);
                doSomethnig(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doSomethnig(File file) {
        ValidRowFinder validRowFinder = new ValidRowFinder();
        Row row = validRowFinder.validRowFinder(workbook);
        if (row != null) {
            resultTable = parseTable(row, file);
        } else {
            System.out.println("В файле " + file.getName() + " нет подходящей таблицы");
        }
    }

    private List<List<String>> parseTable(Row row, File file) {
        List<List<String>> table = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = row.getSheet();
        for (int i = row.getRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            List<String> rows = new ArrayList<>();
            Row row1 = sheet.getRow(i);
            for (Cell cell : row1) {
                String string = dataFormatter.formatCellValue(cell);
                if (string.isEmpty()) {
                    string = "0";
                }
                rows.add(string);
            }
            if (dataFormatter.formatCellValue(row1.getCell(0)).isEmpty() || dataFormatter.formatCellValue(row1.getCell(2)).isEmpty()) {
                continue;
            }
            rows.add(file.getName().substring(0, file.getName().lastIndexOf("_")));
            rows.add(file.getName().substring(file.getName().lastIndexOf("_") + 1));
            table.add(rows);
        }
        return table;
    }

    public List<List<String>> getResultTable() {
        return resultTable;
    }


}
