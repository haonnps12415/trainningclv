/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : carrierBCImpl.java
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.carrier.carrier.basic;

import java.util.ArrayList;
import java.util.List;
import com.clt.apps.opus.esm.clv.carrier.carrier.integration.carrierDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.carrier.carrier.vo.CarrierVO;
/**
 * ALPS-carrier Business Logic Command Interface<br>
 * - ALPS-carrier에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nguyen Nhat Hao
 * @since J2EE 1.6
 */
public class carrierBCImpl extends BasicCommandSupport implements carrierBC {

	// Database Access Object
	private transient carrierDBDAO dbDao = null;

	/**
	 * carrierBCImpl 객체 생성<br>
	 * carrierDBDAO를 생성한다.<br>
	 */
	public carrierBCImpl() {
		dbDao = new carrierDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> CarrierVOSearchVO(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.CarrierVOSearchVO(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierVO> searchRLaneCd(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.searchRLaneCd(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierVO> searchCrrCd(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.searchCrrCd(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void CarrierVOMutiVO(CarrierVO[] carrierVO, SignOnUserAccount account) throws EventException{
		try {
			List<CarrierVO> insertVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> updateVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> deleteVoList = new ArrayList<CarrierVO>();
			for ( int i=0; i<carrierVO .length; i++ ) {
				if ( carrierVO[i].getIbflag().equals("I")){
					carrierVO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(carrierVO[i]);
				} else if ( carrierVO[i].getIbflag().equals("U")){
					carrierVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierVO[i]);
				} else if ( carrierVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addCarrierVOMutiVOS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyCarrierVOMutiVOS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeCarrierVOMutiVOS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}