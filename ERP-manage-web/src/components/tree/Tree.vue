<template>
  <v-list class="pt-0 pb-0" dense>
    <TreeItem
      class="item" :model="model" v-for="(model, index) in db" :key="index"
      :url="url"
      :isEdit="isEdit"
      :nodes="nodes"
      @handleAdd="handleAdd"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
      @handleClick="handleClick"
    />
  </v-list>
</template>

<script>
  import TreeItem from './TreeItem';

  export default {
    name: "vTree",
    props: {
      url: String,
      isEdit: {
        type: Boolean,
        default: false
      },
      treeData:{
        type:Array
      }
    },
    data() {
      return {
        db: [],
        nodes:{
          opened:null,
          selected:{isSelected:false}
        }
      }
    },
    components: {
      TreeItem
    },
    created() {
      if(this.treeData && this.treeData.length > 0){
        this.db = this.treeData;
        return;
      }
      this.getData();
    },
    methods: {
      getData() {
        this.$http.get("/auth/verify/" )
          .catch(() => {
            // 去登录
            this.$router.go(0);
          })
          .then(resp => {
            //查询权限
            this.user = resp.data;
            this.$http.get("/user/check/"+this.user.id+"/3")
              .then(resp => {
                  if (resp.status === 200){
        this.$http.get(this.url, {params: {pid: 0}}).then(resp => {
          this.db = resp.data;
          this.db.forEach(n => n['path'] = [n.name])
        })
                  }})
              .catch((error) =>{
                if (error.response.status === 401) {
                  this.$message({
                    type: 'error',
                    message: '抱歉，您无权访问'
                  })
                }
              })
          })
      },
      handleAdd(node) {
        this.$emit("handleAdd", this.copyNodeInfo(node));
      },
      handleEdit(id, name) {
        this.$emit("handleEdit", id, name)
      },
      handleDelete(id) {
        this.deleteById(id, this.db);
        this.$emit("handleDelete", id);
      },
      handleClick(node){
        this.$emit("handleClick", this.copyNodeInfo(node))
      },
      // 根据id删除
      deleteById(id, arr) {
        let src = arr || this.db;
        for (let i in src) {
          let d = src[i];
          if (d.id === id) {
            src.splice(i, 1)
            return;
          }
          if (d.children && d.children.length > 0) {
            this.deleteById(id, d.children)
          }
        }
      },
      copyNodeInfo(node){
        const o = {};
        for(let i in node){
          o[i] = node[i];
        }
        return o;
      }
    },
    watch: {}
  }
</script>

<style scoped>
  .item {
    cursor: pointer;
  }
</style>
