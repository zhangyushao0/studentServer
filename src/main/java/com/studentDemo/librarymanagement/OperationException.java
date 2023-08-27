package librarymanagement;

public class OperationException extends Exception{
    static final long serialVersionUID=-33875169931L;
    //空参构造器
    public OperationException(){

    }
    public OperationException(String message){
        super(message); //Exception类中存在String参数的构造器和message
    }
}
