import request from '@/utils/request'

// 查询标准类目列表
export function listItem(query) {
  return request({
    url: '/system/item/list',
    method: 'get',
    params: query
  })
}

// 查询标准类目详细
export function getItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'get'
  })
}

// 新增标准类目
export function addItem(data) {
  return request({
    url: '/system/item',
    method: 'post',
    data: data
  })
}

// 修改标准类目
export function updateItem(data) {
  return request({
    url: '/system/item',
    method: 'put',
    data: data
  })
}

// 删除标准类目
export function delItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'delete'
  })
}

// 导出标准类目
export function exportItem(query) {
  return request({
    url: '/system/item/export',
    method: 'get',
    params: query
  })
}