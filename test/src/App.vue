<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/>
</template>

<script>
import $ from 'jquery'

export default {
  setup() {
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "hhh",
    //     password: "123",
    //     confirmedPassword: "123",
    //     role:"1",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "admin",
    //     password: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/info/",
    //   type: "get",
    //   headers:{
    //     Authorization:"Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjOThkNTE1YjdjNWE0ZjliOTRlOTgxZTk2MzMzZTQwZiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcxMDk1ODcxMCwiZXhwIjoxNzEyMTY4MzEwfQ.xxekWYveGs5RXD-S2vIVKvwNZfuAq4WIsTZcTb_9HoY",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });

    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "abc",
    //     password: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/club/register/",
    //   type: "post",
    //   data: {
    //     name: "csc",
    //     president_id: "abc",
    //   },
    //   headers:{
    //     Authorization:"Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhNDYxZjE5ODkzZTc0NzZlODg2ZjliMjgwNDc1OGRiMiIsInN1YiI6IjMiLCJpc3MiOiJzZyIsImlhdCI6MTcxMTAyMjQwNywiZXhwIjoxNzEyMjMyMDA3fQ.VI7gyK6ASjWycCuHfLOkl37CwTJZ7zDVgN-DgmK77C8",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });

    //   $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "admin",
    //     password: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });

    $.ajax({
      url: "http://127.0.0.1:3000/club/register/",
      type: "post",
      data: {
        name: "csc",
        president_id: "2",
      },
      headers:{
        Authorization:"Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzODc0MjYzMzEyZDg0YTIxYjVlZTI2MGVlZjlkYjU4MiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcxMTAyMjc0NCwiZXhwIjoxNzEyMjMyMzQ0fQ.ydSeqm1lpuYnjSzQPtJUqa5wb6ItklYtNjiguCts5-o",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
