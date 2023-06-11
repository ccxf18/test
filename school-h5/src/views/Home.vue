<template>
  <div class="list">
    <van-image class="bg" :src="img2" />
    <div class="body">
    <tar-bar :title="title" />
  
      <!-- <div class="title">
        <div></div>
        <div>新闻资讯</div>
      </div>    -->
        <div class="tab">
         <van-tabs
         @click="changeTab"
           sticky
           id="tabs"
           title-active-color="white"
           title-inactive-color="white"
           v-model="active"
         >
           <van-tab name="1" title="本月收入"></van-tab>
           <van-tab name="2" title="本月支出"></van-tab>
         </van-tabs>
        </div>
          <div class="lunbos" v-for="(item, index) in list"  :key="index">
            <div class="first">
                <div>{{item.way || '暂无'}}>{{item.name || '暂无'}}</div>
                <div>金额:{{item.sum}} </div>
            </div>
            <div class="first">
              <div>备注:{{item.remark || '无备注'}}</div>
              <div>{{item.time}} </div>
            </div>
          </div>
    </div>
    <div style="position:fixed; z-index: 100000; bottom:100px;left: 0px;right: 0px;opacity: 0.8;">
            <van-popover
            v-model="showPopover"
            trigger="click"
            :actions="actions"
            placement="top" 
            @select="onSelect"
            theme="dark"
          >
            <template #reference>
              <van-button type="primary">+ 支出/收入</van-button>
            </template>
          </van-popover>
          </div>
  </div>
</template>
<script>
import tarBar from "../components/tarBar/index.vue";
export default {
  data() {
    return {
      showPopover: false,
      actions: [{ text: '支出' }, { text: '收入' }],
      active: 1,
      immediate:false,
      loading:false,
      finished:false,
      images: [
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F8%2F5453005f74be2.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669993999&t=a63e2a1660fdf1681292cb158c6be530',
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210F2130512J47-0-lp.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669993999&t=df53af084cb6ea4c2ed8af92452ff9dd',
       'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1-lanrentuku.qqxzb-img.com%2F2020%2F11%2F11%2F321f9dc2-854d-4edf-9e97-441a1464eb8c.jpg%3FimageView2%2F2%2Fw%2F1024%2F&refer=http%3A%2F%2Fi-1-lanrentuku.qqxzb-img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669993999&t=3b714b49eabc7e4fd7b64a40d680bc1c'
      ],
      list:[],
      things:'',
      count:'',
      pageIndex:1,
      pageSize:100,
      img1: require("../assets/images/area.png"),
      img2: require("../assets/images/bill.jpg"),
      imgs: [
        {img: require("../assets/images/applyHouse.png"),name:"老人信息"},
        {img: require("../assets/images/backRant.png"),name:"个人中心"},
        {img: require("../assets/images/renew.png"),name:"通知管理"},
      ],
      title: "明细",
    };
  },
  components: { tarBar },
  created(){
    // this.queryList()
    this.havCome()
    // this.havPay()
  },
  methods: {
    onSelect(action) {
      if(action.text == '支出'){
        this.$router.push({name:'out'})
      }else{  
        this.$router.push({name:'income'})
      
      }
    },
    //切换tab栏目
    changeTab(item){
      this.active = item
      if(this.active == 1){
        this.havCome()
      }else{
        this.havPay()
      }
    },
    goDetail(item){
      this.$router.push({name:'newDetail',params:{content:item.content,title:item.title,time:item.time,id:item.id,browse:item.browse,like:item.like}})
    },
    //根据用户名查询所有收入记录
    havCome(){
      let params = {
        userName: sessionStorage.getItem('username')
      };
      this.$http.get("/income/findByUserName", {params}).then((res) => {
        if (res.code == 0) {
          res.data.data.forEach((item)=>{
              item.time = this.utils.dateFormat(item.time)
          })
          this.list = res.data.data
        }
      });
    },
      //根据用户名查询所有支出记录
    havPay(){
      let params = {
        userName: sessionStorage.getItem('username')
      };
      this.$http.get("/expend/findByUserName", {params}).then((res) => {
        if (res.code == 0) {
          res.data.data.forEach((item)=>{
              item.time = this.utils.dateFormat(item.time)
          })
          this.list = res.data.data
        }
      });
    },
    apply(item){
      if(item == 0){
        this.$router.push({name:'oldInfomation',params:{title:'管家信息'}})
      }else if(item == 1){
        this.$router.push({name:'my',params:{title:'个人中心'}})
      }else if(item == 2){
        this.$router.push({name:'process',params:{title:'通知管理'}})
      }
    },
    goModel() {
      this.$toast({
        msg: "成功提示",
        type: "success",
      });
    },
  },
};
</script>

