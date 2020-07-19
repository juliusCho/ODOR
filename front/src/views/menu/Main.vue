<template>
   <v-container>
    <video-bg :sources="['/front/src/assets/img/valentino.mp4']" img="/front/src/assets/img/logo.svg">

    </video-bg>
   </v-container>

</template>

<script>
    import videoBg from 'vue-videobg';


    export default {
        name: 'Home',
        components: {videoBg},
        data: () => ({}),
    }
</script>
