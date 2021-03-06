<template>
  <el-card class="box-card">
    <div class="btn-group">
      <el-button type="primary" icon="el-icon-plus" @click="add">{{ $t("index.btnTxt") }}</el-button>
    </div>
    <div class="table-wrap">
      <el-table :data="tableData" border="" style="width: 100%">
        <el-table-column
          v-for="(item, index) in column"
          :key="index"
          :prop="item.prop"
          :label="item.label"
          :width="item.width"/>
        <el-table-column :label="$t('index.table.operate')" fixed="right" width="140">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handleConnect(scope.row)">{{ scope.row.activated ? $t("common.connected") : $t("common.connection") }}</el-button>
            <el-button type="text" size="small" @click="handlerDel(scope.row)">{{ $t("common.del") }}</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog :title="$t('index.registDialog.title')" :visible.sync="regustDialogVisible">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item :label="$t('index.registDialog.name')" prop="name">
          <el-input v-model="form.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item :label="$t('index.registDialog.centerType')" prop="centerType">
          <el-radio-group v-model="form.centerType">
            <el-radio label="Zookeeper">Zookeeper</el-radio>
            <el-radio label="Etcd">Etcd</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item :label="$t('index.registDialog.address')" prop="address">
          <el-input v-model="form.address" autocomplete="off"/>
        </el-form-item>
        <el-form-item :label="$t('index.registDialog.orchestrationName')" prop="orchestrationName">
          <el-input v-model="form.orchestrationName" autocomplete="off"/>
        </el-form-item>
        <el-form-item :label="$t('index.registDialog.namespaces')" prop="namespaces">
          <el-input v-model="form.namespaces" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="regustDialogVisible = false">{{ $t("index.registDialog.btnCancelTxt") }}</el-button>
        <el-button type="primary" @click="onConfirm('form')">{{ $t("index.registDialog.btnConfirmTxt") }}</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import API from '../api'
export default {
  name: 'RegistConfig',
  data() {
    return {
      regustDialogVisible: false,
      column: [
        {
          label: this.$t('index').registDialog.name,
          prop: 'name'
        },
        {
          label: this.$t('index').registDialog.centerType,
          prop: 'registryCenterType'
        },
        {
          label: this.$t('index').registDialog.address,
          prop: 'serverLists'
        },
        {
          label: this.$t('index').registDialog.orchestrationName,
          prop: 'orchestrationName'
        },
        {
          label: this.$t('index').registDialog.namespaces,
          prop: 'namespace'
        }
      ],
      form: {
        name: '',
        address: '',
        namespaces: '',
        centerType: 'Zookeeper',
        orchestrationName: ''
      },
      rules: {
        name: [
          { required: true, message: this.$t('index').rules.name, trigger: 'change' }
        ],
        address: [
          { required: true, message: this.$t('index').rules.address, trigger: 'change' }
        ],
        namespaces: [
          { required: true, message: this.$t('index').rules.namespaces, trigger: 'change' }
        ],
        centerType: [
          { required: true, message: this.$t('index').rules.centerType, trigger: 'change' }
        ],
        orchestrationName: [
          { required: true, message: this.$t('index').rules.orchestrationName, trigger: 'change' }
        ]
      },
      tableData: []
    }
  },
  created() {
    this.getRegCenter()
  },
  methods: {
    getRegCenter() {
      API.getRegCenter().then((res) => {
        this.tableData = res.model
      })
    },
    handleConnect(row) {
      if (row.activated) {
        this.$notify({
          title: this.$t('common').notify.title,
          message: this.$t('common').connected,
          type: 'success'
        })
      } else {
        const params = {
          // activated: row.activated,
          // digest: row.digest,
          name: row.name
          // namespace: row.namespace,
          // orchestrationName: row.orchestrationName,
          // registryCenterType: row.registryCenterType,
          // serverLists: row.serverLists
        }
        API.postRegCenterConnect(params).then((res) => {
          this.$notify({
            title: this.$t('common').notify.title,
            message: this.$t('common').notify.conSucMessage,
            type: 'success'
          })
          this.getRegCenter()
        })
      }
    },
    handlerDel(row) {
      const params = {
        // activated: row.activated,
        // digest: row.digest,
        name: row.name
        // namespace: row.namespace,
        // orchestrationName: row.orchestrationName,
        // registryCenterType: row.registryCenterType,
        // serverLists: row.serverLists
      }
      API.deleteRegCenter(params).then((res) => {
        this.$notify({
          title: this.$t('common').notify.title,
          message: this.$t('common').notify.delSucMessage,
          type: 'success'
        })
        this.getRegCenter()
      })
    },
    onConfirm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const params = {
            // digest: 'string',
            name: this.form.name,
            namespace: this.form.namespaces,
            orchestrationName: this.form.orchestrationName,
            registryCenterType: this.form.centerType,
            serverLists: this.form.address
          }
          API.postRegCenter(params).then((res) => {
            this.regustDialogVisible = false
            this.$notify({
              title: this.$t('common').notify.title,
              message: this.$t('common').notify.conSucMessage,
              type: 'success'
            })
            this.getRegCenter()
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    add() {
      this.regustDialogVisible = true
    }
  }
}
</script>
<style lang='scss' scoped>
  .btn-group {
    margin-bottom: 20px;
  }
</style>
