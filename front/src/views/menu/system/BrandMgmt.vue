<template>
    <v-container>
        <v-form id="search">
            <v-row>
                <v-col>
                    <v-text-field
                        v-model="nameSearch"
                        label="Name"
                    />
                </v-col>
                <v-col>
                    <v-text-field
                        v-model="powerSearch"
                        label="Power"
                    />
                </v-col>
            </v-row>
        </v-form>
        <v-btn
            class="pl-12 pr-12"
            @click="save"
            align="center"
            color="primary"
            rounded
        >
            Save
        </v-btn>
        <Grid
            :columns="columns"
            :rowData="rowData"
            :searchKeys="[nameSearch, powerSearch]"
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
        data() {
            return {
                nameSearch: '',
                powerSearch: '',
                columns: ['name', 'power'],
                rowData: [
                    {name: '조인효', power: Infinity},
                    {name: 'bruce lee', power: 9000},
                    {name: '햄토리', power: 100000},
                    {name: 'Jackie chan', power: 8000}
                ]
            }
        },
        methods: {
            save() {

            }
        }
    }
</script>