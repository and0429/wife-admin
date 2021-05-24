package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.GrossProfitListStatistics;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * 统计列Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-23
 */
public interface GrossProfitListStatisticsMapper {
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
    public int save(GrossProfitListStatistics grossProfitListStatistics);

    /**
     * 修改统计列
     *
     * @param grossProfitListStatistics 统计列
     * @return 结果
     */
    public int updateGrossProfitListStatistics(GrossProfitListStatistics grossProfitListStatistics);

    /**
     * 删除统计列
     *
     * @param id 统计列ID
     * @return 结果
     */
    public int deleteGrossProfitListStatisticsById(Long id);

    /**
     * 批量删除统计列
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrossProfitListStatisticsByIds(Long[] ids);

    @Delete("delete from b_gross_profit_list_statistics")
    void clear();

    /**
     * @return
     */
    @Select("select sum(amont_) from b_gross_profit_list_statistics")
    Double amontSum();
}
