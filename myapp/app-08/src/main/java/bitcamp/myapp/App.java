package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMainMenu();
    Prompt.close();
  } // main()

  private static void goMainMenu() {
    while (true) {
      System.out.println("1. 회원관리");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        MemberHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("다시");
      }
    }
    System.out.println("ㅂㅇ");
  }

} // class App









