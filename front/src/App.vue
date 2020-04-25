<template>
  <v-app>
    <v-app-bar
      app
      dark
    >
      <div
              @click="goTo('Home')"
              style="cursor: pointer;"
      >
        <v-btn style="font-size: 30px;" text>ODOR</v-btn>
      </div>

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <v-btn small text>FORUM</v-btn>

      <v-spacer/>

      <v-btn small text>PERFUME</v-btn>

      <v-spacer/>

      <v-btn @click="goTo('About')" small text>ABOUT</v-btn>

      <template v-if="loggedInBoo && sysMngrYn">
        <v-spacer/>
        <v-btn small text>SYSTEM</v-btn>
      </template>

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

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
      },
      loggedInBoo() {
        this.sysMngrYn = this.sysMngrCheck();
      }
    },
    data() {
      return {
        routing: '',
        loggedInBoo: false,
        sysMngrYn: false
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
      },
      sysMngrCheck() {
        if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getLoginUser().sysMngrYn)) {
          return false;
        }
        return TMP_SESSION.getLoginUser().sysMngrYn;
      }
    }
  };
</script>
