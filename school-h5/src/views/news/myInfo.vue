<template>
  <div class="list">
    <backtar-bar :title="title" />
    <div class="bg"></div>
    <div class="body">
      <div class="title">
        <div></div>
        <div>基本资料</div>
      </div>
      <div class="content">
        <div v-for="(item, index) in list" :key="index" >
          <div v-if="item.isdisplay == '1'" class="content_d">
          <!-- 单行文本 -->
          <div v-if="item.editmode == 0 && item.isdisplay =='1'">
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              v-model="formInline[item.fieldcode]"
              :label="item.fieldtitle"
              :placeholder="`请输入${item.fieldtitle}`"
              input-align="right"
            />
          </div>
          <!-- 多行文本 -->
          <div v-if="item.editmode == 19 && item.isdisplay =='1'">
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              v-model="formInline[item.fieldcode]"
              rows="3"
              autosize
              :label="item.fieldtitle"
              type="textarea"
              maxlength="50"
              :placeholder="`请输入${item.fieldtitle}`"
              show-word-limit
            />
          </div>
          <!-- 下拉选择 性别-->
          <div v-if="item.editmode == 1 && item.fieldcode == 'sex' && item.isdisplay =='1'">
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              v-model="formInline[item.fieldcode]"
              is-link
              readonly
              round
              :label="item.fieldtitle"
              :placeholder="`请选择${item.fieldtitle}`"
              @click="showPicker = true"
            />
            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                show-toolbar
                :columns="sexList"
                @confirm="onConfirm($event, item)"
                @cancel="showPicker = false"
              />
            </van-popup>
          </div>
          <!-- 不可修改 部门-->
          <div v-if="item.editmode == 1 && item.fieldcode == 'department' && item.isdisplay =='1'">
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              v-model="department"
              disabled
              :label="item.fieldtitle"
              :placeholder="`请输入${item.fieldtitle}`"
              input-align="right"
            />
          </div>
          <!-- 单选框（有无子女/住房） -->
          <div
            v-if="
              item.editmode == 8 && item.isdisplay =='1' &&
              (item.fieldcode == 'haveChild' || item.fieldcode == 'cityHouse')
            "
          >
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              input-align="right"
              name="radio"
              v-model="formInline[item.fieldcode]"
              :label="item.fieldtitle"
            >
              <template #input>
                <van-radio-group
                  v-model="formInline[item.fieldcode]"
                  direction="horizontal"
                >
                  <van-radio name="1">有</van-radio>
                  <van-radio name="0">无</van-radio>
                </van-radio-group>
              </template>
            </van-field>
          </div>
          <!-- 时间选择器年-月-日 -->
          <div v-if="item.editmode == 9 && item.isdisplay =='1'">
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <van-field
              v-model="formInline[item.fieldcode]"
              is-link
              readonly
              round
              name="datetimePicker"
              :label="item.fieldtitle"
              :placeholder="`请选择${item.fieldtitle}`"
              @click="showDatePicker = true"
            />
            <van-popup v-model="showDatePicker" position="bottom">
              <van-datetime-picker
                type="date"
                @confirm="onConfirmDate($event, item)"
                @cancel="showDatePicker = false"
              />
            </van-popup>
          </div>
          <!--上传附件 -->
          <div
            v-if="item.editmode == '93' && item.isdisplay =='1'"
            style="margin-bottom: 10px; margin-top: 10px; display: block"
          >
            <span v-if="item.isnotnull == 1" class="red">*</span>
            <div
              style="
                text-align: left;
                padding: 10px 20px;
                color: #646566;
                font-size: 15px;
              "
            >
              {{ item.fieldtitle }}:
            </div>
            <div style="text-align: left; margin-left: 20px">
              <van-uploader
                :after-read="afterRead"
                accept="['docx','doc','txt', 'pdf']"
              >
                <van-button icon="plus" style="height: 40px" type="primary"
                  >上传文件</van-button
                >
              </van-uploader>
            </div>
            <div v-for="(item, index) in fileList" :key="index" class="flie">
              <van-icon name="orders-o" />
              <div>{{ item }}</div>
              <van-icon name="close" @click="del(item, index)" class="close" />
            </div>
          </div>
        </div>
        </div>
      </div>
    </div>
    <van-button class="btn" @click="submit" type="primary">提交信息</van-button>
  </div>
</template>
    
    <script>
