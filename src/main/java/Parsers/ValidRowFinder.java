package Parsers;

import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;

class ValidRowFinder {
    private final static String REFERENCE_STRING = "АБВ12345678910111213141516171819202122232425";

    Row validRowFinder(Workbook workbook) {
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String formatCell = dataFormatter.formatCellValue(cell);
                    if (formatCell.equals("А")) {
                        Iterator<Cell> testCells = row.iterator();
                        StringBuilder testString = new StringBuilder();
                        while (testCells.hasNext()) {
                            Cell testCell = testCells.next();
                            testString.append(dataFormatter.formatCellValue(testCell));
                        }
                        boolean rowIsValid = testString.toString().equals(REFERENCE_STRING);
                        if (rowIsValid) {
                            return row;
                        }
                    }
                }
            }
        }
        return null;
    }
}
