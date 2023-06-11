<template>
    <div class="list">
      <backtar-bar :title="title" />
      <div class="bg"></div>
      <div class="content_all">
  <!--      一个表单-->
        <div class="body" >
          <div class="title">
            <div></div>
            <div>支出记录：</div>
          </div>
          <div class="content">
            <van-form @submit="onSubmit">
              <van-field
                v-model="Obj.userName"
                name="用户"
                label="用户"
                disabled
                placeholder="请输入用户"
              />
              <van-field
                v-model="Obj.name"
                name="支出项目"
                label="支出项目"
                placeholder="请输入支出项目"
              />
              <van-field
                readonly
                clickable
                name="picker"
                v-model="Obj.way"
                label="请选择来源"
                placeholder="点击选择来源"
                @click="showPicker = true"
                />
                <van-popup v-model="showPicker" position="bottom">
                <van-picker
                    show-toolbar
                    :columns="columns"
                    @confirm="onConfirm"
                    @cancel="showPicker = false"
                />
               </van-popup>
              <van-field
                v-model="Obj.sum"
                name="总额"
                label="总额"
                placeholder="请输入总额"
              />
              <div style="margin-top: 140px; margin: 200px 16px 10x;">
                <van-button style="background:#6ea9f1;border:none" round block type="info" native-type="submit">提交</van-button>
              </div>
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
        showPicker:false,
        columns: ['银行卡', '现金', '支付宝', '微信'],
        Obj:{},
        userInfo:{},
        immediate:false,
        list:[{},{}],
        finished: false,
        pageIndex: 1,
        pageSize: 5,
        loading: false,
        message: "",
        title: "支出记录",
        total:0,
      };
    },
    components: { backtarBar },
    created() {
        this.$set(this.Obj,'userName',sessionStorage.getItem('username'))
    },
    methods: {
        onConfirm(value) {
            this.Obj.way = value;
      this.showPicker = false;
    },
      onSubmit(){
        let params = this.Obj
        this.$http.post("/expend/insert", params).then((res) => {
          if(res.code == '0'){
            this.$toast({
              msg: "提交成功",
              type: "success",
            });
            this.$router.push({name:'home'})
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
  