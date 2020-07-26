<template>
   <v-container class="fill-height" fluid>
       <v-row justify="center">
           <v-col cols="12" sm="8" md="8">
               <v-flex row>
                   <v-col cols="12" sm="6">
                       <p class="display-1 font-weight-thin">{{$t('membership.findId')}}</p>
                       <p class="body-1 font-weight-thin">{{$t('membership.findIdMsg')}}</p>
                       <v-text-field
                               v-model="tmpSession.email"
                               :rules="idRules"
                               v-bind:label="$t('membership.email')"
                               solo
                               required
                       ></v-text-field>
                       <div class="text-left my-2">
                           <v-btn
                                   style="pl-12 pr-12"
                                   @click="createUser"
                                   align="center"
                                   color="secondary"
                                   large
                                   rounded
                           >
                               {{$t('membership.findId')}}
                           </v-btn>
                       </div>
                       <div class="my-4">
                           <v-btn
                                   dark
                                   fab
                                   color="grey lighten-1"
                                   @click="goTo('Login')"
                           ><v-icon>mdi-arrow-left</v-icon>
                           </v-btn>
                       </div>
                   </v-col>
                   <v-col cols="12" sm="6">
                       <p class="display-1 font-weight-thin">{{$t('membership.findPw')}}</p>
                       <p class="body-1 font-weight-thin">{{$t('membership.findPwMsg')}}</p>
                       <v-text-field
                               v-model="tmpSession.id"
                               :rules="idRules"
                               v-bind:label="$t('membership.id')"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.email"
                               :rules="idRules"
                               v-bind:label="$t('membership.email')"
                               solo
                               required
                       ></v-text-field>
                       <div class="text-left">
                           <v-btn
                                   style="pl-12 pr-12"
                                   @click="createUser"
                                   align="center"
                                   color="secondary"
                                   large
                                   rounded
                           >
                               {{$t('membership.findPw')}}
                           </v-btn>
                       </div>
                   </v-col>
               </v-flex>
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
                idRules: SCRIPT_VALIDATOR.idRules(),
            };
        },
        methods: {
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