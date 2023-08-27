package com.studentDemo.librarymanagement;
/*
删除操作通过遍历实现。
提供相应的书名，然后进行搜索，如果找到了，就使用remove()方法进行删除，否则就抛出一个异常
 */

import java.util.Scanner;
public class DelOperation implements IOperation{
    public void work(BookList bookList)throws OperationException{
        System.out.println("请输入你想要删除的书籍:");
        Scanner scan=new Scanner(System.in);
        String name=scan.next();
        boolean flag=true;
        for(int i=0;i< bookList.getBooks().size();i++){
            if(bookList.getBooks().get(i).getName().equals(name)){
                flag=false;
                System.out.println("查找到了，请问是否要进行删除:"+name+" (Y/N)");
                if(scan.next().equalsIgnoreCase("y")){ //进行删除操作
                    bookList.getBooks().remove(i);
                    System.out.println("删除成功!");
                }
            }
        }
        if(flag){
            throw new OperationException("未查找到该书籍!");
        }
    }
}