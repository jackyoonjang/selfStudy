package com.exmaple.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  파일에서 List<User>정보를 저장하거나 읽어오는 기능
 */
public class UserDao {
    private String filename;
    // 파일 이름 저장
    public UserDao(String filename) {
        this.filename = filename;
    }
    // 객체를 실제 파일에 저장하기(쓰기)
    public void saveUser(Iterator<User> iter){
        List<User> users = new ArrayList<>();
        while(iter.hasNext()){
            User user = iter.next();
            users.add(user);
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(users);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    // 파일에 데이터가 있을 경우 리스트에 담아서 반환
    public List<User> getUsers(){
        File file = new File(filename);
        if(!file.exists()){
            return new ArrayList<>();
        }

        List<User> list = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            list = (List<User>)in.readObject();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }



}
