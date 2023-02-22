<template>
  <q-page class="row items-center justify-evenly">
    <table-view table-title="Cargos" api-call="/cargos" :col-data="columnData" />
  </q-page>
</template>

<script setup lang="ts">
import TableView from 'components/TableView.vue'
import { Column } from 'src/components/models';
import { ref } from 'vue';
const columnData: Column[] = [
  { field: "productName", label: "Product Name", type: "text", value: ref('') },
  { field: "weight", label: "Weight", type: "text", value: ref('') },
]
</script>
