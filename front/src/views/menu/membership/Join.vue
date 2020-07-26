<template>
    <v-container class="fill-height" fluid>
        <v-layout column>
               <!-- <v-btn
                    absolute
                    dark
                    top
                    left
                    fab
                    color="grey"
                    @click="goTo('Login')"
                ><v-icon>mdi-arrow-left</v-icon></v-btn>-->
            <v-row align="center" justify="center" style="margin-bottom: -50px">
                <p class="display-1 font-weight-thin">{{$t('membership.joinMainMessage')}}</p>
            </v-row>
            <v-row justify="center">
            <v-col cols="12" sm="8" md="8">
                <v-container column>
                    <v-row align="center">
                        <v-radio-group v-model="tmpSession.genderCode" row>
                            <v-radio class="ma-4" color="primary" label="Male" value="M"></v-radio>
                            <v-radio class="ma-4" color="primary" label="Female" value="F"></v-radio>
                        </v-radio-group>
                    </v-row>
                    <v-flex row>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                    v-model="tmpSession.userId"
                                    :counter="10"
                                    :rules="idRules"
                                    v-bind:label="$t('membership.id')"
                                    solo
                                    required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                    v-model="tmpSession.password"
                                    :counter="20"
                                    v-bind:label="$t('membership.password')"
                                    solo
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-flex>
                    <v-flex row>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                    v-model="tmpSession.nickname"
                                    :counter="10"
                                    v-bind:label="$t('membership.nickname')"
                                    solo
                                    required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                    v-model="tmpSession.age"
                                    :counter="10"
                                    v-bind:label="$t('membership.age')"
                                    solo
                                    required
                            ></v-text-field>
                        </v-col>
                    </v-flex>
                    <v-flex row>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                    v-model="tmpSession.emailId"
                                    :counter="10"
                                    v-bind:label="$t('membership.email')"
                                    solo
                                    required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-select
                                    v-model="selectedEmail"
                                    item-value="emailAdd"
                                    :counter="10"
                                    :items="emailItems"
                                    label="Email"
                                    item-text="emailAdd"
                                    solo
                                    required
                            ></v-select>
                        </v-col>
                    </v-flex>

                    <div class="text-center">
                        <v-btn
                                style="padding-left: 100px; padding-right: 100px; margin: 10px"
                                @click="goTo('Login')"
                                align="center"
                                color="grey lighten-1"
                                large
                                rounded
                        >
                            {{$t('membership.cancel')}}
                        </v-btn>
                        <v-btn
                                style="padding-left: 100px; padding-right: 100px; margin: 10px;"
                                @click="createUser"
                                align="center"
                                color="secondary"
                                large
                                rounded
                        >
                            {{$t('membership.joinButton')}}
                        </v-btn>
                        <!--<div class="text-right">
                            <p type="button"
                               id="goToLogin"
                               class="subtitle-1 font-weight-thin"
                               @click="goTo('Login')"
                            >로그인페이지로 돌아가기</p>
                        </div>-->
                    </div>

                </v-container>
            </v-col>
        </v-row>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "IdPwFinder",
        data() {
            return {
                tmpSession: {},
                genderCode1: 'M',
                genderCode2: 'F',
                idRules: SCRIPT_VALIDATOR.idRules(),
                emailItems: [
                    {emailAdd:'@gmail.com'},
                    {emailAdd:'@naver.com'},
                    {emailAdd:'@daum.net'}
                ],
                selectedEmail: {},
            };
        },
        methods: {
            // routing
            goTo(page, fKey, cateId) {
                this.$emit(
                    'goTo',page
                )
            },
            createUser() {

            }
        },

    }
</script>

<style scoped>

</style>