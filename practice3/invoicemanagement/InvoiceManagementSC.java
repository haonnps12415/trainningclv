/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceManagementSC.java
*@FileTitle : InvoiceManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.invoicemanagement;

import java.util.List;

import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.basic.InvoiceManagementBC;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.basic.InvoiceManagementBCImpl;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.event.DouTrn003Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;


/**
 * ALPS-InvoiceManagement Business Logic ServiceCommand - ALPS-InvoiceManagement 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Nguyen Nhat Hao
 * @see InvoiceManagementDBDAO
 * @since J2EE 1.6
 */

public class InvoiceManagementSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * InvoiceManagement system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("InvoiceManagementSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * InvoiceManagement system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("InvoiceManagementSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-InvoiceManagement system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("DouTrn003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = comboBoxSearchVO(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = InvoiceMgntSearchVO(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = InvoiceDTLSearchVO(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = comboBoxLaneSearchVO(e);
			}
		}
		return eventResponse;
	}
	/**
	 * DOU_TRN_003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse InvoiceMgntSearchVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn003Event event = (DouTrn003Event)e;
		InvoiceManagementBC command = new InvoiceManagementBCImpl();

		try{
			List<InvoiceMgmtVO> list = command.InvoiceMgntSearchVO(event.getInvoiceMgntVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}/**
	 * DOU_TRN_003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse InvoiceDTLSearchVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn003Event event = (DouTrn003Event)e;
		InvoiceManagementBC command = new InvoiceManagementBCImpl();

		try{
			List<DTLVO> list = command.InvoiceDTLSearchVO(event.getDtlvo());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * DOU_TRN_003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse comboBoxSearchVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn003Event event = (DouTrn003Event)e;
		InvoiceManagementBC command = new InvoiceManagementBCImpl();

		try{
			List<ComboBoxVO> list = command.comboBoxSearch(event.getComboBoxVO());
			StringBuilder crrCdsStrBuilder = new StringBuilder();
			if(list != null && list.size() > 0){
				for(int i =0; i < list.size(); i++){
					crrCdsStrBuilder.append(list.get(i).getJoCrrCd());
					if (i < list.size() -1){
						crrCdsStrBuilder.append("|");
					}
				}
			}
			eventResponse.setETCData("crrCds", crrCdsStrBuilder.toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * DOU_TRN_003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse comboBoxLaneSearchVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn003Event event = (DouTrn003Event)e;
		InvoiceManagementBC command = new InvoiceManagementBCImpl();

		try{
			List<ComboBoxVO> list = command.comboBoxLaneSearch(event.getComboBoxVO());
			StringBuilder crrCdsStrBuilder = new StringBuilder();
			if(list != null && list.size() > 0){
				for(int i =0; i < list.size(); i++){
					crrCdsStrBuilder.append(list.get(i).getRlaneCd());
					if (i < list.size() -1){
						crrCdsStrBuilder.append("|");
					}
				}
			}
			eventResponse.setETCData("rlanecd", crrCdsStrBuilder.toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
}