/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceManagementBCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.integration.InvoiceManagementDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;

/**
 * ALPS-InvoiceManagement Business Logic Command Interface<br>
 * - ALPS-InvoiceManagement에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nguyen Nhat Hao
 * @since J2EE 1.6
 */
public class InvoiceManagementBCImpl extends BasicCommandSupport implements InvoiceManagementBC {

	// Database Access Object
	private transient InvoiceManagementDBDAO dbDao = null;

	/**
	 * InvoiceManagementBCImpl 객체 생성<br>
	 * InvoiceManagementDBDAO를 생성한다.<br>
	 */
	public InvoiceManagementBCImpl() {
		dbDao = new InvoiceManagementDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param InvoiceMgmtVO invoiceMgntVO
	 * @return List<InvoiceMgntVO>
	 * @exception EventException
	 */
	public List<InvoiceMgmtVO> InvoiceMgntSearchVO(InvoiceMgmtVO invoiceMgntVO) throws EventException {
		try {
			return dbDao.InvoiceMgntSearchVO(invoiceMgntVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param InvoiceMgmtVO invoiceMgntVO
	 * @return List<InvoiceMgntVO>
	 * @exception EventException
	 */
	@Override
	public List<DTLVO> InvoiceDTLSearchVO(DTLVO dtlvo) throws EventException {
		try {
			return dbDao.InvoiceDTLSearchVO(dtlvo);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ComboBoxVO comboBoxVO
	 * @return List<ComboBoxVO>
	 * @exception EventException
	 */
	@Override
	public List<ComboBoxVO> comboBoxSearch(ComboBoxVO comboBoxVO) throws EventException {
		try {
			return dbDao.comboBoxSearch(comboBoxVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ComboBoxVO comboBoxVO
	 * @return List<ComboBoxVO>
	 * @exception EventException
	 */
	@Override
	public List<ComboBoxVO> comboBoxLaneSearch(ComboBoxVO comboBoxVO) throws EventException {
		try {
			return dbDao.comboBoxLaneSearch(comboBoxVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
}