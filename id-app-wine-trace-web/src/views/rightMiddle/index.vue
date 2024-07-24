<template>
  <div class="right_middle-box chart-box">
    <div class="header-box">
      <div>
        <i class="iconfont icon-chart_header_icon"></i>
      </div>
      <span class="title">补贴发放情况</span>
    </div>
    <chart />
  </div>
</template>

<script>
import { defineComponent, reactive } from "vue";
import Chart from "./chart/index";

export default defineComponent({
  components: {
    Chart,
  },
  setup() {
    const config = reactive({
      data: [
        {
          name: "南阳",
          value: 167,
        },
        {
          name: "周口",
          value: 67,
        },
        {
          name: "漯河",
          value: 123,
        },
        {
          name: "郑州",
          value: 55,
        },
        {
          name: "西峡",
          value: 98,
        },
      ],
    });

    return { config };
  },
});
</script>

<style lang="scss" scoped>
$box-height: 300px;
$box-width: 100%;
.right_middle-box {
  padding: 16px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  // background-color: #c3cbde;
  .text {
    color: #c3cbde;
  }
}
</style>
./chart/index.jsx
