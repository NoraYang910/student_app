<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h3 class="login-title">Welcome to Hogwarts!</h3>
                <el-form :model="loginForm" label-width="100px"
                         :rules="rules" ref="loginForm" class="form" >
                    <el-form-item label="username" prop="name">
                        <el-input style="width: 200px" type="text" v-model="loginForm.name"
                                  autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="password" prop="password">
                        <el-input style="width: 200px" type="password" v-model="loginForm.password"
                                  show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="confirm" style="text-align: center">submit</el-button>
                      <el-button @click="reset" style="text-align: center">reset</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>


<script>
    export default {
        name: "LoginPage",
        data(){
            return{
                // confirm_disabled:false,
                loginForm:{
                    name:'',
                    password:''
                },
                rules:{
                    name: [
                        { required: true, message: 'please input username', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'please input password', trigger: 'blur' }
                    ],
                }
            }
        },
        methods:{
          reset(){
            this.loginForm.name='';
            this.loginForm.password='';
          },
            confirm(){
                // this.confirm_disabled=true;
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.$axios.post("http://localhost:8081/teacher/login",this.loginForm).then(res=>{
                            console.log(res)
                            if(res.data.code==200){
                                sessionStorage.setItem("CurTeacher",JSON.stringify(res.data.data))
                                // console.log(res.data.menu)
                                // this.$store.commit("setMenu",res.data.menu)
                                this.$router.replace('/Index');
                            }else{
                                // this.confirm_disabled=false;
                                alert('Invalid password or username!');
                                return false;
                            }
                        });
                    } else {
                        // this.confirm_disabled=false;
                        console.log('Invalid');
                        return false;
                    }
                });

            }
        }
    }
</script>

<style scoped>
.form {
  font-family: Salsa;
}
    .loginBody {
        position: absolute;
        width: 100%;
        height: 100%;
      background-image: url("imgs/4.jpg");
      background-size: cover;
      background-position: center;
    }
    .loginDiv {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -200px;
        margin-left: -200px;
        width: 400px;
        height: 300px;
      background-color: rgba(255, 255, 255, 0.6);
        border-radius: 5%;
    }
    .login-title {
      font-family: Salsa;
        margin: 20px 0;
        text-align: center;
    }
    .login-content {
        width: 400px;
        height: 250px;
        position: absolute;
        top: 25px;
        left: 25px;
    }
</style>