package com.studentDemo.student;

import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getStudentInfo(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        StudentResponse response = StudentResponse.newBuilder()
                .setId(request.getId())
                .setName("John Doe")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
