package com.kevin.common.excel;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by kevin on 2019/1/26.
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet(name = "商户列表", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class ShopDTO {

    @ExcelField(name = "是否VIP商户")
    private boolean vip;

    @ExcelField(name = "商户名称", align = HorizontalAlignment.CENTER)
    private String shopName;

    @ExcelField(name = "别名")
    private String shopNameAlias;

    @ExcelField(name = "分店数量")
    private short branchNum;

    @ExcelField(name = "商户ID")
    private int shopId;

    @ExcelField(name = "浏览人数")
    private long visitNum;

    @ExcelField(name = "当月营业额")
    private float turnover;

    @ExcelField(name = "历史营业额")
    private double totalTurnover;

    // @ExcelField(name = "开店时间", dateformat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public ShopDTO(boolean vip, String shopName, short branchNum, int shopId, long visitNum, float turnover,
        double totalTurnover, Date addTime) {
        this.vip = vip;
        this.shopName = shopName;
        this.shopNameAlias = "统一";
        this.branchNum = branchNum;
        this.shopId = shopId;
        this.visitNum = visitNum;
        this.turnover = turnover;
        this.totalTurnover = totalTurnover;
        this.addTime = addTime;

    }

    public static void main(String[] args) throws Exception {
        String[] countryName = {};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet realSheet = workbook.createSheet("Sheet xls");
        HSSFSheet hidden = workbook.createSheet("hidden");
        for (int i = 0, length = countryName.length; i < length; i++) {
            String name = countryName[i];
            HSSFRow row = hidden.createRow(i);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(name);
        }
        Name namedCell = workbook.createName();
        namedCell.setNameName("hidden");
        namedCell.setRefersToFormula("hidden!A1:A" + countryName.length);
        DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden");
        CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0);
        HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
        workbook.setSheetHidden(1, true);
        realSheet.addValidationData(validation);
        FileOutputStream stream = new FileOutputStream("c:\\range.xls");
        workbook.write(stream);
        stream.close();
    }

}
