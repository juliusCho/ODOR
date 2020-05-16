<template>
   <v-container class="fill-height" fluid>
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
                               label="ID"
                               solo
                               required
                       ></v-text-field>
                       </v-col>
                       <v-col cols="12" sm="6">
                       <v-text-field
                               v-model="tmpSession.password"
                               :counter="20"
                               label="Password"
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
                               label="Nickname"
                               solo
                               required
                       ></v-text-field>
                       </v-col>
                       <v-col cols="12" sm="6">
                       <v-text-field
                               v-model="tmpSession.age"
                               :counter="10"
                               label="Age"
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
                                   label="Email"
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
                               style="padding-left: 100px; padding-right: 100px"
                               @click="createUser"
                               align="center"
                               color="primary"
                               large
                               rounded
                       >
                           변경하기
                       </v-btn>
                   </div>
               </v-container>
           </v-col>
       </v-row>
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
            createUser() {

            }
        },

    }
</script>

<style scoped>

</style>