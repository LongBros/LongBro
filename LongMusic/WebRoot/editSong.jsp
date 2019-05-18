<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
int id=Integer.parseInt(request.getParameter("id"));
%>
<html>
<head>
	<title>编辑歌曲</title>
	<script type="text/javascript" src="js/jquery.js"></script>

</head>
<script type="text/javascript">
  		$(function(){
  			querySongById();
	   });
  		function querySongById(){
  			$.ajax({
  	  			type:"Get",
  	  			async:false,
  	  			url:"querySongById.do?id="+<%=id%>,
  	  			dataType:"Json",
  	  			success:function(data){
  	  				$('#form').append("id:<input type='text' name='id' value='"+data.id+"'><br>");
  	  				$('#form').append("资源id：<input type='text' name='sourceId' value='"+data.sourceId+"'><br>");
  	  				$('#form').append("歌曲名：<input type='text' name='songName' value='"+data.songName+"'><br>");
  	  				$('#form').append("歌手：<input type='text' name='singer' value='"+data.singer+"'><br>")
  	  				$('#form').append("时长：<input type='text' name='duration' value='"+data.duration+"'><br>");
  	  				$('#form').append("专辑：<input type='text' name='album' value='"+data.album+"'><br>");
  	  				$('#form').append("图片路径：<input type='text' name='imgPath' value='"+data.imgPath+"'><br>");
  	  				$('#form').append("发布时间：<input type='text' name='releaseTime' value='"+data.releaseTime+"'><br>");
  	  				
  	  				$('#form').append("网站:<input type='text' name='website' value='"+data.website+"'><br>");
  	  				
  	  				$('#form').append("描述:<input type='text' name='desc' value='"+data.desc+"'><br>");
  	  				$('#form').append("<input type='submit' value='更新'>");
  	  				document.title="编辑歌曲-"+data.songName;
  	  			}
  	  		});
  		}
  		
  </script>
<body>
	<form id="form" action='editSong.do' method='post'>
	</form>
</body>
</html>
