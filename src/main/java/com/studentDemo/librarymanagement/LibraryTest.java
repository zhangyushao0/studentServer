package com.studentDemo.librarymanagement;
import java.util.Scanner;
public class LibraryTest {
    public static void main(String[] args) {
        System.out.println("请输入你的身份:");
        System.out.println("管理员/普通用户(0/1)");
        Scanner scan=new Scanner(System.in);
        int choice=scan.nextInt();
        System.out.println("请输入你的姓名:");
        User user=null;
        BookList bookList=new BookList();
        switch(choice){ //进行身份的选择
            case 0:
                user=new Admin(scan.next());
                break;
            case 1:
                user=new Reader(scan.next());
        }
        IOperation iOperation=null;
        do {
            user.menu();
            System.out.println("请输入你要进行的操作:");
            int input=scan.nextInt();
            if(input==0){
                System.out.println("退出成功");
                return;
            }
            try {
                user.Operation(input, bookList);
            }catch(OperationException e){
                System.out.println(e.getMessage());
            }
        }while(true);
    }
}