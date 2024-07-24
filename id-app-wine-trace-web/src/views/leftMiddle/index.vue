<template>
  <div class="left_middle-box chart-box">
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

<style lang="scss" scoped>
$box-height: 300px;
$box-width: 100%;

.left_middle-box {
  padding: 20px 10px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  // background-color: #c3cbde;
  .text {
    color: #c3cbde;
  }
}
</style>
