
    function DOU_TRN_0002() {
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
        var sheetObject2=sheetObjects[1];
   	 	var formObject=document.form1;
       
   	try {
   		var srcName=ComGetEvent("name");
   		
           switch(srcName) {
           	case "btn_Retrieve":
           		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
           		break;
            case "btn_Save":
            	
              if(confirm("Do you save selected codes?")){
                
            	  if((sheetObjects[0].RowCount("I")+sheetObjects[0].RowCount("U")+sheetObjects[0].RowCount("D")) >0 ){
            		  if(sheetObjects[0].RowCount("D") > 0){
            			  
                  		var  rowcountD = sheetObjects[0].RowCount();            		
                		for( i = 1; i <= rowcountD; i++){
            			var row = 1;
                			if(sheetObjects[0].GetCellValue(i,0) == "D"){
                				formObject.f_cmd.value=SEARCH01;
                		    	ComSetObjValue(document.form1.codeid, sheetObjects[0].GetCellValue(i, "intg_cd_id"));
                				var xml = sheetObjects[1].GetSearchData("DOU_TRN_0002GS.do", FormQueryString(formObject) );                		
                				if(ComGetTotalRows(xml) > 0){
                					alert('Can not delete data in master list when haved data in detail list at CODE: '+sheetObjects[0].GetCellValue(row, "intg_cd_id"));
                					return;
                				}else{   
                    				doActionIBSheet(sheetObjects[0],formObject,IBSAVE);
                    			}	
                			}
                			row++;
                		} 
            		  }else{
            			  doActionIBSheet(sheetObjects[0],formObject,IBSAVE);
            		  }

            		
            	  } 
            	  if((sheetObjects[1].RowCount("I")+sheetObjects[1].RowCount("U")+sheetObjects[1].RowCount("D")) >0 ) {
            		  doActionIBSheet(sheetObjects[1],formObject,IBSAVE);
            	  }
            	  
              }
    	        break;
           	case "btn_Add_master":
           		doActionIBSheet(sheetObject1,formObject,IBINSERT);
           		break;
           	case "btn_Add_detail":
           		doActionIBSheet(sheetObject2,formObject,IBINSERT);
           		break;
           }     
   	}catch(e) {
   		if( e == "[object Error]") {
   			ComShowMessage(OBJECT_ERROR);
   		} else {
   			ComShowMessage(e);
   		}
   	}
   }
    
    function setSheetObject(sheet_obj){
        sheetObjects[sheetCnt++]=sheet_obj;
     }
    
    function loadPage() {
    	console.log(sheetObjects.length);
		for(i=0;i<sheetObjects.length;i++){
			
		/*	ComConfigSheet (sheetObjects[i]);*/
			initSheet(sheetObjects[i],i+1);		
		/*	ComEndConfigSheet(sheetObjects[i]);*/
			
		}
		doActionIBSheet(sheetObjects[0],document.form1,IBSEARCH)
	}
    
    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetNo) {
            case 1:
            
            	with(sheetObj){
            	var HeadTitle="ibflag|DEL|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date" ;
                var prefix="";

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:0, Width:45,   Align:"Center",  ColMerge:0,   SaveName:"ibflag",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
                             {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",     Hidden:0, Width:70,   Align:"Center",  ColMerge:0,   SaveName:"ownr_sub_sys_cd", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",      KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:200,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_len",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0, Width:100,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_tp_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"General Code|Table Code", ComboCode:"G|T" },
	             {Type:"Text",      Hidden:0,  Width:150,  Align:"Left",    ColMerge:0,   SaveName:"mng_tbl_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:350,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0, Width:40,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_use_flg", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"YES|NO", ComboCode:"Y|N"  },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"cre_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"cre_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"upd_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"upd_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 } ];
                         
                InitColumns(cols);                    
            	SetEditable(1);
	            SetSheetHeight(240);
                }
            break;
            case 2:
           
            	with(sheetObj){
            	var HeadTitle="|DEL|Cd id|Cd Val|Display Name|Description Remark|Order" ;

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:1, Width:10,   Align:"Center",  ColMerge:0,   SaveName:"ibflag",              KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
                             {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
                 {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",          KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_ctnt",    KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:200,  Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_desc", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:600,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_val_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                 
                InitColumns(cols);

                SetEditable(1);
                resizeSheet();
                }
            break;
        }
    }
    function resizeSheet(){
      	         ComResizeSheet(sheetObjects[1]);
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
        	case IBSAVE: 
        		if(validateForm(sheetObj,formObj,sAction)) {
        			if ( sheetObj.id == "sheet1" ) {
        				formObj.f_cmd.value=MULTI;
                        sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
        			}else if ( sheetObj.id == "sheet2" ) {
        				formObj.f_cmd.value=MULTI01;
                        sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
        			}
        		}
        		break; 
        		
			case IBSEARCH: 
				
				if(validateForm(sheetObj,formObj,sAction)) {
					if ( sheetObj.id == "sheet1" ) {
						ComOpenWait(true);
						formObj.f_cmd.value=SEARCH;
		 				sheetObj.DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj) );
		 				
					}else if ( sheetObj.id == "sheet2" ) {
						ComOpenWait(true);
						formObj.f_cmd.value=SEARCH01;
		 				sheetObj.DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj) );
						
					}					
				}
				break;
			case IBINSERT:     
				
				row= sheetObj.DataInsert(-1);
				rowcount=sheetObj.RowCount();
				
	 			if ( sheetObj.id == "sheet2" ) {
	 				
	 				if( sheetObj.SearchRows() == 0 ){
	 					
	 					sheetObjects[1].SetCellValue(row,2,sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(),"intg_cd_id"));
	 				} else {	 					
	 					sheetObjects[1].SetCellValue(row, 2,sheetObj.GetCellValue(2, "intg_cd_id"));
	 				}
	 			}
				break;    	
			

        }
    }
    function sheet1_OnEditValidation(sheetObj, Row, Col, Value) {
		switch(Col) {
		case 3:
			var pattern = /^[A-Z]{3}[0-9]{5}$/;
			if(!pattern.test(Value)){
				alert('Please enter a valid  format: TTTNNNNN ( T: Text , N : Number )',+value);				
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
    
    function sheet1_OnDblClick(sheetObj, Row, Col) {
    	
    	ComSetObjValue(document.form1.codeid, sheetObj.GetCellValue(Row, "intg_cd_id"));
    	console.log(sheetObj.GetCellValue(Row, "intg_cd_id"));
    	doActionIBSheet(sheetObjects[1],document.form1,IBSEARCH);
    	
    }
    
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
    function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
    function sheet1_OnSaveEnd(code, msg) {
    	 var sheetObject1=sheetObjects[0];
    	 if(msg >= 0) {
    		 alert('Saved Suscessfully'); // saving success message
    		 doActionIBSheet(sheetObject1,document.form1,IBSEARCH);
    	 } else {
    		 alert('Saved fail'); // saving failure message
    	}
    }
    function sheet2_OnSaveEnd(code, msg) {
   	 var sheetObject3=sheetObjects[1];
   	 if(msg >= 0) {
   		 alert('Saved Suscessfully'); // saving success message
   		 doActionIBSheet(sheetObject3,document.form1,IBSEARCH);
   	 } else {
   		 alert('Saved fail'); // saving failure message
   	}
   }