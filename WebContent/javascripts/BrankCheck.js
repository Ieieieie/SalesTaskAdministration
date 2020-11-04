'use strict';

function loginBrankCheck(){
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if(username == '' || password == ''){
		alert('ユーザー名とパスワードを入力してください。');
		return false;
	}else{
		return true;
	}
}
function searchNameBrankCheck(){
	var name = document.getElementById("search_name").value;
	if(name == ''){
		alert('氏名を入力してください。');
		return false;
	}else{
		return true;
	}
}
function searchAddressBrankCheck(){
	var address = document.getElementById("search_address").value;
	if(address == ''){
		alert('郵便番号を入力してください。');
		return false;
	}else{
		return true;
	}
}