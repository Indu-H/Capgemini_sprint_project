package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public String getDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum)throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fis);

        DataFormatter formatter = new DataFormatter();

        String data = formatter.formatCellValue(workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum));

        workbook.close();
        fis.close();

        return data;
    }
}
