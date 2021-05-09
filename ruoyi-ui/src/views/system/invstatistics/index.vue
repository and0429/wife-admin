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
      <el-form-item label="规格" prop="format">
        <el-input
          v-model="queryParams.format"
          placeholder="请输入规格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入分类"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <el-input
          v-model="queryParams.unit"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:invstatistics:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:invstatistics:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:invstatistics:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:invstatistics:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="invstatisticsList" @selection-change="handleSelectionChange"
              @cell-click="celledit"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="名称" align="center" prop="name">
        <template slot-scope="scope">
          <el-input v-if="scope.row.name.edit"
                    ref="name"
                    v-model="scope.row.name.value"
                    style="width: 100%"
                    @keyup.enter.native="submitUpdate(scope.row, 'name')"
                    @blur="submitUpdate(scope.row, 'name')"
          >
          </el-input>
          <span v-else>{{ scope.row.name.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="规格" align="center" prop="format">
        <template slot-scope="scope">
          <el-input v-if="scope.row.format.edit"
                    ref="format"
                    v-model="scope.row.format.value"
                    style="width: 100%"
                    @keyup.enter.native="submitUpdate(scope.row, 'format')"
                    @blur="submitUpdate(scope.row, 'format')"
          >
          </el-input>
          <span v-else>{{ scope.row.format.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="category">
        <template slot-scope="scope">
          <el-input v-if="scope.row.category.edit"
                    ref="category"
                    v-model="scope.row.category.value"
                    style="width: 100%"
                    @keyup.enter.native="submitUpdate(scope.row, 'category')"
                    @blur="submitUpdate(scope.row, 'category')"
          >
          </el-input>
          <span v-else>{{ scope.row.category.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="unit">
        <template slot-scope="scope">
          <el-input v-if="scope.row.unit.edit"
                    ref="unit"
                    v-model="scope.row.unit.value"
                    style="width: 100%"
                    @keyup.enter.native="submitUpdate(scope.row, 'unit')"
                    @blur="submitUpdate(scope.row, 'unit')"
          >
          </el-input>
          <span v-else>{{ scope.row.unit.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际数量" align="center" prop="count">
        <template slot-scope="scope">
          <span>{{ scope.row.count.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单价" align="center" prop="price">
        <template slot-scope="scope">
          <span>{{ scope.row.price.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际金额" align="center" prop="amont">
        <template slot-scope="scope">
          <span>{{ scope.row.amont.value }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleUpdate(scope.row)"-->
          <!--            v-hasPermi="['system:invstatistics:edit']"-->
          <!--          >修改-->
          <!--          </el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:invstatistics:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page-sizes="[10, 20, 30, 40, 50, 100, 500, 1000]"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改统计列表对话框 -->
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
  </div>
</template>

<script>
import {
  listInvstatistics,
  getInvstatistics,
  delInvstatistics,
  addInvstatistics,
  updateInvstatistics,
  exportInvstatistics
} from '@/api/system/invstatistics'

export default {
  name: 'Invstatistics',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 统计列表表格数据
      invstatisticsList: [],
      // 弹出层标题
      title: '',
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
        format: [
          { required: true, message: '规格不能为空', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '分类不能为空', trigger: 'blur' }
        ],
        unit: [
          { required: true, message: '单位不能为空', trigger: 'blur' }
        ],
        count: [
          { required: true, message: '实际数量不能为空', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '单价不能为空', trigger: 'blur' }
        ],
        amont: [
          { required: true, message: '实际金额不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询统计列表列表 */
    getList() {
      this.loading = true
      listInvstatistics(this.queryParams).then(response => {
        this.invstatisticsList = response.rows
        this.invstatisticsList.forEach(item => {
          for (let key in item) {
            item[key] = {
              value: item[key],
              edit: false
            }
          }
        })
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
      this.ids = selection.map(item => item.id.value)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    //
    celledit(row, column, cell, event) {
      if (row[column.property]) {
        row[column.property].edit = true
        setTimeout(() => {
          this.$refs[column.property].focus()
        }, 20)
      }
    },

    //
    submitUpdate(row, prop) {
      row[prop].edit = false
      if (!row[prop].value) return
      let data = {
        id: row.id.value,
        [prop]: row[prop].value.trim()
      }
      // this.loading = true
      updateInvstatistics(data).then(() => {
        this.msgSuccess('修改成功')
        // this.loading = false
        // this.getList()
      })
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加统计列表'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id?.value || this.ids
      getInvstatistics(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改统计列表'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInvstatistics(this.form).then(response => {
              this.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addInvstatistics(this.form).then(response => {
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
      const ids = row.id?.value || this.ids
      this.$confirm('是否确认删除统计列表编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delInvstatistics(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams
      this.$confirm('是否确认导出所有统计列表数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportInvstatistics(queryParams)
      }).then(response => {
        this.download(response.msg)
      })
    }
  }
}
</script>
