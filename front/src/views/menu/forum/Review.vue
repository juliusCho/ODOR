<template>
    <v-container>

    </v-container>
</template>

<script>
    export default {
        name: 'Review',
        props: {
            forumKey: {
                type: Number,
                default: 0,
                required: false
            }
        },
        mounted() {
            console.log('REVIEW');
            console.log(this.forumKey);
        }
    }
</script>

<style scoped>

</style>