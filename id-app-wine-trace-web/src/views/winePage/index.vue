<template>
  <div id="index" ref="appRef">
    <div class="bg">
      <div class="header-box">
        <Header @update-value="updateData" ref="header"></Header>
      </div>
      <div class="toast" v-if="toastShow">
        <img src="@/assets/bg/toast.png" alt="" />
      </div>
      <CenterBox :current-index="currentIndex" @show="showIndex"></CenterBox>
      <dv-loading v-if="loading" class="loading">Loading...</dv-loading>

      <div v-else class="host-body">
        <div class="body-box">
          <div class="center__content-box">
            <CenterBottom
              :data="dataList"
              :current-index="currentIndex"
              v-if="bottomShow"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onUnmounted } from "vue";
import { apiBidList } from "@/api/common";
import useDraw from "@/utils/useDraw";
import CenterBox from "./centerBox.vue";
import Header from "./headerBox.vue";
import CenterBottom from "./bottomBox.vue";

// * 加载标识
const loading = ref<boolean>(true);

const header = ref();

const handle = ref("");

const toastShow = ref(false);

const bottomShow = ref(false);

const currentIndex = ref(-1);
// * 适配处理
const { appRef, calcRate, windowDraw, unWindowDraw } = useDraw();
interface Data {
  handle: string;
  values: any;
}
const dataList = ref<Data>({
  handle: "",
  values: [],
});

const cancelLoading = () => {
  setTimeout(() => {
    loading.value = false;
  }, 500);
};
const requestData = (data) => {
  if (!handle.value) {
    return;
  }

  loading.value = true;

  const params = {
    id: currentIndex.value >= 0 ? currentIndex.value : "",
    handle: handle.value,
  };
  apiBidList(params)
    .then((res: any) => {
      loading.value = false;
      dataList.value = res;
      currentIndex.value = data ? 8 : currentIndex.value;
      toastShow.value = false;
      bottomShow.value = true;
    })
    .catch((err) => {
      console.log(err, "err");
      loading.value = false;
      toastShow.value = true;
      bottomShow.value = false;
      const timer = setTimeout(function () {
        toastShow.value = false;
        clearTimeout(timer);
      }, 2000);
    });
};
const showIndex = (index: number) => {
  currentIndex.value = index;

  console.log(`点击了环节ID：${index}`);
  requestData(false);
};

const updateData = (data: any) => {
  handle.value = data;
  requestData(true);
};

// 生命周期
onMounted(() => {
  cancelLoading();
  windowDraw();
  calcRate();
});

onUnmounted(() => {
  unWindowDraw();
});
</script>

<style lang="scss" scoped>
#index {
  color: #d3d6dd;
  width: 1920px;
  height: 1080px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  transform-origin: left top;
  background-size: 1920px 1080px;
  background-repeat: no-repeat;
  background-image: url("../../assets/bg/bg.png");
  .bg {
    position: relative;
    width: 100%;
    height: 100%;
  }

  //头部样式
  .header-box {
    position: relative;
    width: 100%;
    height: 84px;
    top: 0;
    z-index: 200;
    padding: 0 10px;
  }

  // 提示样式
  .toast {
    position: absolute;
    left: 50%;
    top: 278px;
    transform: translateX(-50%);
    width: 397px;
    height: 135px;
    z-index: 201;
  }
  .loading {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    z-index: 201;
  }
  //图表容器样式
  .host-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100%;
    .body-box {
      position: relative;
      display: flex;
      flex: 1;
      margin-top: 20px;
      .center__content-box {
        width: 100%;
        height: calc(100% - 120px);
        flex-direction: column-reverse;
        z-index: 300;
        align-items: center;
      }
    }
  }
}
</style>
