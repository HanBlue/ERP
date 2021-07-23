<template>
  <Card shadow>
    <div style="min-height: 500px;width: 100%;">

      <iframe
        id="iframeId" :src="url" frameborder="0" class="pc iframe"  scrolling="auto">
      </iframe>

    </div>
  </Card>
</template>

<script>

  export default {
    name: "blog",
    components: {},
    data () {
      return {
        url: 'https://hanblue.github.io/2021/0715undefined.html'
      }
    },
    mounted () {
    },
    methods: {
    }
  }
</script>
