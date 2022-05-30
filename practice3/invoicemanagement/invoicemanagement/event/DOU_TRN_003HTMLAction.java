/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_003HTMLAction.java
*@FileTitle : InvoiceManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;
import com.rsa.cryptoj.c.dt;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.invoicemanagement 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 InvoiceManagementSC로 실행요청<br>
 * - InvoiceManagementSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Nguyen Nhat Hao
 * @see InvoiceManagementEvent 참조
 * @since J2EE 1.6
 */

public class DOU_TRN_003HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_003HTMLAction 객체를 생성
	 */
	public DOU_TRN_003HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 InvoiceManagementEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTrn003Event event = new DouTrn003Event();
		if (command.isCommand(FormCommand.DEFAULT)) {
			event.setComboBoxVO((ComboBoxVO)getVO(request, ComboBoxVO .class));
		}else if(command.isCommand(FormCommand.SEARCH)) {
			InvoiceMgmtVO invoiceMgmtVO = new InvoiceMgmtVO();
			
			String fromDate = JSPUtil.getParameter(request, "fr_acct_yrmon");
			String toDate = JSPUtil.getParameter(request, "to_acct_yrmon");
			
			invoiceMgmtVO.setJoCrrCd(JSPUtil.getParameter(request, JSPUtil.getParameter(request, "s_jo_crr_cd")));
			invoiceMgmtVO.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd"));
			invoiceMgmtVO.setFrAcctYrmon(fromDate);
			invoiceMgmtVO.setToAcctYrmon(toDate);
			
			event.setInvoiceMgntVO(invoiceMgmtVO);
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			DTLVO dtlvo = new DTLVO();
			dtlvo.setInvNo(JSPUtil.getParameter(request, "codeid"));
			event.setDtlvo(dtlvo);
		}else if(command.isCommand(FormCommand.SEARCH02)) {
			ComboBoxVO comboBoxVO = new ComboBoxVO();
			comboBoxVO.setRlaneCd(JSPUtil.getParameter(request, "s_jo_crr_cd"));
			event.setComboBoxVO(comboBoxVO);
		}

		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}