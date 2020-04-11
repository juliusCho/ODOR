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
import HelloWorld from '@/views/components/HelloWorld.vue'
import axios from 'axios'
import {API} from "@/api";

// @ == src까지의 경로
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
      console.log(API.UserMgmtController.getUserList);
      axios.get(
              API.UserMgmtController.getUserList
      ).then((res) => {
        console.log(res);
        this.userList = res.data;
      });
    }
  }
}
</script>
