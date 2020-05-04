<template>
    <v-container class="grey lighten-5" >
        <v-form id="search">
            <v-row>
                <v-col>
                    <v-text-field
                        v-model="countryCode"
                        label="Country Code"
                    />
                </v-col>
                <v-col>
                    <v-text-field
                        v-model="name"
                        label="Name"
                    />
                </v-col>
            </v-row>
        </v-form>
        <Grid
            :columns="columns"
            :rowData="rowData"
            :searchKeys="[name, countryCode]"
            @save="save"
            @add="add"
            @search="search"
        />
    </v-container>
</template>

<script>
    import Grid from '@/views/components/Grid';
    import axios from 'axios';

    export default {
        name: 'BrandMgmt',
        components: {
            Grid
        },
        mounted() {
            this.search();
        },
        data() {
            return {
                name: '',
                countryCode: '',
                columns: [
                    {
                        key: 'brandKey'
                    },
                    {
                        key: 'countryCode',
                        label: '국가코드',
                        editor: 'select',
                        display: true,
                        required: true
                    },
                    {
                        key: 'name',
                        label: '브랜드명',
                        editor: 'text',
                        display: true,
                        required: true
                    },
                    {
                        key: 'engName',
                        label: '영문 브랜드명',
                        editor: 'text',
                        display: true,
                        required: true
                    },
                    {
                        key: 'desc',
                        label: '설명',
                        editor: 'textarea',
                        display: true
                    },
                    {
                        key: 'link',
                        label: '웹사이트 URL',
                        editor: 'text',
                        display: true
                    },
                    {
                        key: 'useYn',
                        label: '사용',
                        editor: 'checkbox',
                        display: true
                    },
                    {
                        key: 'updaterName',
                        label: '수정자',
                        display: true
                    },
                    {
                        key: 'updateDate',
                        label: '수정일시',
                        display: true
                    }
                ],
                rowData: []
            }
        },
        methods: {
            save() {

            },
            add() {

            },
            search() {
                axios.post(
                    API.BrandMgmtController.getBrandList,
                    {
                        countryCode: this.countryCode,
                        name: this.name
                    }
                ).then(res => {
                    this.rowData = res.data;
                });
            }
        }
    }
</script>