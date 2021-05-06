import request from '@/utils/request'

export function reduce(data) {
  return request({
    url: '/system/grosslist/reduce',
    method: 'POST',
    data: data
  })
}

//直接移动
export function move(data) {
  return request({
    url: '/system/grosslist/moving',
    method: 'POST',
    data
  })
}

// 查询毛利列表列表
export function listGrosslist(query) {
  return request({
    url: '/system/grosslist/list',
    method: 'get',
    params: query
  })
}

// 查询毛利列表详细
export function getGrosslist(id) {
  return request({
    url: '/system/grosslist/' + id,
    method: 'get'
  })
}

// 新增毛利列表
export function addGrosslist(data) {
  return request({
    url: '/system/grosslist',
    method: 'post',
    data: data
  })
}

// 修改毛利列表
export function updateGrosslist(data) {
  return request({
    url: '/system/grosslist',
    method: 'put',
    data: data
  })
}

// 删除毛利列表
export function delGrosslist(id) {
  return request({
    url: '/system/grosslist/' + id,
    method: 'delete'
  })
}

// 导出毛利列表
export function exportGrosslist(query) {
  return request({
    url: '/system/grosslist/export',
    method: 'get',
    params: query
  })
}

// 下载毛利列表模板
export function importTemplate() {
  return request({
    url: '/system/grosslist/importTemplate',
    method: 'get'
  })
}

export function clear() {
  return request({
    url: '/system/grosslist/clear',
    method: 'delete'
  })
}
