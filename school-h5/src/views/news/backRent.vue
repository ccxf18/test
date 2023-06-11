<template>
  <div class="list">
    <backtar-bar :title="title" />
    <div class="bg"></div>
    <div class="body">
      <div class="title">
        <div></div>
        <div>选择房源</div>
      </div>
      <div class="content">
        <div>
          <van-list
            :immediate-check="immediate"
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
          >
            <van-radio-group v-model="checked">
              <van-cell-group inset>
                <van-cell
                  v-for="(item, index) in list"
                  :key="index"
                  class="left_title"
                  center
                  clickable
                  @click="check(item)"
                >
                  <div style="text-align: left">{{ item.contractNo }}</div>
                  <div style="text-align: left">
                    {{ item.room.communityName }}/{{item.room.buildingName}}/{{item.room.unitName}}/{{item.room.roomNumber}}
                  </div>
                  <template #right-icon>
                    <van-radio :name="index" />
                  </template>
                </van-cell>
              </van-cell-group>
            </van-radio-group>
          </van-list>
        </div>
      </div>
    </div>
    <van-button class="btn" @click="goNext" type="primary">下一步</van-button>
  </div>
</template>
    
    <script>
import backtarBar from "../../components/tarBar/backIndex.vue";
export default {
  data() {
    return {
      immediate:false,
      list:[],
      finished: false,
      pageIndex: 1,
      pageSize: 5,
      loading: false,
      checked: true,
      message: "",
      title: "周转房退租",
    };
  },
  components: { backtarBar },
  created() {
    this.checked = false
    this.havContract();
  },
  methods: {
      //加载更多
      onLoad() {
      if (this.list.length < this.total) {
        this.pageIndex = this.pageIndex+1;
        this.havContract();
      }
    },
    check(item) {
      this.id = item.id
      this.contractNo = item.contractNo
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
          if (this.list.length <= 0) {
            this.list = res.data.list;
          } else {
            this.list = this.list.concat(res.data.list);
          }
          this.loading = false;
          this.total = res.data.total;
          if (this.list.length >= this.total) {
            this.$nextTick(() => {
              this.finished = true;
            });
          }
        }
      });
    },
    goNext() {
      if(this.id){
        this.$router.push({ path: "/news", query: { title: this.title ,id:this.id,contractNo:this.contractNo } });
      }else{
        this.$toast({
          msg: "请选择房源",
          type: "warning",
        })
      }   
    },
  },
  //   beforeRouteLeave (to, from, next) {
  //    // 销毁组件，避免通过vue-router再次进入时，仍是上次的history缓存的状态
  //    this.$destroy(true)
  //    next()
  // }
};
</script>
    
    <style scoped lang="less">
.list {
  height: 100vh;
  width: 100%;
  background-color: #f4f4f4;
  .bg {
    background-color: #4664f0;
    width: 100%;
    height: 130px;
    position: absolute;
  }
  .body {
    position: relative;
    z-index: 100;
    width: 355px;
    margin: 24px 10px;
    background-color: #ffffff;
    border-radius: 6px;
    height: 78%;
    overflow: scroll;
    .title {
      border-bottom: 1px solid #dfe0e4;
      margin: 0px 16px;
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
      .left_title {
        background: #f7f8f9;
        margin-top: 10px;
      }
    }
  }
  .btn {
    z-index: 1000;
    position: fixed;
    width: 85%;
    bottom:24px;
    left: 30px;
    // position: absolute;
    // bottom: 24px;
    // left: 30px;
    // right: 30px;
    background: linear-gradient(135deg, #5b88fb 0%, #3e58ed 100%);
    border-radius: 23px;
  }
}
</style>
    