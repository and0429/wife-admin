package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StandardItem;

/**
 * 标准类目Service接口
 * 
 * @author zhangkai
 * @date 2021-04-23
 */
public interface IStandardItemService 
{
    /**
     * 查询标准类目
     * 
     * @param id 标准类目ID
     * @return 标准类目
     */
    public StandardItem findById(Long id);

    /**
     * 查询标准类目列表
     * 
     * @param standardItem 标准类目
     * @return 标准类目集合
     */
    public List<StandardItem> findAll(StandardItem standardItem);

    /**
     * 新增标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    public int save(StandardItem standardItem);

    /**
     * 修改标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    public int update(StandardItem standardItem);

    /**
     * 批量删除标准类目
     * 
     * @param ids 需要删除的标准类目ID
     * @return 结果
     */
    public int deleteByIds(Long[] ids);

    /**
     * 删除标准类目信息
     * 
     * @param id 标准类目ID
     * @return 结果
     */
    public int deleteById(Long id);
}
