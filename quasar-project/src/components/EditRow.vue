<template>
  <q-dialog v-model="editrowVisible" persistent>
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">{{ tableTitle }}</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <template v-for="col in props.colData" :key="col.field">
          <q-input v-if="col.type === 'text'" class="q-mt-sm" dense v-model="col.value.value" :label="col.label" />
          <q-input v-else-if="col.type === 'date'" class="q-mt-sm" filled v-model="col.value.value" mask="####-##-##"
            stack-label :label="col.label">
            <template v-slot:append>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-date v-model="col.value.value" mask="YYYY-MM-DD">
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="Close" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </template>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Cancel" v-close-popup @click="() => {}" />
        <q-btn flat label="Submit" v-close-popup @click="submit();" />
      </q-card-actions>
    </q-card>
  </q-dialog>

  <q-dialog v-model="listManagerVisible" persistent>
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">tit</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-table :rows="data" :columns="columns" row-key="name">

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
                <q-btn class="q-mr-sm" size="sm" color="positive" round dense icon='add' v-if="addListButtonVisible" />
                <q-btn size="sm" color="negative" round dense icon='remove' v-if="removeListButtonVisible" />
              </q-td>
              <q-td v-for="col in props.cols" :key="col.name" :props="props">
                {{ col.value }}
              </q-td>
            </q-tr>
          </template>

        </q-table>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Cancel" v-close-popup />
        <q-btn flat label="Submit" v-close-popup @click="submit" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue';
import { Driver, Column } from 'components/models'
import { api } from 'src/boot/axios';
import { emit } from 'process';

let editrowVisible = ref(false);
let listManagerVisible = ref(false);
let addListButtonVisible = ref(false);
let removeListButtonVisible = ref(true);
let currentRowID = -1;

interface Props {
  tableTitle: string,
  apiCall: string,
  colData: Column[],
}
const props = withDefaults(defineProps<Props>(), {
  tableTitle: "NullTitle",
});

const emit = defineEmits(["submit-form"])

const editRowFunc = (rowID: number) => {
  editrowVisible.value = true
  currentRowID = rowID
  api.get(props.apiCall + '/' + rowID)
    .then(function (response) {
      console.log(response.data);
      props.colData.forEach((col: Column) => {
        col.value.value = response.data[col.field]
      })
    })
}
const createRow = () => {
  editrowVisible.value = true
  currentRowID = -1
}

const submit = () => {
  let jsonData: { [key: string]: string } = {}
  props.colData.forEach((column: Column) => {
    jsonData[column.field] = column.value.value
  });

  if (currentRowID === -1) {
    api.post(props.apiCall + '/add', null, {
      params: jsonData
    })
      .then(function (response) {
        console.log(response);
        emit('submit-form')
      })
  } else {
    api.put(props.apiCall + '/' + currentRowID, null, {
      params: jsonData
    })
      .then(function (response) {
        console.log(response);
        emit('submit-form')
      })
  }
}

const columns =
  [
    { name: 'id', label: 'id', field: 'id' },
    { name: 'username', label: 'username', field: 'username' },
  ]

const data = ref([]);
api.get('/drivers')
  .then((response: any) => {
    data.value = response.data
  })

defineExpose({
  editRowFunc,
  createRow,
})

</script>