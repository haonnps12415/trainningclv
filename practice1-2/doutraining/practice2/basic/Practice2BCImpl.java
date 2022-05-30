package com.clt.apps.opus.esm.clv.doutraining.practice2.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.practice2.integration.Condition.Practice2DAO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.ConditionVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.INTGVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class Practice2BCImpl extends BasicCommandSupport implements Practice2BC {
	private transient Practice2DAO dbDao = null;

	public Practice2BCImpl() {
		dbDao = new Practice2DAO();
	}
	
	@Override
	public List<ConditionVO> masterListSearch(ConditionVO conditionVO) throws EventException{
		try {
			return dbDao.masterListSearch(conditionVO);
		}  catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<INTGVO> detailSearch(INTGVO intgvo) throws EventException {
		try {
			return dbDao.detailListSearch(intgvo);
		}  catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	@Override
	public void ConditionVO(ConditionVO[] conditionVOs, SignOnUserAccount account) throws EventException{
		try {
			List<ConditionVO> insertVoList = new ArrayList<ConditionVO>();
			List<ConditionVO> updateVoList = new ArrayList<ConditionVO>();
			List<ConditionVO> deleteVoList = new ArrayList<ConditionVO>();
			for ( int i=0; i<conditionVOs .length; i++ ) {
				if ( conditionVOs[i].getIbflag().equals("I")){
					conditionVOs[i].setCreUsrId(account.getUsr_id());
					conditionVOs[i].setUpdUsrId(account.getUsr_id());
					insertVoList.add(conditionVOs[i]);
				} else if ( conditionVOs[i].getIbflag().equals("U")){
					conditionVOs[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(conditionVOs[i]);
				} else if ( conditionVOs[i].getIbflag().equals("D")){
					deleteVoList.add(conditionVOs[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addMasterListMultiS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyMasterListMultiS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeMasterListMultiS(deleteVoList);
			}
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	// DetailList
	@Override
	public void INTGVO(INTGVO[] intgvos, SignOnUserAccount account) throws EventException{
		try {
			List<INTGVO> insertVoList = new ArrayList<INTGVO>();
			List<INTGVO> updateVoList = new ArrayList<INTGVO>();
			List<INTGVO> deleteVoList = new ArrayList<INTGVO>();
			for ( int i=0; i<intgvos .length; i++ ) {
				if ( intgvos[i].getIbflag().equals("I")){				
					insertVoList.add(intgvos[i]);
				} else if ( intgvos[i].getIbflag().equals("U")){					
					updateVoList.add(intgvos[i]);
				} else if ( intgvos[i].getIbflag().equals("D")){
					deleteVoList.add(intgvos[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addDetailListMultiS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyDetailListMultiS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeDetailListMultiS(deleteVoList);
			}
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}
