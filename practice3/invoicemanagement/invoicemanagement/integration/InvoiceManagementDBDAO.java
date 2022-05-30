/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceManagementDBDAO.java
*@FileTitle : InvoiceManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.06 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.basic.InvoiceManagementBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.ComboBoxVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.DTLVO;
import com.clt.apps.opus.esm.clv.invoicemanagement.invoicemanagement.vo.InvoiceMgmtVO;


/**
 * ALPS InvoiceManagementDBDAO <br>
 * - ALPS-InvoiceManagement system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Nguyen Nhat Hao
 * @see InvoiceManagementBCImpl 참조
 * @since J2EE 1.6
 */
public class InvoiceManagementDBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param InvoiceMgmtVO invoiceMgntVO
	 * @return List<InvoiceMgntVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<InvoiceMgmtVO> InvoiceMgntSearchVO(InvoiceMgmtVO invoiceMgntVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceMgmtVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(invoiceMgntVO != null){
				Map<String, String> mapVO = invoiceMgntVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceManagementDBDAOInvoiceMgntVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceMgmtVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
		/**
		 * [처리대상] 정보를 [행위] 합니다.<br>
		 * 
		 * @param InvoiceMgmtVO invoiceMgntVO
		 * @return List<InvoiceMgntVO>
		 * @exception DAOException
		 */
		 @SuppressWarnings("unchecked")
		public List<DTLVO> InvoiceDTLSearchVO(DTLVO dtlvo) throws DAOException {
			DBRowSet dbRowset = null;
			List<DTLVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(dtlvo != null){
					Map<String, String> mapVO = dtlvo .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceManagementDBDAODTLRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, DTLVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
		/**
		 * [처리대상] 정보를 [행위] 합니다.<br>
		 * 
		 * @param InvoiceMgmtVO invoiceMgntVO
		 * @return List<InvoiceMgntVO>
		 * @exception DAOException
		 */
		 @SuppressWarnings("unchecked")
		public List<ComboBoxVO> comboBoxSearch(ComboBoxVO comboBoxVO) throws DAOException {
			DBRowSet dbRowset = null;
			List<ComboBoxVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(comboBoxVO != null){
					Map<String, String> mapVO = comboBoxVO .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceManagementDBDAOComboBoxRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, ComboBoxVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
		 /**
			 * [처리대상] 정보를 [행위] 합니다.<br>
			 * 
			 * @param InvoiceMgmtVO invoiceMgntVO
			 * @return List<InvoiceMgntVO>
			 * @exception DAOException
			 */
			 @SuppressWarnings("unchecked")
			public List<ComboBoxVO> comboBoxLaneSearch(ComboBoxVO comboBoxVO) throws DAOException {
				DBRowSet dbRowset = null;
				List<ComboBoxVO> list = null;
				//query parameter
				Map<String, Object> param = new HashMap<String, Object>();
				//velocity parameter
				Map<String, Object> velParam = new HashMap<String, Object>();

				try{
					if(comboBoxVO != null){
						Map<String, String> mapVO = comboBoxVO .getColumnValues();
					
						param.putAll(mapVO);
						velParam.putAll(mapVO);
					}
					dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceManagementDBLaneDAOComboBoxRSQL(), param, velParam);
					list = (List)RowSetUtil.rowSetToVOs(dbRowset, ComboBoxVO .class);
				} catch(SQLException se) {
					log.error(se.getMessage(),se);
					throw new DAOException(new ErrorHandler(se).getMessage());
				} catch(Exception ex) {
					log.error(ex.getMessage(),ex);
					throw new DAOException(new ErrorHandler(ex).getMessage());
				}
				return list;
			}
}