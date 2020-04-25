<template>
    <div>
        <template v-if="page === 'BrandMgmt'">
            <BrandMgmt/>
        </template>
        <template v-else-if="page === 'CodeMgmt'">
            <CodeMgmt/>
        </template>
        <template v-else-if="page === 'CountryMgmt'">
            <CountryMgmt/>
        </template>
        <template v-else-if="page === 'CreatorMgmt'">
            <CreatorMgmt/>
        </template>
        <template v-else-if="page === 'ForumMgmt'">
            <ForumMgmt/>
        </template>
        <template v-else-if="page === 'IngredientMgmt'">
            <IngredientMgmt/>
        </template>
        <template v-else-if="page === 'MembershipMgmt'">
            <MembershipMgmt/>
        </template>
        <template v-else-if="page === 'ProductMgmt'">
            <ProductMgmt/>
        </template>
        <template v-else-if="page === 'ProductTypeMgmt'">
            <ProductTypeMgmt/>
        </template>
        <template v-else-if="page === 'RatingItemMgmt'">
            <RatingItemMgmt/>
        </template>
        <template v-else-if="page === 'UserMgmt'">
            <UserMgmt/>
        </template>
    </div>
</template>

<script>
    import BrandMgmt from '@/views/menu/system/BrandMgmt';
    import CodeMgmt from '@/views/menu/system/CodeMgmt';
    import CountryMgmt from '@/views/menu/system/CountryMgmt';
    import CreatorMgmt from '@/views/menu/system/CreatorMgmt';
    import ForumMgmt from '@/views/menu/system/ForumMgmt';
    import IngredientMgmt from '@/views/menu/system/IngredientMgmt';
    import MembershipMgmt from '@/views/menu/system/MembershipMgmt';
    import ProductMgmt from '@/views/menu/system/ProductMgmt';
    import ProductTypeMgmt from '@/views/menu/system/ProductTypeMgmt';
    import RatingItemMgmt from '@/views/menu/system/RatingItemMgmt';
    import UserMgmt from '@/views/menu/system/UserMgmt';

    export default {
        name: 'SessionCheck',
        components: {
            BrandMgmt,
            CodeMgmt,
            CountryMgmt,
            CreatorMgmt,
            ForumMgmt,
            IngredientMgmt,
            MembershipMgmt,
            ProductMgmt,
            ProductTypeMgmt,
            RatingItemMgmt,
            UserMgmt
        },
        props: {
            page: {
                type: String,
                default: '',
                required: false
            }
        },
        watch: {
            page() {
                this.$emit('checkSession', this.page);
            }
        }
    }
</script>