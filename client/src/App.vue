<template>
  <div id="app" class="app">
    <!--<router-link to="/register">注册 </router-link>
    <router-link to="/login">注册 </router-link>-->
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
    
  }
}
</script>

<style>
.app{
  width: 100%;
  height: 100%;
}

</style>
