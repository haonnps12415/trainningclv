<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRAN_0004.jsp
*@FileTitle : CarrierManagement
*Open Issues : Comment Code
*Change history : No
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
*@Author :Nguyen Nhat Hao 
* 2022.05.30 
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clv.carrier.carrier.event.DouTran0004Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	DouTran0004Event  event = null;					
	Exception serverException   = null;			
	String strErrMsg = "";						
	int rowCount	 = 0;						

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	Logger log = Logger.getLogger("com.clt.apps.carrier.carrier");
	
		String rlaneCds			= "";
		String crrCds			= "";
	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (DouTran0004Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		rlaneCds = eventResponse.getETCData("rlaneCds");
		crrCds = eventResponse.getETCData("crrCds");
		
	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<script language="javascript">
	var rlanCombo = "<%=rlaneCds%>";
	var crrCombo = "All|<%=crrCds%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>

<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
	<div class="page_title_area clear">
		<div class="opus_design_btn"><!-- 
			--><button class="btn_accent" name="btn_Retrieve" id="btn_Retrieve" type="button">Retrieve</button><!-- 
		    --><button class="btn_normal" name="btn_New" id="btn_New" type="button">New</button><!--
			--><button class="btn_normal" name="btn_Save" id="btn_Save" type="button">Save</button><!--
			--><button class="btn_normal" name="btn_DownExcel" id=btn_DownExcel type="button">Down Excel</button>
		</div>
	</div>
	<div class="wrap_search">
		<div class="opus_design_inquiry wFit">
			<table>
				<colgroup>
					<col width="70" />				
					<col width="100" />						
					<col width="70" />	
					<col width="100" />				
					<col width="70" />					
					<col width="*" />				
			   </colgroup> 
			   <tbody>
			   		<tr>
						<th>Carrier</th>
						<td><script type="text/javascript">ComComboObject('s_jo_crr_cd',1,80, 1, 0, 0);</script></td>
						<th>Vendor</th>
						<td><input type="text" style="width:60px;" name="s_vndr_seq" id="s_vndr_seq"onblur="validateVendor(this)" onKeyPress="ComKeyOnlyNumber(this)" dataformat="num" maxlength="6"/></td>
						<th>Create Date</th>
						<td>
							<input type="text" style="width:78px;text-align:center;" caption="Create Date From" name="s_cre_dt_fm" !cofield="s_cre_dt_to" dataformat="ymd" maxLength="10" minlength="8"><!--
						 --><button type="button" class="calendar ir" name="btns_calendar1" id="btns_calendar1" tabindex="-1"></button>~
						    <input type="text" style="width:78px;text-align:center;" caption="Create Date To" name="s_cre_dt_to" !cofield="s_cre_dt_fm" dataformat="ymd" maxLength="10" minlength="8"><!-- 
						 --><button type="button" class="calendar ir" name="btns_calendar2" id="btns_calendar2" tabindex="-1"></button>
						</td>
					</tr> 
			   </tbody>
			</table>
		</div>
	</div>
	<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button class="btn_normal" name="btn_RowDelete" id="btn_RowDelete" type="button">Row Delete</button>
				<button class="btn_normal" name="btn_RowAdd" id="btn_RowAdd" type="button">Row Add</button>
			</div>
			<script type="text/javascript">ComSheetObject('sheet1');</script>		
		</div>
	</div>
</form>
