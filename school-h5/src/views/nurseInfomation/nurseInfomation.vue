<template>
    <div class="list">
      <backtar-bar :title="title" />
      <div class="bg"></div>
      <div class="content_all">
  <!--      一个表单-->
        <div class="body" >
          <div class="title">
            <div></div>
            <div>护工信息:</div>
          </div>
          <div class="content">
            <van-form @submit="onSubmit">
              <van-field
                v-model="list.name"
                name="护工姓名"
                label="护工姓名"
                disabled
                placeholder="请输入护工姓名"
              />
              <van-field name="radio" label="性别">
                <template #input style="color:blue">
                  <van-radio-group  disabled v-model="list.gender" direction="horizontal">
                    <van-radio name="男">男</van-radio>
                    <van-radio name="女">女</van-radio>
                  </van-radio-group>
                </template>
              </van-field>
              <van-field
                v-model="list.phone"
                name="电话号"
                label="电话号"
                disabled
                placeholder="请输入电话号"
              />
              <van-field
                v-model="list.workYears"
                name="工作时间"
                label="工作时间"
                disabled
                placeholder="请输入工作时间"
              />
              <van-field
                v-model="list.job"
                name="职业"
                label="职业"
                disabled
                placeholder="请输入职业"
              />
              <van-field
                v-model="list.remark"
                name="备注"
                label="备注"
                disabled
                placeholder="暂无"
              />
              <!-- <div style="margin-top: 100px; margin: 200px 16px 10x;">
                <van-button style="background:#6ea9f1;border:none" round block type="info" native-type="submit">提交</van-button>
              </div> -->
            </van-form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
      <script>
  import backtarBar from "../../components/tarBar/backIndex.vue";
  export default {
    data() {
      return {
        name:'',//护工姓名
        list:{},
        immediate:false,
        finished: false,
        pageIndex: 1,
        pageSize: 5,
        loading: false,
        message: "",
        title: "护工信息",
        total:0,
      };
    },
    components: { backtarBar },
    created() {
     this.name = this.$route.params.name
     this.queryList()
      this.userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
    },
    methods: {
        queryList(){
            this.$http.get("/purse/searchPurse?name="+this.name).then((res) => {
                if(res.code == '0'){
                this.list = res.data
                }
            })
        },  
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
    /deep/ .van-field__label {
    color: #0d0e0e;
    }
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
  