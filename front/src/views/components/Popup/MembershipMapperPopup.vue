<template>
    <v-dialog
        v-model="thisShow"
        :width="width"
        persistent
    >
        <RightTopAlert
                :status="alertStatus"
                :msg="alertMsg"
                :show="alertShow"
                @hideDisplay="alertShow = false"
        />

        <v-card>
            <v-card-title
                class="headline grey lighten-2"
                primary-title
            >
                {{ '맴버쉽 포럼 권한 관리' }}
            </v-card-title>
            <v-card-text>
                <v-row>
                    <v-col>
                        <v-autocomplete
                                :items="searchCombos.categoryId"
                                color="white"
                                item-value="categoryId"
                                item-text="categoryName"
                                :label="'Category'"
                                v-model="searchKeys.categoryId"
                        ></v-autocomplete>
                    </v-col>
                    <v-col>
                        <v-autocomplete
                                :items="searchCombos.forumKey"
                                color="white"
                                item-value="forumKey"
                                item-text="forumName"
                                :label="'Forum'"
                                v-model="searchKeys.forumKey"
                        ></v-autocomplete>
                    </v-col>
                    <v-col>
                        <v-btn @click="searchAction" color="teal lighten-5">
                            Search
                        </v-btn>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
        <v-card class="d-flex">
            <v-card width="50%">
                <v-card-text>
                    <v-data-table
                        :headers="categoryHeaders"
                        :items="categoryList"
                        item-key="categoryId"
                        v-model="selectedCategory"
                        show-select
                        single-select
                        height="250px"
                        @click:row="categorySelected"
                    >
                    </v-data-table>
                </v-card-text>
            </v-card>
            <v-card width="50%">
                <v-card-text>
                    <v-data-table
                        :headers="forumHeaders"
                        :items="forumList"
                        item-key="forumKey"
                        v-model="selectedForum"
                        show-select
                        height="250px"
                        @click:row="selectedForum = [$event]"
                        :loading="rightLoading"
                        loading-text="카테고리를 선택해줘"
                    >
                    </v-data-table>
                </v-card-text>
            </v-card>
        </v-card>
        <v-divider/>
        <v-card>
            <v-card-actions>
                <v-spacer/>
                <v-btn
                        color="primary"
                        text
                        @click="cancelAction"
                >
                    Close
                </v-btn>
                <v-btn
                        color="green darken-1"
                        text
                        @click="selectAction"
                        :disabled="blockAdd"
                >
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>

        <ConfirmDialog
            :show="confirmShow"
            :title="'확인'"
            :content="'저장 ㄱ?'"
            type="C"
            :yesBtnText="'ㅇㅇ'"
            :noBtnText="'ㄴㄴ'"
            :width="500"
            @yesAction="saveCheck"
            @noAction="confirmShow = false"
        />
    </v-dialog>
</template>

