package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.GrossProfitList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * 毛利列表Mapper接口
 *
 * @author zhangkai
 * @date 2021-04-22
 */
public interface GrossProfitListMapper {
    /**
     * 查询毛利列表
     *
     * @param id 毛利列表ID
     * @return 毛利列表
     */
    public GrossProfitList selectGrossProfitListById(Long id);

    /**
     * 查询毛利列表列表
     *
     * @param grossProfitList 毛利列表
     * @return 毛利列表集合
     */
    public List<GrossProfitList> selectGrossProfitListList(GrossProfitList grossProfitList);

    /**
     * 新增毛利列表
     *
     * @param grossProfitList 毛利列表
     * @return 结果
     */
    public int insertGrossProfitList(GrossProfitList grossProfitList);

    /**
     * 修改毛利列表
     *
     * @param grossProfitList 毛利列表
     * @return 结果
     */
    public int updateGrossProfitList(GrossProfitList grossProfitList);

    /**
     * 删除毛利列表
     *
     * @param id 毛利列表ID
     * @return 结果
     */
    public int deleteGrossProfitListById(Long id);

    /**
     * 批量删除毛利列表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrossProfitListByIds(Long[] ids);

    //
    List<GrossProfitList> findInIds(@Param("ids") List<Long> srcIds);

    //
    void updateRefId(@Param("refId") Long id, @Param("ids") List<Long> srcIds);

    //
    void unbindByIds(@Param("ids") Long[] ids);

    @Delete("delete from b_gross_profit_list")
    void clear();
}
