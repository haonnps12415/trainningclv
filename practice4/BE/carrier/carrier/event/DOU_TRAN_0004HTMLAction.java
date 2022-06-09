/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRAN_0004HTMLAction.java
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.carrier.carrier.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.carrier.carrier.vo.CarrierVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.carrier 
 * - Parsing the value of the HTML DOM object sent to the server through the screen as a Java variable.
 * - Set EventResponse to request to send execution result from CarrierJooSC to View (JSP).
 * @author Nguyen Nhat Hao
 * @see carrierEvent 참조
 * @since J2EE 1.6
 */

public class DOU_TRAN_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRAN_0004HTMLAction 
	 */
	public DOU_TRAN_0004HTMLAction() {}

	/**
	 * Cast value and return event.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTran0004Event event = new DouTran0004Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setCarrierVOS((CarrierVO[])getVOs(request, CarrierVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			CarrierVO carrierVO = new CarrierVO();
			carrierVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			carrierVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq", ""));
			carrierVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fm", ""));
			carrierVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			
			event.setCarrierVO(carrierVO);
		}
		else if(command.isCommand(FormCommand.COMMAND01)) {//check duplicate 
			event.setCarrierVO((CarrierVO)getVO(request, CarrierVO .class,""));
		}

		return  event;
	}

	/**
	 * Saving the business scenario execution result value in the attribute of HttpRequest.
	 * Setting the Result Set that transmits the execution result from Service Command to View (JSP) in the request.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving HttpRequest parsing result value in HttpRequest attribute.
	 * Http Request parsing result value set in request.
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}