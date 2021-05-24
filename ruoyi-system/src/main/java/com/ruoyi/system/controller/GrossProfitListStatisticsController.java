package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GrossProfitListStatistics;
import com.ruoyi.system.service.IGrossProfitListStatisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 统计列Controller
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@RestController
@RequestMapping("/system/invstatistics")
public class GrossProfitListStatisticsController extends BaseController {
    @Autowired
    private IGrossProfitListStatisticsService grossProfitListStatisticsService;


    @GetMapping("/amont/suming")
    public AjaxResult amontSum() {
        return AjaxResult.success(grossProfitListStatisticsService.amontSum());
    }

    /**
     * 查询统计列列表
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(GrossProfitListStatistics grossProfitListStatistics) {
        startPage();
        List<GrossProfitListStatistics> list = grossProfitListStatisticsService.selectGrossProfitListStatisticsList(grossProfitListStatistics);
        return getDataTable(list);
    }

    /**
     * 导出统计列列表
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:export')")
    @Log(title = "统计列", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GrossProfitListStatistics grossProfitListStatistics) {
        List<GrossProfitListStatistics> list = grossProfitListStatisticsService.selectGrossProfitListStatisticsList(grossProfitListStatistics);
        ExcelUtil<GrossProfitListStatistics> util = new ExcelUtil<GrossProfitListStatistics>(GrossProfitListStatistics.class);
        return util.exportExcel(list, "统计列数据");
    }

    /**
     * 获取统计列详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(grossProfitListStatisticsService.selectGrossProfitListStatisticsById(id));
    }

    /**
     * 新增统计列
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:add')")
    @Log(title = "统计列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GrossProfitListStatistics grossProfitListStatistics) {
        return toAjax(grossProfitListStatisticsService.insertGrossProfitListStatistics(grossProfitListStatistics));
    }

    /**
     * 修改统计列
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:edit')")
    @Log(title = "统计列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GrossProfitListStatistics grossProfitListStatistics) {
        return toAjax(grossProfitListStatisticsService.updateGrossProfitListStatistics(grossProfitListStatistics));
    }

    /**
     * 删除统计列
     */
    @PreAuthorize("@ss.hasPermi('system:invstatistics:remove')")
    @Log(title = "统计列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(grossProfitListStatisticsService.deleteGrossProfitListStatisticsByIds(ids));
    }
}
