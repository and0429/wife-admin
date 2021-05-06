package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.GrossProfitList;
import com.ruoyi.system.domain.vo.ReduceParams;
import com.ruoyi.system.service.IGrossProfitListService;
import com.ruoyi.system.service.impl.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 毛利列表Controller
 *
 * @author zhangkai
 * @date 2021-04-22
 */
@RestController
@RequestMapping("/system/grosslist")
public class GrossProfitListController extends BaseController {

    private final IGrossProfitListService grossProfitListService;

    private final TokenService tokenService;

    public GrossProfitListController(IGrossProfitListService grossProfitListService, TokenService tokenService) {
        this.grossProfitListService = grossProfitListService;
        this.tokenService = tokenService;
    }

    @DeleteMapping("/clear")
    public AjaxResult clear() {
        grossProfitListService.clear();
        return AjaxResult.success();
    }

    /**
     * @return
     */
    @PostMapping("/reduce")
    public AjaxResult redece(@RequestBody ReduceParams params) {
        grossProfitListService.redece(params);
        return AjaxResult.success();
    }


    @PostMapping("/moving")
    public  AjaxResult move(@RequestBody ReduceParams params) {
        grossProfitListService.move(params);
        return AjaxResult.success();
    }


    /**
     * 查询毛利列表列表
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:list')")
    @GetMapping("/list")
    public TableDataInfo list(GrossProfitList grossProfitList) {
        startPage();
        List<GrossProfitList> list = grossProfitListService.selectGrossProfitListList(grossProfitList);
        return getDataTable(list);
    }

    /**
     * 导出毛利列表列表
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:export')")
    @Log(title = "毛利列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GrossProfitList grossProfitList) {
        List<GrossProfitList> list = grossProfitListService.selectGrossProfitListList(grossProfitList);
        ExcelUtil<GrossProfitList> util = new ExcelUtil<GrossProfitList>(GrossProfitList.class);
        return util.exportExcel(list, "毛利列表数据");
    }

    /**
     * 获取毛利列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(grossProfitListService.selectGrossProfitListById(id));
    }

    /**
     * 新增毛利列表
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:add')")
    @Log(title = "毛利列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GrossProfitList grossProfitList) {
        return toAjax(grossProfitListService.insertGrossProfitList(grossProfitList));
    }

    /**
     * 修改毛利列表
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:edit')")
    @Log(title = "毛利列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GrossProfitList grossProfitList) {
        return toAjax(grossProfitListService.updateGrossProfitList(grossProfitList));
    }

    /**
     * 删除毛利列表
     */
    @PreAuthorize("@ss.hasPermi('system:grosslist:remove')")
    @Log(title = "毛利列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(grossProfitListService.deleteGrossProfitListByIds(ids));
    }


    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<GrossProfitList> util = new ExcelUtil<>(GrossProfitList.class);
        return util.importTemplateExcel("毛利数据");
    }


    @Log(title = "毛利列表", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:grosslist:add')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<GrossProfitList> util = new ExcelUtil<>(GrossProfitList.class);
        List<GrossProfitList> grossProfits = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = grossProfitListService.importData(grossProfits, updateSupport, operName);
        return AjaxResult.success(message);
    }
}
