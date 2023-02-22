<template>
  <q-page class="row items-center justify-evenly">
    <q-form @submit="onSubmit" class="q-gutter-md">
      <template v-for="col in columnData" :key="col.field">
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

      <div>
        <q-btn label="Submit" type="submit" color="primary" />
      </div>
    </q-form>
  </q-page>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue';
import { Driver, Column } from 'components/models'
import { api } from 'src/boot/axios';

const columnData: Column[] = [
  { field: "username", label: "Username", type: "text", value: ref('') },
  { field: "password", label: "Password", type: "text", value: ref('') },
  { field: "email", label: "Email", type: "text", value: ref('') },
  { field: "telephoneNumber", label: "Telephone Number", type: "text", value: ref('') },
  { field: "dateOfBirth", label: "Date Of Birth", type: "date", value: ref('') },
  { field: "managerDocument", label: "Manager Document", type: "text", value: ref('') },
]

const onSubmit = () => {
  let jsonData: { [key: string]: string } = {}
  columnData.forEach((column: Column) => {
    jsonData[column.field] = column.value.value
  });

  api.post('managers/add', null, {
    params: jsonData
  })
    .then(function (response) {
      if (response.data === "Saved") {
        window.location.href = "http://localhost:9000/#/login";
        console.log(response.data);
      }
    })
}
</script>