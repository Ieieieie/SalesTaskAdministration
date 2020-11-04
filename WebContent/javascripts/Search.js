'use strict';

function searchKind(){
	var search = document.getElementById("search_kind").value;
	var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	switch(search){
		case '1': 	window.location.href = contextPath+'/ShowTodayTasks';
				  	break;
		case '2': 	window.location.href = contextPath+'/ShowWeekTasks';
		  			break;
		case '3': 	window.location.href = contextPath+'/ShowMonthTasks';
		  			break;
		case '4': 	window.location.href = contextPath+'/ShowAllTasks';
					break;
	}

}