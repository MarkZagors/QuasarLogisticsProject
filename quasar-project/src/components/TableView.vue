<template>
  <div class="q-pa-md">
    <q-btn q-btn class="q-mr-sm q-mb-sm" size="md" color="accent" dense label="Create" padding="sm"
      @click="createRow" />
    <q-table :title="tableTitle" :rows="data" :columns="columns" row-key="name">

      <template v-slot:header="props">
        <q-tr :props="props">
          <q-th auto-width />
          <q-th v-for="col in props.cols" :key="col.name" :props="props">
            {{ col.label }}
          </q-th>
        </q-tr>
      </template>

      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td auto-width>
            <q-btn class="q-mr-sm" size="sm" color="accent" round dense icon='edit' @click="editRowFunc(props.row)" />
            <q-btn size="sm" color="accent" round dense icon='delete' @click="deleteRow(props.row)" />
          </q-td>
          <q-td v-for="col in props.cols" :key="col.name" :props="props">
            {{ col.value }}
          </q-td>
        </q-tr>
      </template>

    </q-table>

    <edit-row ref="editrowobj" :table-title="tableTitle" :api-call="$props.apiCall" :col-data="$props.colData" v-on:submit-form="updateTable" />

  </div>
</template>
  
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { api } from 'boot/axios'
import EditRow from 'components/EditRow.vue'
import { Column } from 'components/models';

interface Props {
  tableTitle: string,
  apiCall: string,
  colData: Column[],
}
const props = withDefaults(defineProps<Props>(), {
  tableTitle: "NullTitle",
});

const editrowobj = ref()
const editRowFunc = (row : any) => {
  editrowobj.value.editRowFunc(row.id)
}
const createRow = () => {
  editrowobj.value.createRow()
}
const deleteRow = (row : any) => {
  api.delete(props.apiCall + '/' + row.id)
  .then((response: any) => {
    console.log(response)
    updateTable()
  })
}

let columns: { name: string, label: string, field: string }[] = []

props.colData.forEach((column: Column) => {
  columns.push({
    name: column.field,
    label: column.label,
    field: column.field
  });
});

const data = ref([]);
const updateTable = () => {
  api.get(props.apiCall)
  .then((response: any) => {
    data.value = response.data
  })
}

updateTable();

</script>
  