<template>
    <v-row justify="center">
        <v-col>
            <v-btn @click="updateAction" color="deep-purple lighten-5">
                Update
            </v-btn>
        </v-col>
        <v-col>
            <v-btn @click="deleteAction" color="red lighten-5">
                Delete
            </v-btn>
        </v-col>
        <v-spacer/><v-spacer/><v-spacer/>
        <v-spacer/><v-spacer/><v-spacer/>
        <v-spacer/><v-spacer/><v-spacer/>
        <v-col>
            <v-btn @click="addAction" color="blue lighten-5">
                Add
            </v-btn>
        </v-col>
        <v-col>
            <v-btn @click="searchAction" color="teal lighten-5">
                Search
            </v-btn>
        </v-col>
    </v-row>
</template>

<script>
    export default {
        name: "SystemBtn",
        methods: {
            searchAction() {
                this.$emit('search');
            },
            addAction() {
                this.$emit('add');
            },
            updateAction() {
                this.$emit('update');
            },
            deleteAction() {
                this.$emit('delete');
            }
        }
    }
</script>