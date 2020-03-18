package com.cw;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author david.cai
 * @date 2019/12/25
 */
@Data
public class DemoData {
    @ExcelProperty
    private String ip;
    @ExcelProperty
    private String province;
    @ExcelProperty
    private String line;
}
