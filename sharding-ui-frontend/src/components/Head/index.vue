<template>
  <div class="s-layout-header">
    <div class="s-pro-components-header">
      <i :class="classes" @click="togger"/>
      <div class="s-pro-components-header-right">
        <div class="avatar">
          <el-dropdown>
            <span class="el-dropdown-link">wqzwh</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>退出登陆</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="lang-more">
          <el-dropdown>
            <div class="lang-icon"/>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>英文</el-dropdown-item>
              <el-dropdown-item disabled>中文</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Head',
  data() {
    return {
      isCollapse: false
    }
  },
  computed: {
    classes() {
      return [
        `s-pro-components-header-trigger`,
        {
          [`el-icon-d-arrow-left`]: !this.isCollapse,
          [`el-icon-d-arrow-right`]: this.isCollapse
        }
      ]
    }
  },
  methods: {
    togger() {
      this.isCollapse = !this.isCollapse
      this.$emit('on-togger', this.isCollapse)
    }
  }
}
</script>
<style lang="scss" scoped>
.s-layout-header {
  background: #001529;
  padding: 0;
  height: 64px;
  line-height: 64px;
  width: 100%;
  .s-pro-components-header {
    height: 64px;
    padding: 0;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0,21,41,.08);
    position: relative;
    i.s-pro-components-header-trigger {
      font-size: 20px;
      height: 64px;
      cursor: pointer;
      transition: all .3s,padding 0s;
      padding: 22px 24px;
    }
    .s-pro-components-header-right {
      float: right;
      height: 100%;
      overflow: hidden;
    }
  }
  .avatar {
    cursor: pointer;
    padding: 0 12px;
    display: inline-block;
    transition: all .3s;
    height: 100%;
    float: left;
  }
  .lang-more {
    cursor: pointer;
    padding: 0 12px;
    display: inline-block;
    transition: all .3s;
    height: 100%;
    .lang-icon {
      background: url('../../assets/img/lang.png') no-repeat center center;
      width: 32px;
      height: 60px;
    }
  }
}
</style>