<script>
    import axios from 'axios';
    import RightTopAlert from "@/views/components/RightTopAlert";
    import ConfirmDialog from "@/views/components/Dialog";

    export default {
        name: "MappingPopup",
        components: {
            RightTopAlert,
            ConfirmDialog
        },
        props: {
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            width: {
                type: Number,
                default: 1000,
                required: false
            },
            membershipKey: {
                type: Number,
                default: 0,
                required: false
            },
            selected: {
                type: Object,
                default: () => ({categoryList: [], forumList: []}),
                required: false
            }
        },
        data() {
            return {
                thisShow: false,

                dataSelected: {categoryList: [], forumList: []},

                searchKeys: {categoryId: '', forumKey: 0},
                searchCombos: {
                    categoryId: [],
                    forumKey: []
                },
                rightLoading: true,

                categoryHeaders: [
                    {
                        text: 'Category ID',
                        value: 'categoryId',
                        width: '100px'
                    },
                    {
                        text: 'Category Name',
                        value: 'localeMessage',
                        width: '100px'
                    }
                ],
                forumHeaders: [
                    {
                        text: 'Forum Name',
                        value: 'forumName',
                        width: '100px'
                    }
                ],
                categoryList: [],
                forumList: [],
                selectedCategory: [],
                selectedForum: [],

                blockAdd: true,

                alertStatus: 'warning',
                alertMsg: '경고',
                alertShow: false,

                confirmShow: false
            }
        },
        mounted() {
            this.getForumListAll();
        },
        watch: {
            show() {
                this.thisShow = this.show;
                if (this.thisShow) {
                    this.initializeData();
                    this.getCategoryListAll();
                    this.getCategoryList();
                }
            },
            selectedForum: {
                handler() {
                    if (this.selectedForum.length === 0) {
                        this.rightLoading = true;
                    } else {
                        this.rightLoading = false;
                    }
                },
                deep: true
            }
        },
        methods: {
            initializeData() {
                this.dataSelected = this.selected;
            },
            async getCategoryListAll() {
                this.searchCombos.categoryId = [];

                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    this.searchCombos.categoryId = [{categoryId: '', categoryName: 'ALL'}].concat(res.data);
                });
            },
            getCategoryList() {
                this.forumList = [];
                this.selectedCategory = this.dataSelected.categoryList.map(v => ({categoryId: v}));

                axios.post(
                    API.CategoryMgmtController.getCategoryList,
                    {
                        categoryId: this.searchKeys.categoryId,
                        useYn: true
                    }
                ).then(res => {
                    this.categoryList = res.data;
                });
            },
            categorySelected(data) {
                this.selectedCategory = [data];
                this.searchKeys.categoryId = data.categoryId;
                this.blockAdd = false;
                this.getForumList();
            },
            async getForumListAll() {
                this.searchCombos.forumKey = [];
                let {categoryId, forumKey} = this.searchKeys;

                await axios.post(
                    API.ForumMgmtController.getForumListSystem,
                    {categoryId, forumKey, useYn: true}
                ).then(res => {
                    let forumKey = [{forumKey: 0, forumName: 'All'}]
                        .concat(res.data.map(v => ({forumKey: v.forumKey, forumName: v.forumName})));
                    this.searchCombos.forumKey = forumKey;
                });
            },
            getForumList() {
                this.selectedForum = this.dataSelected.forumList
                    .map(v => ({categoryId: v.categoryId, forumKey: v.forumKey}));

                let {categoryId, forumKey} = this.searchKeys;

                axios.post(
                    API.ForumMgmtController.getForumListSystem,
                    {categoryId, forumKey, useYn: true}
                ).then(res => {
                    this.forumList = res.data;
                });
            },
            async searchAction() {
                await this.getCategoryList();

                if (this.selectedCategory.length === 0) {
                    this.blockAdd = true;
                    return;
                }
                this.getForumList();
            },
            selectAction() {
                this.confirmShow = true;
            },
            saveCheck() {
                if (this.selectedForum.length === 0) {
                    this.deleteAction();
                    return;
                }
                this.saveAction();
            },
            deleteAction() {
                axios.delete(
                    API.MembershipMgmtController.deleteMapping,
                    {
                        params: {
                            membershipKey: this.membershipKey,
                            categoryId: this.selectedCategory[0].categoryId
                        }
                    }
                ).then(() => {
                    this.deleteDataSelected();
                    this.confirmShow = false;
                    this.showAlert();
                });
            },
            deleteDataSelected() {
                let idx = 0;
                while (idx > -1) {
                    idx = this.dataSelected.forumList
                        .findIndex(v => v.categoryId === this.selectedCategory[0].categoryId);
                    this.dataSelected.forumList.splice(idx, 1);
                }
            },
            showAlert() {
                this.alertStatus = 'success';
                this.alertMsg = '저장성공!';
                this.alertShow = true;
            },
            saveAction() {
                let selectedList = this.selectedForum.map(v => (
                        {membershipKey: this.membershipKey, categoryId: v.categoryId, forumKey: v.forumKey}
                    ));

                axios.patch(
                    API.MembershipMgmtController.saveMapping,
                    selectedList
                ).then(() => {
                    this.updateDataSelected();
                    this.confirmShow = false;
                    this.showAlert();
                });
            },
            updateDataSelected() {
                this.deleteDataSelected();

                this.dataSelected.forumList = this.dataSelected.forumList.concat(
                    this.selectedForum.map(v => ({categoryId: v.categoryId, forumKey: v.forumKey}))
                );
            },
            cancelAction() {
                this.$emit('closeAction');
                this.reinitialize();
            },
            reinitialize() {
                this.dataSelected = {categoryList: [], forumList: []};
                this.searchKeys = {categoryId: '', forumKey: 0};
                this.selectedCategory = [];
                this.selectedForum = [];
                this.blockAdd = true;
                this.alertShow = false;
                this.confirmShow = false;
            }
        }
    }
</script>