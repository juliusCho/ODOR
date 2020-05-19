<template>
    <v-container>
        <v-layout column style="margin-top:80px;">
            <v-row align="center" justify="center">
                <p class="display-2 font-weight-thin">Team ODOR</p>
            </v-row>
            <v-row justify="center">
                <p class="subtitle-1 font-weight-thin">ODOR의 개발자를 소개합니다.</p>
            </v-row>
            <div style="margin: 20px 0 0 0">
                <v-row justify="center" align="center">
                    <v-col cols="12" sm="8" md="6">
                        <v-layout row align-center>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin" style="text-align: center">
                                        몽총이 깨구리
                                    </p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <v-btn small class="ma-1" outlined>
                                        <v-icon left>mdi-blogger</v-icon> blog</v-btn>
                                    <v-btn onclick="window.open('https://github.com/juliusCho','window_name','width=430,height=500,location=no,status=no,scrollbars=yes');"
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
                                        <v-img src="../../../assets/img/pic/Julius.jpg"></v-img>
                                    </v-avatar>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-regular" style="margin-top: 16px; margin-bottom: 0">Julius</p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin">Full Stack</p>
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
                                        <v-img src="../../../assets/img/pic/Renee.jpg"></v-img>
                                    </v-avatar>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-regular" style="margin-top: 16px; margin-bottom: 0">Renee</p>
                                </v-row>
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin">Design, Frontend</p>
                                </v-row>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-row justify="center" align="center">
                                    <p class="subtitle-1 font-weight-thin"
                                       style="text-align: center">
                                        똑순이 햄토리
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
<!--        <section id="about-julius">-->
<!--            <v-container class="text-center">-->
<!--                <h2 class="display-2 font-weight-bold mb-3">JULIUS</h2>-->

<!--                <v-responsive-->
<!--                        class="mx-auto mb-8"-->
<!--                        width="56"-->
<!--                >-->
<!--                    <v-divider class="mb-1"></v-divider>-->

<!--                    <v-divider></v-divider>-->
<!--                </v-responsive>-->

<!--                <v-responsive-->
<!--                        class="mx-auto title font-weight-light mb-8"-->
<!--                        max-width="720"-->
<!--                >-->
<!--                    찐 잘생기고 머리좋고 간지나고 옷빨잘받고 슈퍼나이스하며 언제나 여유를 잃지않는 완벽남.-->
<!--                </v-responsive>-->

<!--                <v-avatar-->
<!--                        class="elevation-12 mb-12"-->
<!--                        size="128"-->
<!--                >-->
<!--                    <v-img src="../../../assets/img/pic/Julius.jpg"></v-img>-->
<!--                </v-avatar>-->

<!--                <div></div>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        href="https://github.com/juliusCho"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="margin-right: 10px;"-->
<!--                        target="_blank"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/github-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        GitHub-->
<!--                    </span>-->
<!--                </v-btn>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        href="https://blog.naver.com/johncris"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="margin-right: 10px;"-->
<!--                        target="_blank"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/blog-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        Blog-->
<!--                    </span>-->
<!--                </v-btn>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="cursor: text;"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/gmail-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        johncrist2000@gmail.com-->
<!--                    </span>-->
<!--                </v-btn>-->
<!--            </v-container>-->

<!--            <div class="py-12"></div>-->
<!--        </section>-->


<!--        <section id="about-renee">-->

<!--            <v-container class="text-center">-->
<!--                <h2 class="display-2 font-weight-bold mb-3">RENEE</h2>-->

<!--                <v-responsive-->
<!--                        class="mx-auto mb-8"-->
<!--                        width="56"-->
<!--                >-->
<!--                    <v-divider class="mb-1"></v-divider>-->

<!--                    <v-divider></v-divider>-->
<!--                </v-responsive>-->

<!--                <v-responsive-->
<!--                        class="mx-auto title font-weight-light mb-8"-->
<!--                        max-width="720"-->
<!--                >-->
<!--                    기여운 햄토리-->
<!--                </v-responsive>-->

<!--                <v-avatar-->
<!--                        class="elevation-12 mb-12"-->
<!--                        size="128"-->
<!--                >-->
<!--                    <v-img src="../../../assets/img/pic/Renee.jpg"></v-img>-->
<!--                </v-avatar>-->

<!--                <div></div>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        href="https://github.com/uzicandi"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="margin-right: 10px;"-->
<!--                        target="_blank"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/github-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        GitHub-->
<!--                    </span>-->
<!--                </v-btn>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        href="https://vuetifyjs.com"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="margin-right: 10px;"-->
<!--                        target="_blank"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/blog-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        Blog-->
<!--                    </span>-->
<!--                </v-btn>-->

<!--                <v-btn-->
<!--                        color="grey"-->
<!--                        outlined-->
<!--                        large-->
<!--                        style="cursor: text;"-->
<!--                >-->
<!--                    <span class="grey&#45;&#45;text text&#45;&#45;darken-1 font-weight-bold">-->
<!--                        <v-avatar size="15">-->
<!--                            <v-img src="../../../assets/img/icon/gmail-icon.svg"/>-->
<!--                        </v-avatar>-->
<!--                        kys7196@gmail.com-->
<!--                    </span>-->
<!--                </v-btn>-->
<!--            </v-container>-->

<!--            <div class="py-12"></div>-->
<!--        </section>-->


    </v-container>
</template>

<script>
    import { mdiGithub } from '@mdi/js';
    import { mdiBlogger } from '@mdi/js';

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