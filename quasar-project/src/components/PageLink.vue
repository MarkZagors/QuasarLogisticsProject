<template>
  <q-item clickable :to="link" tag="a" v-if="visible">
    <q-item-section v-if="icon" avatar>
      <q-icon :name="icon" />
    </q-item-section>

    <q-item-section>
      <q-item-label>{{ title }}</q-item-label>
      <q-item-label caption>{{ caption }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue';
import { useAuthStore } from 'stores/auth';
import auth from 'src/boot/auth';
import { watch } from 'fs';
import { useRoute } from 'vue-router';

export interface PageLink {
  title: string;
  caption?: string;
  link?: string;
  icon?: string;
  role?: string[];
}
const props = withDefaults(defineProps<PageLink>(), {
  caption: '',
  link: '',
  icon: '',
});
var authStore = useAuthStore();
var visible = ref(false);

const countDownTimer = () => {
  setTimeout(() => {
    if (authStore.isAuth) {
      props.role?.forEach((role) => {
        if (role === authStore.authType) {
          visible.value = true;
        }
      });
    }
    countDownTimer();
  }, 1000)
}


countDownTimer();

</script>
