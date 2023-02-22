<template>
  <q-page class="row items-center justify-evenly">
    <table-view table-title="Checkpoints" api-call="/checkpoints" :col-data="columnData" />
  </q-page>
</template>

<script setup lang="ts">
import TableView from 'components/TableView.vue'
import { Column } from 'src/components/models';
import { ref } from 'vue';
const columnData: Column[] = [
  { field: "name", label: "Name", type: "text", value: ref('') },
  { field: "coordinatesLongitude", label: "Longitude", type: "text", value: ref('') },
  { field: "coordinatesLatitude", label: "Latitude", type: "text", value: ref('') },
]
</script>
