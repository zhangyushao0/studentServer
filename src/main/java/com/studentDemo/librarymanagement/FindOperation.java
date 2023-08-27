package com.studentDemo.librarymanagement;
/*
查找操作只有两种情况，一种是找到了，另一种是没找到.
在没找到的时候我们就可以选择抛出一个异常，查找操作的具体实现还是通过遍历来实现
 */
import java.util.Scanner;
//查找功能的实现
public class FindOperation implements IOperation{
    @Override
    public void work(BookList bookList) throws OperationException {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入你想要查找的书名:");
        String name=scan.next();
        boolean flag=true;
        for(int i=0;i<bookList.getBooks().size();i++){
            if(bookList.getBooks().get(i).getName().equals(name)){
                flag=false;
                System.out.println("找到了!   信息如下:");
                System.out.println(bookList.getBooks().get(i));
            }
        }
        if(flag){
            throw new OperationException("没有找到该书");
        }
    }
}