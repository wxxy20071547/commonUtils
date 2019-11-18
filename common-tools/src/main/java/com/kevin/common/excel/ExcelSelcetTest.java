package com.kevin.common.excel;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.xuxueli.poi.excel.ExcelExportUtil;

/**
 * @author zhengkun
 * @date 2019/11/13 16:54
 */
public class ExcelSelcetTest {

    public static void main(String[] args) {
        String[] list = {"东软", "华信", "SAP", "海辉"};
        new ExcelSelcetTest().createListBox(list);

        return;

    }

    public void createListBox(String[] list)

    {

        List<ShopDTO> shopDTOList = new ArrayList<ShopDTO>();
        for (int i = 0; i < 100; i++) {
            ShopDTO shop = new ShopDTO(true, "商户" + i, (short)i, 1000 + i, 10000 + i, (float)(1000 + i),
                (double)(10000 + i), new Date());
            shopDTOList.add(shop);
        }
        String filePath = "/Users/zhengkun/kevin/document/demo-kevin-sheet.xls";

        /**
         * Excel导出：Object 转换为 Excel
         */
        ExcelExportUtil.exportToFile(filePath, shopDTOList);
        Workbook wb = ExcelExportUtil.exportWorkbook(shopDTOList);
        Sheet sheet = wb.getSheetAt(0);

        // 只对（0，0）单元格有效

        CellRangeAddressList regions = new CellRangeAddressList(0, 500, 0, 0);

        // 生成下拉框内容

        DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);

        // 绑定下拉框和作用区域

        HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

        // 对sheet页生效

        sheet.addValidationData(data_validation);

        // 写入文件

        FileOutputStream fileOut;

        try {

            fileOut = new FileOutputStream("/Users/zhengkun/kevin/document/workbook.xls");

            wb.write(fileOut);

            fileOut.close();

        } catch (Exception e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        // 结束

        System.out.println("Over");

    }
}
