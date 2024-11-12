package com.exmaple.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

// 메모리상에 User정보를 관리하는 클래스
public class UserServiceInMemory implements UserService {
    // 키보드의 입력 받기
    private BufferedReader br;

    // 메인에서 User객체를 넘기면 받아둘 객체 선언
    private List<User> users;
    public UserServiceInMemory(List<User> users) {
        this.users = users;
    }


    // == 메뉴 선택 ==
    @Override
    public int menu() {
        // 키보드에서 입력되는 값을 받을 준비
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. 회원 가입");
        System.out.println("2. 회원 조회");
        System.out.println("3. 회원 수정");
        System.out.println("4. 회원 삭제");
        System.out.println("5. 종료");
        //
        String line = null;
        try {
            // 키보드에 입력된 한줄을 읽기
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int menuId = Integer.parseInt(line);
        return menuId;
    }

    // 1. 회원 가입
    @Override
    public Iterator<User> addUser() {
        try {
            System.out.println("Email 입력");
            String email = br.readLine();
            System.out.println("이름 입력");
            String name = br.readLine();
            System.out.println("생년 입력");
            String strYear = br.readLine();
            int year = Integer.parseInt(strYear);
            User user = new User(email, name, year);
            users.add(user);
            System.out.println("등록되었습니다.");
            return users.iterator();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // == 수정, 삭제에서 찾는 이메일의 index 위치를 반환하는 메소드 ==
    private int findUserIndex(List<User> users) {
        int userIndex = -1;
        String email;
        System.out.println("이메일을 입력해주세요.");
        try {
            email = br.readLine();
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getEmail().equals(email)){
                    userIndex = i;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userIndex;
    }

    // 2. 회원 조회
    @Override
    public void listUsers() {
        System.out.println("email   이름    생년");
        System.out.println("==================");
        for (int i = 0; i < users.size(); i++) {
            System.out.print(users.get(i).getEmail());
            System.out.print("      ");
            System.out.print(users.get(i).getName());
            System.out.print("      ");
            System.out.print(users.get(i).getBirthYear());
            System.out.println();
        }
    }

    // 3. 회원 수정
    @Override
    public void updateUser() {

        int userIndex = findUserIndex(users);

        if (userIndex !=-1) {
            try {
            System.out.println("수정할 이름");
            String upName = br.readLine();
            System.out.println("수정할 생년");
            String strUpYear = br.readLine();
            int upYear = Integer.parseInt(strUpYear);
            User upUser = new User(users.get(userIndex).getEmail(),upName,upYear);

            users.set(userIndex,upUser);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("수정할 이메일을 가진 회원이 없습니다.");
        }
    }

    // 4. 회원 삭제
    @Override
    public void deleteUser() {
        int userIndex = findUserIndex(users);
        if (userIndex !=-1) {
            users.remove(userIndex);
            System.out.println("삭제되었습니다.");
        } else {
            System.out.println("삭제할 이메일을 가진 회원이 없습니다.");
        }
    }

    // == 회원 정보를 Iterator로 전체 전달하기. ==
    @Override
    public Iterator<User> getUsers() {
        return users.iterator();
    }



}
