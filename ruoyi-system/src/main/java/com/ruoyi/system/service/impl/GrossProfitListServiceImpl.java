package com.ruoyi.system.service.impl;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.system.domain.GrossProfitList;
import com.ruoyi.system.domain.GrossProfitListStatistics;
import com.ruoyi.system.domain.vo.ReduceParams;
import com.ruoyi.system.mapper.GrossProfitListMapper;
import com.ruoyi.system.mapper.GrossProfitListStatisticsMapper;
import com.ruoyi.system.service.IGrossProfitListService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

/**
 * 毛利列表Service业务层处理
 *
 * @author zhangkai
 * @date 2021-04-22
 */
@Service
@Transactional
public class GrossProfitListServiceImpl implements IGrossProfitListService {

    private final GrossProfitListMapper grossProfitListMapper;
    private final GrossProfitListStatisticsMapper grossProfitListStatisticsMapper;

    public GrossProfitListServiceImpl(GrossProfitListMapper grossProfitListMapper, GrossProfitListStatisticsMapper grossProfitListStatisticsMapper) {
        this.grossProfitListMapper = grossProfitListMapper;
        this.grossProfitListStatisticsMapper = grossProfitListStatisticsMapper;
    }


    /**
     * 查询毛利列表
     *
     * @param id 毛利列表ID
     * @return 毛利列表
     */
    @Override
    public GrossProfitList selectGrossProfitListById(Long id) {
        return grossProfitListMapper.selectGrossProfitListById(id);
    }

    /**
     * 查询毛利列表列表
     *
     * @param grossProfitList 毛利列表
     * @return 毛利列表
     */
    @Override
    public List<GrossProfitList> selectGrossProfitListList(GrossProfitList grossProfitList) {
        return grossProfitListMapper.selectGrossProfitListList(grossProfitList);
    }

    /**
     * 新增毛利列表
     *
     * @param grossProfitList 毛利列表
     * @return 结果
     */
    @Override
    public int insertGrossProfitList(GrossProfitList grossProfitList) {
        return grossProfitListMapper.insertGrossProfitList(grossProfitList);
    }

    /**
     * 修改毛利列表
     *
     * @param grossProfitList 毛利列表
     * @return 结果
     */
    @Override
    public int updateGrossProfitList(GrossProfitList grossProfitList) {
        return grossProfitListMapper.updateGrossProfitList(grossProfitList);
    }

    /**
     * 批量删除毛利列表
     *
     * @param ids 需要删除的毛利列表ID
     * @return 结果
     */
    @Override
    public int deleteGrossProfitListByIds(Long[] ids) {
        return grossProfitListMapper.deleteGrossProfitListByIds(ids);
    }

    /**
     * 删除毛利列表信息
     *
     * @param id 毛利列表ID
     * @return 结果
     */
    @Override
    public int deleteGrossProfitListById(Long id) {
        return grossProfitListMapper.deleteGrossProfitListById(id);
    }

    /**
     * @param grossProfits
     * @param updateSupport
     * @param operName
     * @return
     */
    @Override
    public String importData(List<GrossProfitList> grossProfits, boolean updateSupport, String operName) {
        if (CollectionUtils.isEmpty(grossProfits)) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        for (GrossProfitList gl : grossProfits) {
            successNum += insertGrossProfitList(gl);
        }
        successMsg.append("成功插入").append(successNum).append("条记录");
        return successMsg.toString();
    }

    /**
     * @param params
     */
    @Override
    public void redece(ReduceParams params) {
        final List<Long> srcIds = params.getSrcIds();
        if (CollectionUtils.isEmpty(srcIds)) return;
        final List<GrossProfitList> gps = findInIds(srcIds);
        saveStatisticsAndUpdateRefId(gps, srcIds, params.getName(), params.getFormat(), params.getUnit());
    }


    /**
     * @param params
     */
    @Override
    public void move(ReduceParams params) {
        final List<Long> srcIds = params.getSrcIds();
        if (CollectionUtils.isEmpty(srcIds)) return;
        final List<GrossProfitList> gps = findInIds(srcIds);
        for (GrossProfitList gp : gps) {
            saveStatisticsAndUpdateRefId(Collections.singletonList(gp), Collections.singletonList(gp.getId()),
                    gp.getName(), gp.getFormat(), gp.getUnit());
        }
    }


    @Override
    public List<GrossProfitList> findInIds(List<Long> srcIds) {
        return grossProfitListMapper.findInIds(srcIds);
    }

    @Override
    public void clear() {
        grossProfitListStatisticsMapper.clear();
        grossProfitListMapper.clear();
    }


    //
    private void saveStatisticsAndUpdateRefId(final List<GrossProfitList> gps, final List<Long> srcIds,
                                              final String name, final String format, final String unit) {
        final GrossProfitListStatistics gpls =
                findGrossProfitListStatistics(gps, name, format, unit);//生成统计并保存
        grossProfitListStatisticsMapper.save(gpls);
        grossProfitListMapper.updateRefId(gpls.getId(), srcIds);//修改源数据的引用
    }


    //
    private GrossProfitListStatistics findGrossProfitListStatistics(List<GrossProfitList> items,
                                                                    final String name,
                                                                    final String format,
                                                                    final String unit) {
        String category = "";
        BigDecimal count = BigDecimal.valueOf(0);
        BigDecimal amount = BigDecimal.valueOf(0);
        for (GrossProfitList item : items) {
            category = item.getCategory();
            count = count.add(item.getCount());
            amount = amount.add(item.getAmont());
        }
        GrossProfitListStatistics gps = new GrossProfitListStatistics();
        gps.setName(name);
        gps.setFormat(format);
        gps.setCategory(category);
        gps.setUnit(unit);
        gps.setCount(round(count));
        gps.setPrice(getPrice(amount, count));
        gps.setAmont(round(amount));
        return gps;
    }

    //
    private BigDecimal round(BigDecimal src) {
        return src.setScale(2, RoundingMode.HALF_UP);
    }

    //
    private BigDecimal getPrice(BigDecimal amount, BigDecimal count) {
        try {
            return amount.divide(count, 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}
