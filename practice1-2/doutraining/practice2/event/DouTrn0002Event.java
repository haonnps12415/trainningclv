package com.clt.apps.opus.esm.clv.doutraining.practice2.event;

import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.ConditionVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.INTGVO;
import com.clt.framework.support.layer.event.EventSupport;

public class DouTrn0002Event extends EventSupport{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConditionVO conditionVO = null;
	 
	 ConditionVO[] conditionVOs = null;
	 
	 INTGVO intgvo = null;
	 INTGVO[] intgvos = null;
	 
	 

	public DouTrn0002Event() {
		super();
	}
	
	

	public INTGVO getIntgvo() {
		return intgvo;
	}



	public void setIntgvo(INTGVO intgvo,String codeid) {
		this.intgvo = intgvo;
		intgvo.setCodeid(codeid);
	}



	public INTGVO[] getIntgvos() {
		return intgvos;
	}



	public void setIntgvos(INTGVO[] intgvos) {
		this.intgvos = intgvos;
	}



	public ConditionVO[] getConditionVOs() {
		return conditionVOs;
	}



	public void setConditionVOs(ConditionVO[] conditionVOs) {
		this.conditionVOs = conditionVOs;
	}



	public ConditionVO getConditionVO() {
		return conditionVO;
	}

	public void setConditionVO(ConditionVO conditionVO) {
		this.conditionVO = conditionVO;
	}
	 
	 
	 
}
