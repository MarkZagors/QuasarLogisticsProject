<template>
  <q-page class="row items-center justify-evenly">

    <div class="q-pa-md">
      <q-btn q-btn class="q-mr-sm q-mb-sm" size="md" color="accent" dense label="Create" padding="sm"
        @click="createRow" />

      <q-input class="q-mt-sm q-mb-sm" dense v-model="filterName" label="Name" />


      <q-input class="q-mt-sm q-mb-sm" filled v-model="filterArriveDate" mask="####-##-##" stack-label
        label="Arrive Date">
        <template v-slot:append>
          <q-icon name="event" class="cursor-pointer">
            <q-popup-proxy cover transition-show="scale" transition-hide="scale">
              <q-date v-model="filterArriveDate" mask="YYYY-MM-DD">
                <div class="row items-center justify-end">
                  <q-btn v-close-popup label="Close" color="primary" flat />
                </div>
              </q-date>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>

      <q-input class="q-mt-sm q-mb-sm" filled v-model="filterDepartDate" mask="####-##-##" stack-label
        label="Depart Date">
        <template v-slot:append>
          <q-icon name="event" class="cursor-pointer">
            <q-popup-proxy cover transition-show="scale" transition-hide="scale">
              <q-date v-model="filterDepartDate" mask="YYYY-MM-DD">
                <div class="row items-center justify-end">
                  <q-btn v-close-popup label="Close" color="primary" flat />
                </div>
              </q-date>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>

      <q-btn q-btn class="q-mr-sm q-mb-sm" size="md" color="accent" dense label="Filter" padding="sm"
        @click="filterRows" />

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

      <edit-row ref="editrowobj" :table-title="tableTitle" :api-call="apiCall" :col-data="columnData"
        v-on:submit-form="updateTable" />

    </div>

  </q-page>

</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { api } from 'boot/axios'
import EditRow from 'components/EditRow.vue'
import { Column } from 'components/models';
import { filter } from 'compression';

const columnData: Column[] = [
  { field: "name", label: "Name", type: "text", value: ref('') },
  { field: "arriveDate", label: "Arrive Date", type: "date", value: ref('') },
  { field: "departDate", label: "Depart Date", type: "date", value: ref('') },
]

const apiCall = "destinations"
const tableTitle = "Destinations";

let filterName = ref('');
let filterArriveDate = ref('');
let filterDepartDate = ref('');

const filterRows = () => {
  updateTable();
}

const editrowobj = ref()
const editRowFunc = (row: any) => {
  editrowobj.value.editRowFunc(row.id)
}
const createRow = () => {
  editrowobj.value.createRow()
}
const deleteRow = (row: any) => {
  api.delete(apiCall + '/' + row.id)
    .then((response: any) => {
      console.log(response)
      updateTable()
    })
}

let columns: { name: string, label: string, field: string }[] = []

columnData.forEach((column: Column) => {
  columns.push({
    name: column.field,
    label: column.label,
    field: column.field
  });
});

const data = ref([]);
const updateTable = () => {
  api.get(apiCall)
    .then((response: any) => {
      var responseList: any = []
      response.data.forEach((element: any) => {
        var show = true;
        if (filterName.value !== '') {
          if (element["name"] !== filterName.value) {
            show = false;
          }
        }
        if (filterArriveDate.value !== '') {
          if (element["arriveDate"] !== filterArriveDate.value) {
            show = false;
          }
        }
        if (filterDepartDate.value !== '') {
          if (element["departDate"] !== filterDepartDate.value) {
            show = false;
          }
        }
        if (show == true) responseList.push(element)
      });
      data.value = responseList;
    })
}

updateTable();
</script>
