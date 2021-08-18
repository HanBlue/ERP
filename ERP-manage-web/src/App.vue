<template>
    <router-view></router-view>
</template>

<script>
export default {
  name:"App",
  created() {
    document.body.removeChild(document.getElementById('Loading'))
  }
}

</script>
