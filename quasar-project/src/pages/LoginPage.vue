<template>
    <q-page class="row items-center justify-evenly">

        <q-form v-model="loginForm" @submit="onSubmit" class="q-gutter-md">
            <q-input filled v-model="usernameField" label="Username" lazy-rules
                :rules="[val => val && val.length > 0 || 'Please  type something']" />
            <q-input filled v-model="passwordField" label="Password" lazy-rules
                :rules="[val => val && val.length > 0 || 'Please  type something']" />

            <!-- <q-toggle v-model="accept" label="I accept the license and terms" /> -->

            <div>
                <q-btn label="Submit" type="submit" color="primary" />
            </div>


            <div>
                <q-btn label="Create New Driver Account" color="primary" @click="onCreateNewDriver" />
            </div>

            <div>
                <q-btn label="Create New Manager Account" color="primary" @click="onCreateNewManager" />
            </div>
        </q-form>
    </q-page>
</template>
  
<script setup lang="ts">
import { QForm, QInput, QBtn, QPage } from 'quasar';
import { Ref, ref, computed } from 'vue';
import { useAuthStore } from 'stores/auth';
import { data } from 'browserslist';
import { api } from 'src/boot/axios';
import router from 'src/router';

let usernameField = ref(null);
let passwordField = ref(null);
let loginForm: Ref<QForm>;

const onSubmit = () => {
    api.post('/login', null, {
        params: {
            username: usernameField.value,
            password: passwordField.value,
        }
    })
        .then(function (response) {
            let loginCredentials = response.data.split(',');
            if (loginCredentials[0] === 'true') {
                let authStore = useAuthStore();
                if (loginCredentials[3] === "true") {
                    authStore.authType = "admin";
                } else {
                    authStore.authType = loginCredentials[2];
                }
                authStore.Authenticate();
                authStore.authID = loginCredentials[1]

                if (authStore.authType === "admin") {
                    window.location.href = "http://localhost:9000/#/drivers";
                } else if (authStore.authType === "manager") {
                    window.location.href = "http://localhost:9000/#/destinations";
                } else if (authStore.authType === "driver") {
                    window.location.href = "http://localhost:9000/#/setDestinations";
                }

            }
        })
}

const onCreateNewDriver = () => {
    window.location.href = "http://localhost:9000/#/register/driver";
}

const onCreateNewManager = () => {
    window.location.href = "http://localhost:9000/#/register/manager";
}


</script>