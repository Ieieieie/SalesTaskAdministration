<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>

<!DOCTYPE html>

<%
HttpSession sess              = request.getSession();
UserInfoDto userInfoOnSession = (UserInfoDto)sess.getAttribute("LOGIN_INFO");

if(userInfoOnSession == null){
	response.sendRedirect(request.getContextPath()+"/views/Login.jsp");
}
	List<TaskInputDto> dtoList = (List<TaskInputDto>)request.getAttribute("ShowTasks");
	%>

<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/TaskInput.css">
	<title>日報表</title>
	<div class="header">
	<form action="<%=request.getContextPath() %>/DoLogout">
			<img src="<%=request.getContextPath() %>/pictures/logo.png">
			<button type="submit" id="logout_button" class="btn btn-dark">ログアウト</button>
		</form>
	</div>
</head>
<body>
	<div class="task_container">
		<form action="<%=request.getContextPath()%>/TaskInputExecute" method="post">
			<div class="postal_code_box">
				<p id="postal_codeText">郵便番号(半角で入力)</p>
				<input type="text" name="zip11" id="postal_code" size="16" maxlength="8"  class="form-control" placeholder="郵便番号(ハイフンなし)" onKeyUp="AjaxZip3.zip2addr(this,'','addr11','addr11');">
			</div>
			<div class="address_box">
				<!-- ▼住所入力フィールド(都道府県+以降の住所) -->
				<p id="addressText">住所</p>
				<input type="text" name="addr11" id="address" size="60"  class="form-control" placeholder="都道府県・市区町村・番地">
			</div>
			<div class="visited_name_box">
				<p id="visited_nameText">訪問先氏名</p>
				<input type="text" name="visited_name" id="visited_name"  class="form-control" placeholder="訪問先氏名">
			</div>
			<div class="interviewer_box">
				<p id="interviewerText">会見者</p>
				<input type="text" name="interviewer" id="interviewer"  class="form-control" placeholder="会見者">
			</div>
			<div class="result_box">
				<p id="resultText">訪問結果</p>
				<select name="result" id="result" class="btn btn-secondary dropdown-toggle">
					<option value="1">契約</option>
					<option value="2">検討</option>
					<option value="3">更新・その他</option>
					<option value="4">なし</option>
				</select>
			</div>
			<div class="memo_box">
				<p id="memoText">内容</p>
				<textarea name="memo" id="memo" maxlength="500"  class="memo_textarea form-control" placeholder="内容"></textarea>
			</div>
				<input type="submit" value="送信" id="send_button"class="btn btn-primary" onClick="return taskInputConfirm();">
		</form>
	</div>

	<div class="sales_record">
		<div class="record_button_box">
			<select name="search_kind" id="search_kind" class="btn btn-secondary dropdown-toggle">
					<option value="1">セールス記録(今日)</option>
					<option value="2">セールス記録(今週)</option>
					<option value="3">セールス記録(今月)</option>
					<option value="4">セールス記録(全て)</option>
			</select>
			<button type="button" id="search_button" class="btn btn-light" onclick="searchKind();">表示</button>
		</div>
		<div class="search_box">
			<div class="search_name_box">
				<form action="<%=request.getContextPath() %>/SearchName" method="post">
					<button type="submit" class="search_name_button btn btn-light" onClick="return searchNameBrankCheck();" >検索</button>
					<input type="text" name="search_name" class="search_name form-control" id="search_name" placeholder="訪問先氏名">
				</form>
			</div>
			<div class="search_address_box">
				<form action="<%=request.getContextPath() %>/SearchAddress" method="post">
					<button type="submit" class="search_name_button  btn btn-light" onClick="return searchAddressBrankCheck();">検索</button>
					<input type="text" name="search_address" class="search_address form-control" id="search_address"  placeholder="郵便番号">
				</form>
			</div>
		</div>
		</div>
	<%if(dtoList != null){%>
	<div class=" table-responsive">
		<table class="record_table tbl-r03 table table-bordered table-striped">

		    <%
			for(int i = 0; i<dtoList.size(); i++){
				String address = dtoList.get(i).getAddress();
				String visitedName = dtoList.get(i).getVisitedName();
				String interviewer = dtoList.get(i).getInterviewer();
				int result = dtoList.get(i).getResult();
				String remarks = dtoList.get(i).getMemo();
				Timestamp regitedAt = dtoList.get(i).getRegistedAt();
				String resultText = null;
				switch(result){
					case 1:	resultText = "契約";
							break;
					case 2:	resultText = "検討";
							break;
					case 3:	resultText = "更新・その他";
							break;
					case 4:	resultText = "なし";
							break;
				}
			%>
			<tr id="record_table_column">
		      <th>住所</th>
		      <th>訪問先氏名</th>
		      <th>会見者</th>
		      <th>訪問結果</th>
		      <th width="200">内容</th>
		      <th>登録時間</th>
		    </tr>
		    <tr class="record_table_elements">
		      <td><%=address %></td>
		      <td><%=visitedName %></td>
		      <td><%=interviewer %></td>
		      <td><%=resultText %></td>
		      <td width="200"><%=remarks %></td>
		      <td><%=regitedAt %></td>
		    </tr>
		    <%}
	}%>
	  </table>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/InputConfirm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/Search.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/BrankCheck.js"></script>
</body>
</html>