<template>
    <div>
        <div class="body">
            <tar-bar :title="title" />
            <h3 class="title">{{titles}}</h3>
            <div class="time">发布时间：{{time}} 点赞数：{{like}} 收藏数：{{browse}}</div>
            <p v-html="content" style=" white-space:pre-wrap;width:100%;padding:10px"></p>
             <div style="text-align:left;margin-left:10px"><span style="color:#6ea9f1">|</span> 评论区</div>
            <div class="publishList">
                <div class="publish_one" v-for="(item,index) in comment" :key="index">
                    <div style="font-size:18px">{{item.author}}:</div>
                    <div style="font-size:16px">{{item.comment}}</div>
                    <div style="font-size:14px">发布时间： {{item.time}}</div>
                </div>
            </div>
            <div class="bottom">
                <div style="display:flex">
                    <div class="first">
                        <input type="text" style="width:265px;background:#e2dddd;height: 40px;" v-model="remark">
                    </div>
                    <div>
                        <img @click="havPublish" style="width:30px;height:30px;margin-top:12px;margin-left:10px" src="../../assets/images/pinglun.png" alt="">
                    </div>
                    <div>
                        <img v-if="status=='收藏成功'"  @click="havNocollection" style="width:30px;height:30px;margin-top:11px;margin-left:10px" src="../../assets/images/shoucnag2.png" alt="">
                        <img v-else @click="havcollection" style="width:30px;height:30px;margin-top:11px;margin-left:15px" src="../../assets/images/shoucang.png" alt="">
                    </div>
                    <div>
                        <img v-if="status2=='点赞成功'"  @click="havNoLike" style="width:30px;height:30px;margin-top:11px;margin-left:10px" src="../../assets/images/like2.png" alt="">
                        <img v-else @click="havLike" style="width:30px;height:30px;margin-top:11px;margin-left:15px" src="../../assets/images/like1.png" alt="">
                    </div>
                </div>
            </div>
        </div> 
    </div>
</template>
<script>
import tarBar from "../../components/tarBar/index.vue";

export default {
    components: { tarBar },
    data(){
        return{
            like:0,
            browse:0,
            comment:[],
            remark:'',
            content:'',
            titles:'',
            title: "新闻详情",
            time:'',
            status:'取消收藏成功',
            status2:'取消点赞成功'
        }
    },
    created(){
        this.content = this.utils.formatRich(this.$route.params.content);
        this.titles = this.$route.params.title
        this.time = this.$route.params.time
        this.id = this.$route.params.id
        this.like = this.$route.params.like
        this.browse = this.$route.params.browse
        this.havDetail()//查询点赞 收藏
        this.havCount()
    },
    methods:{
        //点赞收藏数
        havCount(){

        },
        //用户发表评论
        havPublish(){
            console.log('====',this.remark)
            if(this.remark ==''){
                this.$toast({
                        msg: '请输入评论内容',
                        type: "warning",
                    }); 
            }else{
                let params = {
                newId:this.id,
                author:sessionStorage.getItem('username'),
                comment:this.remark
            }
            this.$http.post("/comment/publish", params).then((res) => {
                if(res.code == 0){
                    this.remark = ''
                    this.havDetail()
                    this.$toast({
                        msg: '评论成功',
                        type: "success",
                    });
                }
            })
            }  
        },
        //收藏
        havcollection(){
            let params = {
                newsId:this.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/collect/confirmCollect", params).then((res) => {
                if(res.code == 0){
                    this.status = res.data
                    this.$toast({
                        msg: '收藏成功',
                        type: "success",
                    });
                }
            })
        },
         //取消收藏
        havNocollection(){
            let params = {
                newsId:this.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/collect/cancelCollect", params).then((res) => {
                if(res.code == 0){
                    this.status = res.data
                    this.$toast({
                        msg: '取消收藏成功',
                        type: "warning",
                    });
                }
            })
        },
        //点赞
        havLike(){
            let params = {
                newsId:this.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/like/confirmLike", params).then((res) => {
                if(res.code == 0){
                    this.status2 = res.data
                    this.$toast({
                        msg: '点赞成功',
                        type: "success",
                    });
                }
            })
        },
        // 取消点赞
        havNoLike(){
            let params = {
                newsId:this.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/like/cancelLike", params).then((res) => {
                if(res.code == 0){
                    this.status2 = res.data
                    this.$toast({
                        msg: '取消点赞成功',
                        type: "warning",
                    });
                }
            })
        },
        // allPinglun(){
        //     this.$http.post("/collect/cancelCollect", params).then((res) => {
        //         if(res.code == 0){
        //             this.status = res.data
        //             this.$toast({
        //                 msg: '取消收藏成功',
        //                 type: "warning",
        //             });
        //         }
        //     })
        // },
        havDetail(){
            let params = {
                id:this.id,
                newsId:this.id,
                userName:sessionStorage.getItem('username')
            }
            this.$http.post("/news/queryStatus", params).then((res) => {
                if(res.code == 0){
                    res.data.comment.forEach((item)=>{
                        item.time = this.utils.dateFormat(item.time)
                    })
                   this.comment = res.data.comment;
                    if(res.data.collect){
                        this.status = '收藏成功'
                    }else{
                        this.status = '取消收藏成功'
                    }
                    if(res.data.like){
                        this.status2 = '点赞成功'
                    }else{
                        this.status2 = '取消点赞成功'
                    }
                }
            })
        }
    }
}
</script>
<style lang="less" scoped>
    .body{
        .publishList{
            text-align: left;
            padding: 15px;
            .publish_one{
                margin-bottom:10px;
                background-color: #b4b4b4;
                border-radius: 10px;
                padding:5px 10px 5px;
            }
        }
        height: calc(100vh - 50px) ;
        overflow:scroll;
        .title{
            padding: 10px 15px 10px;
            display:block;
            white-space:nowrap;
            overflow:hidden;
            text-overflow:ellipsis;
        }
        .time{
            text-align: left;
            font-size: 16px;
            color:#b4b4b4;
            margin-left: 12px;
            
        }
        .bottom{
            position: fixed;
            bottom:0px;
            width: 100%;
            height: 50px;
            background: white;
            // z-index:1000;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5);
            .first{
                margin-top: 5px;
                height: 30px;
                margin-left: 10px;
            }
        }
    }
</style>