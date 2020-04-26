<template>
  <v-app>
    <v-app-bar
      app
      dark
    >
      <v-btn @click="goTo('Home')"
             style="font-size: 30px;" text>ODOR</v-btn>

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <MenuWithChildren
        :menu="forum"
        menuName="forum"
        keyCol="forumKey"
        labelCol="forumName"
        @goTo="goTo"
      />

      <v-spacer/>

      <v-btn @click="goTo('Perfume')" small text>PERFUME</v-btn>

      <v-spacer/>

      <v-btn @click="goTo('About')" small text>ABOUT</v-btn>

      <v-spacer/>

      <MenuWithChildren
          :menu="system"
          menuName="system"
          keyCol="forumKey"
          labelCol="forumName"
          @goTo="goTo"
      />

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
    // :자식객체 props 변수
    // @:이벤트명 / 자식템플릿에서 부모템플릿에 있는 메소드 접근
    // ref = $refs 쓸때 자식객체 지정
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
  import MenuWithChildren from '@/views/components/MenuWithChildren';

  export default {
    name: 'App',
    components: {
      Router,
      MenuWithChildren
    },
    created: function() { // == created() {
      this.routing = 'Home';
      this.getForumList();
    },
    watch: { // props, data 사용할 수 있는 객체
      routing() { // data에서 선언한 routing을 function으로 사용
        // rounting 데이터가 바뀌었을 때 아래 함수 실행
        this.$router.push(this.routing).catch(() => {});
      },
      '$route'(to, from) { // vue 기본 객체 ($ 사인 붙은 애들), 싱글쿼테이션으로 감쌀것.
        if (from.name === to.name) { // 비교할때
          return;
        }
        this.$refs.router.goTo(to.name); // 부모가 자식객체에 있는 메소드를 실행시키고 싶을때      loggedInBoo() {
        this.sysMngrYn = this.sysMngrCheck();
        this.getSystemList();
      }
    },
    data() {
      return {
        routing: '',
        loggedInBoo: false,
        sysMngrYn: false,
        forumKey: 0,
        forum: [],
        system: []
      }
    },
    props: { // 해당 선언 규칙 !
      renee: {  // prop 에 선언된 데이터는 바꿀수 없음
        type: Number, // 부모객체가 자식에게 값을 넣어줄 수 있음
        default: 0,
        required: false
      }
    },
    methods: {
      // get system list
      async getSystemList() {
        if (!this.loggedInBoo || !this.sysMngrYn) {
          return;
        }
        if (this.system.length > 0) {
          return;
        }
        await axios.post(
                API.ForumMgmtController.getSystemList,
                TMP_SESSION.getId()
        ).then(res => {
          if (res.data.length > 0) {
            this.system = res.data;
          }
        });
      },
      // get forum list
      getForumList() {
        if (this.forum.length > 0) {
          return;
        }
        axios.get(API.ForumMgmtController.getForumList)
        .then(res => {
          if (res.data.length > 0) {
            this.forum = res.data;
          }
        });
      },
      // routing
      goTo(page, fKey) {
        if (SCRIPT_VALIDATOR.nullCheck(fKey)) {
          this.forumKey = fKey;
        }
        this.routing = page;
      },
      getForumKey() {
        return this.forumKey;
      },
      // ************************************************************
      // Login Icon Clicked + Session check
      // ************************************************************
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
      // ************************************************************
      // Login Icon Clicked + Session check
      // ************************************************************
    }
  };
</script>
