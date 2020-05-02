<template>
    <v-container>
        <v-row justify="center">
            <v-col md="14">
                <v-card class="elevation-10">
                    <v-toolbar color="primary" dark flat>
                        <v-toolbar-title>About ODOR</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        ODOR는 짱!ODOR는 짱!ODOR는 짱!ODOR는 짱!ODOR는 짱!
                        ODOR는 짱!ODOR는 짱!
                        ODOR는 짱!
                        ODOR는 짱!
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
        <v-row justify="center" style="padding-bottom: 7%;">
            <v-col md="14">
                <v-card class="elevation-10">
                    <v-toolbar color="primary" dark flat>
                        <v-toolbar-title>About TEAM</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-col>
                        <v-img
                                aspect-ratio="5"
                                contain
                                src="src/assets/img/pic/Julius.jpg"
                        />
                        </v-col>
                        <v-col>
                        Julius
                        </v-col>
                    </v-card-text>
                </v-card>
                <v-card class="elevation-10">
                    <v-card-text>
                        <v-img
                                aspect-ratio="5"
                                contain
                                src="src/assets/img/pic/Renee.jpg"
                        />
                        Renee
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'About',
        methods: {
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>

<style scoped>

</style>