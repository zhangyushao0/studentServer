package com.studentDemo.librarymanagement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class TestLibraryDAO {
    public static void main(String[] args) {
        LibraryDAO libraryDAO = new LibraryDAOlmpl();
        /*List<Information> informationList=libraryDAO.getAllInformation();
        System.out.println(informationList.get(0).getBody());*/

        //String isbn="948276";
        /*String bcode="999999";
        libraryDAO.deleteBook(bcode);*/
/*        String isbn="9789876543210";
        String bookname="NEW BOOK";
        String author ="New  author";
        String subject="SUBJECT";
        String publisher="NWE PUBLISHER";
        String genre ="GENRE";
        String brief = "BRIEF";
        int price=50;
        int stock=1;

        String bcode="666666";
        String callnum="C667";
        String location="A101";

        DBooks dBooks=new DBooks();
        dBooks.setIsbn(isbn);
        dBooks.setBookname(bookname);
        dBooks.setAuthor(author);
        dBooks.setSubject(subject);
        dBooks.setPublisher(publisher);
        dBooks.setPrice(price);
        dBooks.setGenre(genre);
        dBooks.setBrief(brief);
*//*        dBooks.setStock(1);*//*

        DBooksDetail dBooksDetail=new DBooksDetail();
        dBooksDetail.setBcode(bcode);
        dBooksDetail.setIsbn(isbn);
        dBooksDetail.setBookname(bookname);
        dBooksDetail.setCallnum(callnum);
        dBooksDetail.setLocation(location);
*//*        dBooksDetail.setIsborrowed(false);
        dBooksDetail.setReadername(null);
        dBooksDetail.setReaderid(null);
        dBooksDetail.setDue(null);*//*

        libraryDAO.addBook(dBooks,dBooksDetail);*/

/*
        String bcode="5548711";
        System.out.println(libraryDAO.returnBook(bcode));
*/


        /*String bcode="5548711";
        String readername="zhangsan";
        String readerid="001";
        System.out.println(libraryDAO.borrowBook(bcode,readername,readerid));*/

        //String isbn="9781234567890";
       /* List<Recommendation> recommendations=libraryDAO.getAllRecommendation();
        if (recommendations != null) {
            for (int i=0;i<recommendations.size();i++) {
                Recommendation info = recommendations.get(i);
                System.out.println("num: " + info.getRBookNum());
                System.out.println("name: " + info.getRBookName());
                System.out.println("body: " + info.getRBookAuthor());
                System.out.println("year: " + info.getProfile());

            }
        } else {
            System.out.println("No Information found.");
        }*/
       //根据对应标题取出一条消息


        /*String readerid = "87654";
        List<DBooksDetail> dBooksDetail = libraryDAO.getBooksOfReader(readerid);
        if (dBooksDetail != null) {
            for (int i=0;i<dBooksDetail.size();i++) {
                DBooksDetail dbookdetail = dBooksDetail.get(i);
                System.out.println("bcode: " + dbookdetail.getBcode());
                System.out.println("callnum: " + dbookdetail.getCallnum());
                System.out.println("location: " + dbookdetail.getLocation());
                System.out.println("isBorrowed: " + dbookdetail.getIsBorrowed());
                System.out.println("readername: " + dbookdetail.getReadername());
                System.out.println("readerid: " + dbookdetail.getReaderid());
                System.out.println("due: " + dbookdetail.getDue());
                System.out.println("bookname" + dbookdetail.getBookname());
            }
        } else {
            System.out.println("No Books found.");
        }*/
        /*if (dBooksDetail != null) {
            System.out.println("bcode: " + dBooksDetail.getBcode());
            System.out.println("isbn: " + dBooksDetail.getIsbn());
            System.out.println("callnum: " + dBooksDetail.getCallnum());
            System.out.println("Location" + dBooksDetail.getLocation());
            System.out.println("readername: " + dBooksDetail.getReadername());
            System.out.println("readerid: " + dBooksDetail.getReaderid());
            System.out.println("due: " + dBooksDetail.getDue());
            System.out.println("isborrowed: " + dBooksDetail.getIsBorrowed());

        } else {
            System.out.println("No books found.");
        }*/
        //测试通过
        //测试插入新消息


        // 新建一条信息记录
        /*String newIntroduxtionHead = "车南大学图书馆简介"; // 替换为实际的信息标题
        String newIntroduxtionBody = "车南大学图书馆于2023年8月建成 ，坐落在位于东南大学九龙湖校区的数据库中，有与门、或门、非门、异或门、与非门、或非门六座大门，气势恢宏。馆内设有寄存器、运算器、控制器等重要设施，庄重、典雅、充满现代气息。"; // 替换为实际的信息内容
        String newIntroduxtionYear = "2023";
        String newIntroduxtionMonth = "09";
        String newIntroduxtionDay = "01";
        Introduction newIntroduction=new Introduction();
        newIntroduction.setHead(newIntroduxtionHead);
        newIntroduction.setBody(newIntroduxtionBody);
        newIntroduction.setYear(newIntroduxtionYear);
        newIntroduction.setMonth(newIntroduxtionMonth);
        newIntroduction.setDay(newIntroduxtionDay);
       // libraryDAO.saveInformation(newInformationHead, newInformationBody, newInformationYear, newInformationMonth, newInformationDay);
        libraryDAO.saveIntroduction(newIntroduction);
        System.out.println("save Introduxtion Succeeded!");*/
        // 查询新插入的信息记录
       /* Information newInformation = libraryDAO.getInformation(newInformationHead);

        if (newInformation != null) {
            System.out.println("New Information Head: " + newInformation.getHead());
            System.out.println("New Information Body: " + newInformation.getBody());
            System.out.println("New Information Year: " + newInformation.getYear());
            System.out.println("New Information Month: " + newInformation.getMonth());
            System.out.println("New Information Day: " + newInformation.getDay());
        } else {
            System.out.println("No information found.");
        }*/
        //测试通过
        /*String delIntroductionHead="NewHead";
        libraryDAO.delIntroduction(delIntroductionHead);*/

    }
}

