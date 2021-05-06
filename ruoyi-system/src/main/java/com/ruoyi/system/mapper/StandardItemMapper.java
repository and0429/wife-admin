package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StandardItem;

/**
 * 标准类目Mapper接口
 * 
 * @author zhangkai
 * @date 2021-04-23
 */
public interface StandardItemMapper 
{
    /**
     * 查询标准类目
     * 
     * @param id 标准类目ID
     * @return 标准类目
     */
    public StandardItem selectStandardItemById(Long id);

    /**
     * 查询标准类目列表
     * 
     * @param standardItem 标准类目
     * @return 标准类目集合
     */
    public List<StandardItem> selectStandardItemList(StandardItem standardItem);

    /**
     * 新增标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    public int insertStandardItem(StandardItem standardItem);

    /**
     * 修改标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    public int updateStandardItem(StandardItem standardItem);

    /**
     * 删除标准类目
     * 
     * @param id 标准类目ID
     * @return 结果
     */
    public int deleteStandardItemById(Long id);

    /**
     * 批量删除标准类目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardItemByIds(Long[] ids);
}
