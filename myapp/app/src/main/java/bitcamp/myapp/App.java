package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMenu();
    System.out.println("잘가요 내사랑 이제 보내줄께요.");
    Prompt.close();
  } // main()

  private static void goMenu() {
    while(true) {
      System.out.println("1. 회원 관리");
      System.out.println("9. 나가기");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        MemberHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 번호가 입력되었습니다.");
      }
    }
  }

} // class App