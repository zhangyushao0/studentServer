package com.studentDemo.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerBuilder serverBuilder=ServerBuilder.forPort(8080);
        serverBuilder.addService(new DormitoryServerImpl());
        Server server=serverBuilder.build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }

}
