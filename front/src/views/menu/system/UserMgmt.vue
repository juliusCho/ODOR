<template>
    <v-container>
        <RightTopAlert
                :status="alertStatus"
                :msg="alertMsg"
                :show="alertShow"
                @hideDisplay="alertShow = false"
        />

        <v-form id="search">
            <v-row>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.userId"
                            color="white"
                            label="User ID"
                            v-model="searchKeys.userId"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.email"
                            color="white"
                            label="Email"
                            v-model="searchKeys.email"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.nickname"
                            color="white"
                            label="Nickname"
                            v-model="searchKeys.nickname"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-select
                            :items="searchCombos.genderCode"
                            item-text="codeName"
                            item-value="codeId"
                            v-model="searchKeys.genderCode"
                            label="Gender"
                    />
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-text-field
                            v-model="searchKeys.ageFr"
                            label="Minimum Age"
                            type="number"
                    ></v-text-field>
                </v-col>
                <v-col>
                    <v-text-field
                            v-model="searchKeys.ageTo"
                            label="Maximum Age"
                            type="number"
                    ></v-text-field>
                </v-col>
                <v-col>
                    <v-select
                            :items="searchCombos.membershipKey"
                            item-text="membershipName"
                            item-value="membershipKey"
                            v-model="searchKeys.membershipKey"
                            label="Membership"
                    />
                </v-col>
                <v-col>
                    <v-select
                            :items="searchCombos.useYn"
                            item-text="codeName"
                            item-value="codeId"
                            v-model="searchKeys.useYn"
                            label="Use"
                    />
                </v-col>
                <v-col>
                    <v-select
                            :items="searchCombos.blocked"
                            v-model="searchKeys.blocked"
                            label="Blocked User"
                    />
                </v-col>
            </v-row>
            <SystemBtn
                    @search="getUserList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="userList"
                item-key="userId"
                v-model="selectedUser"
                show-select
                single-select
                @click:row="selectedUser = [$event]"
        >
            <template v-slot:item.blocked="{item}">
                <v-btn v-if="item.blocked === 'N'" color="yellow lighten-4"
                       @click="block(item)">{{item.blocked}}</v-btn>
                <v-btn v-else color="grey lighten-4"
                       @click="unblockConfirm(item)">{{item.blocked}}</v-btn>
            </template>
        </v-data-table>

        <DeleteDialog
                :show="confirmShow"
                :title="'확인'"
                :content="'진짜 삭제 ㄱ?'"
                type="C"
                :yesBtnText="'ㅇㅇ'"
                :noBtnText="'ㄴㄴ'"
                :width="300"
                @yesAction="deleteItem"
                @noAction="confirmShow = false"
        />

        <UnblockDialog
                :show="unblockConfirmShow"
                :title="'확인'"
                :content="'활동 정지 해제?'"
                type="C"
                :yesBtnText="'넹'"
                :noBtnText="'ㄴㄴ'"
                :width="300"
                @yesAction="unblock"
                @noAction="cancelUnblock"
        />

        <UpdatePopup
                ref="updatePopup"
                :show="updatePopShow"
                :title="'사용자수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :values="selectedUser[0]"
                :validation="duplicateEmailValidation"
                :invalidMsg="'이메일중복!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'사용자생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'사용자ID/이메일중복!!'"
                @okAction="addItem"
                @cancelAction="insertPopShow = false"
        />

        <UserBlockPopup
                :show="blockPopShow"
                :userId="blockUserId"
                :email="blockEmail"
                @okAction="showProceededMsg"
                @cancelAction="blockPopShow = false"
        />
    </v-container>
</template>

