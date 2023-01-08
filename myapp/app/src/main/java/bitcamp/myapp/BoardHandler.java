package bitcamp.myapp;

public class BoardHandler {
  static final int SIZE = 100;
  Board[] boards = new Board[SIZE];
  int count;

  void createBoard() {
    this.boards[this.count++] = new Board(
        Prompt.inputInt("번호? >"),
        Prompt.inputString("제목? >"),
        Prompt.inputString("내용? >"),
        Prompt.inputString("암호? >")
        );
  }

  void printBoards() {
    if (this.count == 0) {
      System.out.println("작성된 글이 없습니다.");
      return;
    }

    System.out.println("번호\t제목\t\t작성일\t\t조회수");

    for (int i=0; i<this.count; i++) {
      System.out.println(String.format("%d\t%s\t\t%s\t%d",
          this.boards[i].no, this.boards[i].title,
          this.boards[i].createDate,this.boards[i].viewCount));
    }
  }

  void printBoard() {
    Board searchboard = this.findByNo(Prompt.inputInt("글번호?> "));

    if (searchboard == null) {
      System.out.println("입력된 글번호에 해당하는 글이 없습니다.");
      return;
    }

    System.out.println(String.format("    번호 : %d", searchboard.no));
    System.out.println(String.format("    제목 : %s", searchboard.title));
    System.out.println(String.format("    내용 : %s", searchboard.contents));
    System.out.println(String.format("  작성일 : %s", searchboard.createDate));
    System.out.println(String.format("  수정일 : %s", searchboard.changeDate));
    System.out.println(String.format("  조회수 : %d", searchboard.viewCount));

    searchboard.viewCount++;

  }

  void modifyBoard() {
    Board searchBoard = this.findByNo(Prompt.inputInt("글번호?> "));
    int index = this.indexOf(searchBoard);

    if (searchBoard == null) {
      System.out.println("입력된 글번호에 해당하는 글이 없습니다.");
      return;
    }

    Board chagedBoard = new Board(
        searchBoard.no,
        Prompt.inputString(String.format("제목( %s ) ? >", searchBoard.title)),
        Prompt.inputString(String.format("내용( %s ) ? >", searchBoard.contents)),
        searchBoard.pwd);

    if(!Prompt.inputString("수정하시겠습니까? ( y / N ) \n> ").equalsIgnoreCase("y")) {
      return;
    }

    if(!Prompt.inputString("비밀번호? > ").equals(searchBoard.pwd)) {
      System.out.println("비밀번호가 잘못되었습니다.");
      System.out.println("수정이 실패하였습니다.");
      return;
    }

    chagedBoard.createDate = searchBoard.createDate;
    chagedBoard.viewCount = searchBoard.viewCount;
    this.boards[index] = chagedBoard;

    System.out.println("수정이 완료되었습니다.");
  }

  void deleteBoard() {
    Board searchBoard = this.findByNo(Prompt.inputInt("글번호?> "));
    int index = this.indexOf(searchBoard);


    if (searchBoard == null) {
      System.out.println("입력된 글번호에 해당하는 글이 없습니다.");
      return;
    }

    if(!Prompt.inputString("삭제하시겠습니까? ( y / N ) \n> ").equalsIgnoreCase("y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = index + 1; i < this.count; i ++ ) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[count--] = null;
  }

  Board findByNo(int no) {
    for (int i=0; i<this.count; i++) {
      if(this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  int indexOf(Board board) {

    if (board == null) {
      return -1;
    }

    for (int i=0; i<this.count; i++) {
      if(this.boards[i].no == board.no) {
        return i;
      }
    }
    return -1;
  }


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
        return;
      }

      switch (no) {
        case 1: {
          this.createBoard();
          break;
        } case 2: {
          this.printBoards();
          break;
        } case 3: {
          this.printBoard();
          break;
        } case 4: {
          this.modifyBoard();
          break;
        } case 5: {
          deleteBoard();
          break;
        }
        default:
          System.out.println("메뉴 번호를 확인한 후 재입력 부탁드립니다.");
      }
    }





  }
}
