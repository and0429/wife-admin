package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StandardItemMapper;
import com.ruoyi.system.domain.StandardItem;
import com.ruoyi.system.service.IStandardItemService;

/**
 * 标准类目Service业务层处理
 * 
 * @author zhangkai
 * @date 2021-04-23
 */
@Service
public class StandardItemServiceImpl implements IStandardItemService 
{
    private final StandardItemMapper standardItemMapper;

    public StandardItemServiceImpl(StandardItemMapper standardItemMapper) {
        this.standardItemMapper = standardItemMapper;
    }

    /**
     * 查询标准类目
     * 
     * @param id 标准类目ID
     * @return 标准类目
     */
    @Override
    public StandardItem findById(Long id)
    {
        return standardItemMapper.selectStandardItemById(id);
    }

    /**
     * 查询标准类目列表
     * 
     * @param standardItem 标准类目
     * @return 标准类目
     */
    @Override
    public List<StandardItem> findAll(StandardItem standardItem) {
        return standardItemMapper.selectStandardItemList(standardItem);
    }

    /**
     * 新增标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    @Override
    public int save(StandardItem standardItem)
    {
        return standardItemMapper.insertStandardItem(standardItem);
    }

    /**
     * 修改标准类目
     * 
     * @param standardItem 标准类目
     * @return 结果
     */
    @Override
    public int update(StandardItem standardItem)
    {
        return standardItemMapper.updateStandardItem(standardItem);
    }

    /**
     * 批量删除标准类目
     * 
     * @param ids 需要删除的标准类目ID
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids)
    {
        return standardItemMapper.deleteStandardItemByIds(ids);
    }

    /**
     * 删除标准类目信息
     * 
     * @param id 标准类目ID
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return standardItemMapper.deleteStandardItemById(id);
    }
}
