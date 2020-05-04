<template>
    <v-container>
        <v-form id="search">
            <v-text-field
                v-model="searchKey"
                :counter="10"
                label="Search"
                outlined
            />
        </v-form>
        <Grid
            :searchUrl="'agreaerg'"
            :columns="columns"
            :rowData="rowData"
            :searchKeys="[searchKey]"
        />
    </v-container>
</template>

<script>
    import Grid from '@/views/components/Grid';

    export default {
        name: 'BrandMgmt',
        components: {
            Grid
        },
        data() {
            return {
                searchKey: '',
                columns: ['name', 'power'],
                rowData: [
                    {name: '조인효', power: Infinity},
                    {name: 'bruce lee', power: 9000},
                    {name: '햄토리', power: 100000},
                    {name: 'Jackie chan', power: 8000}
                ]
            }
        }
    }
</script>