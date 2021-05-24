<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="规格" prop="format">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.format"-->
      <!--          placeholder="请输入规格"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="分类" prop="category">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.category"-->
      <!--          placeholder="请输入分类"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="单位" prop="unit">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.unit"-->
      <!--          placeholder="请输入单位"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10">
      <el-col :span="12">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              @click="clear"
              v-hasPermi="['system:grosslist:remove']"
            >清空工作空间
            </el-button>
          </el-col>
          <!--          <el-col :span="1.5">-->
          <!--            <el-button-->
          <!--              type="primary"-->
          <!--              plain-->
          <!--              icon="el-icon-plus"-->
          <!--              size="mini"-->
          <!--              @click="handleAdd"-->
          <!--              v-hasPermi="['system:grosslist:add']"-->
          <!--            >新增-->
          <!--            </el-button>-->
          <!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:grosslist:edit']"
            >修改
            </el-button>
          </el-col>
          <!--          <el-col :span="1.5">-->
          <!--            <el-button-->
          <!--              type="danger"-->
          <!--              plain-->
          <!--              icon="el-icon-delete"-->
          <!--              size="mini"-->
          <!--              :disabled="multiple"-->
          <!--              @click="handleDelete"-->
          <!--              v-hasPermi="['system:grosslist:remove']"-->
          <!--            >删除-->
          <!--            </el-button>-->
          <!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
              v-hasPermi="['system:grosslist:add']"
            >导入
            </el-button>
          </el-col>
          <!--          <el-col :span="1.5">-->
          <!--            <el-button-->
          <!--              type="warning"-->
          <!--              plain-->
          <!--              icon="el-icon-download"-->
          <!--              size="mini"-->
          <!--              @click="handleExport"-->
          <!--              v-hasPermi="['system:grosslist:export']"-->
          <!--            >导出-->
          <!--            </el-button>-->
          <!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-document"
              size="mini"
              :disabled="multiple"
              @click="reduceHandle"
              v-hasPermi="['system:grosslist:add']"
            >合并
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-document"
              size="mini"
              :disabled="multiple"
              @click="moveHandle"
              v-hasPermi="['system:grosslist:add']"
            >移动
            </el-button>
          </el-col>
          <el-col :span="1.5" style="line-height: 28px">
            <span>总金额：</span><span>{{ grossSum }}</span>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading"
                  ref="grosslistTable"
                  :data="grosslistList"
                  @cell-click="rowSelected"
                  @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="45" align="center"/>
          <el-table-column label="名称" align="center" prop="name"/>
          <el-table-column label="规格" align="center" prop="format"/>
          <el-table-column label="分类" align="center" prop="category"/>
          <el-table-column label="单位" align="center" prop="unit"/>
          <el-table-column label="实际数量" align="center" prop="count"/>
          <el-table-column label="单价" align="center" prop="price"/>
          <el-table-column label="实际金额" align="center" prop="amont"/>
          <!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
          <!--        <template slot-scope="scope">-->
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleUpdate(scope.row)"-->
          <!--            v-hasPermi="['system:grosslist:edit']"-->
          <!--          >修改-->
          <!--          </el-button>-->
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['system:grosslist:remove']"-->
          <!--          >删除-->
          <!--          </el-button>-->
          <!--        </template>-->
          <!--      </el-table-column>-->
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page-sizes="[10, 20, 30, 40, 50, 100, 500, 1000]"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>

      <!--  统计部分表格    -->
      <el-col :span="12">
        <el-row :gutter="10" class="mb8">
          <el-col :span="20" style="line-height: 28px; text-align: right;">
            <span>总金额：</span><span>{{ invstatisticsSum }}</span>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" style="visibility: hidden"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="statistics.invstatisticsList">
          <el-table-column label="名称" align="center" prop="name"/>
          <el-table-column label="规格" align="center" prop="format"/>
          <el-table-column label="分类" align="center" prop="category"/>
          <el-table-column label="单位" align="center" prop="unit"/>
          <el-table-column label="实际数量" align="center" prop="count"/>
          <el-table-column label="单价" align="center" prop="price"/>
          <el-table-column label="实际金额" align="center" prop="amont"/>
        </el-table>

        <pagination
          v-show="statistics.total>0"
          :total="statistics.total"
          :page-sizes="[10, 20, 30, 40, 50, 100, 500, 1000]"
          :page.sync="statistics.queryParams.pageNum"
          :limit.sync="statistics.queryParams.pageSize"
          @pagination="getInvstatisticsList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改毛利列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="规格" prop="format">
          <el-input v-model="form.format" placeholder="请输入规格"/>
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-input v-model="form.category" placeholder="请输入分类"/>
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位"/>
        </el-form-item>
        <el-form-item label="实际数量" prop="count">
          <el-input v-model="form.count" placeholder="请输入实际数量"/>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入单价"/>
        </el-form-item>
        <el-form-item label="实际金额" prop="amont">
          <el-input v-model="form.amont" placeholder="请输入实际金额"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 合并对话框对话框 -->
    <el-dialog :title="'合并条目'" :visible.sync="reduce.open" width="1000px" append-to-body :close-on-click-modal="false">
      <el-row style="margin-bottom: 10px; margin-right: 15px; text-align: right">
        <el-button type="primary" @click="doReduce" :disabled="reduce.disabled">合 并</el-button>
      </el-row>
      <el-row>
        <el-form :inline="true" ref="reduceForm" :model="reduce.form" :rules="reduce.rules" label-width="60px">
          <el-form-item label="名称" prop="name">
            <el-input v-model="reduce.form.name" placeholder="请输入名称" ref="reduceFormOfName"
                      @input="debouncedNameInputHandle"
                      clearable
            />
          </el-form-item>
          <el-form-item label="规格" prop="format">
            <el-input v-model="reduce.form.format" placeholder="请输入规格" clearable/>
          </el-form-item>
          <el-form-item label="单位" prop="unit">
            <el-input v-model="reduce.form.unit" placeholder="请输入单位" clearable/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addToStandard">添加到标准</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row style="margin-bottom: 10px">
        <el-table v-loading="loading"
                  ref="standarditemTable"
                  :data="standarditem.datas"
                  @current-change="selectedStandardItem"
                  highlight-current-row
        >
          <el-table-column label="名称" align="center" prop="name"/>
          <el-table-column label="规格" align="center" prop="format"/>
          <el-table-column label="单位" align="center" prop="unit"/>
        </el-table>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="doReduce" :disabled="reduce.disabled">合 并</el-button>
      </div>
    </el-dialog>

    <!-- 导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body
               :close-on-click-modal="false"
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或<em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport"/>
          是否更新已经存在的数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGrosslist,
  getGrosslist,
  delGrosslist,
  addGrosslist,
  updateGrosslist,
  exportGrosslist,
  importTemplate,
  reduce,
  clear, move, sum
} from '@/api/system/grosslist'
import { getToken } from '@/utils/auth'
import { listInvstatistics, sum as invsSum } from '@/api/system/invstatistics'
import { addItem, listItem } from '@/api/system/item'

