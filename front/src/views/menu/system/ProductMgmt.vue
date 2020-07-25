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
                            :items="searchCombos.productKey"
                            item-text="name"
                            item-value="productKey"
                            color="white"
                            label="Product"
                            v-model="searchKeys.productKey"
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
                    <v-autocomplete
                            :items="searchCombos.countryCode"
                            item-text="countryName"
                            item-value="countryCode"
                            color="white"
                            label="Country"
                            v-model="searchKeys.countryCode"
                    ></v-autocomplete>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <YearPicker
                            :date="searchKeys.yearFr"
                            @bind="bindYearFr"
                            :max="searchKeys.yearTo"
                            :label="'Start Year'"
                    >
                    </YearPicker>
                </v-col>
                <v-col>
                    <YearPicker
                            :date="searchKeys.yearTo"
                            @bind="bindYearTo"
                            :min="searchKeys.yearFr"
                            :label="'End Year'"
                    >
                    </YearPicker>
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
            <SystemBtn
                    @search="getProductList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="productList"
                item-key="productKey"
                v-model="selectedProduct"
                show-select
                single-select
                @click:row="selectedProduct = [$event]"
                dense
        >
            <template v-slot:item.mapping="{item}">
                <v-row>
                    <v-btn color="yellow lighten-4"
                           @click="mapping(item, 'brand')">BRAND MAPPING</v-btn>
                </v-row>
                <v-row style="margin-top: 5px;">
                    <v-btn color="green lighten-4"
                           @click="mapping(item, 'creator')">CREATOR MAPPING</v-btn>
                </v-row>
                <v-row style="margin-top: 5px;">
                    <v-btn color="blue lighten-4"
                           @click="mapping(item, 'ingredient')">INGREDIENT MAPPING</v-btn>
                </v-row>
                <v-row style="margin-top: 5px;">
                    <v-btn color="red lighten-4"
                           @click="mapping(item, 'productType')">PRODUCT TYPE MAPPING</v-btn>
                </v-row>
            </template>
            <template v-slot:item.image="{item}">
                <img v-if="item.image" :src="getImage(item.image)" style="width: 100%; height: 100%; margin: 10px;">
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
                :title="'제품수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :values="selectedProduct[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복제품!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'제품생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복제품!!'"
                @okAction="addItem"
                @cancelAction="insertPopShow = false"
        />

        <ProductMapperPopup
                mappingKey="brand"
                :show="brandMappingShow"
                :productKey="mappingKey"
                :selected="brandMappingSelected"
                @closeAction="brandMappingClose"
        />

        <ProductMapperPopup
                mappingKey="creator"
                :show="creatorMappingShow"
                :productKey="mappingKey"
                :selected="creatorMappingSelected"
                @closeAction="creatorMappingClose"
        />

        <ProductMapperPopup
                mappingKey="ingredient"
                :show="ingredientMappingShow"
                :productKey="mappingKey"
                :selected="ingredientMappingSelected"
                @closeAction="ingredientMappingClose"
        />

        <ProductMapperPopup
                mappingKey="productType"
                :show="productTypeMappingShow"
                :productKey="mappingKey"
                :selected="productTypeMappingSelected"
                @closeAction="productTypeMappingClose"
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
    import YearPicker from "@/views/components/YearPicker";
    import ProductMapperPopup from "@/views/components/Popup/ProductMapperPopup";

    export default {
        name: 'ProductMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert,
            YearPicker,
            ProductMapperPopup
        },
        mounted() {
            ENTER_ACTION(this.getProductList);
            this.getCategoryListAll();
            this.getCountryListAll();
            this.getProductListAll();
            this.getProductList();
        },
        data() {
            let genderCode = CODE.getCodeList("GENDER_CODE");

            return {
                searchKeys: {
                    productKey: 0,
                    categoryId: '',
                    countryCode: '',
                    yearFr: 1000,
                    yearTo: Number(new Date().toISOString().substr(0, 4)),
                    genderCode: ''
                },
                searchCombos: {
                    productKey: [],
                    categoryId: [],
                    countryCode: [],
                    genderCode: [{codeId: '', codeName: 'All'}].concat(genderCode),
                },

                datePickerDisplay: false,

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
                        text: 'Country',
                        value: 'countryName',
                        width: '100px',
                        type: 'string',
                        updateType: 'select',
                        insertType: 'select',
                        selectKey: 'countryCode'
                    },
                    {
                        text: 'Product Name',
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
                        text: 'Image',
                        value: 'image',
                        width: '150px',
                        type: 'string',
                        updateType: 'image',
                        insertType: 'image',
                        imageType: 'product',
                        subPath: 'productKey'
                    },
                    {
                        text: 'Year',
                        value: 'year',
                        width: '100px',
                        type: 'number',
                        updateType: 'year',
                        insertType: 'year'
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
                        text: 'Description',
                        value: 'desc',
                        width: '180px',
                        type: 'string',
                        updateType: 'textarea',
                        insertType: 'textarea',
                        noRequire: true
                    },
                    {
                        text: 'Mapping',
                        value: 'mapping',
                        width: '150px'
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
                productList: [],
                selectedProduct: [],

                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: '',

                mappingKey: 0,
                brandMappingShow: false,
                brandMappingSelected: {categoryList: [], brandList: []},
                creatorMappingShow: false,
                creatorMappingSelected: {categoryList: [], creatorList: []},
                ingredientMappingShow: false,
                ingredientMappingSelected: {categoryList: [], ingredientList: []},
                productTypeMappingShow: false,
                productTypeMappingSelected: {categoryList: [], productTypeList: []}
            };
        },
        methods: {
            getImage(path) {
                return API.CommonController.displayImage + '?path=' + path;
            },
            async getCategoryListAll() {
                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    this.searchCombos.categoryId = [{categoryId: '', categoryName: 'All'}]
                        .concat(res.data.map(v => ({categoryId: v.categoryId, categoryName: v.categoryName})));
                    this.headers[0].selectItems = res.data.map(v => ({value: v.categoryId, text: v.categoryName}));
                });
            },
            async getCountryListAll() {
                this.searchCombos.countryCode = [];

                await axios.get(
                    API.CountryMgmtController.getCountryListAll
                ).then(res => {
                    let list = res.data.map(v => ({countryCode: v.countryCode, countryName: v.countryName}));
                    this.searchCombos.countryCode = [{countryCode: '', countryName: 'All'}].concat(list);
                    this.headers[1].selectItems = list.map(v => ({value: v.countryCode, text: v.countryName}));
                });
            },
            async getProductListAll() {
                this.searchCombos.productKey = [];
                this.searchCombos.link = [];

                await axios.get(
                    API.ProductMgmtController.getProductListAll
                ).then(res => {
                    this.searchCombos.productKey = [{productKey: 0, name: 'All'}]
                        .concat(COMMON_UTIL.removeArrDuplicate(res.data));
                });
            },
            getProductList() {
                this.selectedProduct = [];

                axios.post(
                    API.ProductMgmtController.getProductList,
                    this.searchKeys
                ).then(res => {
                    this.productList = res.data;
                });
            },
            bindYearFr(date) {
                this.searchKeys.yearFr = date;
            },
            bindYearTo(date) {
                this.searchKeys.yearTo = date;
            },
            updateConfirm() {
                this.updatePopShow = this.selectedProduct.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedProduct.length !== 0;
            },
            deleteItem() {
                axios.delete(
                    API.ProductMgmtController.deleteProduct,
                    null,
                    {
                        params: {
                            productKey: this.selectedProduct[0].productKey
                        }
                    }
                ).then(res => {
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
                    API.ProductMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.ProductMgmtController.insertProduct, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.ProductMgmtController.updateProduct, data)
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
                    productKey: 0,
                    categoryId: '',
                    countryCode: '',
                    yearFr: 1000,
                    yearTo: Number(new Date().toISOString().substr(0, 4)),
                    genderCode: ''
                };
                this.getProductListAll();
                this.getProductList();
            },
            async mapping(item, type) {
                let typeCapital = type.substr(0, 1).toUpperCase() + type.substr(1, type.length);
                let {productKey} = item;
                await axios.post(
                    API.ProductMgmtController['getMapped' + typeCapital + 'List'],
                    null,
                    {
                        params: {productKey}
                    }
                ).then(res => {
                    let mappingList = res.data;
                    let categoryList = mappingList.map(v => ({categoryId: v.categoryId}));
                    categoryList = COMMON_UTIL.removeArrDuplicate(categoryList);

                    this[type + 'MappingSelected'] = {categoryList};
                    this[type + 'MappingSelected'][type + 'List'] = mappingList;
                    this.mappingKey = productKey;
                    this[type + 'MappingShow'] = true;
                });
            },
            brandMappingClose() {
                ENTER_ACTION(this.getProductList);
                this.brandMappingShow = false;
            },
            creatorMappingClose() {
                ENTER_ACTION(this.getProductList);
                this.creatorMappingShow = false;
            },
            ingredientMappingClose() {
                ENTER_ACTION(this.getProductList);
                this.ingredientMappingShow = false;
            },
            productTypeMappingClose() {
                ENTER_ACTION(this.getProductList);
                this.productTypeMappingShow = false;
            }
        }
    }
</script>
