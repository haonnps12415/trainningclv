/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_003.js
*@FileTitle : InvoiceManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.06 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     */

    /**
     * @extends 
     * @class DOU_TRN_003 : DOU_TRN_003 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function DOU_TRN_003() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
   	/* 개발자 작업	*/
    var btnRetrieveCnt = 0;
    
    var sheetObjects = new Array();
    var sheetCnt = 0;
    var comboObjects = new Array();
    var comboCnt = 0;
    var tabObjects=new Array();
    var tabCnt=0 ;
    var beforetab=1;
    var tabLoad=new Array();
    tabLoad[0]=0;
    tabLoad[1]=0;

    var gJoStlOptCd = "X";
    var ROWMARK="|";
    var FIELDMARK=",";
    
    
    function loadPage() {
        for (i = 0; i < sheetObjects.length; i++) {
            ComConfigSheet(sheetObjects[i]);
            initSheet(sheetObjects[i], i + 1);
            ComEndConfigSheet(sheetObjects[i]);
        }
        for(k=0;k<tabObjects.length;k++){
            initTab(tabObjects[k],k+1);
            tabObjects[k].SetSelectedIndex(0);
        }
       
       initCombo(comboObjects[0], 1);
       initPeriod();
       doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);


    }
    function initPeriod(){
        var formObj=document.form;
        
        //From : -3년, To : +1 월
        var tmpToYm = ComGetNowInfo("ymd","-"); //ComGetDateAdd(formObj.exe_yrmon.value,"M", 1);
        formObj.to_acct_yrmon.value=JooGetDateFormat(tmpToYm,"ym");
        
        //년월 만 하면 -3년이 넘게 설정 되어 동일 월로 설정하기 위해서 2015-07-02 로 Day 를 마춘다.
        var tmpFrYm = ComGetDateAdd(formObj.to_acct_yrmon.value+"-01","M", -2);
        formObj.fr_acct_yrmon.value=JooGetDateFormat(tmpFrYm,"ym"); 
    }
     function JooGetDateFormat(obj, sFormat){
	var sVal = String(getArgValue(obj));
	sVal = sVal.replace(/\/|\-|\.|\:|\ /g,"");
	
    if (ComIsEmpty(sVal)) return "";
    
	var retValue = "";
	switch(sFormat){
	    	
		case "ymd":		//yyyy-mm-dd 10	            
		case "mdy":		//mm-dd-yyyy 10
			retValue = sVal.substring(0,8);
			break;     	            
	    case "ym":		//yyyy-mm 7
	    case "yw":		//yyyy-mm 7
	    case "hms":     //hh:mm:ss 8
			retValue = sVal.substring(0,6);
			break;     	            
	    case "yyyy":     //yyyy 4   	            
	    case "hm":      //hh:mm 5
			retValue = sVal.substring(0,4);
			break;
	    case "ymdhms":     //yyyy-mm-dd hh:mm:ss 19
			retValue = sVal.substring(0,14);
			break;    	            
	    case "ymdhm":     //yyyy-mm-dd hh:mm 16
			retValue = sVal.substring(0,12);
			break;
		}

	retValue = ComGetMaskedValue(retValue,sFormat);
	return retValue;
}
    document.onclick = processButtonClick;
    function processButtonClick() {
    	
    	var formObj = document.form;
    	
    	var doc = document.all;
    	
    	try{
    		var srcName = ComGetEvent("name");
    		if (ComGetBtnDisable(srcName))
                return false;
            switch (srcName) {
                case "btn_Retrieve":               	               	   	
                	 doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
                    break;
	            case "btn_New":
	                initPeriod();
	                sheetObjects[0].RemoveAll();
	                sheetObjects[1].RemoveAll();
	                comboObjects[0].SetSelectIndex(0);
	                comboObjects[1].RemoveAll();
	                comboObjects[2].RemoveAll();
	                break;
	            case "btn_DownExcel":
	                if(sheetObject.RowCount() < 1){//no data
	                    ComShowCodeMessage("COM132501");
	                    return;
	                 }else{
	                     var paramObj = new Object();
	                     paramObj.cols = 10;
	                     //var url = ComJooGetPgmTitle(sheetObject, paramObj);
	                     var varSheetName = "Summary"
	                     if(sheetObject.id=="t2sheet1"){
	                         varSheetName = "Detail";
	                     }
	                     sheetObject.Down2Excel({DownCols : makeHiddenSkipCol(sheetObject), SheetDesign : 1, Merge : 1, SheetName : varSheetName});
	                 }
	                break;           
	
	            case "btn_vvd_from_back":
	                UF_addMonth(formObj.s_fr_acct_yrmon, -1);
	                sheetObject.RemoveAll();
	                break;
	            case "btn_vvd_from_next":
	                if (!GetCheckConditionPeriod()){
	                    ComShowCodeMessage("JOO00078");
	                    return;
	                }
	                UF_addMonth(formObj.s_fr_acct_yrmon, +1);
	                sheetObject.RemoveAll();
	                break;
	            case "btn_vvd_to_back":
	                if (!GetCheckConditionPeriod()){
	                    ComShowCodeMessage("JOO00078");
	                    return;
	                }
	                UF_addMonth(formObj.s_to_acct_yrmon, -1);
	                sheetObject.RemoveAll();
	                break;
	            case "btn_vvd_to_next":
	                UF_addMonth(formObj.s_to_acct_yrmon, +1);
	                sheetObject.RemoveAll();
	                break;
	            
            	} // end switch
        }
    catch (e) {
            if (e == "[object Error]") {
                ComShowMessage(OBJECT_ERROR);
            } else {
                ComShowMessage(e.message);
            }
        }
    }
    function initCombo(comboObj, comboNo) {
    	var formObj = document.form
    	switch (comboNo) {
    	case 1:
    		comboObj.RemoveAll();
    		with (comboObj) {
    			SetMultiSelect(1);
    	        SetDropHeight(200);
    	        ValidChar(2,1);
    		}
    		var comboItems = gjoCrrCdsComboItems.split("|");
    		addComboItem(comboObj, comboItems);
    		comboObj.SetSelectIndex(0);
    		break;
    	case 2:
    		comboObj.RemoveAll();
    		with (comboObj) {
    			SetMultiSelect(0);
    	        SetDropHeight(200);
    	        ValidChar(2,1);
    		}
    		var comboItems = grlaneCdsComboItems.split("|");
    		addComboItem(comboObj, comboItems);
    		comboObj.SetSelectIndex(0);
    		break;
    	case 3:
    		comboObj.RemoveAll();
    		with (comboObj) {
    			SetMultiSelect(0);
    	        SetDropHeight(200);
    	        ValidChar(2,1);
    		}
    		var comboItems = gtrdCdsComboItems.split("|");
    		addComboItem(comboObj, comboItems);
    		comboObj.SetSelectIndex(0);
    		break;
    	}
    }
    function addComboItem(comboObj, comboItems) {
		for (var i=0 ; i < comboItems.length ; i++) {
			var comboItem=comboItems[i].split(",");
/*			//comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			//NYK Modify 2014.10.21
			
*/			if(comboItem.length == 1){
				comboObj.InsertItem(i, comboItem[0], comboItem[0]);
			}else{
				comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			}
			
		}   		
	}
    function doActionIBSheet(sheetObj, formObj, sAction) {
        sheetObj.ShowDebugMsg(false);
        var sheetID=sheetObj.id;
    	switch (sAction) {
    	case IBSEARCH: // retrieve
    		if( !validateForm(sheetObj,formObj,sAction) ){return;}
            if ( sheetID == "t1sheet1"){
                formObj.f_cmd.value=SEARCH;
                var param = FormQueryString(formObj);
                    param += "&" + ComGetPrefixParam(sheetID+"_");
                ComOpenWait(true);
                var sXml=sheetObj.GetSearchData("DOU_TRN_003GS.do", param);
                sheetObj.LoadSearchData(sXml,{Sync:1} );
                ComOpenWait(false);
            }else if ( sheetID == "t2sheet1"){
                formObj.f_cmd.value=SEARCH01;
                var param = FormQueryString(formObj);
                    param += "&" + ComGetPrefixParam(sheetID+"_");
                ComOpenWait(true);
                var sXml=sheetObj.GetSearchData("DOU_TRN_003GS.do", param);
                sheetObj.LoadSearchData(sXml,{Sync:1} );
                ComOpenWait(false);
            }
            break;
    	}
    }

    
    function initSheet(sheetObj, sheetNo) {
        var cnt = 0;
        switch (sheetObj.id) {
            case "t1sheet1": // t1sheet1 init
                with(sheetObj){
                    var HeadTitle1="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider|cust_vndr_cnt_cd|cust_vndr_seq";
                    var HeadTitle2="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name|cust_vndr_cnt_cd|cust_vndr_seq";
                    var headCount=ComCountHeadTitle(HeadTitle1);
                    SetConfig( { SearchMode:0, MergeSheet:7, Page:500, DataRowMerge:0 } );
                    var info    = { Sort:0, ColMove:1, HeaderCheck:1, ColResize:1 };
                    var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
                    InitHeaders(headers, info);
                    var prefix = "t1sheet1_";
                    
                    var cols = [ 
                           {Type:"Status",    Hidden:1, Width:0,    Align:"Center",  ColMerge:0,   SaveName: prefix + "ibflag" },
                           {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName: prefix + "jo_crr_cd",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:70,   Align:"Center",  ColMerge:0,   SaveName: prefix + "rlane_cd",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName: prefix + "inv_no",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:120,  Align:"Center",  ColMerge:0,   SaveName: prefix + "csr_no",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName: prefix + "apro_flg",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName: prefix + "locl_curr_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_rev_act_amt",KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_exp_act_amt",KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "prnr_ref_no",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
                           {Type:"Text",      Hidden:0, Width:200,  Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_eng_nm",KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
                           {Type:"Text",      Hidden:1, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_cnt_cd",KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
                           {Type:"Text",      Hidden:1, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }
                            ];
                     
                    InitColumns(cols);
                    sheetObj.ShowSubSum([{StdCol:3, SumCols:"7|8",  CaptionText:"", CaptionCol:0}]);
                    resizeSheet();
                    SetEditable(1);
                   
                 }
                break;
            case "t2sheet1": // t2sheet1 init
                with(sheetObj){
                    var HeadTitle1="|Partner|Lane|Invoice No|Slip No|Approved|Rev\nExp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
                    var HeadTitle2="|Partner|Lane|Invoice No|Slip No|Approved|Rev\nExp|Item|Curr.|Revenue|Expense|Code|Name";
                    var headCount=ComCountHeadTitle(HeadTitle1);
                    SetConfig( { SearchMode:2, MergeSheet:7, Page:50, DataRowMerge:0 } );
                    var info    = { Sort:0, ColMove:1, HeaderCheck:1, ColResize:1 };
                    var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
                    InitHeaders(headers, info);
                    var prefix = "t2sheet1_";
                    
                    var cols = [ 
                           {Type:"Status",    Hidden:1, Width:0,    Align:"Center",  ColMerge:0,   SaveName: prefix + "ibflag" },
                           {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "jo_crr_cd",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "rlane_cd",           KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName: prefix + "inv_no",             KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:120,  Align:"Center",  ColMerge:0,   SaveName: prefix + "csr_no",             KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:65,   Align:"Center",  ColMerge:0,   SaveName: prefix + "apro_flg",           KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Combo",     Hidden:0, Width:45,   Align:"Center",  ColMerge:0,   SaveName: prefix + "REV_EXP",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "item",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:45,   Align:"Center",  ColMerge:0,   SaveName: prefix + "locl_curr_cd",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_rev_act_amt",	KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_exp_act_amt",	KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName: prefix + "prnr_ref_no",        KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
                           {Type:"Text",      Hidden:0, Width:150,  Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_eng_nm",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }
                            ];
                     
                    InitColumns(cols);
                    sheetObj.ShowSubSum([{StdCol:3, SumCols:"9|10",  CaptionText:"", CaptionCol:0}]);
                    SetEditable(1); 
                    resizeSheet();
                 }
                break;
        }
    }
    function setSheetObject(sheet_obj) {
        sheetObjects[sheetCnt++] = sheet_obj;
    }
    
    function setTabObject(tab_obj){
        tabObjects[tabCnt++]=tab_obj;
    }
    
    function setComboObject(combo_obj) {
    	comboObjects[comboCnt++] = combo_obj;
    }
    
    function initTab(tabObj , tabNo) {
        switch(tabNo) {
            case 1:
               with (tabObj) {
                   var cnt=0 ;
                   InsertItem( "Summary" , "");
                   InsertItem( "Detail" , "");
               }
               
            break;
        }
   }
    function resizeSheet() {
        if(beforetab == 0){
            ComResizeSheet(sheetObjects[0]);
        }else{
            ComResizeSheet(sheetObjects[1]);
        }
    }
    
    function validateForm(sheetObj, formObj, sAction) {
    	    return true;
    }
    
    function t1sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 		 
    	
		   var sRow = sheetObj.FindSubSumRow(3);		  
		   sRow = sRow.split("|");
		
			var cell = []; 
			
			for(x=0 ; x<sRow.length ; x++){
				cell[x] = sheetObj.GetCellValue(sRow[x]-1,6);
			}
			
			
			for(x=0 ; x<sRow.length ; x++){
				sheetObj.SetCellValue(sRow[x], 6,cell[x]);
				
				sheetObj.SetCellFontBold(sRow[x], 6,1);
				sheetObj.SetCellFontBold(sRow[x], 7,1);
				sheetObj.SetCellFontBold(sRow[x], 8,1);
				
				sheetObj.SetRowBackColor(sRow[x], "#F4F4F4");
			}
			
			sumAll(sheetObj, 0);

			
		ComOpenWait(false);		
	}
    
    function t2sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 		 
    	   //Find subsum row
		   var sRow = sheetObj.FindSubSumRow(3);
		   //Convert to array of sRow
		   sRow = sRow.split("|");
		   //Array of cells
			var cell = []

			//Get value from cell above subsum cell into an array			
			for(x=0 ; x<sRow.length ; x++){
				cell[x] = sheetObj.GetCellValue(sRow[x]-1,8);
			}
		   
			//Set value from array to subsum cell
			for(x=0;x<sRow.length;x++){
				sheetObj.SetCellValue(sRow[x], 8,cell[x]);
				
				sheetObj.SetCellFontBold(sRow[x], 8,1);
				sheetObj.SetCellFontBold(sRow[x], 9,1);
				sheetObj.SetCellFontBold(sRow[x], 10,1);
				
				sheetObj.SetRowBackColor(sRow[x], "#F4F4F4");
			}			
			
		ComOpenWait(false);		
	}
    function tab1_OnChange(tabObj , nItem)
    {
        var objs=document.all.item("tabLayer");
        objs[nItem].style.display="Inline";
        objs[beforetab].style.display="none";
        //--------------- important --------------------------//
        objs[beforetab].style.zIndex=objs[nItem].style.zIndex -1 ;
        //------------------------------------------------------//
        beforetab=nItem;
        
//        if (beforetab == 0) {
//            ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
//        }else{
//            ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
//        }
        
        resizeSheet();
    }
    function s_jo_crr_cd_OnCheckClick(comboObj, index, code) {
    	if(index==0) {          
            var bChk=comboObj.GetItemCheck(index);
            if(bChk){
                for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
                    comboObj.SetItemCheck(i,0);
                }
            }
        } else {
            //ALL 이 아닌 다른 Item 체크.
            var bChk=comboObj.GetItemCheck(index);
            if (bChk) {
                comboObj.SetItemCheck(0,0);
            }
        }
        //Combo Item이 전부 Uncheck 일때 자동으로 All 선택이 되도록 한다.
        var checkCnt=0;
        var count = parseInt(comboObj.GetItemCount());
        for(var i = 1 ; i <  count; i++) {
            if(comboObj.GetItemCheck(i)) {
                checkCnt++;
            }
        }
        if(checkCnt == 0) {
            comboObj.SetItemCheck(0,true, null, null, false);
        }
    }
    function s_jo_crr_cd_OnBlur(comboObj, index, code) {
    	
        //Call command to search data for Lane combobox
      var sheetObject = getCurrentSheet();    	
    	var formObj = document.form;        
        sheetObject.RemoveAll();
        
        //Config F_COMMAND
        formObj.f_cmd.value=SEARCH02;
        //Config Param + Prefix sheet
        var param = FormQueryString(formObj);
            param += "&" + ComGetPrefixParam(sheetObject.id+"_");
        //ComOpenWait(true);
        //Get XML
        var sXml=sheetObject.GetSearchData("DOU_TRN_003GS.do", param);
        //Get ETC data to  global var variable
        grlaneCdsComboItems=ComGetEtcData(sXml,"rlanecd");

        initCombo(comboObjects[1], 2);
        //ComOpenWait(false);
        
    }
    function getCurrentSheet(){
        var sheetObj=null;
        if(beforetab == 0){
            sheetObj=sheetObjects[0];
        }else{
            sheetObj=sheetObjects[1];
        }
        
        return sheetObj;
    }
    /*Sum total*/
    function sumAll(sheetObj, offset) {
		if(sheetObj.RowCount()>0){
			var sRow = sheetObj.FindSubSumRow(3);
			sRow = sRow.split("|"); //Find all subsum row	
		}				
		
		const cur = []; 
		const valRe = [];
		const valEx = [];
		
		if (sRow!=null){
			for(i=0;i<sRow.length;i++){
				cur[i]=sheetObj.GetCellValue(sRow[i],6 + offset);
				valRe[i]=sheetObj.GetCellValue(sRow[i],7 + offset);
				valEx[i]=sheetObj.GetCellValue(sRow[i],8 + offset);
			}
			
			var vndRev = 0;
			var vndExp = 0;

			var usdRev = 0;
			var usdExp = 0;
			
			var eurRev = 0;		
			var eurExp = 0;
			
			for(i=0;i<sRow.length;i++){
				if(cur[i]=="VND"){
					vndRev += valRe[i];
					vndExp += valEx[i];
				}
				else if (cur[i]=="USD"){
					usdRev += valRe[i];
					usdExp += valEx[i];
				}
				else if (cur[i]=="EUR"){
					eurRev += valRe[i];
					eurExp += valEx[i];
				}
			}
			
			if(vndRev>0 || vndExp>0){
				var rowVND =sheetObj.DataInsert(-1);
				sumAllSetThreeCellValueAndBackCellColor(sheetObj, rowVND,6 + offset, "VND", vndRev, vndExp, "FFFFEF");
			}
			if(usdRev>0 || usdExp>0){
				var rowUSD =sheetObj.DataInsert(-1);
				sumAllSetThreeCellValueAndBackCellColor(sheetObj, rowUSD,6 + offset, "USD", usdRev, usdExp, "FFFFEF");
			}
			if(eurRev>0 || eurExp>0){
				var rowEUR =sheetObj.DataInsert(-1);
				sumAllSetThreeCellValueAndBackCellColor(sheetObj, rowEUR,6 + offset, "EUR", eurRev, eurExp, "FFFFEF");
			}	
		}				
	}
    /*Set value,set style in last row*/
    function sumAllSetThreeCellValueAndBackCellColor(sheetObj, row, col, curr, rev, exp, color){
	    sheetObj.SetCellValue(row, col, curr );
		sheetObj.SetCellValue(row, col + 1, rev );	
		sheetObj.SetCellValue(row, col + 2, exp );
		
		sheetObj.SetCellFontBold(row, col,1);
		sheetObj.SetCellFontBold(row, col + 1,1);
		sheetObj.SetCellFontBold(row, col + 2,1);
		
		sheetObj.SetRowBackColor(row, color);
   }
    function t1sheet1_OnDblClick(sheetObj, Row, Col) {
    	
    	ComSetObjValue(document.form.codeid, sheetObj.GetCellValue(Row, 3));    	
    	doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
    	tabObjects[0].SetSelectedIndex(1);
    	
    }
	/* 개발자 작업  끝 */