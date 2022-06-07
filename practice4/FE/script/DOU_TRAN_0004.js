/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRAN_0004.js
*@FileTitle : CarrierManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.30 
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
     * @class DOU_TRAN_0004 : DOU_TRAN_0004 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function DOU_TRAN_0004() {
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
 // common global variable
    var sheetObjects = new Array();
    var sheetCnt = 0;
    var comboObjects = new Array();
    var comboCnt = 0;

  //Event handler processing by button click event */
    document.onclick = processButtonClick;
    // Event handler processing by button name */
    function processButtonClick() {
    	/** *** setting sheet object **** */
    	var sheetObject1 = sheetObjects[0];
    	/** **************************************************** */
    	var formObj = document.form;
    	try {
    		var srcName = ComGetEvent("name");
    		if (srcName == null) {
    			return;
    		}

    		switch (srcName) {
    			case "btn_Retrieve":
    				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
    				break;
    			case "btn_New":
    				resetForm(formObj);
    				break;
    			case "btn_Save":
    				doActionIBSheet(sheetObject1, formObj, IBSAVE);
    				break;
    			case "btn_DownExcel":
    				if (sheetObject1.RowCount() < 1) {// no data
    					ComShowCodeMessage("COM132501");
    				} else {
    					sheetObject1.Down2Excel({ HiddenColumn:1,Merge:1});
    				}
    				break;
    			case "btns_calendar1":
    			case "btns_calendar2":
                    var cal=new ComCalendar();
                    if(srcName == "btns_calendar1"){
                        cal.select(formObj.s_cre_dt_fm, 'yyyy-MM-dd');
                    }else{
                        cal.select(formObj.s_cre_dt_to, 'yyyy-MM-dd');
                    }
                    break;
    			case "btn_RowDelete":
    				doActionIBSheet(sheetObject1, formObj, IBDELETE);
    				break;
    			case "btn_RowAdd":
    				doActionIBSheet(sheetObject1, formObj, IBINSERT);
    				break;
    			default :
    				break;
    		} // end switch
    	} catch (e) {
    		if (e == "[object Error]") {
    			ComShowCodeMessage('JOO00001');
    		} else {
    			ComShowMessage(e.message);
    		}
    	}
    }
    /**
     * rest form when click new button
     */
    function resetForm(formObj){
    	formObj.reset();
    	sheetObjects[0].RemoveAll();
    	s_jo_crr_cd.SetSelectIndex(0);
    }
    /**
     * registering IBSheet Object as list adding process for list in case of needing
     * batch processing with other items defining list on the top of source
     */
    function setSheetObject(sheet_obj) {
    	sheetObjects[sheetCnt++] = sheet_obj;
    }
    /**
     * registering IBCombo Object as list param : combo_obj adding process for list
     * in case of needing batch processing with other items defining list on the top
     * of source
     */
    function setComboObject(combo_obj) {
    	comboObjects[comboCnt++] = combo_obj;
    }
    /**
     * initializing sheet implementing onLoad event handler in body tag adding
     * first-served functions after loading screen.
     */
    
    function loadPage() {
    	//generate Grid Layout
    	for (i = 0; i < sheetObjects.length; i++) {
    		ComConfigSheet(sheetObjects[i]);
    		initSheet(sheetObjects[i], i + 1);
    		ComEndConfigSheet(sheetObjects[i]);
    	}
    	////generate dopdownlist data
    	for ( var k = 0; k < comboObjects.length; k++) {
    		initCombo(comboObjects[k], k + 1);
    	}
    	
    	
    	//auto search data after loading finish.
    	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    }
    
    /**
     * setting Combo basic info param : comboObj, comboNo initializing sheet
     */
    function initCombo(comboObj, comboNo) {
    	var formObj = document.form
    	switch (comboNo) {
    	case 1:
    		with (comboObj) {
    			SetMultiSelect(1);
    	        SetDropHeight(200);
    	        ValidChar(2,1);
    		}
    		var comboItems = crrCombo.split("|");    		
    		addComboItem(comboObj, comboItems);
    		comboObj.SetSelectIndex(0);
    		break;
    	}
    }
    /**
     * {addComboItem} Add items array to combo box
     * @param {ibmulticombo}	comboObj    	IBMultiCombo Object
     * @param {array}		comboItems	String array
     */
    function addComboItem(comboObj, comboItems) {
    	for (var i = 0; i < comboItems.length; i++) {
    		comboObj.InsertItem(i, comboItems[i], comboItems[i]);
    	}
    }
    function s_jo_crr_cd_OnCheckClick(comboObj, index, code) {
    	/*if check all then uncheck other*/
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
        //if no check then select ALL
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
    function validateForm(sheetObj, formObj, sAction) {
    	sheetObj.ShowDebugMsg(false);
    	switch (sAction) {
    	case IBSEARCH: // retrieve
    		var creDtFm = form.s_cre_dt_fm;
            var creDtTo = form.s_cre_dt_to;
            if(creDtFm.value != "" && creDtTo.value != "" && creDtFm.value > creDtTo.value) {
                ComShowCodeMessage("JOO00078");
                ComSetFocus(creDtFm);
                return false;
            }
            if(!ComChkObjValid(creDtFm)) {return false;}
            if(!ComChkObjValid(creDtTo)) {return false;}
    		break;
    	}
    	return true;
    }
    /**
     * setting sheet initial values and header param : sheetObj, sheetNo adding case
     * as numbers of counting sheets
     */
    function initSheet(sheetObj, sheetNo) {
    	var cnt = 0;
    	switch (sheetNo) {
    	case 1: // sheet1 init
    		with (sheetObj) {

    			var HeadTitle = "STS|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
    			var headCount = ComCountHeadTitle(HeadTitle);
    			// (headCount, 0, 0, true);
				/**
				 * SetConfig: configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
				 * 	SearchMode: configure search mode (Default: 2)
				 *	LazyLoad mode: Search all data and display search result data on the screen by page as set in Page property value according to the scroll location
				 *	MergeSheet: Merge type (Default=0)
				 *		0 msNone 		 No merge is allowed;
				 * 		5 msHeaderOnly 		Allow merge in the header rows only
				 *	Page: Number of rows to display in one page (Default=20)
				 *	DataRowMerge: whether to allow horizontal merge of the entire row (Default=0)
				 */
    			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 20, DataRowMerge : 0});
				/**
				 * @param Sort Whether to allow sorting by clicking on the header (Default=1)
				 * @param ColMove Whether to allow column movement in header (Default=1)
				 * @param ColResize Whether to allow resizing of column width (Default=1)
				 * @param HeaderCheck Whether the CheckAll in the header is checked (Default=1)
				 */
    			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
				/**
				 * @param:Text String of texts to display in header,adjoined by "|"
				 * @param:Align String How to align header text (Default = "Center")
				 * 
				 */
    			var headers = [ { Text : HeadTitle, Align : "Center" }];
    			InitHeaders(headers, info);
    			/**
				 * @param Type Column data type (Required)
				 * @param Hidden can be used to configure whether to hide a certain data column.
				 * @param Width Column width
				 * @param Align Data alignment
				 * @param ColMerge configures whether to allow vertical merge for data columns. The default value is 1.
				 * @param SaveName can be used to configure the parameter names to use when saving data. If not configured, names will be given sequentially as in C1, C2 and so on.
				 * @param KeyField can be used to configure whether to make a data cell a required field
				 *  If the value is set as 1 and the data cell is empty, a warning message appears when the saving method is called so as to encourage the user to fill the cell.
				 *  The default value is 0.
				 * @param UpdateEdit Whether to allow data editing when transaction is in "Search" state
				 * @param InsertEdit can be used to configure editability of data the transaction status of which is Insert.The default value is 1.
				 * @param EditLen can be used to configure the maximum number of characters to allow for a piece of data.
				 */
    			var cols = [ 
    	             { Type : "Status", Hidden : 1, Width : 50, Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
    	             { Type : "CheckBox", Hidden : 0, Width : 50, Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
    	             { Type : "Text", Hidden : 0, Width : 70, Align : "Center", ColMerge : 0, SaveName : "jo_crr_cd", KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 3 }, 
    	             { Type : "Combo", Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "rlane_cd", KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 5  }, 
    	             { Type : "Text", Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "vndr_seq", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 6 }, 
    	             { Type : "Text", Hidden : 0, Width : 50, Align : "Center", ColMerge : 0, SaveName : "cust_cnt_cd", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 2 }, 
    	             { Type : "Text", Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "cust_seq", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 , EditLen: 6 }, 
    	             { Type : "Popup", Hidden : 0, Width : 70, Align : "Center", ColMerge : 0, SaveName : "trd_cd", KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1	, EditLen: 3 }, 
    	             { Type : "Combo", Hidden : 0, Width : 70, Align : "Center", ColMerge : 0, SaveName : "delt_flg", KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1}, 
    	             { Type : "Text", Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "cre_dt", KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
    	             { Type : "Text", Hidden : 0, Width : 180, Align : "Left", ColMerge : 0, SaveName : "cre_usr_id", KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
    	             { Type : "Text", Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "upd_dt", KeyField : 0, Format : "", UpdateEdit : 0,InsertEdit : 0 }, 
    	             { Type : "Text", Hidden : 0, Width : 180, Align : "Left", ColMerge : 0, SaveName : "upd_usr_id", KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0}
    	             ];

    			InitColumns(cols);
    			SetEditable(1);
    			SetColProperty("jo_crr_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1 });
    			SetColProperty("vndr_seq", { AcceptKeys : "N"});
    			SetColProperty("cust_cnt_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1});
    			SetColProperty("cust_seq", { AcceptKeys : "N"});
    			SetColProperty("trd_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1 });
    			SetColProperty("rlane_cd", { ComboText : rlanCombo, ComboCode : rlanCombo });
    			SetColProperty("delt_flg", { ComboText : "N|Y", ComboCode : "N|Y" });
    			SetWaitImageVisible(0);
    			resizeSheet();
    		}
    		break;
    	}
    }
    function resizeSheet(){
        ComResizeSheet(sheetObjects[0]);
    }
    /**
     * doActionIBSheet
     * 
     * @param sheetObj
     * @param formObj
     * @param sAction
     * @return
     */
    function doActionIBSheet(sheetObj, formObj, sAction) {
    	sheetObj.ShowDebugMsg(false);
    	if (!validateForm(sheetObj, formObj, sAction)) {
    		return false;
    	}
    	switch (sAction) {
    	case IBSEARCH: // retrieve
    		formObj.f_cmd.value = SEARCH;
    		ComOpenWait(true);
    		sheetObj.DoSearch("DOU_TRAN_0004GS.do", FormQueryString(formObj) );
    		break;
    	case IBSAVE: // retrieve
    		formObj.f_cmd.value = MULTI;
    		sheetObj.DoSave("DOU_TRAN_0004GS.do", FormQueryString(formObj));
    		break;
    	case IBINSERT: //Row Add button event
    		sheetObj.DataInsert(-1);
    		break;
    	case IBDELETE: //Row Delete button event
    		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
    			if(sheetObj.GetCellValue(i, "del_chk") == 1){
    				sheetObj.SetRowHidden(i, 1);
    				sheetObj.SetRowStatus(i,"D");
    			}
    		}
    		break;
    	}
    }

    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
    }
    function sheet1_OnChange(sheetObj, Row, Col, Value, OldValue, RaiseFlag){
    	var formObj=document.form ;
    	var colName=sheetObj.ColSaveName(Col);
    	
    	if(Value == ""){
    		return;
    	}
   	
    	if(colName == "jo_crr_cd" || colName == "rlane_cd"){//check duplicate data
    		if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "" && sheetObj.GetCellValue(Row,"rlane_cd") != ""){
    			//check on UI 
    			var headerRowNum = sheetObj.HeaderRows();
    			for(var i = headerRowNum; i <= sheetObj.RowCount(); i++){
    				if(i != Row && sheetObj.GetCellValue(Row,"jo_crr_cd") == sheetObj.GetCellValue(i,"jo_crr_cd")
    						&& sheetObj.GetCellValue(Row,"rlane_cd") == sheetObj.GetCellValue(i,"rlane_cd")){
    					ComShowCodeMessage("JOO00222");
    					sheetObj.SetCellValue(Row, Col,OldValue,0);
    					sheetObj.SelectCell(Row, Col);
    					return;
    				}
    			}
   			//check on Service 
    			formObj.f_cmd.value		= COMMAND01;
    			var sParam				= FormQueryString(formObj) + "&jo_crr_cd=" + sheetObj.GetCellValue(Row,"jo_crr_cd") + "&rlane_cd=" + sheetObj.GetCellValue(Row,"rlane_cd");
    			var sXml 				= sheetObj.GetSearchData("FNS_JOO_0901GS.do", sParam, {sync:1});	
    			var flag				= ComGetEtcData(sXml, "ISEXIST");
    			if(flag == 'Y'){
    				ComShowCodeMessage("JOO00222");
    				sheetObj.SetCellValue(Row, Col,OldValue,0);
    				sheetObj.SelectCell(Row, Col);
    			}
    		}
    	}
    }
    /**
     * {sheet1_OnPopupClick} Event fires when user clicks the pop-up button in the cell that appears when focus is put on the
     * cell, or tries to edit the cell, given that a cell type is either Popup or PopupEdit.
     **/
    function sheet1_OnPopupClick(sheetObj, Row, Col) {
    	switch (sheetObj.ColSaveName(Col)) {
    		case "trd_cd":
    			ComOpenPopup('/opuscntr/COM_COM_0012.do?', 900, 520, 'setTrdCd', '1,0', true, false, Row, Col);
    			break;
    		default:
    			break;
    	}
    }

    // This will be called by ComOpenPopup to handle data from popup and set it into main sheet
    function setTrdCd(aryPopupData, row, col, sheetIdx) {
    	// Select main sheet
    	var sheetObject = sheetObjects[0];
    	// Set cell value
    	sheetObject.SetCellValue(row, col, aryPopupData[0][3]);
    }
	/* 개발자 작업  끝 */