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
                            :items="searchCombos.countryCode"
                            color="white"
                            label="Country Code"
                            v-model="searchKeys.countryCode"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.countryName"
                            color="white"
                            label="Country Name"
                            v-model="searchKeys.countryName"
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
                    @search="getCountryList"
                    @add="addConfirm"
                    @update="updateConfirm"
                    @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
                :headers="headers"
                :items="countryList"
                item-key="countryCode"
                v-model="selectedCountry"
                show-select
                single-select
                @click:row="selectedCountry = [$event]"
                dense
        >
            <template slot="items" slot-scope="props">
                <tr >
                    <td>
                        <v-checkbox
                                v-model="props.selected"
                                hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.countryCode }}</td>
                    <td>{{ props.item.countryMessage }}</td>
                    <td>{{ props.item.countryName }}</td>
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
                :title="'국가수정'"
                :update="true"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :values="selectedCountry[0]"
                @okAction="updateItem"
                @cancelAction="updatePopShow = false"
        />

        <InsertPopup
                ref="insertPopup"
                :show="insertPopShow"
                :title="'국가생성'"
                :okBtnText="'ㄱㄱ'"
                :cancelBtnText="'ㄴㄴ'"
                :width="800"
                :fields="headers"
                :validation="duplicateValidation"
                :invalidMsg="'중복국가코드!!'"
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
        name: 'CountryMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getCountryList);
            this.getCountryListAll();
            this.getCountryList();
        },
        data() {
            return {
                searchKeys: {
                    countryCode: '',
                    countryName: '',
                    useYn: true
                },
                searchCombos: {
                    countryCode: [''],
                    countryName: [''],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Country Code',
                        value: 'countryCode',
                        width: '180px',
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'id'
                    },
                    {
                        text: 'Country Message',
                        value: 'countryMessage',
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
                        text: 'Country Name',
                        value: 'countryName',
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
                countryList: [],
                selectedCountry: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
        },
        methods: {
            async getCountryListAll() {
                this.searchCombos.countryCode = [];
                this.searchCombos.countryName = [];

                await axios.get(
                    API.CountryMgmtController.getCountryList
                ).then(res => {
                    let countryCode = [''].concat(res.data.map(v => v.countryCode));
                    let countryName = [''].concat(res.data.map(v => v.countryName));
                    this.searchCombos.countryCode = countryCode;
                    this.searchCombos.countryName = countryName;
                });
            },
            getCountryList() {
                this.selectedCountry = [];

                axios.post(
                    API.CountryMgmtController.getCountryList,
                    this.searchKeys
                ).then(res => {
                    this.countryList = res.data;
                });
            },
            updateConfirm() {
                this.updatePopShow = this.selectedCountry.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedCountry.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.CountryMgmtController.deleteCountry,
                    this.selectedCountry[0]
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
                    API.CountryMgmtController.checkDuplication,
                    null,
                    {
                        params: {
                            countryCode: data.countryCode
                        }
                    }
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                axios.put(API.CountryMgmtController.insertCountry, data)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.reset();
                    });
            },
            updateItem(data) {
                axios.patch(API.CountryMgmtController.updateCountry, data)
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
                    countryCode: '',
                    countryName: '',
                    useYn: true
                };
                this.getCountryListAll();
                this.getCountryList();
            }
        }
    }
</script>
