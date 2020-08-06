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
                {{ '브랜드 카테고리 관리' }}
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
                        <v-btn @click="getCategoryList" color="teal lighten-5">
                            Search
                        </v-btn>
                    </v-col>
                </v-row>
                <v-row>
                    <v-data-table
                        :headers="categoryHeaders"
                        :items="categoryList"
                        item-key="categoryId"
                        v-model="selectedCategory"
                        show-select
                        height="250px"
                    >
                    </v-data-table>
                </v-row>
            </v-card-text>
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
        name: "BrandMapperPopup",
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
                default: 500,
                required: false
            },
            brandKey: {
                type: Number,
                default: 0,
                required: false
            },
            selected: {
                type: Array,
                default: () => [],
                required: false
            }
        },
        data() {
            return {
                thisShow: false,
                dataSelected: [],

                searchKeys: {categoryId: ''},
                searchCombos: {
                    categoryId: []
                },

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
                categoryList: [],
                selectedCategory: [],

                alertStatus: 'warning',
                alertMsg: '경고',
                alertShow: false,

                confirmShow: false
            }
        },
        watch: {
            show() {
                this.thisShow = this.show;
                if (this.thisShow) {
                    ENTER_ACTION(this.getCategoryList);
                    this.initializeData();
                    this.getCategoryListAll();
                    this.getCategoryList();
                }
            }
        },
        methods: {
            initializeData() {
                this.dataSelected = this.selected;
            },
            async getCategoryListAll() {
                this.searchCombos.categoryId = [];

                await axios.get(
                    API.CategoryMgmtController.getCategoryList
                ).then(res => {
                    this.searchCombos.categoryId = [{categoryId: '', categoryName: 'ALL'}].concat(res.data);
                });
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
                    this.checkAlreadyAdded();
                });
            },
            checkAlreadyAdded() {
                this.selectedCategory = [];

                this.categoryList.forEach(category => {
                    let idx = this.dataSelected.findIndex(v => v.categoryId === category.categoryId);
                    if (idx > -1) {
                        this.selectedCategory.push(category);
                    }
                });
            },
            selectAction() {
                this.confirmShow = true;
            },
            saveCheck() {
                if (this.selectedCategory.length === 0) {
                    this.deleteAction();
                    return;
                }
                this.saveAction();
            },
            deleteAction() {
                axios.delete(
                    API.BrandMgmtController.deleteMapping,
                    {
                        params: {
                            brandKey: this.brandKey
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
                this.dataSelected.forEach(item => {
                    idx = this.selectedCategory.findIndex(v => v.categoryId === item.categoryId);
                    if (idx  > -1) {
                        idxList.push(idx);
                    }
                });
                idxList.reverse().forEach(i => {
                    this.dataSelected.splice(i, 1);
                });
            },
            showAlert() {
                this.cancelAction();
                this.$emit('showAlert');
            },
            saveAction() {
                let list = this.selectedCategory.map(v => Object.assign(v, {brandKey: this.brandKey}));
                axios.patch(
                    API.BrandMgmtController.saveMapping,
                    list
                ).then(() => {
                    this.confirmShow = false;
                    this.showAlert();
                });
            },
            cancelAction() {
                this.$emit('closeAction');
                this.reinitialize();
            },
            reinitialize() {
                this.dataSelected = [];
                this.searchKeys = {categoryId: ''};
                this.selectedCategory = [];
                this.alertShow = false;
                this.confirmShow = false;
            }
        }
    }
</script>