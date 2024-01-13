<template>
  <div id="course">
    <el-button @click="showAddCourse" style="margin-bottom: 5px">add course</el-button>
    <div>
      <el-input v-model="name" placeholder="query by name" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="getCourses"></el-input>
      <el-button  style="margin-left: 5px;" size="small" @click="getCourses">search</el-button>
      <el-button size="small" @click="resetParam">reset</el-button>
    </div>
    <div>
      <el-table :data="tableData" style="height: 100%" class="table_info">
        <el-table-column label="No" type="index" width="50"></el-table-column>
        <!--            <el-table-column prop="id" label="id" width="50">-->
        <!--            </el-table-column>-->
        <el-table-column prop="name" label="Name" width="120">
        </el-table-column>
        <el-table-column prop="imageUrl" label="Image" width="120">
          <template slot-scope="scope">
            <img v-if="scope.row.imageUrl" :src="getImageUrl(scope.row.imageUrl)" alt="Image" style="width: 100px; height: 100px;">
            <span v-else>No Image</span>
          </template>
        </el-table-column>
        <el-table-column prop="lecturer" label="Lecturer" width="150">
        </el-table-column>
        <el-table-column prop="description" label="Description" width="400">
        </el-table-column>
        <el-table-column label="Manage">
          <template slot-scope="scope">
            <el-button @click="showEditCourse(scope.row)" size="small">Edit</el-button>
            <el-popconfirm
                title="Are you sure to delete this?"
                confirm-button-text='Yes'
                cancel-button-text='No'
                @confirm="deleteCourse(scope.row.id)"
            >
              <el-button slot="reference" size="small" style="margin-left: 5px">Delete</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="pageNum"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      >
      </el-pagination>
    </div>

    <el-dialog
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
      <el-form ref="form" :rules="rules" :model="form" label-width="90px">
        <el-form-item label="name" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="lecturer" prop="lecturer" >
          <el-select v-model="form.lecturer" placeholder="Select lecturer">
            <el-option
                v-for="teacher in this.teachers"
                :key="teacher.name"
                :label="teacher.name"
                :value="teacher.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="description" prop="description">
          <el-input type="textarea" :rows="3" v-model="form.description"></el-input>
        </el-form-item>
        <el-form-item label="profile" prop="profile">
          <el-upload class="el-avatar--icon" :action="uploadUrl()"
                     :show-file-list="false"
                     :on-success="handleAvatarSuccess"
                     :before-upload="beforeUpload"
                     ref="upload">
            <img v-if="this.form.imageUrl" :src="getImageUrl(this.form.imageUrl)" class="avatar" style="width: 100px; height: 100px;"/>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">Cancel</el-button>
    <el-button type="primary" @click="saveOrUpdate">Confirm</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style>

