/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0001.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.15
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.15 
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
     * @class DOU_TRN_0001 : DOU_TRN_0001 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function DOU_TRN_0001() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
	var tabObjects=new Array();
	var tabCnt=0 ;
	var beforetab=1;
	var sheetObjects=new Array();
	var sheetCnt=0;
	var rowcount=0;
	document.onclick=processButtonClick;
    function processButtonClick(){

        var sheetObject1=sheetObjects[0];

        var formObject=document.form;
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
           	case "btn_Add":
           		doActionIBSheet(sheetObject1,formObject,IBINSERT);
           		break;
           	case "btn_Retrieve":
           		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
           		break;
           	case "btn_Save":
           		doActionIBSheet(sheetObject1,formObject,IBSAVE);
           		break;
           	case "btn_DownExcel":
           		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
           		break;
           } // end switch
   	}catch(e) {
   		if( e == "[object Error]") {
   			ComShowMessage(OBJECT_ERROR);
   		} else {
   			ComShowMessage(e);
   		}
   	}
   }
    
    function setSheetObject(sheet_obj){
    	console.log(sheet_obj);
        sheetObjects[sheetCnt++]=sheet_obj;
     }
    
    function loadPage() {
		for(i=0;i<sheetObjects.length;i++){
			
			ComConfigSheet (sheetObjects[i]);
			initSheet(sheetObjects[i],i+1);
	
			console.log(document.form);
			ComEndConfigSheet(sheetObjects[i]);
			doActionIBSheet(sheetObjects[i],document.form,IBSEARCH)
		}
	}
    
    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetID) {
            case "sheet1":
            	with(sheetObj){
                var HeadTitle="ibflag|Del|Msg Cd|Msg Type|Msg level|Message|Description" ;
                var headCount=ComCountHeadTitle(HeadTitle);

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:0, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
	                     {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	                     {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_tp_cd",   KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_lvl_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" },
	                     {Type:"Text",      Hidden:0, Width:400,  Align:"Left",    ColMerge:0,   SaveName:"err_msg",     KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, MultiLineText:1 },
	                     {Type:"Text",      Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"err_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                  
                InitColumns(cols);
                SetWaitImageVisible(0);
                resizeSheet();
                }
            break;
        }
    }
    function resizeSheet(){
      	         ComResizeSheet(sheetObjects[0]);
      }
    function validateForm(sheetObj,formObj,sAction){
        with(formObj){
//            if (!isNumber(formObj.iPage)) {
//                return false;
//            }
        }
        return true;
    }
    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
			case IBSEARCH:      
				if(!validateForm(sheetObj,formObj,sAction)) return
                //조회처리
				ComOpenWait(true);
				formObj.f_cmd.value=SEARCH;
 				sheetObj.DoSearch("DOU_TRN_0001GS.do", FormQueryString(formObj) );
				break;
			case IBSAVE:        
				if(!validateForm(sheetObj,formObj,sAction))return;
                //저장처리
            	formObj.f_cmd.value=MULTI;
                sheetObj.DoSave("DOU_TRN_0001GS.do", FormQueryString(formObj));
				break;
			case IBINSERT:     
				rowcount=sheetObj.RowCount();
				row=sheetObj.DataInsert();
				break;
			case IBDOWNEXCEL:	
				if(sheetObj.RowCount() < 1){
					ComShowCodeMessage("COM132502");
				}else{
					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
				}
				break;
        }
    }

    function sheet1_OnChange(sheetObj,Row,Col){
   	 if(Col == 2){
			var code=sheetObj.GetCellValue(Row, Col);
   	    for(var int=1; int < sheetObj.RowCount(); int++) {
			var orlcode=sheetObj.GetCellValue(int, Col);
			/* null 인 경우와 자기 자신은 비교할 필요가 없음 */
				if(code != '' && int != Row && code == orlcode){
   				 //ComShowMessage("동일한 Message Code가 존재합니다.");
   				 ComShowCodeMessage('COM131302',code);
   				 sheetObj.SetCellValue(Row, Col,"");
   				 return;
   			 }
   		 }
   	 }
    }
    function sheet1_OnEditValidation(sheetObj, Row, Col, Value) {
		switch(Col) {
		case 2:
			var pattern = /^[A-Z]{3}[0-9]{5}$/;
			if(!pattern.test(Value)){
				ComShowCodeMessage('COM12117',Value);				
				sheetObj.ValidateFail(1);
				return;
				}else{
					for (var int = 1; int < sheetObj.RowCount(); int++) {
						var orlcode = sheetObj.GetCellValue(int, Col);
						if(Value == orlcode){
							ComShowCodeMessage('COM131302',Value);
							sheetObj.ValidateFail(1);
							return;
						}
					}
				}
			}
	}
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
 /*   function sheet1_OnSaveEnd (Code, Msg) { 
     	if()
     }*/