<template>
  <div id="index" ref="appRef">
    <div class="bg">
      <div class="header-box">
        <Header title="算力券运行成效实时监控"></Header>
      </div>
      <dv-loading v-if="loading">Loading...</dv-loading>
      <div v-else class="host-body">
        <div class="body-box">
          <div class="left__content-box">
            <LeftTop />
            <LeftMiddle />
            <LeftBottom />
          </div>
          <div class="center__content-box">
            <ThreeDView />
            <CenterBottom />
          </div>
          <div class="right__content-box">
            <RightTop />
            <RightMiddle />
            <RightMiddle />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onUnmounted } from "vue";
import useDraw from "@/utils/useDraw";
import Header from "../header/index.vue";
import RightTop from "../rightTop/index.vue";
import LeftTop from "../left-top/index.vue";
import RightMiddle from "../rightMiddle/index.vue";
import LeftMiddle from "../leftMiddle/index.vue";
import LeftBottom from "../leftBottom/index.vue";
import CenterBottom from "../centerBottom/index.vue";
import ThreeDView from "../three/index.vue";
// * 加载标识
const loading = ref<boolean>(true);

// * 适配处理
const { appRef, calcRate, windowDraw, unWindowDraw } = useDraw();

// methods
// todo 处理 loading 展示
const cancelLoading = () => {
  setTimeout(() => {
    loading.value = false;
  }, 500);
};
// 生命周期
onMounted(() => {
  cancelLoading();
  windowDraw();
  calcRate();
});

onUnmounted(() => {
  unWindowDraw();
  // clearInterval(timeInfo.setInterval)
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/index.scss";
</style>
