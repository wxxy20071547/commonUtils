package com.kevin.common.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.xuxueli.poi.excel.ExcelExportUtil;

/**
 * Created by kevin on 2019/1/26.
 */
public class ExcelTest {
    public static void main(String[] args) {
        /**
         * Mock数据，Java对象列表
         */
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

        /**
         * Excel导入：Excel 转换为 Object
         */
        // List<Object> list = ExcelImportUtil.importExcel(filePath, ShopDTO.class);

        // System.out.println(list);

    }

}
