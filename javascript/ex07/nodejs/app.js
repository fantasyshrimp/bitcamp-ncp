//express 라이브러리 로딩하기
const { response, json } = require('express');
const express = require('express');
const request = require('request');
const app = express();
const port = 3000; //포트번호

app.get(
  '/exam01-1',
  (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/plain; charset=UTF-8');
    res.send('바람아 멈추어다오 바람아');

  }); //클라이언트에서 get요청

app.get( '/exam02-1', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  res.send(`이름 : ${req.query.name}\n나이 : ${req.query.age}`);      
  
}); //클라이언트에서 get요청

app.use(express.urlencoded( {extended: true}));
app.post( '/exam02-2', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  res.send(`이름 : ${req.body.name}\n나이 : ${req.body.age}`);      
  
}); //클라이언트에서 post요청
app.get( '/exam03-1', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');

  setTimeout(() => {
    res.send('hello');
  }, 1000);  
});

app.get( '/exam03-4', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  let a = parseInt(req.query.a);
  let b = parseInt(req.query.b);
  res.send(`${a + b }`)
});
app.get( '/header', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/html; charset=UTF-8');
  res.send('<h1>비트캠프 네이버 클라우드 AIaaS 개발자 양성과정 </h1>');
});
app.get( '/footer', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/html; charset=UTF-8');
  res.send('<adress>주사야 양성과정</adress>');
});

app.get( '/exam04-3', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/html; charset=UTF-8');

  let arr = [
    {no:1, title: '제목1', writer:'홍길동',viewCnt:19},
    {no:2, title: '제목2', writer:'임꺽정',viewCnt:312},
    {no:3, title: '제목3', writer:'유관순',viewCnt:31},
    {no:4, title: '제목4', writer:'안중근',viewCnt:100},
    {no:5, title: '제목5', writer:'윤봉길',viewCnt:200},
  ];

  res.send(JSON.stringify(arr));
});



//클라이언트 요청을 다른서버에게 보냄
app.get(
  '/proxy',  (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/plain; charset=UTF-8');

      request.get({
      uri: req.query.url
      },(error, response, body) => {
      res.send(body);
      });
  }
); //클라이언트에서 get요청



app.get(
  '/proxy2',  (req, res) => {

    let openApiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
    "serviceKey=keykeykeykeykey" +
    "&pageNo=1" + 
    "&numOfRows=1000" +
    "&dataType=JSON" + 
    "&base_date=" + req.query.base_date + 
    "&base_time=0600" +
    "&nx=" + req.query.nx +
    "&ny=" + req.query.ny;

    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'application/json; charset=UTF-8');
    
    request.get({
      uri: openApiUrl
    },(error, response, body) => {
      res.send(body);      
      });
  }
); //클라이언트에서 get요청


app.post(
  '/login',  (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/plain; charset=UTF-8');

    var payload = `이메일: ${req.body.email}\n`;
    payload += `암호: ${req.body.password}\n`

    res.send(payload);
  }
);








app.listen(
  3000, //포트번호 지정
  () => {
    console.log(`${port}번 포트에서 서버 실행`);
  },

)
console.log("서버시작");