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
                            :items="searchCombos.creatorKey"
                            item-text="name"
                            item-value="creatorKey"
                            color="white"
                            label="Creator"
                            v-model="searchKeys.creatorKey"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.countryCode"
                            item-text="countryName"
                            item-value="countryCode"
                            color="white"
                            label="Country"
                            v-model="searchKeys.countryCode"
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
                    @search="getCreatorList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="creatorList"
                item-key="creatorKey"
                v-model="selectedCreator"
                show-select
                single-select
                @click:row="selectedCreator = [$event]"
                dense
        >
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
                :title="'제작자수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :values="selectedCreator[0]"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'제작자생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                @okAction="addItem"
                @cancelAction="insertPopShow = false"
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

    export default {
        name: 'CreatorMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getCreatorList);
            this.getCountryListAll();
            this.getCreatorListAll();
            this.getCreatorList();
        },
        data() {
            return {
                searchKeys: {
                    creatorKey: 0,
                    countryCode: '',
                    useYn: true
                },
                searchCombos: {
                    creatorKey: [],
                    countryCode: [],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Country',
                        value: 'countryName',
                        width: '100px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectKey: 'countryCode'
                    },
                    {
                        text: 'Creator Name',
                        value: 'name',
                        width: '150px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'English Name',
                        value: 'engName',
                        width: '150px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text',
                        noRequire: true
                    },
                    {
                        text: 'Description',
                        value: 'desc',
                        width: '180px',
                        type: 'string',
                        updateType: 'textarea',
                        insertType: 'textarea',
                        noRequire: true
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '80px',
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
                    }
                ],
                creatorList: [],
                selectedCreator: [],

                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
        },
        methods: {
            async getCountryListAll() {
                this.searchCombos.countryCode = [];

                await axios.get(
                    API.CountryMgmtController.getCountryList
                ).then(res => {
                    let list = res.data.map(v => ({countryCode: v.countryCode, countryName: v.countryName}));
                    this.searchCombos.countryCode = [{countryCode: '', countryName: 'All'}].concat(list);
                    this.headers[0].selectItems = list.map(v => ({value: v.countryCode, text: v.countryName}));
                });
            },
            async getCreatorListAll() {
                this.searchCombos.creatorKey = [];

                await axios.get(
                    API.CreatorMgmtController.getCreatorList
                ).then(res => {
                    this.searchCombos.creatorKey = [{creatorKey: 0, name: 'All'}]
                        .concat(COMMON_UTIL.removeArrDuplicate(
                            res.data.map(v => ({creatorKey: v.creatorKey, name: v.name}))
                        ));
                });
            },
            getCreatorList() {
                this.selectedCreator = [];

                axios.post(
                    API.CreatorMgmtController.getCreatorList,
                    this.searchKeys
                ).then(res => {
                    this.creatorList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedCreator.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedCreator.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.CreatorMgmtController.deleteCreator,
                    this.selectedCreator[0]
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
            addItem(data) {
                axios.put(API.CreatorMgmtController.insertCreator, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.CreatorMgmtController.updateCreator, data)
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
                    creatorKey: 0,
                    countryCode: '',
                    useYn: true
                };
                this.getCreatorListAll();
                this.getCreatorList();
            }
        }
    }
</script>
