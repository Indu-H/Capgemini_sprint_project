package genericUtility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() throws IOException {

        ExcelUtility excelUtility = new ExcelUtility();

        Object[][] data = new Object[1][2];

        data[0][0] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 0);
        data[0][1] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 1);

        return data;
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() throws IOException {

        ExcelUtility excelUtility = new ExcelUtility();

        Object[][] data = new Object[2][2];

        data[0][0] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 2, 0);
        data[0][1] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 2, 1);

        data[1][0] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 3, 0);
        data[1][1] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 3, 1);

        return data;
    }

    @DataProvider(name = "purchaseData")
    public Object[][] getPurchaseData() throws IOException {

        ExcelUtility excelUtility = new ExcelUtility();

        Object[][] data = new Object[1][5];

        data[0][0] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 0);
        data[0][1] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 1);
        data[0][2] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 2);
        data[0][3] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 3);
        data[0][4] = excelUtility.getDataFromExcel("./src/test/resources/DDT/SwagLabsLogin.xlsx", "Sheet1", 1, 4);

        return data;
    }
}