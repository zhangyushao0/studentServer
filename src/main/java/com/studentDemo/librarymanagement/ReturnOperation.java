package com.studentDemo.librarymanagement;
import java.util.Scanner;
/*
还书操作:提供相应的书名，遍历书架，
若有该书，则进行归还，同时设置其状态为未被借出状态；
如果未找到该书，也抛出一个异常。
 */
public class ReturnOperation implements IOperation{
    @Override
    public void work(BookList bookList) throws Exception {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入你要归还的书籍:");
        String name=scan.next();
        boolean flag=true;
        for(int i=0;i<bookList.getBooks().size();i++){
            if(bookList.getBooks().get(i).getName().equals(name)){
                flag=false;
                System.out.println("成功归还!");
                bookList.getBooks().get(i).setBorrowed(false);
            }
        }
        if(flag){
            throw new OperationException("查无此书!");
        }
    }
}