</style>
<script>
export default {
  name: 'CourseManage',
  methods:{
    getTeacher(){
      this.$axios.get("http://localhost:8081/teacher/list").then(res=>{
        // console.log(res.data.data)
        this.teachers = res.data.data
      })
    },
    resetParam(){
      this.name=''
    },
    showEditCourse(row){
      console.log(row);
      this.centerDialogVisible = true;
      this.$nextTick(()=>{
        this.form.id = row.id;
        this.form.name = row.name;
        this.form.lecturer =row.lecturer;
        this.form.description=row.description;
        this.form.imageUrl = row.imageUrl;
      })
    },
    deleteCourse(id){
      console.log(id)
      this.$axios.get(this.baseUrl+'/course/delete?id='+id).then(res=>{
        if(res.data.code==200){
          this.$message({
            message: 'Delete successfully!',
            type: 'success'
          });
          this.getCourses()
        }else {
          this.$message({
            message: 'Delete Failed!',
            type: 'error'
          });
        }
      })
    },
    // upload image
    uploadUrl() {
      return `${this.baseUrl}/file/upload`
    },
    downloadUrl() {
      return `${this.baseUrl}/file/download`
    },
    getImageUrl(imageUrl){
      if(imageUrl){
        return this.downloadUrl() + `?name=${imageUrl}`;}
    },
    // eslint-disable-next-line
    handleAvatarSuccess (response, file, fileList) {
      // this.download = `${this.downloadUrl()}?name=${response.data}`;
      this.form.imageUrl = response.data;
    },
    beforeUpload (file) {
      if(file){
        const suffix = file.name.split('.')[1]
        const size = file.size / 1024 / 1024 < 2
        if(['png','jpeg','jpg'].indexOf(suffix) < 0){
          this.$message.error('Image format error!')
          this.$refs.upload.clearFiles()
          return false
        }
        if(!size){
          this.$message.error('Uploaded files cannot exceed 2MB in size!')
          return false
        }
        return file
      }
    },
    resetForm(){
      this.$refs.form.resetFields();
      this.form.imageUrl = '';
      this.selectedTeacher=''
    },
    showAddCourse(){
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })
    },

    saveCourse(){
      this.$axios.post("http://localhost:8081/course/add",this.form).then(res=>{
        if(res.data.code==200){
          this.$message({
            message: 'Add successfully!',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.resetForm()
          this.getCourses()
        }else {
          this.$message({
            message: 'Add Failed!',
            type: 'error'
          });
        }
      })
    },
    editCourse(){
      this.$axios.post("http://localhost:8081/course/update",this.form).then(res=>{
        if(res.data.code==200){
          this.$message({
            message: 'Update successfully!',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.resetForm()
          this.getCourses()
        }else {
          this.$message({
            message: 'Update Failed!',
            type: 'error'
          });
        }
      })
    },

    saveOrUpdate(){
      this.$refs.form.validate((valid) =>{
        if(valid){
          if(this.form.id){
            this.editCourse()
          }else {
            this.saveCourse();
          }
        }else {
          alert('invalid input');
          return false;
        }
      })
    },

    handleSizeChange(val) {
      console.log(`${val} items per page`);
      this.pageNum=1
      this.pageSize=val
      this.getCourses()
    },
    handleCurrentChange(val) {
      console.log(`current page: ${val}`);
      this.pageNum = val
      this.getCourses()
    },

    getCourses(){
      this.$axios.post("http://localhost:8081/course/page",{
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param:{
          name:this.name
        }
      }).then(res=>{
        this.tableData = res.data.data;
        this.total = res.data.total;
      })
    },
  },
  validateLecturer(rule, value, callback) {
    if (value === '') {
      callback(new Error('Please select a lecturer'));
    } else {
      callback();
    }
  },
  created(){
    this.getTeacher()
  },
  beforeMount() {
    this.getCourses();
  },
  data() {
    let checkDuplicate = (rule,value,callback)=>{
      console.log("check duplicate")
      if(this.form.id){
        return callback();
      }
      this.$axios.get("http://localhost:8081/course/findByName?name="+this.form.name).then(res=>{
        console.log("check duplicate22")
        if(res.data.code!=200){
          callback()
        }else {
          callback(new Error('name already exists!'))
        }
      })
    }
    return {
      name:'',
      pageSize:2,
      pageNum:1,
      total:0,
      baseUrl: "http://localhost:8081",
      // download: '',
      tableData: [],
      teachers:[],
      // selectedTeacher:'',
      centerDialogVisible:false,
      form:{
        id:'',
        name:'',
        lecturer: '',
        description:'',
        imageUrl:''
      },
      rules: {
        name: [
          {required: true,message: 'name can not be null', trigger: 'blur'},
          {validator:checkDuplicate,trigger: 'blur'}
        ],
        lecturer: [
          {required: true,message: 'please select a lecturer', trigger: 'change'},
          {validator: this.validateLecturer, trigger: 'change'}
        ],
        description: [
          {required: true,message: 'description can not be null', trigger: 'blur'}
        ]
      }
    }
  }
};
</script>
