package com.ruoyi.system.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/4/23
 * @className ReduceParams
 */
public class ReduceParams {

    private String name;
    private String format;
    private String unit;
    private List<Long> srcIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Long> getSrcIds() {
        return srcIds;
    }

    public void setSrcIds(List<Long> srcIds) {
        this.srcIds = srcIds;
    }
}
