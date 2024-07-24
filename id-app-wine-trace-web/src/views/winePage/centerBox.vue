<template>
  <div class="center-box">
    <div class="chain"></div>
    <div class="move1">
      <img width="42" height="29" alt="" src="@/assets/center/lgs.png" />
    </div>
    <div class="move2">
      <img width="42" height="29" alt="" src="@/assets/center/lgs.png" />
    </div>
    <div class="move3">
      <img width="43" height="29" alt="" src="@/assets/center/lgx.png" />
    </div>
    <div class="move4">
      <img width="43" height="29" alt="" src="@/assets/center/lgx.png" />
    </div>
    <div class="move5">
      <img width="43" height="29" alt="" src="@/assets/center/lgx.png" />
    </div>
    <div class="move6">
      <img width="41" height="32" alt="" src="@/assets/center/lgs2.png" />
    </div>
    <div class="move7">
      <img width="41" height="32" alt="" src="@/assets/center/lgs2.png" />
    </div>
    <centerItem
      v-for="d in data"
      :key="d.keyWord"
      :key-word="d.keyWord"
      :top="d.top"
      :left="d.left"
      :width="d.width"
      :height="d.height"
      :on-flag="onFlag"
      @show="showItem"
    ></centerItem>
    <div class="front">
      <img width="1529" height="247" alt="" src="@/assets/center/qjt.png" />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, watch, onMounted, defineEmits, defineProps } from "vue";
import centerItem from "./centerItem.vue";

const props = defineProps({
  currentIndex: {
    type: Number,
    default: -1,
  },
});

const onFlag = ref("");

const data = [
  {
    keyWord: "gys",
    top: 167,
    left: 1709,
    width: 163,
    height: 178,
  },
  {
    keyWord: "ylpc",
    top: 262,
    left: 1551,
    width: 163,
    height: 178,
  },
  {
    keyWord: "zq",
    top: 364,
    left: 1727,
    width: 163,
    height: 178,
  },
  {
    keyWord: "nz",
    top: 576,
    left: 1446,
    width: 163,
    height: 178,
  },
  {
    keyWord: "jtsj",
    top: 474,
    left: 1285,
    width: 163,
    height: 180,
  },
  {
    keyWord: "bzgcgk",
    top: 251,
    left: 1169,
    width: 168,
    height: 178,
  },
  {
    keyWord: "pj",
    top: 325,
    left: 1032,
    width: 163,
    height: 182,
  },
  {
    keyWord: "xj",
    top: 415,
    left: 895,
    width: 163,
    height: 178,
  },
  {
    keyWord: "scrk",
    top: 178,
    left: 766,
    width: 163,
    height: 178,
  },
  {
    keyWord: "xsck",
    top: 261,
    left: 383,
    width: 163,
    height: 178,
  },
  {
    keyWord: "jxssh",
    top: 268,
    left: 185,
    width: 163,
    height: 178,
  },
  {
    keyWord: "jxsfh",
    top: 394,
    left: 410,
    width: 163,
    height: 178,
  },
  {
    keyWord: "mdqs",
    top: 487,
    left: 242,
    width: 163,
    height: 178,
  },
  {
    keyWord: "xfzsm",
    top: 359,
    left: 24,
    width: 163,
    height: 178,
  },
];

const emit = defineEmits(["show"]);

const TypeEnum = {
  gys: 14,
  ylpc: 13,
  zq: 12,
  nz: 11,
  jtsj: 10,
  bzgcgk: 9,
  pj: 8,
  xj: 7,
  scrk: 6,
  xsck: 5,
  jxssh: 4,
  jxsfh: 3,
  mdqs: 2,
  xfzsm: 1,
};

const CodeEnum = {
  14: "gys",
  13: "ylpc",
  12: "zq",
  11: "nz",
  10: "jtsj",
  9: "bzgcgk",
  8: "pj",
  7: "xj",
  6: "scrk",
  5: "xsck",
  4: "jxssh",
  3: "jxsfh",
  2: "mdqs",
  1: "xfzsm",
};

const showItem = (keyWord: string) => {
  onFlag.value = keyWord;
  console.log(TypeEnum[keyWord]);
  emit("show", TypeEnum[keyWord]);
};

