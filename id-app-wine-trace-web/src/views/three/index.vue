<!-- <template>
  <div ref="container" class="container">
    <canvas id="canvas" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader.js";
import { DRACOLoader } from "three/examples/jsm/loaders/DRACOLoader.js";

const container = ref(null);

onMounted(() => {
  const scene = new THREE.Scene();
  //
  const canvas = document.querySelector("#canvas");
  const w = window.getComputedStyle(canvas).width;
  const h = window.getComputedStyle(canvas).height;
  const width = parseInt(w, 10);
  const height = parseInt(h, 10);
  const renderer = new THREE.WebGLRenderer({
    canvas,
    antialias: true,
    logarithmicDepthBuffer: true,
  });
  renderer.setSize(width, height);

  // 设置相机轨道
  const camera = new THREE.PerspectiveCamera(
    60,
    window.innerWidth / window.innerHeight,
    100,
    20000
  );
  camera.position.set(1000, 4500, 6000);
  camera.lookAt(0, 0, 0);
  camera.position.z = 5;
  const controls = new OrbitControls(camera, renderer.domElement);
  controls.minDistance = 4000;
  controls.maxDistance = 8000;
  // //上下翻转的最大角度
  controls.maxPolarAngle = 1.2;
  // //上下翻转的最小角度
  controls.minPolarAngle = 0.9;
  controls.enablePan = false;

  // 渲染动画
  const animate = function () {
    requestAnimationFrame(animate);
    controls.update();
    renderer.render(scene, camera);
  };

  const loadRes = function () {
    // 帧率显示
    const dracoLoader = new DRACOLoader();
    dracoLoader.setDecoderPath("public/gltf/");
    dracoLoader.setDecoderConfig({ type: "js" });
    dracoLoader.preload();

    const loader = new GLTFLoader().setPath("public/gl/");
    loader.setDRACOLoader(dracoLoader);
    /**
     * 加载模型
     */
    loader.load("beijing.glb", (gltf) => {
      console.log("999", gltf);
      const mode = gltf.scene;
      mode.position.set(0, 1000, 0); // 设置目标对象的位置
      scene.add(mode);
      mode.traverse((o: any) => {
        // 给模型每部分上材质
        if (o.name.indexOf("道路") !== -1 && o instanceof THREE.Mesh) {
          const lightTexture = new THREE.TextureLoader().load(
            "public/gl/light3.png"
          );
          lightTexture.wrapS = THREE.RepeatWrapping; // 每个都重复
          lightTexture.wrapT = THREE.RepeatWrapping;
          lightTexture.repeat.set(1, 1);
          lightTexture.needsUpdate = true;

          const material = new THREE.MeshStandardMaterial({
            // color: 0xffffff, // 漫反射颜色（白色）
            emissive: 0x00ffff, // 自发光颜色（绿色）
            emissiveIntensity: 1, // 自发光强度
            transparent: true, // 透明效果不错
            side: THREE.DoubleSide, // 自发光材质应用于双面
            map: lightTexture,
          });
          material.map.offset.x = 0;
          material.map.offset.y = 0;
          o.material = material;
        } else if (o.name === "map_9osm_buildings" && o instanceof THREE.Mesh) {
          o.material.emissive = new THREE.Color(0.02, 0.102, 0.29);
          o.material.emissiveIntensity = 0.4;
          o.material.roughness = 0.5;
          o.material.metalness = 0.8;
          o.material.specular = 0x111111;
          o.material.shininess = 100;
        } else if (
          o.name === "map_9osm_buildings_1" &&
          o instanceof THREE.Mesh
        ) {
          o.material.emissive = new THREE.Color(0.111, 0.262, 0.455);
          o.material.emissiveIntensity = 0.4;
          o.material.roughness = 0.5;
          o.material.metalness = 0.8;
          o.material.specular = 0x111111;
          o.material.shininess = 100;
        } else if (
          o.name.indexOf("底层地图") !== -1 &&
          o instanceof THREE.Mesh
        ) {
          o.material.emissive = new THREE.Color(0.002, 0.005, 0.024);
          o.material.emissiveIntensity = 0.8;
          o.material.roughness = 0.1;
          // o.material.metalness = 0.6;
          // mode.remove(o);
        } else if (o.name === "边缘" && o instanceof THREE.Mesh) {
          o.material.color = new THREE.Color(1, 1, 1);
          o.material.emissive = new THREE.Color(0.18, 0.478, 0.883);
          o.material.emissiveIntensity = 100;
          o.material.side = THREE.DoubleSide;
          o.material.roughness = 6;
        }
      });
    });
  };

  loadRes();
  animate();
});
</script>

<style scoped lang="scss">
.container {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  canvas {
    width: 100%;
    height: 100%;
  }
}
</style> -->
