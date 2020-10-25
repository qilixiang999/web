//����XMLHttpRequest����
function createXmlHttp(){
    if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }else{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function changeImg(){
	document.getElementById("vcodeImg").src="http://localhost:8080/exercise1/servlet/CreateVerifyImageController?t"+Math.random();
}

/*
function ajaxCheckLogin(){
    var userName=document.getElementById("userName").value;
    var password=document.getElementById("password").value;
    var vcode=document.getElementById("vcode").value;
    var autologin=document.getElementById("autologin").value;
    createXmlHttp();//�����Զ��庯������XMLHttpRequest����
    xmlHttp.open("post","ajaxLoginCheck.do",true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
    xmlHttp.send("userName="+userName+"&password="+password+"&vcode="+vcode+"&autologin="+autologin);
    xmlHttp.onreadystatechange=function(){//�ص�����
 //   	if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var response=xmlHttp.responseText;
    //        var json=JSON.parse('{"code":1,"info":"��������"}');
            var json=JSON.parse(response);//����ϵͳ�������ַ���ת��Ϊjson����
            if(json.code==0){//��½�ɹ�
                window.location.href="main.jsp";
            }else{//��½ʧ��
                document.getElementById("checkError").innerText=json.info;//��ʾ���ش�����Ϣ
            }
  //  	}

    }
}*/

function ajaxCheckLogin(){
    $.ajax({
        type: "post",
        url: "ajaxLoginCheck.do",
        data: {userName:$("#userName").val(),password:$("#password").val(),vcode:$("#vcode").val()},
        dataType: "json",
        success: function (response) {
            if(response.code==0){
                window.location.href="main.jsp";
            }else{
                $("#checkError").text(response.info);
            }
        }
    });
}