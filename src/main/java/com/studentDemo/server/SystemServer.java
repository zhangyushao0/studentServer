package com.studentDemo.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.studentDemo.student.StudentServiceImpl;
import com.studentDemo.user.Student;
import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.Session;

public class SystemServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new StudentServiceImpl())
                .build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }
}
