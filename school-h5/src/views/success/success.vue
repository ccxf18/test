<template>
    <div class="list">
      <tar-bar :title="title" />
      <!-- <van-button type="primary" @click="showPopu" style="margin-top:100px">主要按钮</van-button> -->
        <div class="imgs">
            <img src="../../assets/images/success.png" alt="">
            <div style="color:#6ea9f1; margin-top: 20px;font-size: 23px;">支付成功</div>
        </div>    
            <van-button
             style="margin-top:10px"
             @click="goBack"
              round
              block
              type="primary"
              native-type="goRegister"
              class="btn"
            >
              返回首页
            </van-button>
    </div>
  </template>
    <script>
  import tarBar from "../../components/tarBar/index1.vue";
  import { dateFormat } from "../../JS/utils";
  export default {
    data() {
      return {
        money:0,
        ids:[],
        results:[],
        result:[],
        name:'',
        show:false,
        immediate:false,
        finished:false,
        status:null,
        active: 0,
        list: [],
        pageIndex:1,
        pageSize:5,
        img1: require("../../assets/images/house1.png"),
        img2: require("../../assets/images/house2.png"),
        img3: require("../../assets/images/house3.png"),
        loading: false,
        title: "账单信息",
      };
    },
    components: { tarBar },
    created() {

    },
    // filters: {
    //   showDate(value) {
    //       let date = new Date(value * 1000);
    //       return this.dateFormat(date,'yyyy-MM-dd hh:mm:ss');
    //     }
    //   },
    methods: { 
        goBack(){
            this.$router.push({name:'home'})
        }
    },
  };

  </script>
  
    <style scoped lang="less">
  .list {
    height: 100vh;
    width: 100%;
    .imgs{
      margin-top:50%
    }
 
  }
  .btn {
    width: 85%;
    margin-left:8%;
    margin-top:180px !important;
    background: linear-gradient(135deg, #6ea9f1 0%, #a7f0c3 100%);
  }
  </style>
  