package com.exmaple.io;

import java.util.Iterator;

public interface UserService {
    // 메뉴를 선택하다.
    public int menu();

    // 회원 정보를 등록하다.
    public Iterator<User> addUser();

    // 회원 정보를 수정하다.
    public void updateUser();

    // 회원 정보를 삭제하다.
    public void deleteUser();

    // 모든 회원 정보를 가져오다.
    public Iterator<User> getUsers();

    // 모든 회원 정보를 출력하다.
    public void listUsers();


}
