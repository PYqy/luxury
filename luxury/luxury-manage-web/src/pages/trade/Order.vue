<template>
  <v-data-table
    :headers="headers"
    :items="orderList"
    hide-actions
    item-key="orderId"
  >
    <template slot="items" slot-scope="props">
      <tr @click="props.expanded = !props.expanded" >
        <td>{{ props.item.orderId}}</td>
        <td class="text-xs-right" style="align-content: center">{{ props.item.createTime }}</td>
        <td class="text-xs-right">
          <span v-if="props.item.status==1">未付款</span>
          <span v-else-if="props.item.status==2">已付款</span>
          <span v-else-if="props.item.status==3">已发货</span>
          <span v-else>已结束</span>


        </td>
        <td class="text-xs-right">{{ props.item.paymentTime }}</td>
        <td class="text-xs-right">{{ props.item.actualPay }}</td>
      </tr>
    </template>
    <template slot="expand" slot-scope="props" >
      <v-card flat>

        <v-data-table
        :headers="headers2"
        :items="props.item.detailshow"
        hide-actions
        item-key="title">

        <template slot="items" slot-scope="props">
        <tr>
        <td>{{ props.item.title}}</td>
        <td class="text-xs-right">{{ props.item.price }}</td>
        <td class="text-xs-right">{{ props.item.num }}</td>
        </tr>
        </template>
        </v-data-table>


      </v-card>
    </template>
  </v-data-table>
</template>

<script>
  export default {
    name: "order",
    data () {
      return {
        headers: [
          {
            text: '订单编号',
            align: 'left',
            sortable: false,
            value: 'orderid'
          },
          { text: '创建时间', value: 'createTime'},
          { text: '支付状态', value: 'status'},
          { text: '付款时间', value: 'paymentTime'},
          { text: '支付金额', value: 'actualPay'},
        ],
        headers2: [
          {
            text: 'title',
            align: 'left',
            sortable: false,
            value: 'title'
          },
          { text: '价格', value: 'price'},
          { text: '购买数量', value: 'num'},

        ],
        orderList:[],
        orderDetails:[],
        orderDetailList:[],
        orderId:Number
      }
    },
    created(){
      this.getDataFromServer();
    },
    methods:{
      getDataFromServer(){
        this.$http.get("/order/order/queryOrders").then(res=>{
          this.orderList=res.data;
          this.orderList.forEach(order=>{
            this.$http.get("/order/order/querysOrderDetails?orderId="+order.orderId).then(resp=>{
              order.detailshow=resp.data;
            })
          })
        })

      },



    },

  }
</script>

<style scoped>

</style>
