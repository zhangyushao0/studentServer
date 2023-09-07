package com.studentDemo.librarymanagement;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LibraryServicelmpl extends LibraryServiceGrpc.LibraryServiceImplBase {
    @Override
    public void loginto(IdRequest request, StreamObserver<IdResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO = new LibraryDAOlmpl();
        //创建空的的introduction、information recommendation类
        Introduction introduction=libraryDAO.getIntroduction();
        List<Information> informationList=libraryDAO.getAllInformation();
        List<Recommendation> recommendationList=libraryDAO.getAllRecommendation();

        // 创建一个 idResponse.Builder
        IdResponse responseBuilder = IdResponse.newBuilder()
                .setIdentity("0")
                .setIntro(convertToIntroductionInfo(introduction))
                .addAllInfor(convertToInformationInfo(informationList))
                .addAllRecom(convertToRecomInfo(recommendationList))
                .build();

        System.out.println(responseBuilder);
        // 发送响应给客户端
        responseObserver.onNext(responseBuilder);
        responseObserver.onCompleted();
    }
    @Override
    public void checkBooksByName( CheckBookNameRequest request, StreamObserver<CheckBooksNameResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO = new LibraryDAOlmpl();
        List<DBooks> dBooks=libraryDAO.getDBooksBYname(request.getBookname());

        CheckBooksNameResponse checkBooksNameResponse=CheckBooksNameResponse.newBuilder()
                .addAllDbooks(convertToBookListReponse(dBooks))
                .build();
        System.out.println(checkBooksNameResponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBooksNameResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void checkBooksByAuthor( CheckBookAuthorRequest request, StreamObserver<CheckBookAuthorResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO = new LibraryDAOlmpl();
        List<DBooks> dBooks=libraryDAO.getDBooksBYname(request.getAuthor());

        CheckBookAuthorResponse checkBooksAuthorResponse=CheckBookAuthorResponse.newBuilder()
                .addAllDbooks(convertToBookListReponse(dBooks))
                .build();
        System.out.println(checkBooksAuthorResponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBooksAuthorResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void checkBooksByIsbn(CheckBookIsbnRequest request, StreamObserver<CheckBooksIsbnResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        DBooks dBooks=libraryDAO.getDBooksBYisbn(request.getIsbn());

        CheckBooksIsbnResponse checkBookIsbnResponse= CheckBooksIsbnResponse.newBuilder()
                .setDbook(convertToDBookReponse(dBooks))
                .build();
        System.out.println(checkBookIsbnResponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBookIsbnResponse);
        responseObserver.onCompleted();

    }
    @Override
    public void checkBooksDetail(CheckBooksDetailRequest request, StreamObserver<CheckBooksDetailResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        List<DBooksDetail> dBooksDetails=libraryDAO.getDBooksDetail(request.getIsbn());

        CheckBooksDetailResponse checkBooksDetailResponse= CheckBooksDetailResponse.newBuilder()
                .addAllDBooksDetailInfo(convertToDBooksDetailList(dBooksDetails))
                .build();
        System.out.println(checkBooksDetailResponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBooksDetailResponse);
        responseObserver.onCompleted();

    }
    @Override
    public void  bookBorrowService(BorrowRequest request,StreamObserver<BorrowReponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        boolean borrrowresults=libraryDAO.borrowBook(request.getBcode(),request.getReadername(),request.getReaderid());
        BorrowReponse borrowReponse=BorrowReponse.newBuilder()
                .setBorrowresult(borrrowresults)
                .build();
        System.out.println(borrowReponse);
        // 发送响应给客户端
        responseObserver.onNext(borrowReponse);
        responseObserver.onCompleted();
    }
    @Override
    public void bookReturnService(ReturnRequest request,StreamObserver<ReturnResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        boolean returnresults=libraryDAO.returnBook(request.getBcode());
        ReturnResponse returnResponse=ReturnResponse.newBuilder()
                .setReturnresult(returnresults)
                .build();
        System.out.println(returnResponse);
        // 发送响应给客户端
        responseObserver.onNext(returnResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void checkReaderBorrowed(CheckBorrowedRequest request,StreamObserver<CheckBurrowedResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        List<DBooksDetail> dBooksDetails=libraryDAO.getBooksOfReader(request.getReaderid());

        CheckBurrowedResponse checkBurrowedResponse=CheckBurrowedResponse.newBuilder()
                .addAllDBooksDetailInfo(convertToDBooksDetailList(dBooksDetails))
                .build();
        System.out.println(checkBurrowedResponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBurrowedResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void addNewBook(AddBookRequest request,StreamObserver<AddBookResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        boolean addresults=libraryDAO.addBook(convertDBooksInfoToDBooks(request.getDbooks()),convertDBooksDetailInfoToDBooksDetail(request.getDBooksdetail()));
        AddBookResponse addBookResponse=AddBookResponse.newBuilder()
                .setAddresult(addresults)
                .build();
        System.out.println(addBookResponse);
        // 发送响应给客户端
        responseObserver.onNext(addBookResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteBook(DeleteBookRequest request,StreamObserver<DeleteBookResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        boolean deleteresults=libraryDAO.deleteBook(request.getBcode());

        DeleteBookResponse deleteBookResponse=DeleteBookResponse.newBuilder()
                .setDeleteresult(deleteresults)
                .build();
        System.out.println(deleteBookResponse);
        // 发送响应给客户端
        responseObserver.onNext(deleteBookResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void checkBookBorrowed(CheckBookBorrowedRequest request,StreamObserver<CheckBookBorrowedReponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        DBooksDetail dBooksDetail=libraryDAO.getDetail(request.getBcode());

        CheckBookBorrowedReponse checkBookBorrowedReponse=CheckBookBorrowedReponse.newBuilder()
                .setDBooksDetailInfo(convertToDBooksDetailInfo(dBooksDetail))
                .build();
        System.out.println(checkBookBorrowedReponse);
        // 发送响应给客户端
        responseObserver.onNext(checkBookBorrowedReponse);
        responseObserver.onCompleted();
    }
    @Override
    public void alterIntroduction(AlterIntroRequest request,StreamObserver<AlterIntroResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();

        libraryDAO.saveIntroduction(convertIntroductionInfoToIntroduction(request.getIntro()));
        Introduction newIntroduction=libraryDAO.getIntroduction();

        AlterIntroResponse alterIntroResponse=AlterIntroResponse.newBuilder()
                        .setIntro(convertToIntroductionInfo(newIntroduction))
                        .build();
        System.out.println(alterIntroResponse);
        // 发送响应给客户端
        responseObserver.onNext(alterIntroResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void alterInformation(AlterInforRequest request,StreamObserver<AlterInferResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        libraryDAO.saveInformation(convertInformationInfoToInformation(request.getInfor()));
        List<Information> newInformationList=libraryDAO.getAllInformation();

        AlterInferResponse alterInferResponse=AlterInferResponse.newBuilder()
                .addAllInfoList(convertToInformationInfo(newInformationList))
                .build();
        System.out.println(alterInferResponse);
        // 发送响应给客户端
        responseObserver.onNext(alterInferResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteInformation(DeleteInfoRequest request,StreamObserver<DeleteInfoResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();

        libraryDAO.deleteInformation(request.getInfoHead());
        List<Information> information=libraryDAO.getAllInformation();

        DeleteInfoResponse deleteInfoResponse=DeleteInfoResponse.newBuilder()
                .addAllInfoList(convertToInformationInfo(information))
                .build();
        System.out.println(deleteInfoResponse);
        // 发送响应给客户端
        responseObserver.onNext(deleteInfoResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void alterRecom(AlterRecomRequest request,StreamObserver<AlterRecomResponse> responseObserver){
        System.out.println("Request received from client:\n" + request);
        LibraryDAO libraryDAO=new LibraryDAOlmpl();
        libraryDAO.saveRecommendation(convertRecomInfoToRecommendation(request.getRecom()));
        List<Recommendation> newRecom=libraryDAO.getAllRecommendation();

        AlterRecomResponse alterRecomResponse=AlterRecomResponse.newBuilder()
                .addAllRecomList(convertToRecomInfo(newRecom))
                .build();
        System.out.println(alterRecomResponse);
        // 发送响应给客户端
        responseObserver.onNext(alterRecomResponse);
        responseObserver.onCompleted();

    }
    public Recommendation convertRecomInfoToRecommendation(RecommendationInfo recommendationInfo){
        Recommendation recommendation=new Recommendation();
        recommendation.setRBookNum(recommendationInfo.getReBooknum());
        recommendation.setRBookName(recommendationInfo.getReBooksName());
        recommendation.setRBookAuthor(recommendationInfo.getReBooksAuthor());
        recommendation.setProfile(recommendationInfo.getProfile());
        return recommendation;
    }
    public Information convertInformationInfoToInformation(InformationInfo informationInfo){
        Information information=new Information();
        information.setHead(informationInfo.getInformationHead());
        information.setBody(informationInfo.getInformationBody());
        information.setYear(informationInfo.getInformationYear());
        information.setMonth(informationInfo.getInformationMonth());
        information.setDay(informationInfo.getInformationDay());
        return information;
    }
    public Introduction convertIntroductionInfoToIntroduction(IntroductionInfo introductionInfo){
        Introduction introduction=new Introduction();
        introduction.setHead(introductionInfo.getIntroductionHead());
        introduction.setBody(introductionInfo.getIntroductionBody());
        introduction.setYear(introductionInfo.getIntroductionYear());
        introduction.setMonth(introductionInfo.getIntroductionMonth());
        introduction.setDay(introductionInfo.getIntroductionDay());
        return introduction;

    }
    public DBooksDetailInfo convertToDBooksDetailInfo(DBooksDetail dBooksDetail){
        DBooksDetailInfo dBooksDetailInfo=DBooksDetailInfo.newBuilder()
                .setIsbn(dBooksDetail.getIsbn())
                .setBcode(dBooksDetail.getBcode())
                .setBookname(dBooksDetail.getBookname())
                .setCallnum(dBooksDetail.getCallnum())
                .setLocation(dBooksDetail.getLocation())
                .setIsborrowed(dBooksDetail.getIsBorrowed())
                .setReadername(dBooksDetail.getReadername())
                .setReaderid(dBooksDetail.getReaderid())
                .setDue(dBooksDetail.getDue())
                .build();
        return dBooksDetailInfo;
    }
    public DBooksDetail convertDBooksDetailInfoToDBooksDetail(DBooksDetailInfo dBooksDetailInfo){
        DBooksDetail dBooksDetail=new DBooksDetail();
        dBooksDetail.setIsbn(dBooksDetailInfo.getIsbn());
        dBooksDetail.setBcode(dBooksDetailInfo.getBcode());
        dBooksDetail.setBookname(dBooksDetail.getBookname());
        dBooksDetail.setCallnum(dBooksDetailInfo.getCallnum());
        dBooksDetail.setLocation(dBooksDetail.getLocation());
        dBooksDetail.setIsborrowed(dBooksDetailInfo.getIsborrowed());
        dBooksDetail.setReadername(dBooksDetail.getReadername());
        dBooksDetail.setReaderid(dBooksDetail.getReaderid());
        dBooksDetail.setDue(dBooksDetail.getDue());
        return dBooksDetail;
    }
    public DBooks convertDBooksInfoToDBooks(DBooksInfo dBooksInfo){
        DBooks dBooks=new DBooks();
        dBooks.setIsbn(dBooksInfo.getIsbn());
        dBooks.setBookname(dBooksInfo.getBookname());
        dBooks.setAuthor(dBooksInfo.getAuthor());
        dBooks.setPrice(dBooksInfo.getPrice());
        dBooks.setSubject(dBooksInfo.getSubject());
        dBooks.setStock(dBooksInfo.getStock());
        dBooks.setPublisher(dBooksInfo.getPublisher());
        dBooks.setGenre(dBooksInfo.getGenre());
        dBooks.setBrief(dBooksInfo.getBrief());
        return dBooks;
    }
    public List<DBooksDetailInfo> convertToDBooksDetailList(List<DBooksDetail> dBooksDetails){
        List<DBooksDetailInfo> dBooksDetailInfos=new ArrayList<>();
        for(DBooksDetail dBookDetail:dBooksDetails){
            DBooksDetailInfo dBooksDetailInfo=DBooksDetailInfo.newBuilder()
                    .setIsbn(dBookDetail.getIsbn())
                    .setBcode(dBookDetail.getBcode())
                    .setBookname(dBookDetail.getBookname())
                    .setCallnum(dBookDetail.getCallnum())
                    .setIsborrowed(dBookDetail.getIsBorrowed())
                    .setDue(dBookDetail.getDue())
                    .setReadername(dBookDetail.getReadername())
                    .setLocation(dBookDetail.getLocation())
                    .setReaderid(dBookDetail.getReaderid())
                    .build();
            dBooksDetailInfos.add(dBooksDetailInfo);
        }
        return dBooksDetailInfos;
    }
    public DBooksInfo convertToDBookReponse(DBooks dBooks){//构造成DBooksInfo消息体类型
        DBooksInfo dBooksInfo=DBooksInfo.newBuilder()
                .setIsbn(dBooks.getIsbn())
                .setBookname(dBooks.getBookname())
                .setAuthor(dBooks.getAuthor())
                .setPrice(dBooks.getPrice())
                .setSubject(dBooks.getSubject())
                .setStock(dBooks.getStock())
                .setPublisher(dBooks.getPublisher())
                .setGenre(dBooks.getGenre())
                .setBrief(dBooks.getBrief())
                .build();
        return  dBooksInfo;
    }
    public List<DBooksInfo> convertToBookListReponse(List<DBooks> dBooks){//构造成List<DBooksInfo>消息体类型
        List<DBooksInfo> dBooksInfoList=new ArrayList<>();//用于承接每个InformationInfo消息体
        for(DBooks dbook:dBooks){
            DBooksInfo dBooksInfo=DBooksInfo.newBuilder()
                    .setIsbn(dbook.getIsbn())
                    .setBookname(dbook.getBookname())
                    .setAuthor(dbook.getAuthor())
                    .setPrice(dbook.getPrice())
                    .setSubject(dbook.getSubject())
                    .setStock(dbook.getStock())
                    .setPublisher(dbook.getPublisher())
                    .setGenre(dbook.getGenre())
                    .setBrief(dbook.getBrief())
                    .build();
            dBooksInfoList.add(dBooksInfo);
        }
        return dBooksInfoList;
    }
    public IntroductionInfo convertToIntroductionInfo(Introduction introduction){//构造成introductionInfo消息体类型
        IntroductionInfo introductionInfo=IntroductionInfo.newBuilder()
                .setIntroductionHead(introduction.getHead())
                .setIntroductionBody(introduction.getBody())
                .setIntroductionYear(introduction.getYear())
                .setIntroductionMonth(introduction.getMonth())
                .setIntroductionDay(introduction.getDay())
                .build();
        return introductionInfo;
    }
    public List<InformationInfo> convertToInformationInfo(List<Information> information){//构造成informationInfo消息体类型
        List<InformationInfo> resultInformation=new ArrayList<>();//用于承接每个InformationInfo消息体
        for(Information info:information){
            InformationInfo informationInfo=InformationInfo.newBuilder()
                    .setInformationHead(info.getHead())
                    .setInformationBody(info.getBody())
                    .setInformationYear(info.getYear())
                    .setInformationMonth(info.getMonth())
                    .setInformationDay(info.getDay())
                    .build();
            resultInformation.add(informationInfo);
        }
        return resultInformation;
    }
    public List<RecommendationInfo> convertToRecomInfo(List<Recommendation> recommendations){//构造成RecommendationInfo消息体类型
        List<RecommendationInfo> resultrecom=new ArrayList<>();//用于承接每个InformationInfo消息体
        for(Recommendation recom:recommendations){
            RecommendationInfo recommendationInfo=RecommendationInfo.newBuilder()
                    .setReBooknum(recom.getRBookNum())
                    .setReBooksName(recom.getRBookName())
                    .setReBooksAuthor(recom.getRBookAuthor())
                    .setProfile(recom.getProfile())
                    .build();
            resultrecom.add(recommendationInfo);
        }
        return resultrecom;
    }

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(ServerInterceptors.intercept(new LibraryServicelmpl(), new CharsetInterceptor()))
                .build();

        server.start();
        System.out.println("Server started on port 8080");
        server.awaitTermination();
    }
    // 定义一个拦截器来设置字符编码
    public static class CharsetInterceptor implements ServerInterceptor {

        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call,
                Metadata headers,
                ServerCallHandler<ReqT, RespT> next) {

            // 设置响应的Content-Type标头为UTF-8
            Metadata.Key<String> contentTypeKey = Metadata.Key.of("Content-Type", Metadata.ASCII_STRING_MARSHALLER);
            headers.put(contentTypeKey, "application/grpc;charset=utf-8");

            // 调用下一个拦截器或处理程序
            return next.startCall(call, headers);
        }
    }

}


