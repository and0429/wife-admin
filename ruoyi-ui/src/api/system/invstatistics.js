import request from '@/utils/request'

// 查询统计列表列表
export function listInvstatistics(query) {
  return request({
    url: '/system/invstatistics/list',
    method: 'get',
    params: query
  })
}

// 查询统计列表详细
export function getInvstatistics(id) {
  return request({
    url: '/system/invstatistics/' + id,
    method: 'get'
  })
}

// 新增统计列表
export function addInvstatistics(data) {
  return request({
    url: '/system/invstatistics',
    method: 'post',
    data: data
  })
}

// 修改统计列表
export function updateInvstatistics(data) {
  return request({
    url: '/system/invstatistics',
    method: 'put',
    data: data
  })
}

// 删除统计列表
export function delInvstatistics(id) {
  return request({
    url: '/system/invstatistics/' + id,
    method: 'delete'
  })
}

// 导出统计列表
export function exportInvstatistics(query) {
  return request({
    url: '/system/invstatistics/export',
    method: 'get',
    params: query
  })
}
