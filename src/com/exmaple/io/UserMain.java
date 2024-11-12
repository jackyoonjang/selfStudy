package com.exmaple.io;

import java.util.Iterator;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        // 회원 정보에 접근할 DAO 생성
        UserDao userDao = new UserDao("tmp/users.txt");
        // 입력하는 회원 정보를 처리할 Service 객체 생성
        UserService userService = new UserServiceInMemory(userDao.getUsers());
        // 회원 관리 UI
        while (true){
            int menuId = userService.menu();
            // 5. 종료
            if (menuId ==5){
                System.out.println("종료합니다.");
                userDao.saveUser(userService.getUsers());
                break;
            }
            // 1. 회원 가입
            else if(menuId == 1){
                userDao.saveUser(userService.addUser());
            }
            // 2. 회원 조회
            else if(menuId ==2){
                userService.listUsers();
            }
            // 3. 회원 수정
            else if(menuId == 3){
                userService.updateUser();
            }
            // 4. 회원 삭제
            else if (menuId == 4) {
                userService.deleteUser();
            }
        }
    }
}