<style scoped lang="less">
.list {
  .bg{
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0px;
    right: 0px;
    top:0px;
    bottom:0px;
  }
  .lunbos {
      position: relative;
      z-index: 100;
      background: white;
      box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
      border-radius: 8px;
      // width: 355px;
      width: 95%;
      // height: 134px;
      margin-top: 10px;
      margin-left: 10px;
      text-align: left;
      .first {
        display: flex;
        justify-content: space-between;
        padding:10px 15px;
        font-weight: 400;
      }
      .two {
        height: 60px;
        overflow: scroll;
        padding-top: 20px;
        margin-left: 15px;
        font-size: 14px;
        font-weight: 400;
        color: #666666;
      }
      .three {
        padding-top: 5px;
        margin-left: 15px;
        font-size: 14px;
        font-weight: 400;
        color: #666666;
      }
    }
  .tab {
      // margin-top: 20px;
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
      // background-color: #9768c6;
      padding: 10px;
      height: 500px;
      overflow: scroll;
    }
  
  height: 100vh;
  width: 100%;
  // overflow: scroll;
  .listItem {
    width: 100%;
    padding: 0 15px;
    height: 45px;
    line-height: 45px;
    position: relative;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
    text-align: left;
  }
  .im {
    width: 100px;
    height: 100px;
  }
  .body {
    // height: 85vh;
    height: 760px;
    overflow: scroll;
    // margin-top: 45px;
    .top {
      height: 150px;
      width: 100%;
      background-color: #6ea9f1;
    }
    .lunbo {
      overflow: hidden;
      padding:10px;
      background: #ffffff;
      box-shadow: 0px 0px 6px 2px rgba(92, 136, 251, 0.12);
      border-radius: 8px;
      width: 95%;
      // width: 355px;
      height: 134px;
      margin-top: -120px;
      margin-left: 10px;
      .imgs {
        display: flex;
        .house {
          display: flex;
          margin-top: 40px;
          margin-left: 0.25rem;
          .house_left {
            margin-top: 3px;
          }
          .house_right {
            :nth-child(1) {
              font-weight: 500;
              color: #ff7a00;
              line-height: 40px;
              font-size: 28px;
            }
            :nth-child(2) {
              font-weight: 400;
              color: #666666;
              line-height: 21px;
              font-size: 15px;
            }
          }
        }
        .house2 {
          display: flex;
          margin-top: 40px;
          margin-left: 50px;
          .house_left {
            margin-top: 3px;
          }
          .house_right {
            :nth-child(1) {
              font-weight: 500;
              color: #ff7a00;
              line-height: 40px;
              font-size: 28px;
            }
            :nth-child(2) {
              font-weight: 400;
              color: #666666;
              line-height: 21px;
              font-size: 15px;
            }
          }
        }
      }
    }
    .title {
      margin-top: 20px;
      display: flex;
      :nth-child(1) {
        margin-top: 4px;
        margin-left: 5px;
        margin-right: 5px;
        width: 3px;
        height: 20px;
        background: #6ea9f1;
        border-radius: 2px;
      }
      :nth-child(2) {
        font-size: 22px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: #333333;
        line-height: 30px;
      }
    }
    .apply_house {
      margin-top: 10px;
      position: relative;
      .imgs {
        margin-top: 10px;
        .imgs_left {
          position: absolute;
          z-index: 10;
          margin-top: 30px;
          font-weight: 500;
          color: #8b603b;
          font-size: 26px;
          margin-left: 50px;
      }
    }
  }
  }
}
</style>
