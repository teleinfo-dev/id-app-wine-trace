<template>
  <div class="left_top-box chart-box">
    <div class="header-box">
      <div>
        <i class="iconfont icon-chart_header_icon"></i>
      </div>
      <span class="title">补贴发放情况</span>
    </div>
    <div v-for="item in titleItem" :key="item.title" class="item">
      <i class="iconfont" :class="item.config.icon"></i>
      <span class="item-title">{{ item.title }}</span>
      <div>
        <span class="item-number">{{ item.config.number }}</span>
        <span class="item-unit">万元</span>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, reactive, onMounted } from "vue";

export default defineComponent({
  components: {
    // Chart
  },
  setup() {
    const titleDate = [
      {
        number: 5000,
        text: "补贴总额",
        icon: "icon-renminbifuhao",
      },
      {
        number: 1350,
        text: "已申领金额",
        icon: "icon-renminbifuhao",
      },
      {
        number: 730,
        text: "已兑现总额",
        icon: "icon-renminbifuhao",
      },
      {
        number: 200,
        text: "申领企业",
        icon: "icon-jianzhuwuguanli",
      },
      {
        number: 15,
        text: "算力服务商",
        icon: "icon-yunfuwushezhi",
      },
    ];
    const titleItem = reactive([]);
    // 设置数据
    const setData = () => {
      // eslint-disable-next-line array-callback-return
      titleDate.map((e) => {
        titleItem.push({
          title: e.text,
          config: {
            number: e.number,
            icon: e.icon,
            toFixed: 1,
            textAlign: "left",
            content: "{nt}",
            style: {
              fontSize: 26,
            },
          },
        });
      });
    };
    // 初始化数据
    onMounted(() => {
      setData();
    });
    const rate = reactive([
      {
        id: "centerRate1",
        tips: 60,
        colorData: {
          textStyle: "#3fc0fb",
          series: {
            color: ["#00bcd44a", "transparent"],
            dataColor: {
              normal: "#03a9f4",
              shadowColor: "#97e2f5",
            },
          },
        },
      },
      {
        id: "centerRate2",
        tips: 40,
        colorData: {
          textStyle: "#67e0e3",
          series: {
            color: ["#faf3a378", "transparent"],
            dataColor: {
              normal: "#ff9800",
              shadowColor: "#fcebad",
            },
          },
        },
      },
    ]);

    return {
      titleItem,
    };
  },
});
</script>

<style lang="scss" scoped>
.left_top-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: 100%;
  height: 300px;
  padding: 20px 14px;
  // background-color: red;

  .item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: calc(50% - 10px);
    height: 37px;
    padding: 0 14px;
    box-sizing: border-box;
    background: url("../../assets/text_bg.png") no-repeat;
    background-size: 100% 100%;
    text-align: center;
    .item-title {
      font-size: 13px;
    }
    .item-number {
      height: 37px;
      line-height: 37px;
      font-size: 14px;
      color: #fa9600;
      font-weight: 600;
    }
    .item-unit {
      font-size: 10px;
      margin-left: 5px;
    }
  }
}
</style>
