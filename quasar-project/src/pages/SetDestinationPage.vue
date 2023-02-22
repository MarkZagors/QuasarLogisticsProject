<template>
    <q-page class="row items-center justify-evenly">
        <q-table title="Destinations" :rows="data" :columns="columns" row-key="name">

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
                        <q-btn class="q-mr-sm" size="sm" color="accent" round dense icon='add'
                            @click="assignRow(props.row)" />
                        <!-- <q-btn size="sm" color="accent" round dense icon='edit' /> -->
                    </q-td>
                    <q-td v-for="col in props.cols" :key="col.name" :props="props">
                        {{ col.value }}
                    </q-td>
                    <!-- <q-td>
                        {{ props.cols[0] }}
                    </q-td> -->
                </q-tr>
            </template>

        </q-table>
    </q-page>
</template>
  
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { api } from 'boot/axios'
import EditRow from 'components/EditRow.vue'
import { Column } from 'components/models';
import { useAuthStore } from 'stores/auth';

const apiCall = "/destinations";

let columnData: Column[] = [
    { field: "name", label: "Name", type: "text", value: ref('') },
    { field: "arriveDate", label: "Arrive Date", type: "date", value: ref('') },
    { field: "departDate", label: "Depart Date", type: "date", value: ref('') },
    { field: "driverName", label: "Driver", type: "text", value: ref('') },
]

const assignRow = (row: any) => {
    let authStore = useAuthStore();
    let jsonData: { [key: string]: number } = {
        "driverID": authStore.authID,
    }
    api.put("/destinations/assign/" + row.id, null, {
        params: jsonData
    })
        .then(function (response) {
            console.log(response);
            updateTable();
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
            data.value = response.data;
            data.value.forEach(element => {
                if (element["driverID"] != -1) {
                    api.get("/drivers/" + element["driverID"]).then((response: any) => {
                        (element as { [key: string]: any })["driverName"] = response.data["username"];
                    })
                }
            });
        })
}

updateTable();
</script>