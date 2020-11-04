'use strict';

function taskInputConfirm()	{
	var postalCode = document.getElementById("postal_code").value;
	var address = document.getElementById("address").value;
	var visited_name = document.getElementById("visited_name").value;
	var result = document.getElementById("result");
	var resultIndex = result.selectedIndex;
	var resultLabel = result.options[resultIndex].text;
	var memo = document.getElementById("memo").value;

	if(postalCode == '' || address == '' || visited_name ==''){
		alert('必須項目を入力してください。');
		return false;
	}else{
		 if(confirm('これでよろしいですか？\n'
				  +'〒：'+postalCode
				  +'\n住所：'+address
				  +'\n訪問先氏名：'+visited_name
				  +'\n訪問結果：'
				  +resultLabel
				  +'\n内容：'+memo
		  )){
		    return true;
		  }else{
		    alert('キャンセルされました。');
		    return false;
		  }
	}
}