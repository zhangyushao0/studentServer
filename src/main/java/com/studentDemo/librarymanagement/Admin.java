package com.studentDemo.librarymanagement;

public class Admin extends User{
    public Admin(String name){
        super(name);
    }
    public void menu(){
        System.out.println("尊敬的管理员:"+this.getName()+"欢迎来到图书管理系统");
        System.out.println("1.查找图书");
        System.out.println("2.新增图书");
        System.out.println("3.删除图书");
        System.out.println("4.显示图书");
        System.out.println("5.归还图书");
        System.out.println("0.退出系统");
    }
    public  void Operation(int n,BookList bookList)throws OperationException{
        IOperation iOperation=null;
        switch(n){
            case 0:
                iOperation= new ExitOperation();
                break;
            case 1:
                iOperation=new FindOperation();
                break;
            case 2:
                iOperation=new AddOperation();
                break;
            case 3:
                iOperation=new DelOperation();
                break;
            case 4:
                iOperation= new DisplayOperation();
                break;
            case 5:
                iOperation=new ReturnOperation();
                break;
            default:
                throw new OperationException("输入错误，请重新输入！");
        }
        try {
            iOperation.work(bookList);
        }catch(OperationException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
