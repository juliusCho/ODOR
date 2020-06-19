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
                            :items="searchCombos.membershipKey"
                            item-text="membershipName"
                            item-value="membershipKey"
                            color="white"
                            label="Membership"
                            v-model="searchKeys.membershipKey"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.lvlCode"
                            item-text="codeName"
                            item-value="codeId"
                            color="white"
                            label="Membership Level"
                            v-model="searchKeys.lvlCode"
                    ></v-autocomplete>
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
            </v-row>
            <SystemBtn
                    @search="getMembershipList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="membershipList"
                item-key="membershipKey"
                v-model="selectedMembership"
                show-select
                single-select
                @click:row="selectedMembership = [$event]"
        >
            <template v-slot:item.mapping="{item}">
                <v-btn color="yellow lighten-4"
                       @click="mapping(item)">MAPPING</v-btn>
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

        <UpdatePopup
                ref="updatePopup"
                :show="updatePopShow"
                :title="'맴버쉽수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :values="selectedMembership[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복맴버쉽!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'맴버쉽생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복맴버쉽!!'"
                @okAction="addItem"
                @cancelAction="insertPopShow = false"
        />

        <MembershipMapperPopup
                :show="mappingShow"
                :membershipKey="mappingKey"
                :selected="mappingSelected"
                @closeAction="mappingClose"
        />
    </v-container>
</template>

<script>
    import axios from 'axios';
    import SystemBtn from "@/views/components/SystemBtn";
    import DeleteDialog from "@/views/components/Dialog";
    import UpdatePopup from "@/views/components/Popup/SystemPopup";
    import InsertPopup from "@/views/components/Popup/SystemPopup";
    import RightTopAlert from "@/views/components/RightTopAlert";
    import MembershipMapperPopup from "@/views/components/Popup/MembershipMapperPopup";

    export default {
        name: 'MembershipMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert,
            MembershipMapperPopup
        },
        mounted() {
            ENTER_ACTION(this.getMembershipList);
            this.getMembershipListAll();
            this.getMembershipList();
        },
        data() {
            let lvlCode = CODE.getCodeList('MEMBERSHIP_LVL_CODE');

            return {
                searchKeys: {
                    membershipKey: 0,
                    lvlCode: '',
                    useYn: true
                },
                searchCombos: {
                    membershipKey: [],
                    lvlCode: [{codeId: '', codeName: 'All'}].concat(lvlCode),
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Membership Message',
                        value: 'membershipMessage',
                        width: '180px',
                        type: 'string',
                        updateType: 'message',
                        insertType: 'message'
                    },
                    {
                        text: 'Locale Message',
                        value: 'localeMessage',
                        width: '*'
                    },
                    {
                        text: 'Membership Name',
                        value: 'membershipName',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Membership Level',
                        value: 'lvlName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: lvlCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'lvlCode'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px',
                        type: 'boolean',
                        updateType: 'switch'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '100px'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDtTime',
                        width: '150px'
                    },
                    {
                        text: 'Mapping',
                        value: 'mapping',
                        width: '150px'
                    }
                ],
                membershipList: [],
                selectedMembership: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: '',

                mappingShow: false,
                mappingKey: 0,
                mappingSelected: {categoryList: [], forumList: []}
            };
        },
        methods: {
            async getMembershipListAll() {
                this.searchCombos.membershipKey = [];

                await axios.get(
                    API.MembershipMgmtController.getMembershipListAll
                ).then(res => {
                    this.searchCombos.membershipKey = [{membershipKey: 0, membershipName: 'All'}]
                        .concat(res.data.map(v => ({membershipKey: v.membershipKey, membershipName: v.membershipName})));
                });
            },
            getMembershipList() {
                this.selectedMembership = [];

                axios.post(
                    API.MembershipMgmtController.getMembershipList,
                    this.searchKeys
                ).then(res => {
                    this.membershipList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedMembership.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedMembership.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.MembershipMgmtController.deleteMembership,
                    this.selectedMembership[0]
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
                    API.MembershipMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.MembershipMgmtController.insertMembership, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.MembershipMgmtController.updateMembership, data)
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
                    membershipKey: 0,
                    lvlCode: '',
                    useYn: true
                };
                this.getMembershipListAll();
                this.getMembershipList();
            },
            async mapping(item) {
                let {membershipKey} = item;
                await axios.post(
                    API.MembershipMgmtController.getMappedForumList,
                    {membershipKey}
                ).then(res => {
                    let forumList = res.data;
                    let categoryList = forumList.map(v => ({categoryId: v.categoryId}));
                    categoryList = COMMON_UTIL.removeArrDuplicate(categoryList);

                    this.mappingSelected = {categoryList, forumList};
                    this.mappingKey = membershipKey;
                    this.mappingShow = true;
                });
            },
            mappingClose() {
                ENTER_ACTION(this.getMembershipList);
                this.mappingShow = false;
            }
        }
    }
</script>
