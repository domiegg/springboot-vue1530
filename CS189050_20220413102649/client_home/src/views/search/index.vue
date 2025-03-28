<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 商品搜索结果 -->
			  <list_result_search
				:list="result_goods"
				title="图书商城"
				source_table="goods"
			  ></list_result_search>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="图书资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/book_mall/list', 'get')"
				:list="result_book_mall_author"
				title="图书商城作者"
				source_table="book_mall"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/sales_list/list', 'get')"
				:list="result_sales_list_book_name"
				title="销售榜单图书名称"
				source_table="sales_list"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/message_box/list', 'get')"
				:list="result_message_box_theme"
				title="留言箱主题"
				source_table="message_box"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_goods": [],
	  "result_article": [],
			"result_registered_user_user_name":[],
			"result_book_mall_author":[],
			"result_sales_list_book_name":[],
			"result_message_box_theme":[],
	};
  },
  methods: {
	/**
	 * 获取图书商城
	 */
	get_goods() {
	  this.$get("~/api/goods/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_goods = json.result.list;
		}
	  });
	},
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取author
	 */
	get_book_mall_author(){
		this.$get("~/api/book_mall/get_list?like=0", { page: 1, size: 10, "author": this.query.word }, (json) => {
		  if (json.result) {
			var result_book_mall_author = json.result.list;
			result_book_mall_author.map(o => o.title = o['author'])
	  			this.result_book_mall_author = result_book_mall_author
		 	}
		});
	},
	/**
	 * 获取book_name
	 */
	get_sales_list_book_name(){
		this.$get("~/api/sales_list/get_list?like=0", { page: 1, size: 10, "book_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_sales_list_book_name = json.result.list;
			result_sales_list_book_name.map(o => o.title = o['book_name'])
	  			this.result_sales_list_book_name = result_sales_list_book_name
		 	}
		});
	},
	/**
	 * 获取theme
	 */
	get_message_box_theme(){
		this.$get("~/api/message_box/get_list?like=0", { page: 1, size: 10, "theme": this.query.word }, (json) => {
		  if (json.result) {
			var result_message_box_theme = json.result.list;
			result_message_box_theme.map(o => o.title = o['theme'])
	  			this.result_message_box_theme = result_message_box_theme
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_goods();
	this.get_article();
		this.get_registered_user_user_name();
		this.get_book_mall_author();
		this.get_sales_list_book_name();
		this.get_message_box_theme();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_goods();
	  this.get_article();
	  this.get_registered_user_user_name();
	  this.get_book_mall_author();
	  this.get_sales_list_book_name();
	  this.get_message_box_theme();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
