package com.eomcs.oop.ex02.test;

import com.eomcs.oop.ex02.test.util.Calculator;

//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 클래스 변수의 한계 확인
//5) 인스턴스 변수 도입
//6) 인스턴스 메서드 활용
//7) 패키지 멤버 클래스로 분리
//8) 클래스를 역할에 따라 패키지로 분류하기

public class CalculatorTest {

  public static void main(String[] args) {

    Calculator c = new Calculator();

    c.plus(2);
    c.plus(3);
    c.minus(1);
    c.multiple(7);
    c.divide(3);

    System.out.printf("result = %d\n", c.result);

  }
}

