<template>
    <v-container>

    </v-container>
</template>

<script>
    export default {
        name: 'Etc',
        props: {
            forumKey: {
                type: Number,
                default: 0,
                required: false
            },
            categoryId: {
                type: String,
                default: '',
                required: false
            }
        },
        mounted() {
            console.log('ETC');
            console.log(this.forumKey);
            console.log(this.categoryId);
        }
    }
</script>

<style scoped>

</style>