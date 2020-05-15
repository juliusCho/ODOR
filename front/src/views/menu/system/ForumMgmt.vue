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
                            :items="searchCombos.forumKey"
                            item-text="forumName"
                            item-value="forumKey"
                            color="white"
                            label="Forum"
                            v-model="searchKeys.forumKey"
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
                            :items="searchCombos.forumTypeCode"
                            item-text="codeName"
                            item-value="codeId"
                            v-model="searchKeys.forumTypeCode"
                            label="Forum Type"
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
                    @search="getForumList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="forumList"
                item-key="forumKey"
                v-model="selectedForum"
                show-select
                single-select
                @click:row="selectedForum = [$event]"
        >
            <template slot="items" slot-scope="props">
                <tr >
                    <td>
                        <v-checkbox
                                v-model="props.selected"
                                hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.categoryName }}</td>
                    <td>{{ props.item.forumName }}</td>
                    <td>{{ props.item.forumTypeName }}</td>
                    <td>{{ props.item.reviewYn }}</td>
                    <td>{{ props.item.useYn }}</td>
                    <td>{{ props.item.updaterName }}</td>
                    <td>{{ props.item.updateDtTime }}</td>
                </tr>
            </template>
        </v-data-table>

        <DeleteDialog
                :show="confirmShow"
                :title="'확인'"
                :content="'진짜 삭제 ㄱ?'"
                type="C"
                :yesBtnText="'ㅇㅇ'"
                :noBtnText="'ㄴㄴ'"
                :width="500"
                @yesAction="deleteItem"
                @noAction="confirmShow = false"
        />

        <UpdatePopup
                ref="updatePopup"
                :show="updatePopShow"
                :title="'포럼수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="1000"
                :fields="headers"
                :values="selectedForum[0]"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'포럼생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="1000"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복포럼!!'"
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
        name: 'ForumMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            this.getCategoryListAll();
            this.getForumListAll();
            this.getForumList();
        },
        data() {
            let forumTypeCode = CODE.getCodeList('FORUM_TYPE_CODE');

            return {
                searchKeys: {
                    forumKey: 0,
                    categoryId: '',
                    forumTypeCode: '',
                    useYn: true
                },
                searchCombos: {
                    forumKey: [{forumKey: 0, forumName: 'All'}],
                    categoryId: [{categoryId: '', categoryName: 'All'}],
                    forumTypeCode: [{codeId: '', codeName: 'All'}].concat(forumTypeCode),
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Category',
                        value: 'categoryName',
                        width: '180px',
                        type: 'string',
                        updateType: 'category',
                        insertType: 'category'
                    },
                    {
                        text: 'Forum Name',
                        value: 'forumName',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Forum Type',
                        value: 'forumTypeName',
                        width: '180px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: forumTypeCode.map(v => ({value: v.codeId, text: v.codeName})),
                        selectKey: 'forumTypeCode'
                    },
                    {
                        text: 'Review YN',
                        value: 'reviewYn',
                        width: '100px',
                        type: 'boolean',
                        updateType: 'switch',
                        insertType: 'switch'
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
                    }
                ],
                forumList: [],
                selectedForum: [],


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
                this.searchCombos.categoryId = [];

                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    let categoryId = [{categoryId: '', categoryName: 'All'}]
                        .concat(res.data.map(v => ({categoryId: v.categoryId, categoryName: v.categoryName})));
                    this.searchCombos.categoryId = categoryId;
                });
            },
            async getForumListAll() {
                this.searchCombos.forumKey = [];

                await axios.get(
                    API.ForumMgmtController.getForumListAll
                ).then(res => {
                    let forumKey = [{forumKey: 0, forumName: 'All'}]
                        .concat(res.data.map(v => ({forumKey: v.forumKey, forumName: v.forumName})));
                    this.searchCombos.forumKey = forumKey;
                });
            },
            getForumList() {
                this.selectedForum = [];

                axios.post(
                    API.ForumMgmtController.getForumListSystem,
                    this.searchKeys
                ).then(res => {
                    this.forumList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedForum.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedForum.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.ForumMgmtController.deleteForum,
                    this.selectedForum[0]
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
                    API.ForumMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.ForumMgmtController.insertForum, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.ForumMgmtController.updateForum, data)
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
                    forumKey: 0,
                    categoryId: '',
                    forumTypeCode: '',
                    useYn: true
                };
                this.getCategoryListAll();
                this.getForumListAll();
                this.getForumList();
            }
        }
    }
</script>
