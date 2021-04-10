<template>
  <v-card>
      <v-flex xs12 sm10>
        <v-tree url="/item/category/list"
                :isEdit="isEdit"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
  </v-card>
</template>

<script>
  import {treeData} from "../../mockDB";
  export default {
    name: "category",
    data() {
      return {
        isEdit:true,
         treeData:[]
      }
    },
    methods: {
      getDataFromServer() { // 从服务的加载数的方法。
        // 发起请求
        this.$http.get("/item/category/list"
        ).then(resp => { // 这里使用箭头函数
          this.treeData = resp.data.items;
        // 完成赋值后，把加载状态赋值为false
      }).catch(() => {
          this.treeData = [];
      })
      },

      handleAdd(node) {
        this.$http({
          method:'post',
          url:'/item/category',
          data:this.$qs.stringify(node)
        }).then(() => {
          this.$message.success("添加成功")
        this.getDataFromServer()
      })

      },
      handleEdit(id, name) {
        const node={
          id:id,
          name:name
        }
        this.$http({
          method:'put',
          url:'/item/category',
          data:this.$qs.stringify(node)
        }).then(() => {
          this.$message.info("修改成功！");
      }).catch(() => {
          this.$message.info("修改失败！");
      });

      },
      handleDelete(id) {
        console.log("delete ... " + id)
        this.$http.delete("/item/category/remove/"+id).then(() =>{
          this.$message.info("删除成功！");
      }).catch(() =>{
          this.$message.info("删除失败！");
      })

      },
      handleClick(node) {
        console.log(node)
      }
    }
  };
</script>

<style scoped>

</style>
