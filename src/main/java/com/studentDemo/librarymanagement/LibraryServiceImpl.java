package com.studentDemo.librarymanagement;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import io.grpc.stub.StreamObserver;

public class LibraryServiceImpl extends LibraryServiceGrpc.LibraryServiceImplBase {
    @Override
    public void loginto(idRequest request, StreamObserver<idResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        // 1 处理request
        // 2 生成response
        idResponse response = idResponse.newBuilder()
                // .setId(request.getId())
                // .setName(request.getName())
                .setIdentity("0")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
