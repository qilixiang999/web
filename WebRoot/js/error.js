window.setInterval(changeLeaveTime,1000);
function changeLeaveTime() {
    var time=parseInt(document.getElementById("leaveTime").innerText);
    time=time-1;
    if(time==0){
        window.location.href="/exercise1/login.html";
    }else{
        document.getElementById("leaveTime").innerText=time;
    }
}