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
                            :items="searchCombos.brandKey"
                            item-text="name"
                            item-value="brandKey"
                            color="white"
                            label="Brand"
                            v-model="searchKeys.brandKey"
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
                    <v-autocomplete
                            :items="searchCombos.link"
                            color="white"
                            label="Link"
                            v-model="searchKeys.link"
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
                    @search="getBrandList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="brandList"
                item-key="brandKey"
                v-model="selectedBrand"
                show-select
                single-select
                @click:row="selectedBrand = [$event]"
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
                :title="'브랜드수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :values="selectedBrand[0]"
                :validation="duplicateValidation"
                :invalidMsg="'중복브랜드!!'"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'브랜드생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="500"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복브랜드!!'"
                @okAction="addItem"
                @cancelAction="insertPopShow = false"
        />

        <BrandMapperPopup
                :show="mappingShow"
                :brandKey="mappingKey"
                :selected="mappingSelected"
                @closeAction="mappingClose"
                @showAlert="mappingAlert"
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
    import BrandMapperPopup from "@/views/components/Popup/BrandMapperPopup";

    export default {
        name: 'BrandMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert,
            BrandMapperPopup
        },
        mounted() {
            ENTER_ACTION(this.getBrandList);
            this.getCountryListAll();
            this.getBrandListAll();
            this.getBrandList();
        },
        data() {
            return {
                searchKeys: {
                    brandKey: 0,
                    countryCode: '',
                    link: '',
                    useYn: true
                },
                searchCombos: {
                    brandKey: [],
                    countryCode: [],
                    link: [],
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
                        text: 'Brand Name',
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
                        text: 'Link',
                        value: 'link',
                        width: '150px',
                        type: 'string',
                        updateType: 'url',
                        insertType: 'url',
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
                brandList: [],
                selectedBrand: [],

                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: '',

                mappingShow: false,
                mappingKey: 0,
                mappingSelected: []
            };
        },
        methods: {
            async getCountryListAll() {
                this.searchCombos.countryCode = [];

                await axios.get(
                    API.CountryMgmtController.getCountryListAll
                ).then(res => {
                    let list = res.data.map(v => ({countryCode: v.countryCode, countryName: v.countryName}));
                    this.searchCombos.countryCode = [{countryCode: '', countryName: 'All'}].concat(list);
                    this.headers[0].selectItems = list.map(v => ({value: v.countryCode, text: v.countryName}));
                });
            },
            async getBrandListAll() {
                this.searchCombos.brandKey = [];
                this.searchCombos.link = [];

                await axios.get(
                    API.BrandMgmtController.getBrandListAll
                ).then(res => {
                    this.searchCombos.brandKey = [{brandKey: 0, name: 'All'}]
                        .concat(COMMON_UTIL.removeArrDuplicate(
                            res.data.map(v => ({brandKey: v.brandKey, name: v.name}))
                        ));
                    this.searchCombos.link = ['']
                        .concat(COMMON_UTIL.removeArrDuplicate(
                            res.data.map(v => v.link)
                        ));
                });
            },
            getBrandList() {
                this.selectedBrand = [];

                axios.post(
                    API.BrandMgmtController.getBrandList,
                    this.searchKeys
                ).then(res => {
                    this.brandList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedBrand.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedBrand.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.BrandMgmtController.deleteBrand,
                    this.selectedBrand[0]
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
                    API.BrandMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.BrandMgmtController.insertBrand, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.BrandMgmtController.updateBrand, data)
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
                    brandKey: 0,
                    countryCode: '',
                    link: '',
                    useYn: true
                };
                this.getBrandListAll();
                this.getBrandList();
            },
            async mapping(item) {
                let {brandKey} = item;
                await axios.post(
                    API.BrandMgmtController.getMappedCategoryList,
                    null,
                    {
                        params: {
                            brandKey
                        }
                    }
                ).then(res => {
                    this.mappingSelected = res.data;
                    this.mappingKey = brandKey;
                    this.mappingShow = true;
                });
            },
            mappingClose() {
                ENTER_ACTION(this.getBrandList);
                this.mappingShow = false;
            },
            mappingAlert() {
                this.alertStatus = 'success';
                this.alertMsg = '저장성공!';
                this.alertShow = true;
            }
        }
    }
</script>
