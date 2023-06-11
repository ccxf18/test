<template>
  <div class="list">
    <backtar-bar :title="title" />
    <div class="bg"></div>
    <div class="content_all">
<!--      一个表单-->
      <div class="body" >
        <div class="title">
          <div></div>
          <div>个人信息：</div>
        </div>
        <div class="content">
          <van-form @submit="onSubmit">
            <van-field
              v-model="userInfo.username"
              name="账号"
              label="账号"
              disabled
              placeholder="请输入账号"
            />
            <van-field
              v-model="userInfo.email"
              name="邮箱"
              label="邮箱"
              disabled
              placeholder="请输入邮箱"
            />
            <van-field
              v-model="userInfo.realname"
              name="姓名"
              label="姓名"
              placeholder="请输入姓名"
            />
            <van-field name="radio" label="性别">
              <template #input>
                <van-radio-group v-model="userInfo.gender" direction="horizontal">
                  <van-radio name="男">男</van-radio>
                  <van-radio name="女">女</van-radio>
                </van-radio-group>
              </template>
            </van-field>
            <van-field
              v-model="userInfo.age"
              name="年龄"
              label="年龄"
              placeholder="请输入年龄"
            />
            <van-field
              v-model="userInfo.address"
              name="地址"
              label="地址"
              placeholder="请输入地址"
            />
            <div style="margin-top: 100px; margin: 200px 16px 10x;">
              <van-button style="background:#6ea9f1;border:none" round block type="info" native-type="submit">提交</van-button>
            </div>
          </van-form>
          <!-- <div class="content_d">
            <div>户主：{{userInfo.username}}</div>
           
          </div>
          <div class="content_d">
            <div>住户：{{userInfo.realname}}</div>
          </div>
          <div class="content_d">
            <div>性别：{{userInfo.gender}}</div>
          </div>
          <div class="content_d">
            <div>年龄：{{userInfo.age}}</div>
          </div>
        
          <div class="content_d">
            <div>地址：{{userInfo.address}}</div>
          </div>
          <div class="content_d">
            <div>电话号：{{userInfo.phone}}</div>
          </div> -->
        </div>
      </div>
<!--    <van-list-->
<!--      :immediate-check = 'immediate'-->
<!--      v-model="loading"-->
<!--      :finished="finished"-->
<!--      finished-text="没有更多了"-->
<!--      @load="onLoad"-->
<!--    >-->
<!--      <div class="body" v-for="(item, index) in list" :key="index">-->
<!--        <div class="title">-->
<!--          <div></div>-->
<!--          <div>合同编号：{{ item.contractNo }}</div>-->
<!--        </div>-->
<!--        <div class="content">-->
<!--          <div class="content_d">-->
<!--            <div>小区：{{ item.room.communityName }}</div>-->
<!--            <div>楼栋：{{ item.room.buildingName }}</div>-->
<!--          </div>-->
<!--          <div class="content_d">-->
<!--            <div>单元：{{ item.room.unitName }}</div>-->
<!--            <div>楼层：{{ item.room.floor || "" }}</div>-->
<!--          </div>-->
<!--          <div class="content_d">-->
<!--            <div>户型：{{ item.room.houseTypeInfo }}</div>-->
<!--            <div>房间：{{ item.room.roomNumber }}</div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </van-list>-->
    </div>
  </div>
</template>

    <script>
import backtarBar from "../../components/tarBar/backIndex.vue";
export default {
  data() {
    return {
      userInfo:{},
      immediate:false,
      list:[{},{}],
      finished: false,
      pageIndex: 1,
      pageSize: 5,
      loading: false,
      message: "",
      title: "完善信息",
      total:0,
    };
  },
  components: { backtarBar },
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
    console.log('====',this.userInfo)
  },
  methods: {
    onSubmit(){
      let params = this.userInfo
      this.$http.post("/user/updateOne", params).then((res) => {
        if(res.code == '0'){
          this.$toast({
            msg: "提交成功",
            type: "success",
          });
          this.$router.push({name:'my'})
        }else{
          this.$toast({
            msg: "提交失败",
            type: "success",
          });
        }
      })
    },
  },
  beforeRouteLeave(to, from, next) {
    // 销毁组件，避免通过vue-router再次进入时，仍是上次的history缓存的状态
    this.$destroy(true);
    next();
  },
};
</script>

    <style scoped lang="less">
.list {
  // height: 100vh;
  width: 100%;
  // background-color: #f4f4f4;
  .bg {
    background-color: #6ea9f1;
    width: 100%;
    height: 130px;
    position: absolute;
  }
  .body {
    position: relative;
    z-index: 100;
    width: 355px;
    margin: 24px 10px;
    padding: 10px;
    height: 90vh;
    border-radius: 6px;
    //   height: 650px;
      background: white;
      box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
      border-radius: 8px;
    .title {
      border-bottom: 1px solid #dfe0e4;
      padding: 0px 16px;
      display: flex;
      :nth-child(1) {
        margin-top: 10px;
        width: 3px;
        height: 21px;
        background: #ff7a00;
        margin-bottom: 11px;
        margin-left: -16px;
      }
      :nth-child(2) {
        margin-left: 10px;
        margin-top: 10px;
        font-weight: 500;
        font-size: 15px;
        color: #333333;
      }
    }
    .content {
      text-align: right;
      .content_d {
        height: 60px;
        line-height: 60px;
        border-bottom:1px solid   #dfe0e4;
        color: #666666;
        display: flex;
        font-weight: 400;
        font-size: 18px;
        // justify-content: space-between;
        margin: 10px 10px;
        text-align: left;
        > :nth-child(1) {
          width: 300px;
        }
        > :nth-child(1) {
          // flex:1
          // width: 185px;
        }
      }
    }
  }
}
</style>
