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
                {{ '제품, ' + this.mappingKeyCapital + ' 매핑 관리' }}
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
                                :items="searchCombos[mappingKey + 'Key']"
                                color="white"
                                :item-value="mappingKey + 'Key'"
                                :item-text="mappingKey + 'Name'"
                                :label="mappingKeyCapital"
                                v-model="searchKeys[mappingKey + 'Key']"
                        ></v-autocomplete>
                    </v-col>
                    <v-col>
                        <v-btn @click="getCategoryList" color="teal lighten-5">
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
                        :headers="mappingHeaders"
                        :items="mappingList"
                        :item-key="this.mappingKey + 'Key'"
                        v-model="selectedMapping"
                        show-select
                        height="250px"
                        :loading="rightLoading"
                        @click:row="selectedMapping.push($event)"
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
            :width="300"
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
        name: "ProductMapperPopup",
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
            productKey: {
                type: Number,
                default: 0,
                required: false
            },
            mappingKey: {
                type: String,
                default: '',
                required: true
            },
            selected: {
                type: Object,
                default: () => {
                    let result = {categoryList: []};
                    result[this.mappingKey + 'List'] = [];
                    return result;
                },
                required: false
            }
        },
        data() {
            let dataSelected = {categoryList: []};
            dataSelected[this.mappingKey + 'List'] = [];
            let searchKeys = {categoryId: ''};
            searchKeys[this.mappingKey + 'Key'] = 0;
            let searchCombos = {categoryId: []};
            searchCombos[this.mappingKey + 'Key'] = [];

            return {
                thisShow: false,

                dataSelected,

                searchKeys,
                searchCombos,
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
                mappingHeaders: [
                    {
                        text: this.mappingKeyCapital + ' Name',
                        value: this.mappingKey + 'Name',
                        width: '100px'
                    }
                ],
                categoryList: [],
                mappingList: [],
                selectedCategory: [],
                selectedMapping: [],
                clickedCategory: {},

                blockAdd: true,

                alertStatus: 'warning',
                alertMsg: '경고',
                alertShow: false,

                confirmShow: false,
                mappingKeyCapital: ''
            }
        },
        watch: {
            async show() {
                this.thisShow = this.show;
                if (this.thisShow) {
                    ENTER_ACTION(this.getCategoryList);
                    let result = await this.initializeData();
                    if (result) {
                        this.searchCombos[this.mappingKey + 'Key'] = await this.getMappingListAll();
                        this.searchCombos.categoryId = await this.getCategoryListAll();
                        this.getCategoryList();
                    }
                }
            },
            'clickedCategory.categoryId'() {
                if (this.clickedCategory.categoryId) {
                    this.rightLoading = false;
                    this.blockAdd = false;
                    this.searchKeys.categoryId = this.clickedCategory.categoryId;
                    this.searchKeys[this.mappingKey + 'Key'] = 0;
                } else {
                    this.rightLoading = true;
                    this.blockAdd = true;
                }
            }
        },
        methods: {
            async initializeData() {
                this.mappingKeyCapital = this.mappingKey.substr(0, 1).toUpperCase() +
                        this.mappingKey.substr(1, this.mappingKey.length);

                this.dataSelected[this.mappingKey + 'List'] = [];
                this.searchKeys[this.mappingKey + 'Key'] = 0;
                this.searchCombos[this.mappingKey + 'Key'] = [];
                this.mappingHeaders = [
                    {
                        text: this.mappingKeyCapital + ' Name',
                        value: this.mappingKey + 'Name',
                        width: '100px'
                    }
                ];

                this.dataSelected = this.selected;

                if (!this.selected[this.mappingKey + 'List']) {
                    this.dataSelected[this.mappingKey + 'List'] = [];
                }
                if (this.dataSelected.categoryList.length > 0) {
                    this.categorySelected(this.dataSelected.categoryList[0]);
                }
                await this.$forceUpdate();
                return true;
            },
            async getCategoryListAll() {
                this.searchCombos.categoryId = [];
                let result = [];

                await axios.get(
                    API.CategoryMgmtController.getCategoryListAll
                ).then(res => {
                    result = [{categoryId: '', categoryName: 'ALL'}].concat(res.data);
                });
                return result;
            },
            getCategoryList() {
                axios.post(
                    API.CategoryMgmtController.getCategoryList,
                    {
                        categoryId: this.searchKeys.categoryId,
                        useYn: true
                    }
                ).then(res => {
                    this.categoryList = res.data;
                    this.checkAlreadyAddedCategory();
                });
            },
            checkAlreadyAddedCategory() {
                this.selectedCategory = [];
                for (let i = 0, ii = this.categoryList.length; i < ii; i++) {
                    let idx = this.dataSelected.categoryList
                        .findIndex(v => v.categoryId === this.categoryList[i].categoryId);
                    if (idx > -1) {
                        this.selectedCategory.push(this.categoryList[i]);
                        break;
                    }
                }
                if (this.selectedCategory.length > 0) {
                    this.categorySelected(this.selectedCategory[0]);
                }
            },
            categorySelected(data) {
                this.selectedCategory = [data];
                this.clickedCategory = data;
                this.searchKeys.categoryId = data.categoryId;
                this.getMappingList();
            },
            async getMappingListAll() {
                this.searchCombos[this.mappingKey + 'Key'] = [];
                let result = [];

                await axios.get(
                    API[this.mappingKeyCapital + 'MgmtController']['get' + this.mappingKeyCapital + 'ListAll']
                ).then(res => {
                    let mappingKey = {};
                    mappingKey[this.mappingKey + 'Key'] = 0;
                    mappingKey[this.mappingKey + 'Name'] = 'All';

                    let mappingKeyList = [mappingKey].concat(
                                            res.data.map(
                                                v => {
                                                    let result = {};
                                                    result[this.mappingKey + 'Key'] = v[this.mappingKey + 'Key'];
                                                    result[this.mappingKey + 'Name'] = v[this.mappingKey + 'Name'];
                                                    return result;
                                                }
                                            )
                                        );
                    result = mappingKeyList;
                });
                return result;
            },
            async getMappingList() {
                let {categoryId} = this.searchKeys;
                let mappingKey = this.searchKeys[this.mappingKey + 'Key'];
                let searchKeys = {
                    categoryId, useYn: true
                };
                searchKeys[this.mappingKey + 'Key'] = mappingKey;

                await axios.post(
                    API[this.mappingKeyCapital + 'MgmtController']['get' + this.mappingKeyCapital + 'List'],
                    searchKeys
                ).then(res => {
                    this.mappingList = res.data;
                    this.checkAlreadyAddedMapping();
                });
            },
            checkAlreadyAddedMapping() {
                this.selectedMapping = [];
                this.mappingList.forEach(mapping => {
                    let idx = this.dataSelected[this.mappingKey + 'List']
                        .findIndex(v => v[this.mappingKey + 'Key'] === mapping[this.mappingKey + 'Key']);
                    if (idx > -1) {
                        this.selectedMapping.push(mapping);
                    }
                });
            },
            selectAction() {
                this.confirmShow = true;
            },
            saveCheck() {
                if (this.selectedMapping.length === 0) {
                    this.deleteAction();
                    return;
                }
                this.saveAction();
            },
            deleteAction() {
                axios.delete(
                    API.ProductMgmtController['delete' + this.mappingKeyCapital + 'Mapping'],
                    {
                        params: {
                            productKey: this.productKey,
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
                let idxList = [];
                this.mappingList.forEach(item => {
                    idx = this.dataSelected[this.mappingKey + 'List']
                        .findIndex(v => v[this.mappingKey + 'Key'] === item[this.mappingKey + 'Key']);
                    if (idx > -1) {
                        idxList.push(idx);
                    }
                });
                idxList.reverse().forEach(i => {
                    this.dataSelected[this.mappingKey + 'List'].splice(i, 1);
                });
            },
            showAlert() {
                this.alertStatus = 'success';
                this.alertMsg = '저장성공!';
                this.alertShow = true;
            },
            saveAction() {
                let selectedList = this.selectedMapping.map(v =>
                        Object.assign(v, {
                            productKey: this.productKey,
                            categoryId: this.clickedCategory.categoryId
                        })
                    );

                axios.patch(
                    API.ProductMgmtController['save' + this.mappingKeyCapital + 'Mapping'],
                    selectedList
                ).then(() => {
                    this.updateDataSelected();
                    this.confirmShow = false;
                    this.showAlert();
                });
            },
            updateDataSelected() {
                this.deleteDataSelected();

                this.dataSelected[this.mappingKey + 'List'] = this.dataSelected[this.mappingKey + 'List'].concat(
                    this.selectedMapping.map(v => {
                        let result = {categoryId: v.categoryId};
                        result[this.mappingKey + 'Key'] = v[this.mappingKey + 'Key'];
                        return result;
                    })
                );
            },
            cancelAction() {
                this.$emit('closeAction');
                this.reinitialize();
            },
            reinitialize() {
                this.dataSelected = {categoryList: []};
                this.dataSelected[this.mappingKey + 'List'] = [];
                this.searchKeys = {categoryId: ''};
                this.searchKeys[this.mappingKey + 'Key'] = 0;
                this.selectedCategory = [];
                this.selectedMapping = [];
                this.blockAdd = true;
                this.alertShow = false;
                this.confirmShow = false;
                this.clickedCategory = {};
            }
        }
    }
</script>