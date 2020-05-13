<template>
    <div>
        <template v-if="page === 'MessageMgmt'">
            <MessageMgmt :key="page"/>
        </template>
        <template v-if="page === 'BrandMgmt'">
            <BrandMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'CodeMgmt'">
            <CodeMgmt :key="page" @goTo="goTo"/>
        </template>
        <template v-else-if="page === 'CodeDetailMgmt'">
            <CodeDetailMgmt :key="page" @goTo="goTo"/>
        </template>
        <template v-else-if="page === 'CountryMgmt'">
            <CountryMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'CreatorMgmt'">
            <CreatorMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'ForumMgmt'">
            <ForumMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'IngredientMgmt'">
            <IngredientMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'MembershipMgmt'">
            <MembershipMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'ProductMgmt'">
            <ProductMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'ProductTypeMgmt'">
            <ProductTypeMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'RatingItemMgmt'">
            <RatingItemMgmt :key="page"/>
        </template>
        <template v-else-if="page === 'UserMgmt'">
            <UserMgmt :key="page"/>
        </template>
    </div>
</template>

<script>
    import MessageMgmt from "@/views/menu/system/MessageMgmt";
    import BrandMgmt from '@/views/menu/system/BrandMgmt';
    import CodeMgmt from '@/views/menu/system/CodeMgmt';
    import CodeDetailMgmt from '@/views/menu/system/CodeDetailMgmt';
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
            MessageMgmt,
            BrandMgmt,
            CodeMgmt,
            CodeDetailMgmt,
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
        },
        methods: {
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>