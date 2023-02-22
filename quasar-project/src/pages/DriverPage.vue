<template>
  <q-page class="row items-center justify-evenly">
    <table-view table-title="Drivers" api-call="/drivers" :col-data="columnData" />
  </q-page>
</template>

<script setup lang="ts">
import TableView from 'components/TableView.vue'
import { Column } from 'src/components/models';
import { ref } from 'vue';
const columnData: Column[] = [
  { field: "username", label: "Username", type: "text", value: ref('') },
  { field: "password", label: "Password", type: "text", value: ref('') },
  { field: "email", label: "Email", type: "text", value: ref('') },
  { field: "telephoneNumber", label: "Telephone Number", type: "text", value: ref('') },
  { field: "dateOfBirth", label: "Date Of Birth", type: "date", value: ref('') },
  { field: "driverLicenceID", label: "Driver Licence ID", type: "text", value: ref('') },
  { field: "medicalCheckID", label: "Medical Check ID", type: "text", value: ref('') },
]
</script>
