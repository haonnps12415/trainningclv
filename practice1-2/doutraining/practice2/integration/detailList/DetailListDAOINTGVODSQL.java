/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DetailListDAOINTGVODSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.03
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.03 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.practice2.integration.detailList ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nguyen Nhat Hao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class DetailListDAOINTGVODSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * DESC Enter..
	  * </pre>
	  */
	public DetailListDAOINTGVODSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("intg_cd_val_ctnt",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("intg_cd_id",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.doutraining.practice2.integration.detailList ").append("\n"); 
		query.append("FileName : DetailListDAOINTGVODSQL").append("\n"); 
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
		query.append("DELETE" ).append("\n"); 
		query.append("FROM COM_INTG_CD_DTL" ).append("\n"); 
		query.append("WHERE INTG_CD_ID = @[intg_cd_id]" ).append("\n"); 
		query.append("AND   INTG_CD_VAL_CTNT = NVL2(@[intg_cd_val_ctnt],@[intg_cd_val_ctnt],INTG_CD_VAL_CTNT)" ).append("\n"); 

	}
}