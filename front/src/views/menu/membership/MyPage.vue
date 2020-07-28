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
                <v-flex style="margin:20px">
                   <v-row justify="center" align="center">
                        <p class="display-1 font-weight-light">{{$t('membership.myPage')}}</p>
                   </v-row>
                    <!-- Avatar Changer -->
                    <v-container grid-list-xl>
                        <v-row justify="center" align="center">
                            <image-input v-model="avatar" @input="updateAvatar">
                                <div slot="activator">
                                    <v-avatar size="150px" v-ripple v-if="!avatar" class="grey lighten-3 mb-3" >
                                        <span>Click to add avatar</span>
                                    </v-avatar>
                                    <v-avatar size="150px" v-ripple v-else class="mb-3" style="border: groove;">
                                        <img :src="avatar.imageURL" alt="avatar">
                                    </v-avatar>
                                </div>
                            </image-input>
                        </v-row>
                    </v-container>
                    <v-row justify="center" align="center">
                        <p class="subtitle-1 font-weight-light">{{$t('membership.membershipLvl')}}</p>
                        <p style="padding-left: 5px" class="subtitle-1 font-weight-regular">{{tmpSession.membershipName}}</p>
                    </v-row>
                    <v-row justify="center" align="center">
                        <v-btn
                                style="padding-left: 50px; padding-right: 50px; margin: 10px"
                                @click="logout()"
                                align="center"
                                color="grey lighten-3"
                                small
                                depressed
                                rounded
                        >
                            {{$t('membership.logoutButton')}}
                        </v-btn>
                    </v-row>
                </v-flex>
                <v-flex style="margin:20px;">
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
                                   style="padding-left: 100px; padding-right: 100px; margin: 10px"
                                   @click="updateUser"
                                   align="center"
                                   color="secondary"
                                   large
                                   rounded
                           >
                               {{$t('membership.updateButton')}}
                           </v-btn>
                       </div>
                   </v-form>
               </v-flex>
               <v-flex></v-flex>
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
                idRules: SCRIPT_VALIDATOR.idRules(),
            };
        },

        //============ tmpSession이 바뀔때, tmpSession에 image가 존재하면 이미지 표시
        watch: {
            tmpSession: {
                handler() {
                    if (this.tmpSession?.image) {
                        this.displayImage();
                    }
                },
                deep: true
            }
        },
        //============ tmpSession이 바뀔때, tmpSession에 image가 존재하면 이미지 표시

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
            showAlert(msg) {
                this.alertMsg = msg;
                this.alertShow = true;
            },

            //=======================이미지 표시=====================
            displayImage() {
                let imageURL = API.CommonController.displayImage + '?path=' + this.tmpSession.image;
                let formData = new FormData;
                formData.append('file', imageURL);
                this.avatar = {imageURL, formData};
            },
            //=======================이미지 표시=====================


            //=======================이미지 업로드=====================
            updateAvatar(data) {
                let {formData, imageURL} = data;
                this.avatar = {formData, imageURL};
            },
            uploadImage() {
                this.avatar.formData.append('type', 'profile');
                this.avatar.formData.append('subPath', TMP_SESSION.getId());

                axios.post(
                    API.CommonController.fileUpload,
                    this.avatar.formData
                    ).then(res => {
                        if (res.status !== 200) {
                            this.showAlert(this.$t('common.unableImgUpload'));
                            return;
                        }
                        setTimeout(() => this.savedAvatar(res.data), 1000);
                    });
            },
            savedAvatar(data) {
                axios.patch(API.UserMgmtController.uploadPhoto, {
                    userId: this.tmpSession.userId,
                    image: data[0]
                }).then(() => {
                    this.tmpSession.image = data[0];
                    TMP_SESSION.setLognUser(this.tmpSession);
                });
            },
            //=======================이미지 업로드=====================


            // mypage수정 버튼 클릭 시, 이미지가 존재하면 이미지 업로드도 함께 진행
            updateUser() {

                if (this.avatar) {
                    this.uploadImage();
                }
                this.showAlert(this.$t('membership.myInfoUpdated'));
            }
        }
    }
</script>

<style scoped>

</style>