<script>
    import axios from 'axios';
    import SystemBtn from "@/views/components/SystemBtn";
    import DeleteDialog from "@/views/components/Dialog";
    import UnblockDialog from "@/views/components/Dialog";
    import UpdatePopup from "@/views/components/Popup/SystemPopup";
    import InsertPopup from "@/views/components/Popup/SystemPopup";
    import RightTopAlert from "@/views/components/RightTopAlert";
    import UserBlockPopup from "@/views/components/Popup/UserBlockPopup";

    export default {
        name: 'UserMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert,
            UnblockDialog,
            UserBlockPopup
        },
        mounted() {
            this.getUserListAll();
            this.getMembershipListAll();
            this.getUserList();
        },
        data() {
            let genderCode = CODE.getCodeList("GENDER_CODE");

            return {
                searchKeys: {
                    userId: '',
                    email: '',
                    nickname: '',
                    genderCode: '',
                    ageFr: 10,
                    ageTo: 100,
                    membershipKey: 0,
                    blocked: '',
                    useYn: true
                },
                searchCombos: {
                    userId: [''],
                    email: [''],
                    nickname: [''],
                    genderCode: [{codeId: '', codeName: 'All'}].concat(genderCode),
                    membershipKey: [{membershipKey: 0, membershipName: 'All'}],
                    blocked: ['', 'Y', 'N'],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'User ID',
                        value: 'userId',
                        width: '80px',
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'id'
                    },
                    {
                        text: 'Password',
                        value: 'password',
                        width: '150px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Email',
                        value: 'email',
                        width: '180px',
                        type: 'string',
                        updateType: 'email',
                        insertType: 'email'
                    },
                    {
                        text: 'Nickname',
                        value: 'nickname',
                        width: '100px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Gender',
                        value: 'genderName',
                        width: '80px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: [{value: '', text: ''}]
                            .concat(genderCode.map(v => ({value: v.codeId, text: v.codeName}))),
                        selectKey: 'genderCode',
                        noRequire: true
                    },
                    {
                        text: 'Age',
                        value: 'age',
                        width: '60px',
                        type: 'number',
                        updateType: 'number',
                        insertType: 'number'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '60px',
                        type: 'boolean',
                        updateType: 'switch'
                    },
                    {
                        text: 'System Manager',
                        value: 'sysMngrYn',
                        width: '80px',
                        type: 'boolean',
                        updateType: 'switch',
                        insertType: 'switch'
                    },
                    {
                        text: 'Membership',
                        value: 'membershipName',
                        width: '80px',
                        type: 'number',
                        updateType: 'select',
                        insertType: 'select',
                        selectKey: 'membershipKey'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '100px'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDtTime',
                        width: '120px'
                    },
                    {
                        text: '활동정지',
                        value: 'blocked',
                        width: '100px'
                    }
                ],
                userList: [],
                selectedUser: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: '',

                unblockConfirmShow: false,
                blockPopShow: false,
                blockUserId: '',
                blockEmail: ''
            };
        },
        methods: {
            async getUserListAll() {
                this.searchCombos.userId = [];
                this.searchCombos.email = [];
                this.searchCombos.nickname = [];

                await axios.get(
                    API.UserMgmtController.getUserListAll
                ).then(res => {
                    let userId = [''].concat(res.data.map(v => v.userId));
                    let email = [''].concat(res.data.map(v => v.email));
                    let nickname = [''].concat(res.data.map(v => v.nickname));
                    this.searchCombos.userId = userId;
                    this.searchCombos.email = email;
                    this.searchCombos.nickname = nickname;
                });
            },
            async getMembershipListAll() {
                this.searchCombos.membershipKey = [{membershipKey: 0, membershipName: 'All'}];

                await axios.get(
                    API.MembershipMgmtController.getMembershipListAll
                ).then(res => {
                    this.searchCombos.membershipKey = this.searchCombos.membershipKey.concat(res.data);
                    this.headers[8].selectItems = res.data.map(v => ({value: v.membershipKey, text: v.membershipName}));
                });
            },
            getUserList() {
                this.selectedUser = [];

                axios.post(
                    API.UserMgmtController.getUserList,
                    this.searchKeys
                ).then(res => {
                    this.userList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedUser.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedUser.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.UserMgmtController.deleteUser,
                    this.selectedUser[0]
                )
                    .then(res => {
                        this.confirmShow = false;
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            addConfirm() {
                this.insertPopShow = true;
            },
            async duplicateValidation(data) {
                let result = true;

                await axios.post(
                    API.UserMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            async duplicateEmailValidation(data) {
                let result = true;

                await axios.post(
                    API.UserMgmtController.checkEmailDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.UserMgmtController.insertUser, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.UserMgmtController.updateUser, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            doneAlert(type) {
                if (type === 'success') {
                    this.alertStatus = 'info';
                    this.alertMsg = '저장완료';
                } else {
                    this.alertStatus = 'error';
                    this.alertMsg = '저장실패';
                }
                this.alertShow = true;
            },
            reset() {
                this.searchKeys = {
                    userId: '',
                    email: '',
                    nickname: '',
                    genderCode: '',
                    ageFr: 10,
                    ageTo: 1000,
                    membershipKey: 0,
                    blocked: '',
                    useYn: true
                };
                this.getUserListAll();
                this.getMembershipListAll();
                this.getUserList();
            },
            block(item) {
                this.blockUserId = item.userId;
                this.blockEmail = item.email;
                this.blockPopShow = true;
            },
            unblockConfirm(item) {
                this.blockUserId = item.userId;
                this.blockEmail = item.email;
                this.unblockConfirmShow = true;
            },
            unblock() {
                this.unblockConfirmShow = false;

                axios.delete(
                    API.UserMgmtController.unblockUser,
                    {
                        params: {
                            userId: this.blockUserId,
                            email: this.blockEmail
                        }
                    }
                ).then(() => {
                    this.showProceededMsg();
                });
            },
            showProceededMsg() {
                this.alertStatus = 'info';
                this.alertMsg = '처리완료';
                this.alertShow = true;

                this.blockUserId = '';
                this.blockEmail = '';

                this.getUserList();
                this.reset();
            },
            cancelUnblock() {
                this.unblockConfirmShow = false;
                this.blockUserId = '';
                this.blockEmail = '';
            }
        }
    }
</script>
