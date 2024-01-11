<script>
export default {
  name:"HeaderPage",
  data(){
    return {
    teacher:JSON.parse(sessionStorage.getItem('CurTeacher'))
    }
  },
  methods:{
    profile(){
      console.log("test profile")
    },
    logout(){
      this.$confirm("Are you sure you want to log out?",'Confirm',{
        confirmButtonText:'Yes',
        center:true
      }).then(()=>{
        this.$router.push("/");
        sessionStorage.clear();
      }).catch(()=>{})
    },
  }
}
</script>

<template>
  <div class="header">
    <span style="font-size: 18px;color: white;font-style: italic"><strong>{{teacher.name}}</strong></span>
    <el-button type="text" style="color: white" icon="el-icon-switch-button" size="medium" circle @click="logout"></el-button>
  </div>
</template>

<style scoped>

</style>