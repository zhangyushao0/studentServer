package librarymanagement;

public class Book {
    private String name;//书名
    private String author;//作者
    private int price;//价格
    private String type;//分类
    private  boolean isBorrowed; //是否全部被借出
    private String isbn;//ISBN 号
    private int stock;//库存
    //构造器
    public Book(){
    }
    //创建书籍时库存会自动+1
    public Book(String name,String author,int price,String type,String isbn){
        this.author=author;
        this.name=name;
        this.price=price;
        this.type=type;
        this.isbn=isbn;
        //this.stock+=1;
        this.isBorrowed=false;
    }
    //get、set调用封装方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
    public String getisbn() {
        return isbn;
    }
    public void setisbn(String isbn) {
        this.isbn=isbn;
    }
    public int getstock() {
        return stock;
    }
    public void setstock(int stock) {
        this.stock=stock;
    }
    @Override
//重写toString()
    public String toString() {
        return "Book{" +
                "书名:'" + name + '\'' +
                ", 作者:'" + author + '\'' +
                ", 价格:" + price +
                ", 类型:'" + type + '\'' +
                ", 是否借出:" + isBorrowed +
                ",isbn码："+isbn+
                "库存:"+stock+
                '}';
    }
}
