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
            }
        },
        mounted() {
            console.log('ETC');
            console.log(this.forumKey);
        }
    }
</script>

<style scoped>

</style>