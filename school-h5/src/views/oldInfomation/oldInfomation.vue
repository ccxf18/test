<template>
    <div class="list">
      <tar-bar :title="title" />
      <div class="body">
        <div class="top"></div>
        <div class="tab">
  <!--        <van-tabs-->
  <!--          sticky-->
  <!--          id="tabs"-->
  <!--          title-active-color="white"-->
  <!--          title-inactive-color="white"-->
  <!--          v-model="active"-->
  <!--          @change="onClickTab"-->
  <!--        >-->
  <!--          <van-tab title="全部"></van-tab>-->
  <!--          <van-tab title="办理中"></van-tab>-->
  <!--          <van-tab title="已办结"></van-tab>-->
  <!--          <van-tab title="已取消"></van-tab>-->
  <!--        </van-tabs>-->
        </div>
  
        <van-list
        :immediate-check = 'immediate'
          class="all_list"
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
          <div class="lunbo" v-for="(item, index) in list" :key="index">
            <div class="first">
              <div>
                <van-image width="22px" height="22px" :src="img2" />
                <div style="margin-left:5px"> 老人姓名：{{item.name}}</div>
              </div>
            </div>
            <div class="second">
                <div>年龄:{{item.age}}</div>
                <div>性别:{{item.gender}}</div>
            </div>
            <div  class="second">
                <div >护工:{{item.name}}</div>
                <div >监护人{{item.username}}</div>
            </div>
            <div  class="third">
                <van-button round class="btns" @click="goNurse(item)" type="info">查看护工</van-button>
                <van-button round class="btns" @click="goBill(item)" type="info">查看账单</van-button>
            </div>
          </div>
        </van-list>
      </div>
    </div>
  </template>
    <script>
  import tarBar from "../../components/tarBar/index1.vue";
  import { dateFormat } from "../../JS/utils";
  export default {
    data() {
      return {
        immediate:false,
        finished:false,
        status:null,
        active: 0,
        list: [],
        pageIndex:1,
        pageSize:5,
        img4: require("../../assets/images/area.png"),
        img1: require("../../assets/images/house1.png"),
        img2: require("../../assets/images/house2.png"),
        img3: require("../../assets/images/house3.png"),
        loading: false,
        title: "老人信息",
      };
    },
    components: { tarBar },
    created() {
      // this.havContract();
      this.havOldList()
    },
    // filters: {
    //   showDate(value) {
    //       let date = new Date(value * 1000);
    //       return this.dateFormat(date,'yyyy-MM-dd hh:mm:ss');
    //     }
    //   },
    methods: { 
      //查看护工
    goNurse(item){
        this.$router.push({name:'nurseInfomation',params:{name:item.nurse}});
    },
    //查看账单
    goBill(item){
        this.$router.push({name:'bill',params:{name:item.name}});
    },
      //加载更多
      onLoad() {
        if (this.list.length < this.total) {
          this.pageIndex= this.pageIndex + 1;
          this.havOldList()
          // this.havContract();
        }
      },
      // 获取合同列表
      havOldList() {
        let username = sessionStorage.getItem('username')
        this.$http.get("/oldman/searchOldman?username="+username).then((res) => {
          if (res.code == 0) {
            if (this.list.length <= 0) {
              this.list = res.data;
            } else {
              this.list = this.list.concat(res.data);
            }
            this.loading = false;
            this.total = res.total;
            if (this.total<= this.list.length) {
              this.$nextTick(() => {
                this.finished = true;
              });
            }
          }
        });
      },
      onClickTab(item) {
        this.list = []
        this.pageIndex=1
        this.loading = true
        this.finished = false
        if(item == '0'){
          this.status = null
        }else if(item == '1'){
          this.status = 1
        }else if(item == '2'){
          this.status = 2
        }else if(item == '3'){
          this.status = 3
        }
       this.havOldList()
      },
    },
  };
  </script>
  
    <style scoped lang="less">
  .list {
    width: 100%;
    .body {
      .top {
        position: absolute;
        height: 80px;
        width: 100%;
        background-color: #6ea9f1;
      }
      .tab {
        margin-top: 45px;
        width: 100%;
        background-color: #6ea9f1;
        #tabs /deep/ .van-tabs__nav--line {
          background-color: #6ea9f1;
          color: white;
          > .van-tabs__line {
            background-color: white !important;
          }
        }
      }
      .all_list {
        padding: 10px;
        height: 700px;
        overflow: scroll;
      }
      .lunbo {
        position: relative;
        z-index: 100;
        background: white;
        box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
        border-radius: 8px;
        // width: 355px;
        width: 95%;
        height: 210px;
        margin-top: 10px;
        margin-left: 10px;
        text-align: left;
        .second{
            display: flex;
            justify-content: space-between;
            padding: 0px 15px 0px;
            margin-top:20px
        }
        .third{
            display: flex;
            justify-content: space-between;
            padding: 0px 15px 0px;
            margin-top:25px;
            .btns{
                border:none;
                width:140px;
                background:linear-gradient(135deg, #6ea9f1 0%, #a7f0c3 100%);
            }
        }
        .first {
          display: flex;
          justify-content: space-between;
          margin-left: 15px;
          padding-top: 15px;
          font-weight: 500;
          > :nth-child(1) {
            display: flex;
          }
         .tag1 {
            background: #ff9d00;
            border-radius: 100px 0px 0px 100px;
            width: 60px;
            text-align: center;
            font-size: 12px;
            color: white;
            line-height: 28px;
          }
          .tag2 {
            color: white;
            background: #B8B8B8;
            border-radius: 100px 0px 0px 100px;
            width: 60px;
            text-align: center;
            font-size: 12px;
            line-height: 28px;
          }
          .tag3 {
            color: white;
            background: #56BA3E;
            border-radius: 100px 0px 0px 100px;
            width: 60px;
            text-align: center;
            font-size: 12px;
            line-height: 28px;
          }
        }
        .two {
          height: 50px;
          overflow: scroll;
          padding-top: 20px;
          margin-left: 15px;
          font-size: 14px;
          font-weight: 400;
          color: #666666;
        }
        .three {
          padding-top: 15px;
          margin-left: 15px;
          font-size: 14px;
          font-weight: 400;
          color: #666666;
        }
      }
    }
  }
  </style>
  