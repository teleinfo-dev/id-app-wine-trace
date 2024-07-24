<template>
  <div
    :style="{
      position: 'absolute',
      top: `${top}px`,
      left: `${left}px`,
    }"
    @click="emit('show', keyWord)"
  >
    <img
      v-if="onFlag === keyWord"
      :width="width"
      :height="height"
      :src="onImageUrl"
      alt=""
    />
    <img v-else :width="width" :height="height" :src="imageUrl" alt="" />
  </div>
</template>

<script lang="ts" setup>
import { defineEmits, defineProps } from "vue";

const props = defineProps({
  keyWord: {
    type: String,
    default: "",
  },
  onFlag: {
    type: String,
    default: "",
  },
  top: {
    type: Number,
    default: 0,
  },
  left: {
    type: Number,
    default: 0,
  },
  width: {
    type: Number,
    default: 0,
  },
  height: {
    type: Number,
    default: 0,
  },
});

const imageUrl = new URL(
  `/src/assets/center/${props.keyWord}.png`,
  import.meta.url
).toString();
const onImageUrl = new URL(
  `/src/assets/center/${props.keyWord}on.png`,
  import.meta.url
).toString();

const emit = defineEmits(["show"]);
</script>

<style lang="scss" scoped>
.center-box {
  width: 1920px;
  height: 640px;
  .gongyingshang {
    position: absolute;
    right: 48px;
    top: 167px;
  }
  .yuanliaopici {
    position: absolute;
    right: 206px;
    top: 262px;
  }
}
</style>
