package dataprovider;

import org.testng.annotations.DataProvider;
import utils.ExcelUtil;

public class ExcelDataProvider {
    @DataProvider
    public static Object[][] loginData(){
        return ExcelUtil.getExcelData("src/test/resources/testData/TestData.xlsx","Sheet1");
    }
}
