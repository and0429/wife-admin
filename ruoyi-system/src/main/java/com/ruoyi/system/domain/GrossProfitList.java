package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 毛利列表对象 b_gross_profit_list
 * 
 * @author zhangkai
 * @date 2021-04-22
 */
public class GrossProfitList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 名称 */
    @Excel(name = "商品")
    private String name;

    /** 规格 */
    @Excel(name = "规格")
    private String format;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 实际数量 */
    @Excel(name = "实际数量")
    private BigDecimal count;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 实际金额 */
    @Excel(name = "实际金额")
    private BigDecimal amont;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFormat(String format) 
    {
        this.format = format;
    }

    public String getFormat() 
    {
        return format;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setCount(BigDecimal count) 
    {
        this.count = count;
    }

    public BigDecimal getCount() 
    {
        return count;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setAmont(BigDecimal amont) 
    {
        this.amont = amont;
    }

    public BigDecimal getAmont() 
    {
        return amont;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("format", getFormat())
            .append("category", getCategory())
            .append("unit", getUnit())
            .append("count", getCount())
            .append("price", getPrice())
            .append("amont", getAmont())
            .toString();
    }
}
