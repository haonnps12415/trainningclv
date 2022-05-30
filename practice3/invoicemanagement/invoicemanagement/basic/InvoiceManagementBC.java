/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceManagementBC.java
*@FileTitle : InvoiceManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.basic;

import java.util.List;

import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;

/**
 * ALPS-Invoicemanagement Business Logic Command Interface<br>
 * - ALPS-Invoicemanagement에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nguyen Nhat Hao
 * @since J2EE 1.6
 */

public interface InvoiceManagementBC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param InvoiceMgmtVO	invoiceMgntVO
	 * @return List<InvoiceMgntVO>
	 * @exception EventException
	 */
	public List<InvoiceMgmtVO> InvoiceMgntSearchVO(InvoiceMgmtVO invoiceMgntVO) throws EventException;

	public abstract List<ComboBoxVO> comboBoxSearch(ComboBoxVO comboBoxVO) throws EventException;
	public abstract List<ComboBoxVO> comboBoxLaneSearch(ComboBoxVO comboBoxVO) throws EventException;

	public abstract List<DTLVO> InvoiceDTLSearchVO(DTLVO dtlvo)
			throws EventException;

}