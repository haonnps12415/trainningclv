

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clv.doutraining.practice2.event.DouTrn0002Event"%>
<%
	DouTrn0002Event event = null; //PDTO(Data Transfer Object including Parameters)
	Exception serverException = null; //서버에서 발생한 에러
	String strErrMsg = ""; //에러메세지
	String successFlag = "";
	String codeList = "";
	String pageRows = "100";
	String strSubSysCd		= "";
	String strUsr_id		= "";
	String strUsr_nm		= "";
 	try {
 		SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();
 	
		event = (DouTrn0002Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);
		

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		
		
;	} catch (Exception e) {
		out.println(e.toString());
	} 
%>

<html>
<head>
<title>Error Message Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript">
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if

		loadPage();
	}
</script>
</head>

<body  onLoad="setupPage();">
<form name="form1">
<input type="hidden" name="f_cmd">
<input type="hidden" name="iPage">
<input type="hidden" name="codeid">
<input type="hidden" name="selectedcodes">


<!-- page_title_area(E) -->
	<div class="opus_design_btn">
		 <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button>
		 <button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button>
	 </div>

<div class="wrap_search">
	<div class="opus_design_inquiry wFit">   <!-- no TAB  -->
		<table class="search" border="0" style="width: 100%;">
			<tr class="h23">
						<th width="40">SubSystem</th>
						<td width="120"><input type="text" style="width:100px;" class="input" value="" name="ownr_sub_sys_cd" id="ownr_sub_sys_cd"></td>
						<th width="40">cd ID</th>
						<td><input type="text" style="width:100px;" class="input" value="" name="intg_cd_id" id="intg_cd_id"></td>						
			</tr>
		</table>
	</div>
</div>

<div class="wrap_result">
		

	<div class="opus_design_grid">
		<h3 class="title_design">Master</h3>
	</div>
		<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Add_master" id="btn_Add_master">Row Add</button>
			</div>
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>
	</div>
	<div class="opus_design_inquiry"><table class="line_bluedot"><tr><td></td></tr></table></div>
	

	<div class="opus_design_grid">
		<h3 class="title_design">Detail</h3>		
	</div>
	
		<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Add_detail" id="btn_Add_detail">Row Add</button>
			</div>
			<script language="javascript">ComSheetObject('sheet2');</script>
		</div>
	</div>

</div>

</form>
</body>
</html>