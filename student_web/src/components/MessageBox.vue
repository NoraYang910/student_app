<script>
export default {
  name:'MessageBox',
  beforeMount() {
    this.getList()
  },
  methods:{
    getList(){
      this.buttonText='Delete'
      this.$axios.get("http://localhost:8081/msg/list?teacherId="+this.teacher.id).then(res=>{
        this.tableData = res.data.data
      })
    },
    formatDate(row) {
      const date = new Date(row.date);
      return date.toLocaleDateString();
    },
    showDetail(row){
      this.centerDialogVisible=true;
      console.log(row);
      this.detailForm=row
    },
    getRead(){
      this.buttonText='Delete'
      this.$axios.get("http://localhost:8081/msg/read?teacherId="+this.teacher.id).then(res=>{
            this.tableData = res.data.data
      })
    },
    getUnread(){
      this.buttonText='Delete'
      this.$axios.get("http://localhost:8081/msg/unread?teacherId="+this.teacher.id).then(res=>{
        this.tableData = res.data.data
      })
    },
    deleteMail(row){
      console.log(row)
      if(row.isDelete === 0){
        this.$axios.post("http://localhost:8081/msg/delete",row).then(res=>{
          if(res.data.code==200){
            this.$message({
              message: 'Delete successfully!',
              type: 'success'
            });
            this.getList()
          }else {
            this.$message({
              message: 'Delete Failed!',
              type: 'error'
            });
          }
        })
      }else {
        this.$axios.post("http://localhost:8081/msg/restore",row).then(res=>{
          if(res.data.code==200){
            this.$message({
              message: 'Restore successfully!',
              type: 'success'
            });
            this.getTrash()
          }else {
            this.$message({
              message: 'restore Failed!',
              type: 'error'
            });
          }
        })
      }
    },
    getTrash(){
      this.buttonText='Restore',
      this.$axios.get("http://localhost:8081/msg/listDele?teacherId="+this.teacher.id).then(res=>{
        this.tableData = res.data.data
      })
    },
    changeRead(){
      this.$axios.post("http://localhost:8081/msg/update",this.detailForm).then(res=>{
        if(res.data.code==200){
          this.$message({
            message: 'Mark successfully!',
            type: 'success'
          });
          this.getList()
        }else {
          this.$message({
            message: 'Mark Failed!',
            type: 'error'
          });
        }
      })
      this.centerDialogVisible=false;
    },
    formatYesNo(isRead){
      return isRead === 1 ? 'Yes' : 'No';
    },
    formatFormDate(date){
      const formattedDate = new Date(date);
      return formattedDate.toLocaleDateString();
    }
  },
computed:{
  readButtonText(){
    return this.detailForm.isRead === 1? 'mark as unread':'mark as read'
  },
  formattedFormDate(){
    return this.formatFormDate(this.detailForm.date)
  }
},
  data(){
    return {
      centerDialogVisible:false,
      detailForm:'',
      teacher: JSON.parse(sessionStorage.getItem("CurTeacher")),
      tableData:[],
      buttonText:'Delete'
    }
  }
}
</script>

<template>
  <div>
    <div class="buttonDiv">
      <el-button @click="getList">All</el-button>
      <el-button @click="getRead">Read</el-button>
      <el-button @click="getUnread">Unread</el-button>
      <el-button @click="getTrash">Trash</el-button>
    </div>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%">
      <el-table-column prop="no" type="index" label="no." width="80"></el-table-column>
      <el-table-column prop="subject" label="Subject" width="250">
      </el-table-column>
      <el-table-column prop="sender" label="Sender"></el-table-column>
      <el-table-column prop="date" label="Date" :formatter="formatDate"></el-table-column>
      <el-table-column prop="isRead" label="isRead">
        <template slot-scope="scope">
        {{ formatYesNo(scope.row.isRead)}}
      </template></el-table-column>
      <el-table-column label="Manage">
<!--         eslint-disable-next-line-->
        <template slot-scope="scope">
          <el-button size="small" @click="showDetail(scope.row)">Detail</el-button>
          <el-popconfirm
              title="Are you sure to do this?"
              confirm-button-text='Yes'
              cancel-button-text='No'
          @confirm="deleteMail(scope.row)">
            <el-button slot="reference" size="small" style="margin-left: 5px">{{buttonText}}</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
        :visible.sync="centerDialogVisible"
        width="50%"
        center>
      <el-form ref="form" :model="detailForm" label-width="120px" size="mini">
        <el-form-item label="subject" prop="subject">
          <el-input v-model="detailForm.subject" readonly></el-input>
        </el-form-item>
        <el-form-item label="sender" prop="sender">
          <el-input v-model="detailForm.sender" readonly></el-input>
        </el-form-item>
        <el-form-item label="date" prop="date">
          <el-input v-model="formattedFormDate" readonly></el-input>
        </el-form-item>
        <el-form-item label="detail" prop="detail">
          <el-input v-model="detailForm.detail" readonly type="textarea" :rows="10"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">close</el-button>
    <el-button @click="changeRead">{{readButtonText}}</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<style scoped>
.buttonDiv {
  margin-left: 5px;
  margin-bottom: 5px;
}
</style>