import _ from 'lodash'

export default {
  name: 'Grosslist',
  data() {
    return {
      upload: {
        open: false,
        title: '毛利列表导入',
        isUploading: false,
        updateSupport: 0,
        headers: { Authorization: 'Bearer ' + getToken() },
        url: process.env.VUE_APP_BASE_API + '/system/grosslist/importData'
      },
      reduce: {
        open: false,
        disabled: true,
        currName: '',
        form: {},
        rules: {
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          unit: [
            { required: true, message: '单位不能为空', trigger: 'blur' }
          ]
        }
      },
      loading: true,
      ids: [],
      single: true, // 非单个禁用
      multiple: true, // 非多个禁用
      showSearch: true, // 显示搜索条件
      total: 0, // 总条数
      grosslistList: [], // 毛利列表表格数据
      title: '',// 弹出层标题
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        name: null,
        format: null,
        category: null,
        unit: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
        count: [
          { required: true, message: '实际数量不能为空', trigger: 'blur' }
        ],
        amont: [
          { required: true, message: '实际金额不能为空', trigger: 'blur' }
        ]
      },
      statistics: {
        invstatisticsList: [],
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 100
        }
      },
      standarditem: {
        datas: []
      },
      grossSum: 0,
      invstatisticsSum: 0
    }
  },
  created() {
    this.getList()
    this.getInvstatisticsList()
    this.debouncedNameInputHandle = _.debounce(this.nameInputHandle, 500)
  },
  methods: {
    handleImport() {
      this.upload.open = true
    },
    /** 查询毛利列表列表 */
    getList() {
      this.loading = true
      listGrosslist(this.queryParams).then(response => {
        this.grosslistList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        format: null,
        category: null,
        unit: null,
        count: null,
        price: null,
        amont: null
      }
      this.resetForm('form')
    },

    resetParam() {
      this.queryParams.name = undefined
      this.queryParams.format = undefined
      this.queryParams.category = undefined
      this.queryParams.unit = undefined
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
      if (selection.length) {
        this.reduce.currName = selection[selection.length - 1].name
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加毛利列表'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getGrosslist(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改毛利列表'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGrosslist(this.form).then(response => {
              this.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addGrosslist(this.form).then(response => {
              this.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除毛利列表编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delGrosslist(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams
      this.$confirm('是否确认导出所有毛利列表数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportGrosslist(queryParams)
      }).then(response => {
        this.download(response.msg)
      })
    },
    //
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true
    },
    //
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false
      this.upload.isUploading = false
      this.$refs.upload.clearFiles()
      this.$alert(response.msg, '导入结果', { dangerouslyUseHTMLString: true })
      this.getList()
      this.getSum()
    },
    //
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg)
      })
    },
    //
    submitFileForm() {
      this.$refs.upload.submit()
    },

    // 合并时候的name输入改变
    nameInputHandle() {
      let name = this.reduce.form.name
      listItem({ name }).then(respone => {
        this.standarditem.datas = respone.rows
      })
    },

    //
    reduceHandle() { // 打开合并模态框
      this.loading = true
      this.reduce.form = {}
      this.reduce.disabled = false
      const name = this.reduce.currName || this.queryParams.name
      console.log(name)
      listItem({ name }).then(respone => {
        this.reduce.open = true
        this.standarditem.datas = respone.rows
        this.loading = false
        setTimeout(() => {
          this.$refs['reduceFormOfName'].focus()
        }, 100)

        // if (this.standarditem.datas.length) {
        //   setTimeout(() => {
        // this.$refs.standarditemTable.setCurrentRow(this.standarditem.datas[0]) // 不设置默认
        //   })
        // }
      })
    },

    //直接移动条目(即无需合并)
    moveHandle() {
      let srcIds = this.ids
      if (srcIds.length) {
        move({ srcIds }).then(() => {
          this.msgSuccess('移动成功')
          this.getList()
          this.getInvstatisticsList()
          this.getSum()
        })
      } else {
        this.msgError('请选择条目')
      }
    },

    //
    doReduce() { //合并
      this.reduce.disabled = true
      this.$refs['reduceForm'].validate(valid => {
        if (valid) {
          reduce({
            srcIds: this.ids,
            name: this.reduce.form.name,
            format: this.reduce.form.format,
            unit: this.reduce.form.unit
          }).then(() => {
            this.reduce.open = false
            this.msgSuccess('合并成功')
            this.resetParam()
            this.getList()
            this.getInvstatisticsList()
            this.getSum()
          })
        } else {
          this.reduce.disabled = false
        }
      })
    },

    //
    getInvstatisticsList() {
      this.loading = true
      listInvstatistics({}).then(response => {
        this.statistics.invstatisticsList = response.rows
        this.statistics.total = response.total
        this.loading = false
      })
    },

    //
    addToStandard() {
      this.$refs['reduceForm'].validate(valid => {
        if (valid) {
          addItem(this.reduce.form).then(() => {
            this.msgSuccess('添加标准类目成功')
          })
        }
      })
    },

    // 清空工作空间
    clear() {
      this.$confirm('是否确认删除所有的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return clear()
      }).then(() => {
        this.getList()
        this.getInvstatisticsList()
        this.getSum()
        this.msgSuccess('清除成功')
      })
    },

    //
    selectedStandardItem(row) {
      this.reduce.form = { ...row }
    },

    //
    rowSelected(row) {
      this.$refs.grosslistTable.toggleRowSelection(row)
    },

    //
    getSum() {
      sum().then(resp => {
        this.grossSum = resp.data
      })

      invsSum().then(resp => {
        this.invstatisticsSum = resp.data
      })
    }
  },
  mounted() {
    this.getSum()
  }
}
</script>
