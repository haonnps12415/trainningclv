/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : carrierBC.java
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

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.carrier.carrier.vo.CarrierVO;

/**
 * ALPS-Carrier Business Logic Command Interface<br>
 * @author Nguyen Nhat Hao
 * @since J2EE 1.6
 */

public interface JooCarrierBC {

	/**
	 * This method search list data for Grid.
	 * 
	 * @param CarrierVO	carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchJooCarrierList(CarrierVO carrierVO) throws EventException;
	
	/**
	 * This is a method make actions(save,modify,remove). 
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageJooCarrier(CarrierVO[] carrierVO,SignOnUserAccount account) throws EventException;
	/**
	 * This is a method make combo in sheet
	 * 
	 * @param CarrierVO carrierVO
	 * @exception EventException
	 */
	public abstract List<CarrierVO> searchRLaneCd(CarrierVO carrierVO) throws EventException;
	/**
	 * This is a method make combo search
	 * 
	 * @param CarrierVO carrierVO
	 * @exception EventException
	 */
	public abstract List<CarrierVO> searchCrrCd(CarrierVO carrierVO) throws EventException;
}