<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn flat dense round icon="menu" aria-label="Menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          DriverCo
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header>
          Pages
        </q-item-label>

        <EssentialLink v-for="link in essentialLinks" :key="link.title" v-bind="link" />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import EssentialLink, { PageLink } from 'src/components/PageLink.vue';

const essentialLinks: PageLink[] = [
  {
    title: 'Drivers',
    caption: 'Manage Drivers',
    icon: 'person',
    link: 'drivers',
    role: ['admin']
  },
  {
    title: 'Managers',
    caption: 'Manage Managers',
    icon: 'badge',
    link: 'managers',
    role: ['admin']
  },
  {
    title: 'Trucks',
    caption: 'Manage Trucks',
    icon: 'local_shipping',
    link: 'trucks',
    role: ['admin', 'manager']
  },
  {
    title: 'Checkpoints',
    caption: 'Manage Checkpoints',
    icon: 'track_changes',
    link: 'checkpoints',
    role: ['admin', 'manager']
  },
  {
    title: 'Destinations',
    caption: 'Manage Destinations',
    icon: 'pin_drop',
    link: 'destinations',
    role: ['admin', 'manager']
  },
  {
    title: 'Cargos',
    caption: 'Manage Cargos',
    icon: 'view_in_ar',
    link: 'cargos',
    role: ['admin', 'manager']
  },
  {
    title: 'Set Destinations',
    caption: 'Manage Cargos',
    icon: 'view_in_ar',
    link: 'setDestinations',
    role: ['admin', 'driver']
  },
  {
    title: 'Reports',
    caption: 'Show reports',
    icon: 'summarize',
    link: 'report',
    role: ['admin', 'manager']
  },
  {
    title: 'Forum',
    caption: 'Go to the forum',
    icon: 'forum',
    link: 'forum',
    role: ['admin', 'manager', 'driver']
  },
];

const leftDrawerOpen = ref(false)

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value
}
</script>
