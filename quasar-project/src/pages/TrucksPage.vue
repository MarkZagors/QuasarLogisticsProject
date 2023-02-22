<template>
  <q-page class="row items-center justify-evenly">
    <table-view table-title="Trucks" api-call="/trucks" :col-data="columnData" />
  </q-page>
</template>

<script setup lang="ts">
import TableView from 'components/TableView.vue'
import { Column } from 'src/components/models';
import { ref } from 'vue';
const columnData: Column[] = [
  { field: "model", label: "Model", type: "text", value: ref('') },
  { field: "maxSpeed", label: "Max Speed", type: "text", value: ref('') },
]
</script>
