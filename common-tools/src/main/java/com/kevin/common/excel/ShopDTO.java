package com.kevin.common.excel;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.Date;

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

    @ExcelField(name = "开店时间", dateformat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public ShopDTO(boolean vip, String shopName, short branchNum, int shopId, long visitNum, float turnover, double totalTurnover, Date addTime) {
        this.vip = vip;
        this.shopName = shopName;
        this.shopNameAlias="统一";
        this.branchNum = branchNum;
        this.shopId = shopId;
        this.visitNum = visitNum;
        this.turnover = turnover;
        this.totalTurnover = totalTurnover;
        this.addTime =addTime;

    }



}