watch(
  () => props.currentIndex,
  () => {
    onFlag.value = CodeEnum[props.currentIndex] ?? "";
  }
);

onMounted(() => {
  onFlag.value = CodeEnum[props.currentIndex] ?? "";
});
</script>

<style lang="scss" scoped>
.center-box {
  width: 1920px;
  height: 640px;
  .front {
    position: absolute;
    left: 185px;
    top: 486px;
    pointer-events: none;
  }
  .chain {
    position: absolute;
    left: 0;
    top: 0;
    width: 1920px;
    height: 1080px;
    background-size: 1920px 1080px;
    background-repeat: no-repeat;
    animation: animat 0.2s infinite linear;
  }
  @keyframes animat {
    0% {
      background-image: url(@/assets/center/jt1@2x.png);
    }
    50% {
      background-image: url(@/assets/center/jt2@2x.png);
    }
    100% {
      background-image: url(@/assets/center/jt3@2x.png);
    }
  }
  .move1 {
    position: absolute;
    left: 587px;
    top: 252px;
    animation: mv1 1.5s infinite linear;
  }
  @keyframes mv1 {
    0% {
      left: 587px;
      top: 252px;
      opacity: 1;
    }
    90% {
      left: 657px;
      top: 208px;
      opacity: 1;
    }
    100% {
      left: 657px;
      top: 208px;
      opacity: 0;
    }
  }
  .move2 {
    position: absolute;
    left: 587px;
    top: 252px;
    animation: mv2 1.5s infinite linear;
  }
  @keyframes mv2 {
    0% {
      left: 507px;
      top: 302px;
      opacity: 0;
    }
    5% {
      left: 507px;
      top: 302px;
      opacity: 1;
    }
    100% {
      left: 587px;
      top: 252px;
    }
  }
  .move3 {
    position: absolute;
    left: 693px;
    top: 208px;
    animation: mv3 1.5s infinite linear;
  }
  @keyframes mv3 {
    0% {
      left: 693px;
      top: 208px;
      opacity: 0;
    }
    5% {
      left: 693px;
      top: 208px;
      opacity: 1;
    }
    90% {
      left: 738px;
      top: 233px;
      opacity: 1;
    }
    100% {
      left: 738px;
      top: 233px;
      opacity: 0;
    }
  }
  .move4 {
    position: absolute;
    left: 927px;
    top: 341px;
    animation: mv4 1.5s infinite linear;
  }
  @keyframes mv4 {
    0% {
      left: 927px;
      top: 341px;
      opacity: 0;
    }
    5% {
      left: 927px;
      top: 341px;
      opacity: 1;
    }
    90% {
      left: 1012px;
      top: 384px;
      opacity: 1;
    }
    100% {
      left: 1012px;
      top: 384px;
      opacity: 0;
    }
  }
  .move5 {
    position: absolute;
    left: 927px;
    top: 341px;
    animation: mv5 1.5s infinite linear;
  }
  @keyframes mv5 {
    0% {
      left: 1188px;
      top: 490px;
      opacity: 0;
    }
    5% {
      left: 1188px;
      top: 490px;
      opacity: 1;
    }
    90% {
      left: 1249px;
      top: 524px;
      opacity: 1;
    }
    100% {
      left: 1249px;
      top: 524px;
      opacity: 0;
    }
  }
  .move6 {
    position: absolute;
    left: 1492px;
    top: 565px;
    animation: mv6 1.5s infinite linear;
  }
  @keyframes mv6 {
    0% {
      left: 1492px;
      top: 565px;
      opacity: 0;
    }
    5% {
      left: 1492px;
      top: 565px;
      opacity: 1;
    }
    100% {
      left: 1554px;
      top: 522px;
    }
  }
  .move7 {
    position: absolute;
    left: 1554px;
    top: 522px;
    animation: mv7 1.5s infinite linear;
  }
  @keyframes mv7 {
    0% {
      left: 1554px;
      top: 522px;
    }
    90% {
      left: 1614px;
      top: 478px;
      opacity: 1;
    }
    100% {
      left: 1614px;
      top: 478px;
      opacity: 0;
    }
  }
}
</style>
