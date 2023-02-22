<template>
    <li>
        <span class="comment" @click="$emit('submit-reply', props.id)">{{ props.comment }}</span>

        <ul class="replies" v-if="replies.length">
            <div v-for="(item, index) in replies" :key="index">
                <comment-component v-bind="{
                    comment: item.comment,
                    replies: item.replies,
                    id: item.id,
                }" v-on:submit-reply="(n) => $emit('submit-reply', n)" />
            </div>
        </ul>
    </li>
</template>
  
<script setup lang="ts">
import { computed, ref } from 'vue';

interface Comment {
    comment: string,
    replies: Array<Comment>,
    id: number,
}

const props = withDefaults(defineProps<Comment>(), {
    comment: "null",
    id: 0,
});

const emit = defineEmits(["submit-reply"])

</script>
  