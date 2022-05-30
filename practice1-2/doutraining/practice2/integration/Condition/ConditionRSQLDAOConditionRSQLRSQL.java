/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ConditionRSQLDAOConditionRSQLRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.10
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.10 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.practice2.integration.Condition;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nguyen Nhat Hao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ConditionRSQLDAOConditionRSQLRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * DESC Enter..
	  * </pre>
	  */
	public ConditionRSQLDAOConditionRSQLRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("ownr_sub_sys_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("intg_cd_id",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.doutraining.practice2.integration.Condition").append("\n"); 
		query.append("FileName : ConditionRSQLDAOConditionRSQLRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT" ).append("\n"); 
		query.append("A.OWNR_SUB_SYS_CD," ).append("\n"); 
		query.append("A.INTG_CD_ID," ).append("\n"); 
		query.append("REPLACE(A.INTG_CD_NM,'&','&'||'amp;') INTG_CD_NM," ).append("\n"); 
		query.append("REPLACE(A.INTG_CD_DESC,'&','&'||'amp;') INTG_CD_DESC," ).append("\n"); 
		query.append("A.INTG_CD_TP_CD," ).append("\n"); 
		query.append("A.MNG_TBL_NM," ).append("\n"); 
		query.append("A.INTG_CD_LEN," ).append("\n"); 
		query.append("NVL(A.INTG_CD_USE_FLG,'Y') INTG_CD_USE_FLG," ).append("\n"); 
		query.append("A.CRE_USR_ID," ).append("\n"); 
		query.append("TO_CHAR(A.CRE_DT, 'YYYYMMDD') CRE_DT," ).append("\n"); 
		query.append("A.UPD_USR_ID," ).append("\n"); 
		query.append("TO_CHAR(A.UPD_DT, 'YYYYMMDD') UPD_DT" ).append("\n"); 
		query.append("FROM COM_INTG_CD A" ).append("\n"); 
		query.append("WHERE a.ownr_sub_sys_cd is not null and a.intg_cd_id is not null" ).append("\n"); 
		query.append("" ).append("\n"); 
		query.append("AND INTG_CD_TP_CD IN ('G','T')" ).append("\n"); 
		query.append("#if (${ownr_sub_sys_cd} != '') " ).append("\n"); 
		query.append("AND   A.OWNR_SUB_SYS_CD LIKE '%'||@[ownr_sub_sys_cd]||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${intg_cd_id} != '') " ).append("\n"); 
		query.append("and	UPPER(A.INTG_CD_ID) like '%'||UPPER(@[intg_cd_id])|| '%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 

	}
}