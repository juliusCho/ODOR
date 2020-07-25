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
                            :items="searchCombos.masterKey"
                            item-text="itemName"
                            item-value="masterKey"
                            color="white"
                            label="Rating Item"
                            v-model="searchKeys.masterKey"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.categoryId"
                            item-text="categoryName"
                            item-value="categoryId"
                            color="white"
                            label="Category"
                            v-model="searchKeys.categoryId"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-select
                            :items="searchCombos.dataTypeCode"
                            item-text="codeName"
                            item-value="codeId"
                            v-model="searchKeys.dataTypeCode"
                            label="Data Type"
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
            </v-row>
            <SystemBtn
                    @search="getRatingItemMasterList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="ratingItemList"
                item-key="masterKey"
                v-model="selectedRatingItem"
                show-select
                single-select
                @click:row="selectedRatingItem = [$event]"
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
                :title="'투표항목 수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :values="selectedRatingItem[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복투표항목!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'투표항목생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복투표항목!!'"
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
        name: 'RatingItemMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getRatingItemMasterList);
            this.getCategoryListAll();
            this.getRatingItemMasterListAll();
            this.getRatingItemMasterList();
        },
        data() {
            let dataTypeCode = CODE.getCodeList('DATA_TYPE_CODE');
            let displayTypeCode = CODE.getCodeList('DISPLAY_TYPE_CODE');
            let displayOptionCode = CODE.getCodeList('DISPLAY_OPTION_CODE');
            let inputOptionCode = CODE.getCodeList('INPUT_OPTION_CODE');
            let rankCode = CODE.getCodeList('RANK_CODE');

            return {
                searchKeys: {
                    masterKey: 0,
                    categoryId: '',
                    dataTypeCode: '',
                    displayTypeCode: '',
                    displayOptionCode: '',
                    inputOptionCode: '',
                    rankCode: '',
                    useYn: true
                },
                searchCombos: {
                    masterKey: [{masterKey: 0, itemName: 'All'}],
                    categoryId: [],
                    dataTypeCode: [{codeId: '', codeName: 'All'}].concat(dataTypeCode),
                    displayTypeCode: [{codeId: '', codeName: 'All'}].concat(displayTypeCode),
                    displayOptionCode: [{codeId: '', codeName: 'All'}].concat(displayOptionCode),
                    inputOptionCode: [{codeId: '', codeName: 'All'}].concat(inputOptionCode),
                    rankCode: [{codeId: '', codeName: 'All'}].concat(rankCode),
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Category',
                        value: 'categoryName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: [],
                        selectKey: 'categoryId'
                    },
                    {
                        text: 'Column Sequence',
                        value: 'colSeq',
                        width: '80px',
                        type: 'number',
                        updateType: 'number',
                        insertType: 'number',
                        noRequire: true
                    },
                    {
                        text: 'Item Message',
                        value: 'itemMessage',
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
                        text: 'Item Name',
                        value: 'itemName',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Data Type',
                        value: 'dataTypeName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: dataTypeCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'dataTypeCode'
                    },
                    {
                        text: 'Display Type',
                        value: 'displayTypeName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: displayTypeCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'displayTypeCode'
                    },
                    {
                        text: 'Sorting Sequence',
                        value: 'sortSeq',
                        width: '80px',
                        type: 'number',
                        updateType: 'number',
                        insertType: 'number',
                        noRequire: true
                    },
                    {
                        text: 'Display Option',
                        value: 'displayOptionName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: displayOptionCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'displayOptionCode'
                    },
                    {
                        text: 'Input Option',
                        value: 'inputOptionName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: inputOptionCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'inputOptionCode'
                    },
                    {
                        text: 'Required YN',
                        value: 'requiredYn',
                        width: '100px',
                        type: 'boolean',
                        updateType: 'switch',
                        insertType: 'switch'
                    },
                    {
                        text: 'Minimum Value',
                        value: 'minValue',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text',
                        noRequire: true
                    },
                    {
                        text: 'Maximum Value',
                        value: 'maxValue',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text',
                        noRequire: true
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px',
                        type: 'boolean',
                        updateType: 'switch'
                    },
                    {
                        text: 'Rank',
                        value: 'rankName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: rankCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'rankCode',
                        noRequire: true
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
                ratingItemList: [],
                selectedRatingItem: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
        },
        methods: {
            async getCategoryListAll() {
                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    this.searchCombos.categoryId = [{categoryId: '', categoryName: 'All'}]
                        .concat(res.data.map(v => ({categoryId: v.categoryId, categoryName: v.categoryName})));
                    this.headers[0].selectItems = res.data.map(v => ({value: v.categoryId, text: v.categoryName}));
                });
            },
            async getRatingItemMasterListAll() {
                this.searchCombos.masterKey = [];

                await axios.get(
                    API.RatingItemMasterMgmtController.getRatingItemMasterListAll
                ).then(res => {
                    let masterKey = [{masterKey: 0, itemName: 'All'}]
                        .concat(res.data.map(v => ({masterKey: v.masterKey, itemName: v.itemName})));
                    this.searchCombos.masterKey = masterKey;
                });
            },
            getRatingItemMasterList() {
                this.selectedRatingItem = [];

                axios.post(
                    API.RatingItemMasterMgmtController.getRatingItemMasterList,
                    this.searchKeys
                ).then(res => {
                    this.ratingItemList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedRatingItem.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedRatingItem.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.RatingItemMasterMgmtController.deleteRatingItemMaster,
                    this.selectedRatingItem[0]
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
                    API.RatingItemMasterMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.RatingItemMasterMgmtController.insertRatingItemMaster, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.RatingItemMasterMgmtController.updateRatingItemMaster, data)
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
                    masterKey: 0,
                    categoryId: '',
                    dataTypeCode: '',
                    displayTypeCode: '',
                    displayOptionCode: '',
                    inputOptionCode: '',
                    rankCode: '',
                    useYn: true
                };
                this.getRatingItemMasterListAll();
                this.getRatingItemMasterList();
            }
        }
    }
</script>
