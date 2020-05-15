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
                            :items="searchCombos.categoryId"
                            color="white"
                            label="Category ID"
                            v-model="searchKeys.categoryId"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.categoryName"
                            color="white"
                            label="Category Name"
                            v-model="searchKeys.categoryName"
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
                    @search="getCategoryList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="categoryList"
                item-key="categoryId"
                v-model="selectedCategory"
                show-select
                single-select
                @click:row="selectedCategory = [$event]"
        >
            <template slot="items" slot-scope="props">
                <tr >
                    <td>
                        <v-checkbox
                                v-model="props.selected"
                                hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.categoryId }}</td>
                    <td>{{ props.item.categoryMessage }}</td>
                    <td>{{ props.item.categoryName }}</td>
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
                :title="'카테고리수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="1000"
                :fields="headers"
                :values="selectedCategory[0]"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'카테고리생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="1000"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복카테고리코드!!'"
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
        name: 'CategoryMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            this.getCategoryListAll();
            this.getCategoryList();
        },
        data() {
            return {
                searchKeys: {
                    categoryId: '',
                    categoryName: '',
                    useYn: true
                },
                searchCombos: {
                    categoryId: [''],
                    categoryName: [''],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Category ID',
                        value: 'categoryId',
                        width: '180px',
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'id'
                    },
                    {
                        text: 'Category Message',
                        value: 'categoryMessage',
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
                        text: 'Category Name',
                        value: 'categoryName',
                        width: '190px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
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
                categoryList: [],
                selectedCategory: [],


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
                this.searchCombos.categoryName = [];

                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    let categoryId = [''].concat(res.data.map(v => v.categoryId));
                    let categoryName = [''].concat(res.data.map(v => v.categoryName));
                    this.searchCombos.categoryId = categoryId;
                    this.searchCombos.categoryName = categoryName;
                });
            },
            getCategoryList() {
                this.selectedCategory = [];

                axios.post(
                    API.CategoryMgmtController.getCategoryList,
                    this.searchKeys
                ).then(res => {
                    this.categoryList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedCategory.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedCategory.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.CategoryMgmtController.deleteCategory,
                    this.selectedCategory[0]
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
                    API.CategoryMgmtController.checkDuplication,
                    null,
                    {
                        params: {
                            categoryId: data.categoryId
                        }
                    }
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.CategoryMgmtController.insertCategory, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.CategoryMgmtController.updateCategory, data)
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
                    categoryId: '',
                    categoryName: '',
                    useYn: true
                };
                this.getCategoryListAll();
                this.getCategoryList();
            }
        }
    }
</script>
