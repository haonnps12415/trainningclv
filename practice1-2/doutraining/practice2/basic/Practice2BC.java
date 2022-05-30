package com.clt.apps.opus.esm.clv.doutraining.practice2.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.ConditionVO;
import com.clt.apps.opus.esm.clv.doutraining.practice2.vo.Condition.INTGVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface Practice2BC {

	public abstract List<ConditionVO> masterListSearch(ConditionVO conditionVO) throws EventException;
	public abstract List<INTGVO> detailSearch(INTGVO intgvo) throws EventException;
	public abstract void ConditionVO(ConditionVO[] conditionVO, SignOnUserAccount account)
			throws EventException;
	public abstract void INTGVO(INTGVO[] intgvos, SignOnUserAccount account)
			throws EventException;

}
