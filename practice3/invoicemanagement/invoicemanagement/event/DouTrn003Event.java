/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrn003Event.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;


/**
 * DOU_TRN_003 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  DOU_TRN_003HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Nguyen Nhat Hao
 * @see DOU_TRN_003HTMLAction 참조
 * @since J2EE 1.6
 */

public class DouTrn003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	InvoiceMgmtVO invoiceMgntVO = null;
	
	/** Table Value Object Multi Data 처리 */
	InvoiceMgmtVO[] invoiceMgntVOs = null;

	/** Table Value Object 조회 조건 및 단건 처리  */
	ComboBoxVO comboBoxVO  = null;
	
	/** Table Value Object Multi Data 처리 */
	ComboBoxVO[] comboBoxVOs  = null;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	DTLVO dtlvo = null;
	
	/** Table Value Object Multi Data 처리 */
	DTLVO[] DTLVOs = null;
	
	public DouTrn003Event(){}
	
	
	public DTLVO getDtlvo() {
		return dtlvo;
	}


	public void setDtlvo(DTLVO dtlvo) {
		this.dtlvo = dtlvo;
	}


	public DTLVO[] getDTLVOs() {
		return DTLVOs;
	}


	public void setDTLVOs(DTLVO[] dTLVOs) {
		DTLVOs = dTLVOs;
	}


	public void setInvoiceMgntVO(InvoiceMgmtVO invoiceMgntVO){
		this. invoiceMgntVO = invoiceMgntVO;
	}

	public void setInvoiceMgntVOS(InvoiceMgmtVO[] invoiceMgntVOs){
		this. invoiceMgntVOs = invoiceMgntVOs;
	}

	public InvoiceMgmtVO getInvoiceMgntVO(){
		return invoiceMgntVO;
	}

	public InvoiceMgmtVO[] getInvoiceMgntVOS(){
		return invoiceMgntVOs;
	}

	public ComboBoxVO getComboBoxVO() {
		return comboBoxVO;
	}

	public void setComboBoxVO(ComboBoxVO comboBoxVO) {
		this.comboBoxVO = comboBoxVO;
	}

	public ComboBoxVO[] getComboBoxVOs() {
		return comboBoxVOs;
	}

	public void setComboBoxVOs(ComboBoxVO[] comboBoxVOs) {
		this.comboBoxVOs = comboBoxVOs;
	}

}