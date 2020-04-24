<template>
  <v-app>
    <v-app-bar
      app
      dark
    >
      <div
              @click="goTo('Home')"
              style="cursor: pointer;"
              class="d-flex align-center"
      >
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

      <v-btn  v-if="loggedInBoo"
              @click="membershipClicked"
              fab color="secondary"
              class="mx-2"
              elevation="5"
      >
        <v-icon>
          mdi-account-cog
        </v-icon>
      </v-btn>

      <v-btn  v-else
              @click="membershipClicked"
              fab color="#d6d6d6"
              class="mx-2"
              elevation="5"
      >
        <v-icon>mdi-account-arrow-left-outline</v-icon>
      </v-btn>

    </v-app-bar>

    <Router
            :routing="routing"
            @goTo="goTo"
            ref="router"
            @loggedIn="loggedIn"
    />
  </v-app>
</template>

<script>
  import Router from '@/router/Router';

  export default {
    name: 'App',
    components: {
      Router
    },
    created() {
      this.routing = 'Home';
    },
    watch: {
      routing() {
        this.$router.push(this.routing).catch(() => {});
      },
      '$route'(to, from) {
        if (from.name === to.name) {
          return;
        }
        this.$refs.router.goTo(to.name);
      }
    },
    data() {
      return {
        routing: '',
        loggedInBoo: false
      }
    },
    methods: {
      goTo(page) {
        this.routing = page;
      },
      membershipClicked() {
        let goTo = true;
        for (let i in MEMBERSHIP_PAGES) {
          if (this.routing === MEMBERSHIP_PAGES[i]) {
            goTo = false;
          }
        }
        if (!goTo) return;

        if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getId())) {
          this.loggedIn(false);
          this.goTo(MEMBERSHIP_PAGES[1]);
        } else {
          this.loggedIn(true);
          this.goTo(MEMBERSHIP_PAGES[0]);
        }
      },
      loggedIn(boo) {
        this.loggedInBoo = boo;
      }
    }
  };
</script>
