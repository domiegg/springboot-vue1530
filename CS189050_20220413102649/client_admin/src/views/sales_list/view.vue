<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','book_number') || $check_field('add','book_number') || $check_field('set','book_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="图书编号" prop="book_number">
					<el-input id="book_number" v-model="form['book_number']" placeholder="请输入图书编号"
							  v-if="user_group === '管理员' || (form['sales_list_id'] && $check_field('set','book_number')) || (!form['sales_list_id'] && $check_field('add','book_number'))" :disabled="disabledObj['book_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','book_number')">{{form['book_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','book_name') || $check_field('add','book_name') || $check_field('set','book_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="图书名称" prop="book_name">
					<el-input id="book_name" v-model="form['book_name']" placeholder="请输入图书名称"
							  v-if="user_group === '管理员' || (form['sales_list_id'] && $check_field('set','book_name')) || (!form['sales_list_id'] && $check_field('add','book_name'))" :disabled="disabledObj['book_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','book_name')">{{form['book_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sales_volumes') || $check_field('add','sales_volumes') || $check_field('set','sales_volumes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="销售数量" prop="sales_volumes">
					<el-input id="sales_volumes" v-model="form['sales_volumes']" placeholder="请输入销售数量"
							  v-if="user_group === '管理员' || (form['sales_list_id'] && $check_field('set','sales_volumes')) || (!form['sales_list_id'] && $check_field('add','sales_volumes'))" :disabled="disabledObj['sales_volumes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','sales_volumes')">{{form['sales_volumes']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','ranking') || $check_field('add','ranking') || $check_field('set','ranking')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="排名" prop="ranking">
					<el-input id="ranking" v-model="form['ranking']" placeholder="请输入排名"
							  v-if="user_group === '管理员' || (form['sales_list_id'] && $check_field('set','ranking')) || (!form['sales_list_id'] && $check_field('add','ranking'))" :disabled="disabledObj['ranking_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','ranking')">{{form['ranking']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['sales_list_id'] && $check_field('set','remarks')) || (!form['sales_list_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "sales_list_id",
				url_add: "~/api/sales_list/add?",
				url_set: "~/api/sales_list/set?",
				url_get_obj: "~/api/sales_list/get_obj?",
				url_upload: "~/api/sales_list/upload?",

				query: {
					"sales_list_id": 0,
				},

				form: {
					"book_number":'', // 图书编号
					"book_name":'', // 图书名称
					"sales_volumes":'', // 销售数量
					"ranking":'', // 排名
					"remarks":'', // 备注
					"sales_list_id": 0, // ID

				},
				disabledObj:{
					"book_number_isDisabled": false,
					"book_name_isDisabled": false,
					"sales_volumes_isDisabled": false,
					"ranking_isDisabled": false,
					"remarks_isDisabled": false,
				},
			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/sales_list/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/sales_list/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/sales_list/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/sales_list/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/sales_list/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
