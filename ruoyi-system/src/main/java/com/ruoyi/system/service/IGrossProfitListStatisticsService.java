package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.GrossProfitListStatistics;

/**
 * 统计列Service接口
 *
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IGrossProfitListStatisticsService {
    /**
     * 查询统计列
     *
     * @param id 统计列ID
     * @return 统计列
     */
    public GrossProfitListStatistics selectGrossProfitListStatisticsById(Long id);

    /**
     * 查询统计列列表
     *
     * @param grossProfitListStatistics 统计列
     * @return 统计列集合
     */
    public List<GrossProfitListStatistics> selectGrossProfitListStatisticsList(GrossProfitListStatistics grossProfitListStatistics);

    /**
     * 新增统计列
     *
     * @param grossProfitListStatistics 统计列
     * @return 结果
     */
    public int insertGrossProfitListStatistics(GrossProfitListStatistics grossProfitListStatistics);

    /**
     * 修改统计列
     *
     * @param grossProfitListStatistics 统计列
     * @return 结果
     */
    public int updateGrossProfitListStatistics(GrossProfitListStatistics grossProfitListStatistics);

    /**
     * 批量删除统计列
     *
     * @param ids 需要删除的统计列ID
     * @return 结果
     */
    public int deleteGrossProfitListStatisticsByIds(Long[] ids);

    /**
     * 删除统计列信息
     *
     * @param id 统计列ID
     * @return 结果
     */
    public int deleteGrossProfitListStatisticsById(Long id);

    Double amontSum();
}
