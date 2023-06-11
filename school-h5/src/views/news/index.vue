<template>
  <div class="list">
    <backtar-bar :title="title" />
    <div class="bg"></div>
    <div class="body">
      <div class="title">
        <div></div>
        <div>{{title}}信息</div>
      </div>
      <div class="content">
        <div>
          <van-field
            disabled
            v-model="contractNo"
            label="合同编号"
            placeholder="请输入合同编号"
            input-align="right"
          />
          <van-field
            v-model="reason"
            rows="2"
            autosize
            label="申请理由"
            type="textarea"
            maxlength="50"
            show-word-limit
            placeholder="请输入申请理由"
            input-align="right"
          />
        </div>
      </div>
    </div>
    <van-button class="btn" @click="submit" type="primary">提交信息</van-button>
  </div>
</template>
  
  <script>
import backtarBar from "../../components/tarBar/backIndex.vue";
export default {
  data() {
    return {
      reason:'',
      title: "",
      id:'',
      contractNo:''
    };
  },
  components: { backtarBar },
  created(){
    this.title = this.$route.query.title
    this.id = this.$route.query.id
    this.contractNo = this.$route.query.contractNo
  },
  methods:{
    submit() {
      if(this.title == '周转房续租'){
        let params = {
        reason:this.reason,
        contractId:this.id
        }
        this.$http.post('revolving/select/renew/add',params).then(res=>{
          if(res.code == 200){
            this.$toast({
            msg: "续租申请提交成功",
            type: "success",
          });
          this.$router.push({path:'/'})
          }else{
            this.$toast({
            msg: res.message,
            type: "warning",
          });
          }
        })
      }else{
        let params = {
        reason:this.reason,
        contractId:this.id
        }
        this.$http.post('revolving/select/back/add',params).then(res=>{
          if(res.code ==200){
            this.$toast({
            msg: "退租申请提交成功",
            type: "success",
          });
          this.$router.push({path:'/'})
          }else{
            this.$toast({
            msg: res.message,
            type: "warning",
          });
          }
        },
        (error) => {
          this.$toast({
            msg: error,
            type: "fail",
          });
        })
      } 
    },
  },
  beforeRouteLeave (to, from, next) {
     // 销毁组件，避免通过vue-router再次进入时，仍是上次的history缓存的状态
     this.$destroy(true)
     next()
  }
};
</script>
  
  <style scoped lang="less">
.list {
  height: 100vh;
  overflow: scroll;
  width: 100%;
  background-color: #f4f4f4;
  .bg{
    background-color: #4664F0;
    width: 100%;
    height: 130px;
    position: absolute;
  }
  .body {
    position: relative;
    z-index: 1000;
    width: 355px;
    margin: 24px 10px;
    background-color: #ffffff;
    border-radius: 6px;
    height: 78%;
    // max-height: 500px;
    .title {
        border-bottom: 1px solid #DFE0E4;
        margin: 0px 16px;
      display: flex;
      :nth-child(1){
        margin-top: 10px;
        width: 3px;
        height: 21px;
        background: #FF7A00;
        margin-bottom: 11px;
        margin-left: -16px;
      }
      :nth-child(2){
        margin-left: 10px;
        margin-top: 10px;
        font-weight: 500;
        font-size: 15px;
        color: #333333;
      }
    }
    .content {
      text-align: right;
    }
  }
  .btn{
    // position: absolute;
    // bottom:24px;
    // height: 46px;
    // left: 30px;
    // right: 30px;
    position: fixed;
    width: 85%;
    bottom:24px;
    left: 30px;
    z-index: 1000;
    background: linear-gradient(135deg, #5B88FB 0%, #3E58ED 100%);
    border-radius: 23px;
  }
}
</style>
  