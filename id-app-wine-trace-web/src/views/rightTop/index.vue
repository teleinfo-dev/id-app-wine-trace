<template>
  <div class="right_top-box chart-box">
    <div class="header-box">
      <div>
        <i class="iconfont icon-chart_header_icon"></i>
      </div>
      <span class="title">补贴发放情况</span>
    </div>
    <chart />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, onUnmounted, reactive } from "vue";
import Chart from "./chart/index";

export default defineComponent({
  components: {
    Chart,
  },
  setup() {
    // 下层数据
    const dataArr = [
      {
        number: 150,
        text: "今日构建总量",
      },
      {
        number: 144,
        text: "总共完成数量",
      },
      {
        number: 361,
        text: "正在编译数量",
      },
      {
        number: 571,
        text: "未通过数量",
      },
    ];
    // 对应图标
    const iconFont = [
      "icon-diagnose",
      "icon-monitoring",
      "icon-cloudupload",
      "icon-clouddownload",
    ];
    const numberData = reactive([]);
    let intervalInstance = null;

    const changeNumber = () => {
      numberData.forEach((item, index) => {
        // eslint-disable-next-line no-plusplus
        item.config.number[0] += ++index;
        item.config = { ...item.config };
      });
    };

    const setData = () => {
      dataArr.forEach((e) => {
        numberData.push({
          config: {
            number: [e.number],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 24,
            },
          },
          text: e.text,
        });
      });
    };

    const changeTiming = () => {
      intervalInstance = setInterval(() => {
        changeNumber();
      }, 2000);
    };
    onMounted(() => {
      setData();
      changeTiming();
    });
    onUnmounted(() => {
      clearInterval(intervalInstance);
    });

    return { numberData, iconFont };
  },
});
</script>

<style lang="scss" scoped>
$box-width: 100%;
$box-height: 300px;

.right_top-box {
  padding: 16px;
  height: $box-height;
  width: $box-width;
  border-radius: 10px;
  // background-color: red;
  .bg-color-black {
    height: $box-height - 30px;
    border-radius: 10px;
  }
  .text {
    color: #c3cbde;
  }
  // .dv-dec-3 {
  //   position: relative;
  //   width: 100px;
  //   height: 20px;
  //   top: -3px;
  // }
}
</style>
