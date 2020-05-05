<template>
    <v-container>
        <RightTopAlert
            :msg="alertMsg"
            :show="alertShow"
            :show-time="2500"
            @hideDisplay="hideAlert"
        />
<!--        <v-btn @click="logout">Logout</v-btn>-->
<!--        <v-btn @click="myPage">myPage</v-btn>-->
<!--        <div>-->
<!--            {{tmpSession.nickname}}-->
<!--        </div>-->

        <v-layout column style="margin-top:80px;">
           <v-flex>
               <v-layout row>
                <v-flex xs3>
                   <v-row justify="center" align="center">
                        <p class="display-1 font-weight-light">My Page</p>
                   </v-row>
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
                    <v-row justify="center" align="center">
                        <p class="subtitle-1 font-weight-light">회원등급</p>
                        <p style="padding-left: 5px" class="subtitle-1 font-weight-regular">{{tmpSession.membershipName}}</p>
                    </v-row>
                </v-flex>
                <v-flex xs6>
                   <div>
                       <p class="display-1 font-weight-regular">회원정보 변경</p>
                   </div>
                   <v-row align="center">
                         <v-radio-group row>
                           <v-radio v-model="genderCode1" class="ma-4" color="primary" label="Male" value="M"></v-radio>
                           <v-radio v-model="genderCode2" class="ma-4" color="primary" label="Female" value="F"></v-radio>
                         </v-radio-group>
                   </v-row>
                   <v-form>
                       <v-text-field
                               v-model="tmpSession.userId"
                               :counter="10"
                               label="ID"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.password"
                               :counter="20"
                               label="Password"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.nickname"
                               :counter="10"
                               label="Nickname"
                               solo
                               required
                       ></v-text-field>
                       <v-text-field
                               v-model="tmpSession.age"
                               :counter="10"
                               label="Age"
                               solo
                               required
                       ></v-text-field>
                       <v-row>
                           <v-col cols="12" sm="6">
                               <v-text-field
                                       v-model="tmpSession.email"
                                       :counter="10"
                                       label="Email"
                                       solo
                                       required
                               ></v-text-field>
                           </v-col>
                           <v-col cols="12" sm="6">
                               <v-select
                                       v-model="selectedEmail"
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
                                   rounded
                           >
                               변경하기
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

    export default {
        name: "MyPage",
        components: {RightTopAlert},
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
                selectedEmail: {emailAdd:'@gmail.com'},
                genderCode: 'F'
            };
        },
        mounted() {
            this.checkSession();
            console.log('tepsession >',this.tmpSession);
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
            }
        }
    }
</script>

<style scoped>

</style>