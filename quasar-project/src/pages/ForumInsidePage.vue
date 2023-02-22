<template>
    <q-page class="row items-center justify-evenly">
        <q-card dark bordered class="bg-grey-9 my-card">
            <q-card-section>
                <div class="text-h6">{{ data["title"] }}</div>
            </q-card-section>

            <q-separator dark inset />

            <q-card-section>
                {{ data.description }}
            </q-card-section>

            <q-separator dark inset />

            <q-editor class="q-mr-sm q-mb-sm q-mt-sm q-ml-sm" toolbar-text-color="black"
                content-class="bg-black text-white" v-model="newCommentText" min-height="5rem" />

            <q-btn q-btn class="q-mr-sm q-mb-sm q-mt-sm q-ml-sm" size="md" color="accent" dense label="New Comment"
                padding="sm" @click="newComment" />

            <ul v-for="(item, index) in comments" :key="index" class="comments">
                <comment-component v-bind="{
                    comment: item.comment,
                    replies: item.replies,
                    id: item.id,
                }" v-on:submit-reply="(id) => replyComment(item, id)" />
            </ul>
        </q-card>

        <q-dialog v-model="replyVisible" persistent>
            <q-card style="min-width: 350px">
                <q-card-section>
                    <div class="text-h6">New Reply</div>
                </q-card-section>

                <q-card-section class="q-pt-none">
                    <q-editor v-model="replyField" min-height="5rem" />
                </q-card-section>

                <q-card-actions align="right" class="text-primary">
                    <q-btn flat label="Cancel" v-close-popup />
                    <q-btn flat label="Submit" v-close-popup @click="onSubmitReply" />
                </q-card-actions>
            </q-card>
        </q-dialog>
    </q-page>
</template>
  
<script setup lang="ts">
import { useRoute } from 'vue-router';
import { api } from 'src/boot/axios';
import { ref } from 'vue';
import { h } from 'vue';
import CommentComponent from 'src/components/CommentComponent.vue'

const comments = ref();

const route = useRoute()
let data = ref({
    "title": "aaa",
    "description": "aaa",
    "comments": []
});
const updateTable = () => {
    api.get("forums/" + route.params.id).then((response: any) => {
        data.value = response.data;
        var unfilteredComments: any = data.value["comments"]
        const commentsGotten = JSON.parse(JSON.stringify(unfilteredComments));
        comments.value = commentsGotten;
        console.log(comments.value)
    });
}


const newComment = () => {
    let jsonData: { [key: string]: string } = {
        commentBody: newCommentText.value
    }

    api.post('comment/add/' + route.params.id, null, {
        params: jsonData
    })
        .then(function (response) {
            updateTable();
        })
}

let newCommentText = ref('')
let replyVisible = ref(false)
let replyField = ref('')
let replyCommentID = ref(0);

function replyComment(item: any, id: number) {
    replyVisible.value = true;
    replyCommentID.value = id;
    console.log(replyCommentID.value);
}

const onSubmitReply = () => {
    let commentID = replyCommentID.value;
    console.log(commentID);
    let forumID = route.params.id;

    let jsonData: { [key: string]: string } = {
        commentBody: replyField.value
    }

    api.post('comment/reply/' + commentID, null, {
        params: jsonData
    })
        .then(function (response) {
            console.log(response);
            updateTable();
        })
}

updateTable();

</script>