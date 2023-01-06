package bitcamp.myapp;

public class BoardHandler {



  void service() {


    while (true) {
      System.out.println("1. 입력");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int no = Prompt.inputInt("메뉴를 선택하시오> ");


      if(no == 0) {
        break;
      } else if (no == 1) {
        Prompt.inputInt("번호? >");
        Prompt.inputString("제목? >");

      }
    }





  }
}
