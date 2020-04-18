<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />

      </div>

      <v-spacer></v-spacer>

      <v-btn
              @click="switchToOdor = !switchToOdor"
              fab color="accent"
              class="mx-2"
              elevation="5"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-app-bar>


    <Router :routing="routing" @goTo="goTo" ref="router"/>
  </v-app>
</template>

<script>
  import Router from '@/router/Router';

  const SESSION_EXCLUDE_PAGES = [
          'Home',
          'Login',
          'Join',
          'IdPwFinder'
  ];

  export default {
    name: 'App',
    components: {
      Router
    },
    created() {
      this.routing = 'Home';
    },
    watch: {
      switchToOdor() {
        if (this.switchToOdor) {
          this.goTo(SESSION_EXCLUDE_PAGES[1]);
        } else {
          this.goTo(SESSION_EXCLUDE_PAGES[0]);
        }
      },
      routing() {
        this.$router.push(this.routing).catch(() => {});
      },
      '$route'(to, from) {
        if (from.name === to.name) {
          return;
        }

        let sessionCheck = true;
        for (let i in SESSION_EXCLUDE_PAGES) {

          if (SESSION_EXCLUDE_PAGES[i] === to.name) {
            sessionCheck = false;

            if (SESSION_EXCLUDE_PAGES[i] === 'Home') {
              this.switchToOdor = false;
            }
          }
        }
        if (!sessionCheck) {
          this.goTo(to.name);
        } else {
          this.$refs.router.goTo(to.name);
        }
      }
    },
    data() {
      return {
        switchToOdor: false,
        routing: ''
      }
    },
    methods: {
      goTo(page) {
        this.routing = page;
      }
    }
  };
</script>
