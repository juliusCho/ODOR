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
                            :items="searchCombos.productTypeKey"
                            item-text="productTypeName"
                            item-value="productTypeKey"
                            color="white"
                            label="Product Type"
                            v-model="searchKeys.productTypeKey"
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
                    @search="getProductTypeList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="productTypeList"
                item-key="productTypeKey"
                v-model="selectedProductType"
                show-select
                single-select
                @click:row="selectedProductType = [$event]"
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
                :title="'제품타입수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :values="selectedProductType[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복제품타입!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'제품타입생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복제품타입!!'"
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
        name: 'ProductTypeMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getProductTypeList);
            this.getCategoryListAll();
            this.getProductTypeListAll();
            this.getProductTypeList();
        },
        data() {
            return {
                searchKeys: {
                    productTypeKey: 0,
                    categoryId: '',
                    useYn: true
                },
                searchCombos: {
                    productTypeKey: [{productTypeKey: 0, productTypeName: 'All'}],
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
                        text: 'Product Type Message',
                        value: 'productTypeMessage',
                        width: '*',
                        type: 'string',
                        updateType: 'message',
                        insertType: 'message'
                    },
                    {
                        text: 'Product Type Name',
                        value: 'productTypeName',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Product Type Name2',
                        value: 'productTypeName2',
                        width: '0px'
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
                productTypeList: [],
                selectedProductType: [],


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
                    API.CategoryMgmtController.cruCategory
                ).then(res => {
                    this.searchCombos.categoryId = [{categoryId: '', categoryName: 'All'}]
                        .concat(res.data.map(v => ({categoryId: v.categoryId, categoryName: v.categoryName})));
                    this.headers[0].selectItems = res.data.map(v => ({value: v.categoryId, text: v.categoryName}));
                });
            },
            async getProductTypeListAll() {
                this.searchCombos.productTypeKey = [];

                await axios.get(
                    API.ProductTypeMgmtController.cruProductType
                ).then(res => {
                    let productTypeKey = [{productTypeKey: 0, productTypeName: 'All'}]
                        .concat(res.data.map(v => ({productTypeKey: v.productTypeKey, productTypeName: v.productTypeName})));
                    this.searchCombos.productTypeKey = productTypeKey;
                });
            },
            getProductTypeList() {
                this.selectedProductType = [];

                axios.post(
                    API.ProductTypeMgmtController.cruProductType,
                    this.searchKeys
                ).then(res => {
                    this.productTypeList = res.data;
                });
            },
            updateConfirm() {
                if (this.selectedProductType.length === 0) return;

                this.selectedProductType[0].productTypeName = this.selectedProductType[0].productTypeName2;
                this.updatePopShow = true;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedProductType.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.ProductTypeMgmtController.deleteProductType,
                    this.selectedProductType[0]
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
                    API.ProductTypeMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.ProductTypeMgmtController.cruProductType, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.ProductTypeMgmtController.cruProductType, data)
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
                    productTypeKey: 0,
                    categoryId: '',
                    useYn: true
                };
                this.getProductTypeListAll();
                this.getProductTypeList();
            }
        }
    }
</script>
