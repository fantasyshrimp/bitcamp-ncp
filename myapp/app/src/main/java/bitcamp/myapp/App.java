package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMenu();
    System.out.println("잘가요 내사랑 이제 보내줄께요.");
    Prompt.close();
  } // main()

  private static void goMenu() {
    while(true) {
      System.out.println("1. 일반학생관리");
      System.out.println("2. 국비학생관리");
      System.out.println("3. 위탁교육생관리");
      System.out.println("9. 나가기");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        MemberHandler.title = "일반학생";
        MemberHandler.service();
      } else if (menuNo == 2) {
        Member2Handler.title = "국비지원학생";
        Member2Handler.service();
      } else if (menuNo == 3) {
        Member2Handler.title = "위탁교육생";
        Member3Handler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 번호가 입력되었습니다.");
      }
    }
  }

} // class App