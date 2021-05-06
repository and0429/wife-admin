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
import com.ruoyi.system.domain.StandardItem;
import com.ruoyi.system.service.IStandardItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标准类目Controller
 *
 * @author zhangkai
 * @date 2021-04-23
 */
@RestController
@RequestMapping("/system/item")
public class StandardItemController extends BaseController {
    @Autowired
    private IStandardItemService standardItemService;

    /**
     * 查询标准类目列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(StandardItem standardItem) {
        startPage();
        List<StandardItem> list = standardItemService.findAll(standardItem);
        return getDataTable(list);
    }

    /**
     * 导出标准类目列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:export')")
    @Log(title = "标准类目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StandardItem standardItem) {
        List<StandardItem> list = standardItemService.findAll(standardItem);
        ExcelUtil<StandardItem> util = new ExcelUtil<>(StandardItem.class);
        return util.exportExcel(list, "标准类目数据");
    }

    /**
     * 获取标准类目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(standardItemService.findById(id));
    }

    /**
     * 新增标准类目
     */
    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "标准类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StandardItem standardItem) {
        return toAjax(standardItemService.save(standardItem));
    }

    /**
     * 修改标准类目
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "标准类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StandardItem standardItem) {
        return toAjax(standardItemService.update(standardItem));
    }

    /**
     * 删除标准类目
     */
    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "标准类目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(standardItemService.deleteByIds(ids));
    }
}
