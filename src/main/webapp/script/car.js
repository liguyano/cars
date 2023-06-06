var socket;
function car(msg) {
sendMessage(msg);
$.post("/car/car","command="+msg),function () {
    
};
}
function openSocket() {
    if(typeof(WebSocket) == "undefined") {
        console.log("don't support websock");
    }else{
        console.log("support WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //等同于socket = new WebSocket("ws://localhost:8888/xxxx/im/25");
        //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
        var socketUrl="ws://localhost:80/car/sock/"+$("#userId").val();
        //socketUrl=socketUrl.replace("https","ws");

        if(socket!=null){
            socket.close();
            socket=null;
        }
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function() {
            console.log("websocket open");
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        socket.onclose = function() {
            console.log("websocket close ");
        };
        //发生了错误事件
        socket.onerror = function() {
            console.log("websocket erro");
        }
    }
}
$(function () {
    openSocket();


})
function sendMessage(message) {
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else {
        console.log("spport WebSocket");
        console.log('{"toUserId":"'+$("#toUserId").val()+'","contentText":"'+$("#contentText").val()+'"}');
        socket.send('{"toUserId":"1'+'","contentText":"'+message+'"}');
        // socket.send("hello");
    }
}