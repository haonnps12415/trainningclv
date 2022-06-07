/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : JooCarrierMgmtDBSearchRLaneCdRSQLDAOCarrierVORSQL.java
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.31
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.31 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.carrier.carrier.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nguyen Nhat Hao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class JooCarrierMgmtDBSearchRLaneCdRSQLDAOCarrierVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * DESC Enter..
	  * </pre>
	  */
	public JooCarrierMgmtDBSearchRLaneCdRSQLDAOCarrierVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.carrier.carrier.integration ").append("\n"); 
		query.append("FileName : JooCarrierMgmtDBSearchRLaneCdRSQLDAOCarrierVORSQL").append("\n"); 
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
		query.append("Select VSL_SLAN_CD as rlane_cd" ).append("\n"); 
		query.append("FROM MDM_REV_LANE " ).append("\n"); 
		query.append("WHERE 1 = 1 " ).append("\n"); 
		query.append("     AND DECODE (DELT_FLG, 'Y','D','A') = 'A' " ).append("\n"); 
		query.append("ORDER BY RLANE_CD" ).append("\n"); 

	}
}