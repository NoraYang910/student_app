<template>
    <el-container style="height: 100%; border: 1px solid #eee" class="container">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%">
        <SideMenu></SideMenu>
      </el-aside>

      <el-container style="height: 100%">
        <el-header style="height: 80px; text-align: right; font-size: 12px">
          <HeaderPage></HeaderPage>
        </el-header>

        <el-main style="height: 100%">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
</template>

<style>
.container {
  background-color: #171a39;
}
.el-header {
  line-height: 120px;
  background-image: url("imgs/header2.jpg");
}
.el-main{
  background-color: #ecdfe2;
  min-height: 520px;
  //background-image: linear-gradient(to top, #ebbba7 0%, #cfc7f8 100%);
 }

</style>
<script>
import SideMenu from "@/components/SideMenu.vue";
import HeaderPage from "@/components/HeaderPage.vue";

export default {
  name: 'IndexPage',
  components: {HeaderPage, SideMenu},
  methods:{
  },
  data(){
    return{

    }
  }
};
</script>


