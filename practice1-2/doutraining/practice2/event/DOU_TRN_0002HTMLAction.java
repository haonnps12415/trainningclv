package com.clt.apps.opus.esm.clv.doutraining.practice2.event;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.ConditionVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.INTGVO;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;


public class DOU_TRN_0002HTMLAction extends HTMLActionSupport {


	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		DouTrn0002Event event =  new DouTrn0002Event();		
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setConditionVOs( (ConditionVO[]) getVOs(request, ConditionVO.class, ""));
		}else if(command.isCommand(FormCommand.SEARCH)) {
			event.setConditionVO( (ConditionVO) getVO(request, ConditionVO.class, ""));
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			String codeid = (String) request.getParameter("codeid");
			event.setIntgvo( (INTGVO) getVO(request, INTGVO.class, ""),codeid);
		}
		else if(command.isCommand(FormCommand.MULTI01)) {
			//String codeid = (String) request.getParameter("codeid");
			event.setIntgvos( (INTGVO[]) getVOs(request, INTGVO.class, ""));
		}
		
		return event;
	}

	@Override
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		// TODO Auto-generated method stub
		request.setAttribute("EventResponse", eventResponse);
	}

	@Override
	public void doEnd(ServletRequest request, EventResponse eventResponse) {
		// TODO Auto-generated method stub
		request.setAttribute("EventResponse", eventResponse);
	}



}
