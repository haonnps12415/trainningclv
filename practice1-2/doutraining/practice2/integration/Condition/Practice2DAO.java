package com.clt.apps.opus.esm.clv.doutraining.practice2.integration.Condition;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVOCSQL;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVODSQL;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAOErrMsgVOUSQL;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.detailList.DetailListDAOINTGVOCSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.detailList.DetailListDAOINTGVODSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.detailList.DetailListDAOINTGVOUSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.masterList.MasterListDAOConditionVOCSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.masterList.MasterListDAOConditionVODSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.masterList.MasterListDAOConditionVOUSQL;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.ConditionVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.INTGVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Validate.ValVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


public class Practice2DAO extends DBDAOSupport{
	@SuppressWarnings("unchecked")
	public List<ConditionVO> masterListSearch(ConditionVO conditionVO)throws DAOException {
		DBRowSet dbRowSet = null ;
		List<ConditionVO> list = null;
		
		//query parameter
				Map<String, Object> param = new HashMap<String, Object>();
				//velocity parameter
				Map<String, Object> velParam = new HashMap<String, Object>();
				try{
					if(conditionVO != null){
						Map<String, String> mapVO = conditionVO .getColumnValues();
					
						param.putAll(mapVO);
						velParam.putAll(mapVO);
					}
					dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate)new ConditionRSQLDAOConditionRSQLRSQL(), param, velParam);
					list = (List)RowSetUtil.rowSetToVOs(dbRowSet, ConditionVO .class);
				} catch(SQLException se) {
					log.error(se.getMessage(),se);
					throw new DAOException(new ErrorHandler(se).getMessage());
				} catch(Exception ex) {
					log.error(ex.getMessage(),ex);
					throw new DAOException(new ErrorHandler(ex).getMessage());
				}
		return list;
	}
	public List<INTGVO> detailListSearch(INTGVO intgvo)throws DAOException {
		DBRowSet dbRowSet = null ;
		List<INTGVO> list = null;
		
		//query parameter
				Map<String, Object> param = new HashMap<String, Object>();
				//velocity parameter
				Map<String, Object> velParam = new HashMap<String, Object>();
				
				try{
					param.put("codeid", intgvo.getCodeid());
					dbRowSet = new SQLExecuter("").executeQuery((ISQLTemplate)new DetailListDAOINTGRSQL(), param, velParam);
					list = (List)RowSetUtil.rowSetToVOs(dbRowSet, INTGVO .class);
				} catch(SQLException se) {
					log.error(se.getMessage(),se);
					throw new DAOException(new ErrorHandler(se).getMessage());
				} catch(Exception ex) {
					log.error(ex.getMessage(),ex);
					throw new DAOException(new ErrorHandler(ex).getMessage());
				}
		return list;
	}

	public int[] addMasterListMultiS(List<ConditionVO> conditionVO) throws DAOException,Exception {
		int insCnt[] = null;
		
		try {
			SQLExecuter sqlExe = new SQLExecuter(""); // how can do that ?
/*			ValVO valVO = new ValVO();
			for(int j=0; j < conditionVO.size();j++){
				valVO.setSl(conditionVO);
			}*/
			if(conditionVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new MasterListDAOConditionVOCSQL(), conditionVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to insert No"+ i + " SQL");
					}
						
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	public int[] modifyMasterListMultiS(List<ConditionVO> conditionVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(conditionVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new MasterListDAOConditionVOUSQL(), conditionVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to update No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	public int[] removeMasterListMultiS(List<ConditionVO> conditionVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(conditionVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new MasterListDAOConditionVODSQL(), conditionVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	// detail list
	public int[] addDetailListMultiS(List<INTGVO> intgvos) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter(""); // how can do that ?
			if(intgvos .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new DetailListDAOINTGVOCSQL(), intgvos,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to insert No"+ i + " SQL");
					}
						
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	public int[] modifyDetailListMultiS(List<INTGVO> intgvos) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(intgvos .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new DetailListDAOINTGVOUSQL(), intgvos,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to update No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	public int[] removeDetailListMultiS(List<INTGVO> intgvos) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(intgvos .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new DetailListDAOINTGVODSQL(), intgvos,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
}
