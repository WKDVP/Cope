블랙잭 게임임
이거 다 만들고 디코봇으로 만들어 본다

규칙정리 
1. 21 넘으면 뒤짐
2. 내가 21인데 딜러가 21이면 본전만 들고감
3. 딜러가 버스트면 버스트 말고 다 받음

구현할거 목록

배팅 - 0
유저 카드 받기 - 0
봇 카드 - 0
21 넘는지 계산 - 0
{
유저 슽/힡 0
21 넘는지 0 
봇 슽힡 0
21 넘는지 0
} 다 슽 혹은 죽을때 까지 반복 - 0
{
딜러 카드 받기 - 0
17인지 검사 - 0
} 17 넘을때 까지 반복 - 0
 딜러 뒤집기 - 0
돈 주기 - 0
반복하기 - 0
A 1 or 11인거
KQJ 구현

버그
======================
스테이 힡 고를 때 좆같은 문자 널으면 턴 넘얻감 - 고침 << 걍 if문 써서 반복을 못함
+ 저기다 뭐라고 다음에 println 에서 print로 바꾸죠 - 고침
2회차 부터 이상해지는거 - 고침 << 카드 리스트 초기화 안해서 그랬음
딜러가 첫 카드 까기전에 버스트면 돈 안주는 거 - 고침 << 저때 버스트가 true로 바꾸는거 깜빡함


GJE Soft