<template>
  <div class="left_bottom-box chart-box">
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
import { defineComponent } from "vue";
import Chart from "./chart/index";

export default defineComponent({
  components: {
    Chart,
  },
});
</script>

<style lang="scss" class>
$box-height: 300px;
$box-width: 100%;
.left_bottom-box {
  padding: 14px 16px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  // margin-top: 20px;
  // background-color: red;
  .bg-color-black {
    height: $box-height - 30px;
    border-radius: 10px;
  }
  .text {
    color: #c3cbde;
  }
  .chart-box {
    margin-top: 16px;
    width: 170px;
    height: 170px;
    .active-ring-name {
      padding-top: 10px;
    }
  }
}
</style>
