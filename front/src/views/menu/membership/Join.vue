<template>
    <v-container>
        <RightTopAlert
                :msg="alertMsg"
                :show="alertShow"
                :showTime="2500"
                :status="alertStatus"
                @hideDisplay="hideAlert"
        />
        <v-row justify="center" class="main_content">
            <v-col md="7">
                <v-card class="elevation-10">
                    <v-toolbar color="primary" dark flat>
                        <v-toolbar-title>Join</v-toolbar-title>
                        <v-spacer/>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                label="ID"
                                name="id"
                                required
                                type="text"
                                v-model="joinInput.userId"
                            >

                            </v-text-field>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import RightTopAlert from '@/views/components/RightTopAlert';
    import axios from 'axios';

    export default {
        name: 'Join',
        components: {
            RightTopAlert
        },
        data() {
            return {
                alertMsg: '',
                alertShow: false,
                alertStatus: '',

                joinInput: {
                    userId: '',
                    password: '',
                    email: '',
                    nickname: '',
                    genderCode: '',
                    age: 0
                }
            };
        },
        methods: {
            hideAlert() {
                this.alertShow = false;
            }
        }
    }
</script>

<style scoped>
    .main_content {
        padding-bottom: 7%;
    }
</style>