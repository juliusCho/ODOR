<template>
    <v-container>

        <section id="hero">
            <v-row no-gutters>
                <v-img
                        :min-height="'calc(100vh - ' + $vuetify.application.top + 'px)'"
                        src="https://images.unsplash.com/photo-1487017159836-4e23ece2e4cf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=80"
                >
                    <v-theme-provider dark>
                        <v-container fill-height>
                            <v-row
                                    align="center"
                                    class="white--text mx-auto"
                                    justify="center"
                            >
                                <v-col
                                        class="white--text text-center"
                                        cols="12"
                                        tag="h1"
                                >

                                <br>

                                <span
                                        style="text-shadow: 2px 2px 2px gray;"
                                        :class="[$vuetify.breakpoint.smAndDown ? 'display-3': 'display-4']"
                                        class="font-weight-black"
                                >
                                  About ODOR
                                </span>
                                <br/>
                                <v-responsive
                                        class="mx-auto title font-weight-light mb-8"
                                        max-width="720"
                                        style="text-shadow: 2px 2px 2px gray; white-space: pre-line;"
                                >
                                  {{'ODOR짱짱먠\n모두가 외쳐 ODOR'}}
                                </v-responsive>

                                <br/>
                                <div class="py-12"></div>
                                <div class="py-12"></div>



                                <span
                                        style="text-shadow: 2px 2px 2px gray;"
                                        :class="[$vuetify.breakpoint.smAndDown ? 'display-2' : 'display-3']"
                                        class="font-weight-light"
                                >
                                  Contact Us
                                </span>

                                </v-col>

                                <v-btn
                                        class="align-self-end"
                                        fab
                                        outlined
                                        @click="$vuetify.goTo('#contact-us')"
                                >
                                    <v-icon>mdi-chevron-double-down</v-icon>
                                </v-btn>
                            </v-row>
                        </v-container>
                    </v-theme-provider>
                </v-img>
            </v-row>
            <div class="py-12"></div>
        </section>

        <section id="about-julius">
            <v-container class="text-center">
                <h2 class="display-2 font-weight-bold mb-3">JULIUS</h2>

                <v-responsive
                        class="mx-auto mb-8"
                        width="56"
                >
                    <v-divider class="mb-1"></v-divider>

                    <v-divider></v-divider>
                </v-responsive>

                <v-responsive
                        class="mx-auto title font-weight-light mb-8"
                        max-width="720"
                >
                    찐 잘생기고 머리좋고 간지나고 옷빨잘받고 슈퍼나이스하며 언제나 여유를 잃지않는 완벽남.
                </v-responsive>

                <v-avatar
                        class="elevation-12 mb-12"
                        size="128"
                >
                    <v-img src="../../../assets/img/pic/Julius.jpg"></v-img>
                </v-avatar>

                <div></div>

                <v-btn
                        color="grey"
                        href="https://github.com/juliusCho"
                        outlined
                        large
                        style="margin-right: 10px;"
                        target="_blank"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/github-icon.svg"/>
                        </v-avatar>
                        GitHub
                    </span>
                </v-btn>

                <v-btn
                        color="grey"
                        href="https://blog.naver.com/johncris"
                        outlined
                        large
                        style="margin-right: 10px;"
                        target="_blank"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/blog-icon.svg"/>
                        </v-avatar>
                        Blog
                    </span>
                </v-btn>

                <v-btn
                        color="grey"
                        outlined
                        large
                        style="cursor: text;"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/gmail-icon.svg"/>
                        </v-avatar>
                        johncrist2000@gmail.com
                    </span>
                </v-btn>
            </v-container>

            <div class="py-12"></div>
        </section>


        <section id="about-renee">

            <v-container class="text-center">
                <h2 class="display-2 font-weight-bold mb-3">RENEE</h2>

                <v-responsive
                        class="mx-auto mb-8"
                        width="56"
                >
                    <v-divider class="mb-1"></v-divider>

                    <v-divider></v-divider>
                </v-responsive>

                <v-responsive
                        class="mx-auto title font-weight-light mb-8"
                        max-width="720"
                >
                    기여운 햄토리
                </v-responsive>

                <v-avatar
                        class="elevation-12 mb-12"
                        size="128"
                >
                    <v-img src="../../../assets/img/pic/Renee.jpg"></v-img>
                </v-avatar>

                <div></div>

                <v-btn
                        color="grey"
                        href="https://github.com/uzicandi"
                        outlined
                        large
                        style="margin-right: 10px;"
                        target="_blank"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/github-icon.svg"/>
                        </v-avatar>
                        GitHub
                    </span>
                </v-btn>

                <v-btn
                        color="grey"
                        href="https://vuetifyjs.com"
                        outlined
                        large
                        style="margin-right: 10px;"
                        target="_blank"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/blog-icon.svg"/>
                        </v-avatar>
                        Blog
                    </span>
                </v-btn>

                <v-btn
                        color="grey"
                        outlined
                        large
                        style="cursor: text;"
                >
                    <span class="grey--text text--darken-1 font-weight-bold">
                        <v-avatar size="15">
                            <v-img src="../../../assets/img/icon/gmail-icon.svg"/>
                        </v-avatar>
                        kys7196@gmail.com
                    </span>
                </v-btn>
            </v-container>

            <div class="py-12"></div>
        </section>

        <section id="contact-us">
            <v-sheet
                    id="contact"
                    color="#333333"
                    dark
                    tag="section"
                    tile
            >
                <div class="py-12"></div>

                <v-container>
                    <h2 class="display-2 font-weight-bold mb-3 text-uppercase text-center">Contact Us</h2>

                    <v-responsive
                            class="mx-auto mb-12"
                            width="56"
                    >
                        <v-divider class="mb-1"></v-divider>

                        <v-divider></v-divider>
                    </v-responsive>

                    <v-theme-provider light>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        flat
                                        label="Name*"
                                        solo
                                ></v-text-field>
                            </v-col>

                            <v-col cols="12">
                                <v-text-field
                                        flat
                                        label="Email*"
                                        solo
                                ></v-text-field>
                            </v-col>

                            <v-col cols="12">
                                <v-text-field
                                        flat
                                        label="Subject*"
                                        solo
                                ></v-text-field>
                            </v-col>

                            <v-col cols="12">
                                <v-textarea
                                        flat
                                        label="Message*"
                                        solo
                                ></v-textarea>
                            </v-col>

                            <v-col
                                    class="mx-auto"
                                    cols="auto"
                            >
                                <v-btn
                                        color="accent"
                                        x-large
                                >
                                    Submit
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-theme-provider>
                </v-container>

            </v-sheet>
            <div class="py-12"></div>
        </section>
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