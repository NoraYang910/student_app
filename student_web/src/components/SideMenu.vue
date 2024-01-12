<script>
export default {
  name:"SideMenu"
}

</script>

<template>
  <el-menu class="sideMenu" :default-openeds="['/HomePage']" background-color="#171a39" text-color="#fff" active-text-color="#ffd04b" style="height: 100vh" router>
    <el-menu-item index="/HomePage">
      <i class="el-icon-s-home"></i><span>HomePage</span>
    </el-menu-item>
    <el-menu-item>
      <i class="el-icon-user-solid"></i><span>Students</span>
    </el-menu-item>
    <el-menu-item index="/CourseManage">
      <i class="el-icon-menu"></i><span>Courses</span>
    </el-menu-item>
    <el-menu-item index="/MessageBox">
      <i class="el-icon-message"></i><span>MailBox</span>
    </el-menu-item>
    <el-submenu index="4">
      <template slot="title"><i class="el-icon-medal"></i>Competitions</template>
      <el-menu-item-group>
        <el-menu-item>Quidditch World Cup</el-menu-item>
        <el-menu-item>Triwizard Tournament</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title"><i class="el-icon-s-check"></i>Organizations</template>
      <el-menu-item-group>
        <el-menu-item>Order of the Phoenix</el-menu-item>
        <el-menu-item>Wizards' Council</el-menu-item>
      </el-menu-item-group>
    </el-submenu>

  </el-menu>
</template>
<style scoped>

</style>