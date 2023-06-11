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
                <div style="margin-left:5px"> 管家姓名：{{item.name}}</div>
              </div>
  <!--            <div>-->
  <!--              <div v-if="item.status == 1" class="tag1">办理中</div>-->
  <!--              <div v-if="item.status == 2" class="tag2">已办结</div>-->
  <!--              <div v-if="item.status == 3" class="tag3">已取消</div>-->
  <!--            </div>-->
            </div>
            <div class="two">管家职责:{{item.work}}</div>
            <div class="three">管家职位{{item.job}}</div>
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
        title: "管家信息中心",
      };
    },
    components: { tarBar },
    created() {
      // this.havContract();
      this.havProperty()
    },
    // filters: {
    //   showDate(value) {
    //       let date = new Date(value * 1000);
    //       return this.dateFormat(date,'yyyy-MM-dd hh:mm:ss');
    //     }
    //   },
    methods: { 
      //获取物业通知
      havProperty(){
        let params = {
          page: this.pageIndex,
          pagesize: this.pageSize,
        };
        this.$http.post("/worker/findAll", params).then((res) => {
          if (res.code == 0) {
            if (this.list.length <= 0) {
              this.list = res.data;
            } else {
              this.list = this.list.concat(res.data);
            }
            this.loading = false;
            this.total = res.total;
            if (this.total <= this.list.length) {
              this.$nextTick(() => {
                this.finished = true;
              });
            }
          }
        });
      },
      //加载更多
      onLoad() {
        if (this.list.length < this.total) {
          this.pageIndex= this.pageIndex + 1;
          this.havProperty()
          // this.havContract();
        }
      },
      // 获取合同列表
      havContract() {
        let params = {
          pageIndex: this.pageIndex,
          pageSize: this.pageSize,
          status: this.status,
          pageSorts: [
            {
              asc: false,
              column: "applyDate",
            },
          ],
        };
        this.$http.post("revolving/select/progress/page", params).then((res) => {
          if (res.code == 200) {
            if (this.list.length <= 0) {
              this.list = res.data.list;
            } else {
              this.list = this.list.concat(res.data.list);
            }
            this.loading = false;
            this.total = res.data.total;
            if (res.data.isLastPage) {
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
       this.havContract()
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
        background-color: #4868f1;
      }
      .tab {
        margin-top: 45px;
        width: 100%;
        background-color: #4868f1;
        #tabs /deep/ .van-tabs__nav--line {
          background-color: #4868f1;
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
        height: 134px;
        margin-top: 10px;
        margin-left: 10px;
        text-align: left;
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
  