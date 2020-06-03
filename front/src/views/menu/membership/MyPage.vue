<template>
    <v-container class="fill-height" fluid>
        <RightTopAlert
            :msg="alertMsg"
            :show="alertShow"
            :show-time="2500"
            @hideDisplay="hideAlert"
        />
        <v-layout column style="margin-top:80px;">
           <v-flex>
               <v-layout row>
                <v-flex xs3>
                   <v-row justify="center" align="center">
                        <p class="display-1 font-weight-light">{{$t('membership.myPage')}}</p>
                   </v-row>
                    <!-- Avatar Changer -->
                    <v-container grid-list-xl>
                        <v-row justify="center" align="center">
                        <image-input v-model="avatar">
                            <div slot="activator">
                                <v-avatar size="150px" v-ripple v-if="!avatar" class="grey lighten-3 mb-3">
                                    <span>Click to add avatar</span>
                                </v-avatar>
                                <v-avatar size="150px" v-ripple v-else class="mb-3">
                                    <img :src="avatar.imageURL" alt="avatar">
                                </v-avatar>
                            </div>
                        </image-input>
                        <v-slide-x-transition>
                            <div v-if="avatar && saved == false">
                                <v-btn class="primary" @click="uploadImage" :loading="saving">Save Avatar</v-btn>
                            </div>
                        </v-slide-x-transition>
                        </v-row>
                    </v-container>
                    <ImageInput />
                    <v-row justify="center" align="center">
                        <p class="subtitle-1 font-weight-light">{{$t('membership.membershipLvl')}}</p>
                        <p style="padding-left: 5px" class="subtitle-1 font-weight-regular">{{tmpSession.membershipName}}</p>
                    </v-row>
                </v-flex>
                <v-flex xs6>
                   <div>
                       <p class="display-1 font-weight-regular">{{$t('membership.chgMembershipInfo')}}</p>
                   </div>
                   <v-row align="center">
                         <v-radio-group v-model="tmpSession.genderCode" row>
                           <v-radio class="ma-4" color="primary" label="Male" value="M"></v-radio>
                           <v-radio class="ma-4" color="primary" label="Female" value="F"></v-radio>
                         </v-radio-group>
                   </v-row>
                   <v-form>
                       <v-text-field
                               v-model="tmpSession.userId"
                               :counter="10"
                               :rules="idRules"
                               v-bind:label="$t('membership.id')"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.password"
                               :counter="20"
                               v-bind:label="$t('membership.password')"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.nickname"
                               :counter="10"
                               v-bind:label="$t('membership.nickname')"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.age"
                               :counter="10"
                               v-bind:label="$t('membership.age')"
                               solo
                               required
                       ></v-text-field>
                       <v-row>
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
                       </v-row>
                       <div class="text-center">
                           <v-btn
                                   class="pl-12 pr-12"
                                   @click="updateUser"
                                   align="center"
                                   color="primary"
                                   block
                                   rounded
                           >
                               {{$t('membership.updateButton')}}
                           </v-btn>
                       </div>
                   </v-form>
               </v-flex>
               <v-flex xs3></v-flex>
               </v-layout>
           </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import axios from 'axios';
    import RightTopAlert from "../../components/RightTopAlert";
    import ImageInput from "../../components/ImageInput";

    export default {
        name: "MyPage",
        components: {ImageInput, RightTopAlert},
        data() {
            return {
                tmpSession: {},
                alertMsg: '구냥',
                alertShow: false,
                emailItems: [
                    {emailAdd:'@gmail.com'},
                    {emailAdd:'@naver.com'},
                    {emailAdd:'@daum.net'}
                    ],
                selectedEmail: {},
                genderCode1: 'M',
                genderCode2: 'F',
                avatar: null,
                saving: false,
                saved: false,
                idRules: SCRIPT_VALIDATOR.idRules(),
            };
        },
        watch: {
            avatar: {
                handler: function() {
                    this.saved = false
                },
                deep: true
            }
        },
        mounted() {
            this.checkSession();
            console.log('tmpSession >',this.tmpSession);
            //dd
        },
        methods: {
            checkSession() {
                if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getId())) {
                    this.$emit('loggedIn', false);
                    this.goTo('Login');
                    return;
                }
                this.tmpSession = TMP_SESSION.getLoginUser();
                let email = this.tmpSession.email.split('@');
                this.selectedEmail = '@'+email[1];
                this.tmpSession.emailId = email[0];
                this.tmpSession.membershipName = CODE.getCodeName('MEMBERSHIP_LVL_CODE', this.tmpSession.membershipKey);
                //this.tmpSession.genderCode = CODE.getCodeName('GENDER_CODE', this.tmpSession.genderCode);

            },
            goTo(page) {
                this.$emit('goTo', page);
            },
            logout() {
                    axios.get(API.SessionController.logout, {})
                    .then(() => {
                        TMP_SESSION.emptyUser();
                        this.$emit('loggedIn', false);
                        this.goTo('Home');
                    });
                },
            myPage() {
                this.alertMsg = 'my Page';
                this.alertShow = true;
            },
            hideAlert() {
                this.alertShow = false;
            },
            uploadImage() {
                console.log('UPLOADIMAGE');
                console.log(this.avatar);
                this.saving = true;
                setTimeout(() => this.savedAvatar(), 1000);
            },
            savedAvatar() {
                console.log('sAVEDAVATAR');
                console.log(this.avatar);
                this.saving = false;
                this.saved = true;
            },
            updateUser() {

            }
        }
    }
</script>

<style scoped>

</style>