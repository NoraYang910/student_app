<script>
export default {
  data(){
    return{
      badgeValue: 3,
      dialogVisible: false,
      imageUrl: '',
      teacher:JSON.parse(sessionStorage.getItem("CurTeacher")),
      messages: [
          "Quidditch matches to start on 5 February",
          "Academy meeting to be held on 18 January",
          "Daily Prophet to visit on 25 January"
      ],
      currentIndex: 0
    }
  },
  mounted() {
    this.getImageUrl()
  },
  computed: {
    currentInfo() {
      return this.messages[this.currentIndex];
    },
  },
  methods: {
    handleConfirm(){
      this.dialogVisible=false;
      if(this.badgeValue >=1){
      this.badgeValue = (this.badgeValue - 1);
      console.log(this.badgeValue)
      }
      this.currentIndex = (this.currentIndex + 1);
    },
    noticeInfo(){
      this.dialogVisible=true;
    },
    getImageUrl() {
      this.imageUrl = `http://localhost:8081/file/download?name=${this.teacher.imageUrl}`;
    }
  }
}
</script>

<template>
  <div>
    <div class="badges">
    <el-badge :value="badgeValue" class="item">
      <el-button size="small" @click="noticeInfo">notice</el-button>
    </el-badge>
      <el-dialog
          title="Notice"
          style="text-align: center; font-style: italic;"
          :visible.sync="dialogVisible"
          width="30%">
        <span style="font-size: large">
          <template v-if="badgeValue===0">You have no notice!</template>
          <template v-else>{{currentInfo}}</template>
          </span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">Cancel</el-button>
    <el-button @click="handleConfirm">Confirm</el-button>
  </span>
      </el-dialog>
    <el-badge :value="3" class="item">
      <el-button size="small">query</el-button>
    </el-badge>
    <el-badge :value="1" class="item" type="primary">
      <el-button size="small">reply</el-button>
    </el-badge>
    </div>

    <div class="flex-container">
      <el-descriptions class="margin-top" title="Personal Info" :column="1" style="width: 400px" border>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-key"></i>
          id
        </template>
        {{this.teacher.id}}
      </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            name
          </template>
          {{this.teacher.name}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-house"></i>
            Academy
          </template>
          {{this.teacher.academy}}
        </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          Telephone
        </template>
        {{this.teacher.telephone}}
      </el-descriptions-item>
      </el-descriptions>
      <div class="image-container">
        <img :src="imageUrl" alt="Teacher Image" class="imageTe"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.imageTe {
  margin-top: 40px;
  width: 150px;
  height: 150px;
  border-radius: 50%; /* 将边框半径设置为50%以创建圆形效果 */
  overflow: hidden;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
  margin-bottom: 50px;
}

.badges {
  text-align: right;
}
.flex-container {
  display: flex;
  margin-left: 150px;
  height: 385px;
}

.image-container {
  margin-left: 100px;
}
</style>