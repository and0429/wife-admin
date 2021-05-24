package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GrossProfitListStatistics;
import com.ruoyi.system.mapper.GrossProfitListMapper;
import com.ruoyi.system.mapper.GrossProfitListStatisticsMapper;
import com.ruoyi.system.service.IGrossProfitListStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 统计列Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
@Transactional
public class GrossProfitListStatisticsServiceImpl implements IGrossProfitListStatisticsService {


    private final GrossProfitListStatisticsMapper grossProfitListStatisticsMapper;

    private final GrossProfitListMapper grossProfitListMapper;

    public GrossProfitListStatisticsServiceImpl(GrossProfitListStatisticsMapper grossProfitListStatisticsMapper, GrossProfitListMapper grossProfitListMapper) {
        this.grossProfitListStatisticsMapper = grossProfitListStatisticsMapper;
        this.grossProfitListMapper = grossProfitListMapper;
    }

    /**
     * 查询统计列
     *
     * @param id 统计列ID
     * @return 统计列
     */
    @Override
    public GrossProfitListStatistics selectGrossProfitListStatisticsById(Long id) {
        return grossProfitListStatisticsMapper.selectGrossProfitListStatisticsById(id);
    }

    /**
     * 查询统计列列表
     *
     * @param grossProfitListStatistics 统计列
     * @return 统计列
     */
    @Override
    public List<GrossProfitListStatistics> selectGrossProfitListStatisticsList(GrossProfitListStatistics grossProfitListStatistics) {
        return grossProfitListStatisticsMapper.selectGrossProfitListStatisticsList(grossProfitListStatistics);
    }

    /**
     * 新增统计列
     *
     * @param grossProfitListStatistics 统计列
     * @return 结果
     */
    @Override
    public int insertGrossProfitListStatistics(GrossProfitListStatistics grossProfitListStatistics) {
        return grossProfitListStatisticsMapper.save(grossProfitListStatistics);
    }

    /**
     * 修改统计列
     *
     * @param grossProfitListStatistics 统计列
     * @return 结果
     */
    @Override
    public int updateGrossProfitListStatistics(GrossProfitListStatistics grossProfitListStatistics) {
        return grossProfitListStatisticsMapper.updateGrossProfitListStatistics(grossProfitListStatistics);
    }

    /**
     * 批量删除统计列
     *
     * @param ids 需要删除的统计列ID
     * @return 结果
     */
    @Override
    public int deleteGrossProfitListStatisticsByIds(Long[] ids) {
        if (Objects.isNull(ids) || ids.length == 0) return 0;
        grossProfitListMapper.unbindByIds(ids);//解绑原始条目
        return grossProfitListStatisticsMapper.deleteGrossProfitListStatisticsByIds(ids);
    }

    /**
     * 删除统计列信息
     *
     * @param id 统计列ID
     * @return 结果
     */
    @Override
    public int deleteGrossProfitListStatisticsById(Long id) {
        return grossProfitListStatisticsMapper.deleteGrossProfitListStatisticsById(id);
    }

    @Override
    public Double amontSum() {
        return grossProfitListStatisticsMapper.amontSum();
    }
}
