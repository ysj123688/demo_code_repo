<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!-- <style>
	.pageFormContent .textInput {float: none;width: 95%;padding:2px 0;}
	table.list td{text-align:center;}
	.pageFormContent select {float: none;width: 95%;padding:1px 0;}
</style> -->
<!-- 查看合同表 此html较为复杂，不推荐修改html代码 -->
<div class="pageContent pageContent_bld" id="contractUpdateId">
	<form method="post" action="${path}/business/contractUpdate?navTabId=Nav_Contract&callbackType=closeCurrent" 
		class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<input type="hidden" value="${privId}" name="privId"/>
		<input type="hidden" value="${contract.id}" name="contract.id"/>
		<div class="pageFormContent" layoutH="250">
			<table cellpadding="0" cellspacing="0" style="width: 98%;line-height: 32px;border-collapse: collapse;" align="center" class="insidePages">
				<tr>
					<td colspan="6" class="currPosition">
						当前位置：合同管理 > <span class="childNode">查看合同</span>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" style="width: 98%;line-height: 32px;border-collapse: collapse;" align="center" class="insidePages">
				<tr>
					<td colspan="6" class="inPagesTitle">
						合同基本信息
					</td>
				</tr>
				<tr>
					<td class="inTdTitle" width="10%">合同类型</td>
					<td class="inTdCon">
						<input name="" maxlength="10" type="text" value="${contract.contract_type.value }" readonly="readonly"/>
						<input name="contract.contract_type" maxlength="10" type="hidden" value="${contract.contract_type }"/>
					</td>
					<td class="inTdTitle" width="10%">合同编号</td>
					<td class="inTdCon">
						<c:if test="${contract.contract_type eq 'SUPPLEMENT' }"><!-- 补播合同 -->
							<input id="supplement_add_contract_no" name="contract.add_contract_no" value="${contract.add_contract_no }" type="text" readonly="readonly" />
						</c:if>
						<input name="" maxlength="40" type="text" class="required" value="${contract.contract_no }" readonly="readonly"/>
					</td>
					<td class="inTdTitle" width="10%">广告商</td>
					<td class="inTdCon">
						<input name=""  type="text"class="required" readonly="readonly" value="${contract.advertiser_name }"  />
					</td>
				</tr>
				<tr>
					<td class="inTdTitle" width="10%">签订时间</td>
					<td class="inTdCon">
						<input type="text" style="width: 80px;float: none;" class="textInput readonly input_validation-failed Wdate1"  
							value="<fmt:formatDate value="${contract.sign_time}" pattern="yyyy-MM-dd"/>" >
					</td>
					<td class="inTdTitle" width="10%">业务员姓名</td>
					<td class="inTdCon">
						<input name="contract.salesman_name" maxlength="10" type="text" value="${contract.salesman_name }" />
					</td>
					<td class="inTdTitle" width="10%">业务员手机号</td>
					<td class="inTdCon">
						<input name="contract.salesman_phone" maxlength="11" type="text" value="${contract.salesman_phone }" class="mobilephone"/>
					</td>
				</tr>
				<tr>
					<td class="inTdTitle" width="10%">所属公司</td>
					<td class="inTdCon">
						<input name="" maxlength="10" type="text" value="${contract.salesman_company }" readonly="readonly" />
					</td>
					<td class="inTdTitle" width="10%">备注</td>
					<td class="inTdCon">
						<input name="contract.remark" maxlength="50" type="text" size="50" value="${contract.remark }" />
					</td>
				</tr>
				
			</table>
			<c:if test="${brands != null }">    
			<c:forEach var="brand" items="${brands }" varStatus="index">
				<div>
					<table cellpadding="0" cellspacing="0" style="width: 98%;line-height: 32px;border-collapse: collapse;" align="center" class="insidePages">
						<tr>
							<td class="inPagesTitle">
								广告发布要求
							</td>
							<td  class="inPagesTitle" >
								<span style="float:right;">
									<a href="javascript:;" onclick="updateBrand(this)" class="btnAdd" title="新增品牌">新增品牌</a>
									<!-- <a href="javascript:;" onclick="deleteBrandRow(this)" class="btnDel" title="删除品牌">删除品牌</a> -->
								</span>
							</td>
						</tr>
		
						<tr>
							<td class="inTdTitle" width="10%">品牌名称</td>
							<td class="inTdCon">
								<input name="brands[${index.index }].brand_name" class="required" maxlength="20" type="text" value="${brand.brand_name }" readonly="readonly" />
								<input type="hidden" name="brands[${index.index }].id" value="${brand.id }">
							</td>
						</tr>
						<tr>
							<td class="inTdTitle" width="10%">媒体平台</td>
							<td class="inTdCon required" >
								<c:forEach var="mediaPattern" items="${mediaPatterns }" varStatus="status" >
									<input type="radio" name="brands[${index.index }].media_platform" <c:if test="${brand.media_platform eq mediaPattern}" >checked="checked"</c:if> value="${mediaPattern }" disabled >${mediaPattern.value }
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="inTdTitle" width="10%">广告形态</td>
							<td class="inTdCon">
								<c:forEach var="adModel" items="${adModels }" varStatus="status">
									<input type="radio" name="brands[${index.index }].ad_model" <c:if test="${adModel eq brand.ad_model}" >checked="checked"</c:if> value="${adModel }" disabled >${adModel.value }
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="inPagesTitle" colspan="6" style="background-color: #ccc;line-height: 20px;">
								投放城市
								<a href="javascript:;" style="float: right; margin: 2px 5px;" onclick="updateAdPosition(this)" class="btnAdd" title="新增广告位">新增</a>
							</td>
						</tr>
						<table class="list" width="98%" >
							<thead>
								<tr>
									<th align="center" width="40">序号</th>
									<th align="center" width="50">片长</th>
									<th align="center" width="75">频次(次/天)</th>
									<th align="center" width="70">预警阀值</th>
									<th align="center" width="90">投放起始日期</th>
									<th align="center" width="90">投放结束日期</th>
									<th align="center" width="40">天数</th>
									<th align="center" width="70">播放总频次</th>
									<th align="center" width="130">省市</th>
									<th align="center" width="60">小区</th>
									<th align="center" width="70">播放设备数</th>
									<th align="center" width="60">素材</th>
									<th align="center" width="80">备注</th>
									<th align="center" width="50">操作</th>
								</tr>
							</thead>
							<tbody id="adPositionsTd">
								<c:forEach var="adPosition" items="${brand.adPositions }" varStatus="status" >
								<tr>
									<td><span>${status.count }</span></td>
									<td><input class="required" name="brands[${index.index }].adPositions[${status.index }].film_length" type="text" size="2" value="${adPosition.film_length }" reg="^\d*(5|0)$" disabled/></td>
									<td><input class="required" name="brands[${index.index }].adPositions[${status.index }].frequency" type="text" size="4" value="${adPosition.frequency }" disabled maxlength="4"/></td>
									<td><input class="required" name="brands[${index.index }].adPositions[${status.index }].warning_threshold" type="text" size="4" value="${adPosition.warning_threshold }" disabled /></td>
									<td><input type="text" style="width: 75px;float: none;" readonly="" name="brands[${index.index }].adPositions[${status.index }].put_start_time"
										class="textInput readonly required input_validation-failed Wdate1" tip="时间不能为空" value="<fmt:formatDate value="${adPosition.put_start_time}" pattern="yyyy-MM-dd"/>" 
										onclick="WdatePicker({skin:'default',readOnly:true,dateFmt:'yyyy-MM-dd',onpicked:checkPutStartTime})" >
									</td>
									<td><input type="text" style="width: 75px;float: none;" name="brands[${index.index }].adPositions[${status.index }].put_stop_time"
										class="textInput readonly required input_validation-failed Wdate1" tip="时间不能为空" value="<fmt:formatDate value="${adPosition.put_stop_time}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({skin:'default',readOnly:true,dateFmt:'yyyy-MM-dd',onpicked:checkPutStopTime})" ></td>
									<td>
										<input name="brands[${index.index }].adPositions[${status.index }].date_qty" type="hidden" value="${adPosition.date_qty }"/>
										<span name="brands[${index.index }].adPositions[${status.index }].date_qty_span">${adPosition.date_qty }</span>
									</td>
									<td>
										<input name="brands[${index.index }].adPositions[${status.index }].all_frequency" type="hidden" value="${adPosition.all_frequency }"/>
										<span name="brands[${index.index }].adPositions[${status.index }].all_frequency_span">${adPosition.all_frequency }</span>
									</td>
									<td>
										<select  name="brands[${index.index }].adPositions[${status.index }].province_id" class="required combox" onchange="updateChangeAdPositionArea(this,'province_id');" disabled="disabled" >
											<option value="0">请选择</option>
											<s:iterator var="areaList" value="#request.areaLists">
												<option value="${areaList.id }"  ${areaList.id eq adPosition.province_id ? 'selected="selected"' : ''}>${areaList.name }</option>
											</s:iterator>
										</select>
										<select class="combox" name="brands[${index.index }].adPositions[${index.index }].city_id" onchange="updateChangeAdPositionArea(this,'city_id');" disabled>
			
				    							<option value="0">${adPosition.city_name }</option>
										</select>
									</td>
									<td>
										<a name="brands[${index.index }].adPositions[${status.index }].community_arr_in" class="btnLook"  href="#"
										onclick='addContractDialog(this,"contract_update_dl","查看小区");' title></a>
										<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].community_arr" value="${adPosition.community_arr }" >
										<span name="brands[${index.index }].adPositions[${status.index }].community_arr_span">${adPosition.community_len }</span>
										<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].href" value="${path}/business/contractCommunityDialog?privId=${privId}&province_id=0&city_id=0&method=check" >
									</td>
									<td>
										<input type="hidden" class="required" name="brands[${index.index }].adPositions[${status.index }].put_eq_qty" type="text" size="2" value="${adPosition.put_eq_qty }"readonly="readonly" />
										<span name="brands[${index.index }].adPositions[${status.index }].put_eq_qty_span">${adPosition.put_eq_qty }</span>
									</td>
									<td>
										<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].material_id" value="123" />
										<span name="brands[${index.index }].adPositions[${status.index }].material_name_span"></span>
									</td>
									<td><input name="brands[${index.index }].adPositions[${status.index }].remark" type="text"  value="${adPosition.remark }" align="center" <c:if test="${adPosition.ad_position_status eq 'SUSPEND' }">disabled="disabled"</c:if> /></td>
									<td>
										<c:if test="${! empty contractUpdate_Suspend}">
											<c:if test="${adPosition.ad_position_status eq 'USED' }">
											<a title="确定中止吗？" href="javascript:;" onclick="suspendAdPosition(${adPosition.ad_position_id},${contractUpdate_Suspend});" class="btnStop">中止</a>
													
											</c:if>
										</c:if>
									</td>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].id" value="${adPosition.ad_position_id }"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].film_length" value="${adPosition.film_length }"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].city_id" value="${adPosition.city_id }"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].ad_position_status" value="${adPosition.ad_position_status}"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].put_start_time.hidden" value="<fmt:formatDate value="${adPosition.put_start_time}" pattern="yyyy-MM-dd"/>"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].put_stop_time.hidden" value="<fmt:formatDate value="${adPosition.put_stop_time}" pattern="yyyy-MM-dd"/>"/>
									<input type="hidden" name="brands[${index.index }].adPositions[${status.index }].ad_position_status.hidden" value="${adPosition.ad_position_status}"/>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</table>
					</br>
					</div>
				</c:forEach>
			</c:if>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script src="${path}/js/contract.js" type="text/javascript" />
