<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <div>
      <ul v-for="user in userList" :key="user.userId">
        <li>
          {{user.userId + ': ' + user.nickname}}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import axios from 'axios'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  data: () => (
          {
            userList: []
          }
  ),
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      console.log('#%#%#J#%J');
      axios.get(
              'api/getUserList'
      ).then((res) => {
        window.console.log(res);
        this.userList = res.data;
      });
    }
  }
}
</script>
