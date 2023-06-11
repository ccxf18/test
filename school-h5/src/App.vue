<template>
  <div id="app">
    <router-view v-if="!$route.meta.keepAlive"></router-view>
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <van-tabbar
    route
      style="z-index: 1000"
      v-if="$route.meta.meta"
      v-model="active"
      @change="onChange"
    >
      <van-tabbar-item to="/" icon="wap-home-o">明细</van-tabbar-item>
      <van-tabbar-item  to="/bill" icon="friends-o">统计</van-tabbar-item>
      <van-tabbar-item to="/process" icon="coupon-o">社区</van-tabbar-item>
      <van-tabbar-item to="/my" icon="contact">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      active:sessionStorage.getItem('active') || 0
    };
  },
  computed: {
    // active: {
    //   get() {
    //     switch (this.$route.path) {
    //       case "/":
    //         return 0;
    //       case "/process":
    //         return 1;
    //       case "/my":
    //         return 2;
    //       default:
    //         break;
    //     }
    //   },
    //   set() {},
    // },
  },
  methods: {
    onChange(item) {
      // if(item == 2){
      //   this.$toast({
      //   msg: "暂未开放",
      //   type: "success",
      // });
      // }
    },
  },
};
</script>

<style lang="less">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  width: 100% !important;
  height: 100%;
  font-size: 16px;
  background: #fff;
  box-sizing: border-box;
  text-align: center;
}
</style>
