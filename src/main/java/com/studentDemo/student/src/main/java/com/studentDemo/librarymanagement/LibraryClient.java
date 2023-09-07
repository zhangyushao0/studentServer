package com.studentDemo.librarymanagement;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.studentDemo.librarymanagement.IdResponse;



public class LibraryClient {
    public static void main(String[] args) throws Exception{
        // 创建 gRPC 通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 8080)
                .usePlaintext()
                .build();

        // 创建 gRPC 存根
        LibraryServiceGrpc.LibraryServiceBlockingStub stub = LibraryServiceGrpc.newBlockingStub(channel);

        // 构造请求对象
        IdRequest request = IdRequest.newBuilder()
                .setId("your_student_id_here")
                .setName("your_student_name_here")
                .build();

        // 调用 loginto 函数
        IdResponse response = stub.loginto(request);

        System.out.println(response.getIntro().getIntroductionHead()+response.getInforList().get(0).getInformationHead());
        // 关闭通道
        channel.shutdown();
    }
}

