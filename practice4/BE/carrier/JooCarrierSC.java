/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : carrierSC.java
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.carrier;

import java.util.List;

import com.clt.apps.opus.esm.clv.carrier.carrier.basic.JooCarrierBC;
import com.clt.apps.opus.esm.clv.carrier.carrier.basic.JooCarrierBCImpl;
import com.clt.apps.opus.esm.clv.carrier.carrier.event.DouTran0004Event;
import com.clt.apps.opus.esm.clv.carrier.carrier.integration.JooCarrierDBDAO;
import com.clt.apps.opus.esm.clv.carrier.carrier.vo.CarrierVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-carrier Business Logic ServiceCommand - ALPS-CarrierJoo process business transactions.
 * 
 * @author Nguyen Nhat Hao
 * @see JooCarrierDBDAO
 * @since J2EE 1.6
 */

public class JooCarrierSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * CarrierJoo Start the work scenario.
	 * Creating related internal objects when calling a business scenario.
	 */
	public void doStart() {
		log.debug("carrierSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * CarrierJoo system Closing the work scenario
	 * Release related internal objects at the end of the business scenario
	 */
	public void doEnd() {
		log.debug("carrierSC 종료");
	}

	/**
	 * This is a method that divides tasks by different actions
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("DouTran0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchJooCarrierList(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageJooCarrier(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = checkDuplicate(e);
			}
		}
		return eventResponse;
	}
	/**
	 * this method for checking duplicate data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse checkDuplicate(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
				GeneralEventResponse eventResponse = new GeneralEventResponse();
				DouTran0004Event event = (DouTran0004Event)e;
				JooCarrierBC command = new JooCarrierBCImpl();
				
				try{
					List<CarrierVO> list = command.searchJooCarrierList(event.getCarrierVO());
					eventResponse.setETCData("ISEXIST", list.size() > 0 ? "Y" : "N");
				}catch(EventException ex){
					throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				}catch(Exception ex){
					throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				}	
				return eventResponse;
	}

	/**
	 * This method for initial data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse initData(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTran0004Event event = (DouTran0004Event)e;
		JooCarrierBC command = new JooCarrierBCImpl();
		
		try {
			List<CarrierVO> rlaneCds = command.searchRLaneCd(event.getCarrierVO());
			StringBuilder rlaneCdsBuilder = new StringBuilder();
			if(null != rlaneCds && rlaneCds.size() > 0){
				for(int i =0; i < rlaneCds.size(); i++){
					rlaneCdsBuilder.append(rlaneCds.get(i).getRlaneCd());
					if (i < rlaneCds.size() - 1){
						rlaneCdsBuilder.append("|");
					}	
				}
			}
			List<CarrierVO> crrCds = command.searchCrrCd(event.getCarrierVO());
			StringBuilder crrCdsBuilder = new StringBuilder();
			if(null != crrCds && crrCds.size() > 0){
				for(int i =0; i < crrCds.size(); i++){
					crrCdsBuilder.append(crrCds.get(i).getJoCrrCd());
					if (i < crrCds.size() - 1){
						crrCdsBuilder.append("|");
					}	
				}
			}
			eventResponse.setETCData("rlaneCds", rlaneCdsBuilder.toString());
			eventResponse.setETCData("crrCds", crrCdsBuilder.toString());

		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * This is a method search a list data on Grid.
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchJooCarrierList(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTran0004Event event = (DouTran0004Event)e;
		JooCarrierBC command = new JooCarrierBCImpl();

		try{
			List<CarrierVO> list = command.searchJooCarrierList(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 *This is a method make actions(save,modify,remove). 
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageJooCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTran0004Event event = (DouTran0004Event)e;
		JooCarrierBC command = new JooCarrierBCImpl();
		try{
			begin();
			command.manageJooCarrier(event.getCarrierVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("BKG06071").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}