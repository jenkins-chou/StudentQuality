
var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var path = require('path');

//路由列表

var uploadController = require("./controller/upload")
var errorController = require("./controller/operate_record")

//var urlencodedParser = bodyParser.urlencoded({extended:false})
app.use(express.static('public'));//静态文件
app.use(express.static(path.join(__dirname, '')))

// app.use(urlencodedParser)//这里使用 urlencodedParser方式
app.use(bodyParser.urlencoded({extended: true}));

// app.use(bodyParser.json({limit: '1mb'}));  //这里指定参数使用 json 格式
app.get('/', function(req,res){
    res.send('hello this is xiaoyunhui app server api');
})

app.post('/post',function(req,res){
    console.log("params:"+req.body.params);
    res.send('hello this is post request!');
})

app.use('/upload',uploadController)
app.use('/error',errorController)

app.listen(7777)
console.log("数据服务器已打开, 端口: 7777");