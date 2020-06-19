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
                            :items="searchCombos.ingredientKey"
                            item-text="ingredientName"
                            item-value="ingredientKey"
                            color="white"
                            label="Ingredient"
                            v-model="searchKeys.ingredientKey"
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
                            :items="searchCombos.useYn"
                            item-text="codeName"
                            item-value="codeId"
                            v-model="searchKeys.useYn"
                            label="Use"
                    />
                </v-col>
            </v-row>
            <SystemBtn
                    @search="getIngredientList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="ingredientList"
                item-key="ingredientKey"
                v-model="selectedIngredient"
                show-select
                single-select
                @click:row="selectedIngredient = [$event]"
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
                :title="'제품 구성 요소 수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :values="selectedIngredient[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복 제품 구성 요소!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'제품 구성 요소 생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복 제품 구성 요소!!'"
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
        name: 'IngredientMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getIngredientList);
            this.getCategoryListAll();
            this.getIngredientListAll();
            this.getIngredientList();
        },
        data() {
            return {
                searchKeys: {
                    ingredientKey: 0,
                    categoryId: '',
                    useYn: true
                },
                searchCombos: {
                    ingredientKey: [{ingredientKey: 0, ingredientName: 'All'}],
                    categoryId: [],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Category',
                        value: 'categoryName',
                        width: '120px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectItems: [],
                        selectKey: 'categoryId'
                    },
                    {
                        text: 'Ingredient Message',
                        value: 'ingredientMessage',
                        width: '150px',
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
                        text: 'Ingredient Name',
                        value: 'ingredientName',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Description',
                        value: 'desc',
                        width: '100px',
                        type: 'string',
                        noRequire: true,
                        updateType: 'textarea',
                        insertType: 'textarea'
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
                ingredientList: [],
                selectedIngredient: [],


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
            async getIngredientListAll() {
                this.searchCombos.ingredientKey = [];

                await axios.get(
                    API.IngredientMgmtController.getIngredientListAll
                ).then(res => {
                    let ingredientKey = [{ingredientKey: 0, ingredientName: 'All'}]
                        .concat(res.data.map(v => ({ingredientKey: v.ingredientKey, ingredientName: v.ingredientName})));
                    this.searchCombos.ingredientKey = ingredientKey;
                });
            },
            getIngredientList() {
                this.selectedIngredient = [];

                axios.post(
                    API.IngredientMgmtController.getIngredientList,
                    this.searchKeys
                ).then(res => {
                    this.ingredientList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedIngredient.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedIngredient.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.IngredientMgmtController.deleteIngredient,
                    this.selectedIngredient[0]
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
                    API.IngredientMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.IngredientMgmtController.insertIngredient, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.IngredientMgmtController.updateIngredient, data)
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
                    ingredientKey: 0,
                    categoryId: '',
                    useYn: true
                };
                this.getIngredientListAll();
                this.getIngredientList();
            }
        }
    }
</script>
