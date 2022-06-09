/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTran0004Event.java
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.carrier.carrier.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.carrier.carrier.vo.CarrierVO;


/**
 * @author Nguyen Nhat Hao
 * @see DOU_TRAN_0004HTMLAction 
 * @since J2EE 1.6
 */

public class DouTran0004Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CarrierVO carrierVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CarrierVO[] carrierVOs = null;

	public DouTran0004Event(){}
	
	public void setCarrierVO(CarrierVO carrierVO){
		this. carrierVO = carrierVO;
	}

	public void setCarrierVOS(CarrierVO[] carrierVOs){
		this. carrierVOs = carrierVOs;
	}

	public CarrierVO getCarrierVO(){
		return carrierVO;
	}

	public CarrierVO[] getCarrierVOS(){
		return carrierVOs;
	}

}