import backtarBar from "../../components/tarBar/backIndex.vue";
import { v4 as uuidv4 } from "uuid";
export default {
  data() {
    return {
      department: "",
      sexList: [
        { text: "男", value: "1" },
        { text: "女", value: "2" },
      ],
      fileList: [],
      ensureList: [],
      ownerId: uuidv4(),
      showPicker: false,
      showDatePicker: false,
      formInline: {},
      list: [],
      message: "",
      title: "周转房申请",
    };
  },
  components: { backtarBar },
  created() {
    this.queryList();
    let myInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    this.department = JSON.parse(myInfo.extInfo.department).name;
  },
  computed: {},
  methods: {
    queryList() {
      let params = {
        viewId: "1325db34-d924-434a-a87f-ef158ffd1d99",
        dataModuleCode: "Revolving_Apply",
      };
      this.$http.get("column/group", { params }).then((res) => {
        //s使用lodash转化驼峰格式
        for (let i = 0; i < res.data[0].groupFields.length; i++) {
          let a = res.data[0].groupFields[i].fieldcode;
          if (a == "gams2.biz.attachmentgroup-0") {
            break;
          } else {
            res.data[0].groupFields[i].fieldcode = this.$lodash.camelCase(a);
          }
        }
        this.list = res.data[0].groupFields;
      });
    },
    // 删除某个文件
    del(item, index) {
      this.fileList.splice(index, 1);
      this.ensureList.splice(index, 1);
    },
    //上传时间
    afterRead(file) {
      this.fileList.push(file.file.name);
      let params = new FormData();
      params.append("file", file.file);
      const config = {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      };
      this.$http
        .post(
          `common/attachment/?ownerId=${this.ownerId}&ownerType=bill&category=gams2.biz.attachmentgroup-0`,
          params,
          false,
          config
        )
        .then((res) => {
          this.ensureList.push(res[0]);
        });
    },
    show() {
      this.showPicker = true;
    },
    submit() {
      this.Obj = JSON.parse(JSON.stringify(this.formInline))
      this.Obj.department = this.department;
      // this.Obj.sex = this.formInline.sex == '男'?'1':'2'; 
      for (let i = 0; i < this.list.length; i++) {
        if (
          this.list[i].isnotnull == "1" &&
          !this.Obj[this.list[i].fieldcode]
        ) {
          this.$toast({
            msg: "必填信息不能为空",
          });
          return;
        }
        if (this.list[i].fieldcode == "gams2.biz.attachmentgroup-0") {
          this.Obj[this.list[i].fieldcode] = this.ensureList;
        }
        if (this.list[i].fieldcode == "sex") {
          this.Obj[this.list[i].fieldcode] = this.Obj.sex == '男'?'1':'2';
        }
        if (this.list[i].fieldcode == "haveChild" ) {
          this.Obj[this.list[i].fieldcode] = this.Obj.haveChild == '0'?Boolean(false):Boolean(true);
        } 
        if (this.list[i].fieldcode == "cityHouse" ) {
          this.Obj[this.list[i].fieldcode] = this.Obj.cityHouse == '0'?Boolean(false):Boolean(true);
        }
      }
      let params = this.Obj;
      this.$http
        .post("revolving/select/apply/add", params, false)
        .then((res) => {
          if (res.code == 200) {
            this.formInline = {};
            this.Obj = {};
            this.$toast({
              msg: "提交信息成功",
              type: "success",
            });
            this.$router.go(-1);
          }
        })
    },
    //性别下拉选择
    onConfirm(val, item) {
      this.formInline[item.fieldcode] = val.text;
      this.showPicker = false;
    },
    //   下拉选择时间
    onConfirmDate(value, item) {
      const strMonth = this.utils.dateFormat(value);
      this.formInline[item.fieldcode] = strMonth;
      this.showDatePicker = false;
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
    position: absolute;
    overflow: scroll;
    width: 355px;
    margin: 24px 10px;
    background-color: #ffffff;
    border-radius: 6px;
    height: 78%;
    .title {
      border-bottom: 1px solid #dfe0e4;
      //   margin: 0px 16px;
      padding-left: 16px;
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
        // padding:0px 0px 5px;
        border-bottom: 1px solid #dfe0e4;
        .red {
          color: red;
          margin-top: 12px;
          margin-left: 5px;
          position: absolute;
          z-index: 100;
        }
         >div {
          display: flex;
        }
        .flie {
          display: flex;
          justify-content: space-around;
          padding: 5px;
          border-radius: 5px;
          background-color: #00bc56;
          color: white;
          margin: 10px 15px;
          text-align: left;
          > :nth-child(1) {
            text-align: left;
            width: 20px;
            color: green;
            padding-top: 3px;
          }
          > :nth-child(2) {
            margin-left: 10px;
            width: 300px;
            display: block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
          }
          .close {
            text-align: right;
            width: 70px;
            padding: 3px 5px 0px 0px;
            color: white;
          }
        }
      }
    }
  }
  .btn {
    // position: absolute;
    // bottom: 24px;
    // left: 30px;
    // right: 30px;
    position: fixed;
    width: 85%;
    bottom:24px;
    left: 30px;
    // right: 30px;
    border-radius: 23px;
    background: linear-gradient(135deg, #5b88fb 0%, #3e58ed 100%);
    border-radius: 23px;
  }
  /deep/ .van-field__control {
    text-align: right;
  }
}
</style>
    