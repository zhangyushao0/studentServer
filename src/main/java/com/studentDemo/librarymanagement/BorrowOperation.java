package com.studentDemo.librarymanagement;
import java.util.Scanner;
/*
借书操作:先提供一个书名，然后遍历书架，若有则判断其是否已被借出，
如果是，则抛出一个异常，否则就借出成功，并更改书的借出状态；
若没有，即抛出一个未找到的异常。
 */
public class BorrowOperation implements IOperation{
    public void work(BookList bookList)throws OperationException{
        System.out.println("请输入你想要借的书:");
        Scanner scan=new Scanner(System.in);
        String name=scan.next();
        boolean flag=true;
        for(int i=0;i< bookList.getBooks().size();i++){
            if(bookList.getBooks().get(i).getName().equals(name)){ //查找到该书籍在图书馆中
                flag=false;
                if(bookList.getBooks().get(i).isBorrowed()==true){
                    throw new OperationException("借书失败，该书已被借出");
                }else{ //书未被借出
                    System.out.println("借出成功!");
                    bookList.getBooks().get(i).setBorrowed(true);
                }
            }
        }
        //出循环了，仍未找到
        if(flag) {
            throw new OperationException("未查找到该书");
        }
    }
}