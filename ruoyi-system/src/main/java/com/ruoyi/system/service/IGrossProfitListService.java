package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.GrossProfitList;
import com.ruoyi.system.domain.vo.ReduceParams;

/**
 * 毛利列表Service接口
 *
 * @author zhangkai
 * @date 2021-04-22
 */
public interface IGrossProfitListService {
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
     * 批量删除毛利列表
     *
     * @param ids 需要删除的毛利列表ID
     * @return 结果
     */
    public int deleteGrossProfitListByIds(Long[] ids);

    /**
     * 删除毛利列表信息
     *
     * @param id 毛利列表ID
     * @return 结果
     */
    public int deleteGrossProfitListById(Long id);

    /**
     * @param grossProfits
     * @param updateSupport
     * @param operName
     * @return
     */
    String importData(List<GrossProfitList> grossProfits, boolean updateSupport, String operName);

    /**
     * @param params
     */
    void reduce(ReduceParams params);

    /**
     * @param srcIds
     * @return
     */
    List<GrossProfitList> findInIds(List<Long> srcIds);

    void clear();


    /**
     * @param params
     */
    void move(ReduceParams params);

    /**
     * @return
     */
    Double amontSum();

    void autoReduce();
}
