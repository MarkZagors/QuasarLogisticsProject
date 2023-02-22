<template>
    <q-page class="row items-center justify-evenly">
        <q-list>
            <q-btn q-btn class="q-mr-sm q-mb-sm" size="md" color="accent" dense label="Create" padding="sm"
                @click="newForum" />
            <q-item v-for="item in data" :key="item.id">
                <q-item-section>
                    <router-link :to="'/forum/' + item.id">
                        <q-item-label>{{ item.title }}</q-item-label>
                    </router-link>
                    <q-item-label caption lines="4">{{ item.description }}</q-item-label>
                </q-item-section>

                <q-item-section side top>
                    <!-- <q-item-label caption>asd</q-item-label> -->
                </q-item-section>
            </q-item>
        </q-list>

        <q-dialog v-model="listManagerVisible" persistent>
            <q-card style="min-width: 350px">
                <q-card-section>
                    <div class="text-h6">New Forum</div>
                </q-card-section>

                <q-card-section class="q-pt-none">
                    <q-input v-model="titleField" label="Title" />
                    <q-editor v-model="descriptionField" min-height="5rem" />
                </q-card-section>

                <q-card-actions align="right" class="text-primary">
                    <q-btn flat label="Cancel" v-close-popup />
                    <q-btn flat label="Submit" v-close-popup @click="onSubmit" />
                </q-card-actions>
            </q-card>
        </q-dialog>
    </q-page>
</template>
  
<script setup lang="ts">
import { api } from 'src/boot/axios';
import { ref } from 'vue';

var title = ""

var data = ref()

var listManagerVisible = ref(false)
var titleField = ref('')
var descriptionField = ref('')

const updateTable = () => {
    api.get("forums").then((response: any) => {
        data.value = response.data;
    });
}

updateTable();


const newForum = () => {
    listManagerVisible.value = true;
}

const onSubmit = () => {
    let jsonData: { [key: string]: string } = {
        title: titleField.value,
        description: descriptionField.value
    }

    api.post('forums/add', null, {
        params: jsonData
    })
        .then(function (response) {
            console.log(response);
            updateTable();
        })
}
</script>