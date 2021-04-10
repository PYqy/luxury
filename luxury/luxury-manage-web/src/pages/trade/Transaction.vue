<template>
  <v-data-table
    :headers="headers"
    :items="goodsList"
    :loading="loading"
    hide-actions
    class="elevation-1"
  >
    <template slot="items" slot-scope="props">
      <td class="text-xs-center">{{ props.item.id }}</td>
      <td class="text-xs-center">{{ props.item.title }}</td>
      <td class="text-xs-center">{{props.item.price/100}}.00</td>
      <td class="text-xs-center">{{ props.item.num }}</td>
      <td class="text-xs-center">{{props.item.price*props.item.num/100}}.00</td>
    </template>
  </v-data-table>
</template>

<script>


  export default {
    name: "transaction",
    data() {
      return {

        goodsList: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        headers: [
          {text: 'id', align: 'center', sortable: false, value: 'id'},
          {text: '商品', align: 'center', sortable: false, value: 'title'},
          {text: '单价', align: 'center', sortable: false, value: 'price'},
          {text: '总销量', align: 'center', value: 'num'},
          {text: '总销售额', align: 'center', sortable: false}
        ],


      }
    },
    created() { // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },

    methods: {
      getDataFromServer() { // 从服务的加载数的方法。
        // 发起请求
      this.$http.get("/order/order/transaction")
        .then(resp=> { // 这里使用箭头函数
          console.log(resp.data)
        this.goodsList =resp.data;
        console.log(this.goodsList)
        // 完成赋值后，把加载状态赋值为false
        this.loading = false;
      })

      },

    },

  }
</script>

<style scoped>

</style>
