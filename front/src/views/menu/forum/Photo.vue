<template>
    <v-container>

    </v-container>
</template>

<script>
    export default {
        name: 'Photo',
        props: {
            forumKey: {
                type: Number,
                default: 0,
                required: false
            }
        },
        mounted() {
            // console.log(this.forumKey);
        },
        watch: {
            forumKey() {
                // console.log(this.forumKey);
            }
        }
    }
</script>

<style scoped>

</style>