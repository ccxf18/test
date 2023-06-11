<template>
  <div class="list">
    <backtar-bar :title="title" />
    <div class="bg"></div>
    <div class="content_all">
<!--      一个表单-->
      <div class="body" v-for="(item, index) in list" :key="index">
        <div class="title">
          <div></div>
          <div>房间信息：</div>
        </div>
        <div class="content">
          <div class="content_d">
            <div>小区：</div>
            <div>楼栋：</div>
          </div>
          <div class="content_d">
            <div>单元：</div>
            <div>楼层：</div>
          </div>
          <div class="content_d">
            <div>户型：</div>
            <div>房间：</div>
          </div>
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
      immediate:false,
      list:[{},{}],
      finished: false,
      pageIndex: 1,
      pageSize: 5,
      loading: false,
      message: "",
      title: "缴费信息",
      total:0,
    };
  },
  components: { backtarBar },
  created() {
    // this.havContract();
  },
  methods: {
    //加载更多
    onLoad() {
      if (this.list.length < this.total) {
        this.pageIndex = this.pageIndex+1;
        this.havContract();
      }
    },
    // 获取合同列表
    havContract() {
      let params = {
        pageIndex: this.pageIndex,
        pageSize: this.pageSize,
        status: 2,
        partyB: JSON.parse(sessionStorage.getItem("userInfo")).username,
      };
      this.$http.post("revolving/contract/page", params).then((res) => {
        if (res.code == 200) {
          if(this.list.length <= 0){
            this.list = res.data.list;
          }else{
            this.list = this.list.concat(res.data.list);
          }
          this.loading = false;
          this.total = res.data.total;
          if (this.list.length >= this.total) {
            this.$nextTick(()=>{
              this.finished = true;
            })
          }
        }
      });
    },
    submit() {
      this.$toast({
        msg: "提交成功",
        type: "success",
      });
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
        color: #666666;
        display: flex;
        font-weight: 400;
        font-size: 14px;
        // justify-content: space-between;
        margin: 10px 10px;
        text-align: left;
        > :nth-child(1) {
          width: 185px;
        }
        > :nth-child(1) {
          // flex:1
          width: 185px;
        }
      }
    }
  }
}
</style>
