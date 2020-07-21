<template>
    <v-container>
        <v-layout column style="margin-top:80px;">
            <v-row align="center" justify="center">
                <p class="display-2 font-weight-thin">Team ODOR</p>
            </v-row>
            <div style="margin: 20px 0 0 0">
                <v-row justify="center" align="center">
                    <v-col cols="12" sm="8" md="6">
                        <v-layout row align-center>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin" style="text-align: center">
                                        {{ $t('about.julius.intro') }}
                                    </p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <v-btn href="https://blog.naver.com/johncris" target="_blank"
                                           small class="ma-1" outlined>
                                        <v-icon left>mdi-blogger</v-icon> blog</v-btn>
                                    <v-btn href="https://github.com/juliusCho" target="_blank"
                                           small class="ma-1" outlined>
                                        <v-icon left>mdi-github</v-icon> github
                                    </v-btn>
                                </v-row>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <v-avatar
                                            class="elevation-6"
                                            size="128"
                                    >
                                        <v-img src="@/assets/img/pic/Julius.jpg"></v-img>
                                    </v-avatar>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-regular" style="margin-top: 16px; margin-bottom: 0">
                                        {{ $t('about.julius.name') }}
                                    </p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin">
                                        {{ $t('about.julius.sub') }}
                                    </p>
                                </v-row>
                            </v-col>
                        </v-layout>
                    </v-col>
                </v-row>
                <v-row justify="center" align="center">
                    <v-col cols="12" sm="8" md="6">
                        <v-layout row align-center>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <v-avatar
                                            class="elevation-6"
                                            size="128"
                                    >
                                        <v-img src="@/assets/img/pic/Renee.jpg"></v-img>
                                    </v-avatar>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-regular" style="margin-top: 16px; margin-bottom: 0">
                                        {{ $t('about.renee.name') }}
                                    </p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin">
                                        {{ $t('about.renee.sub') }}
                                    </p>
                                </v-row>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin"
                                       style="text-align: center">
                                        {{ $t('about.renee.intro') }}
                                    </p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <v-btn onclick="window.open('https://github.com/uzicandi','window_name','width=430,height=500,location=no,status=no,scrollbars=yes');"
                                           small class="ma-1" outlined>
                                        <v-icon left>mdi-blogger</v-icon> blog</v-btn>
                                    <v-btn onclick="window.open('https://github.com/uzicandi','window_name','width=430,height=500,location=no,status=no,scrollbars=yes');"
                                           small class="ma-1" outlined>
                                        <v-icon left>mdi-github</v-icon> github
                                    </v-btn>
                                </v-row>
                            </v-col>
                        </v-layout>
                    </v-col>
                </v-row>
            </div>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: 'About',
        methods: {
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>

<style scoped>

</style>