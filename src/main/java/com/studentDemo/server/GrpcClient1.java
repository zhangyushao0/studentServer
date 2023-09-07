package com.studentDemo.server;

import com.google.protobuf.ProtocolStringList;
import com.studentDemo.dormitory.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class GrpcClient1 {
    public static void main(String[] args) {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",8080)
                .usePlaintext().build();
        DormitoryServerGrpc.DormitoryServerBlockingStub dormitoryServer
                =DormitoryServerGrpc.newBlockingStub(managedChannel);


        //-----------学生---------（前端提供提供学号查询） setId函数里的数值即为学号

        //查看水电
        /*getDormitoryResponse response=dormitoryServer.getDormitoryIdWal
                (getDormitoryRequest.newBuilder().setId(666).build());
        System.out.println(response.getProduct());*/

        //查看报修
        /*getDormitoryResponse response=dormitoryServer.getDormitoryIdRepair
                (getDormitoryRequest.newBuilder().setId(666).build());
        System.out.println(response.getProduct());*/

        //添加报修信息
        /*Repair repair=new Repair();
        //repair.setReId(11);
        repair.setReType("12");
        repair.setReCampus("12");
        repair.setRePlace("12");
        repair.setRePhone("12");
        repair.setReDate("12");
        repair.setReCondition("21");
        repair.setReEvaluate("12");
        System.out.println(repair);
        RepairMesssage repairMesssage=RepairMesssage.newBuilder()
                    .setReId(repair.getReId())
                    .setReType(repair.getReType())
                    .setReCampus(repair.getReCampus())
                    .setRePlace(repair.getRePlace())
                    .setRePhone(repair.getRePhone())
                    .setReDate(repair.getReDate())
                    .setReCondition(repair.getReCondition())
                    .setReEvaluate(repair.getReEvaluate())
                    .build();

        makeRepairResponse response=dormitoryServer.makeRepair
                (makeRepairRequest.newBuilder().setId(666).setProduct(repairMesssage).build());
        System.out.println(response);*/

        //查看宿舍信息
        /*getDormitoryResponse response=dormitoryServer.getDormitoryId
                (getDormitoryRequest.newBuilder().setId(666).build());
        System.out.println(response.getProduct());*/

        //-----------------管理员-----------------

        //添加宿舍信息
        /*测试用数据
        Dormitory dormitory=new Dormitory();
        dormitory.setDorId(8);
        dormitory.setStuName("1");
        dormitory.setStuNum(95);
        dormitory.setDorNum("5");
        dormitory.setBedNum(6);
        DormitoryMessage dormitoryMessage = DormitoryMessage.newBuilder()
                .setDorId(dormitory.getDorId())
                .setStuName(dormitory.getStuName())
                .setStuNum(dormitory.getStuNum())
                .setDorNum(dormitory.getDorNum())
                .setBedNum(dormitory.getBedNum())
                .build();
        System.out.println(dormitoryMessage);
        存入数据
        setDormitoryResponse response=dormitoryServer.setDormitory
                (setDormitoryRequest.newBuilder().setProduct(dormitoryMessage).build());
        System.out.println(response);*/

        //显示所有宿舍信息
        getDormitoriesResponse response=dormitoryServer.getDormitories
                (getDormitoriesRequest.newBuilder().build());
        System.out.println(response);

        //添加水电信息
        /*WaterAndEle waterAndEle=new WaterAndEle();
        waterAndEle.setWalId(4);
        waterAndEle.setWalDate("5");
        waterAndEle.setWalNum(16);
        waterAndEle.setWalType("df");
        System.out.println(waterAndEle);
        WaterAndEleMassage waterAndEleMassage=WaterAndEleMassage.newBuilder()
                .setWalId(waterAndEle.getWalId())
                .setWalDate(waterAndEle.getWalDate())
                .setWalNum(waterAndEle.getWalId())
                .setWalType(waterAndEle.getWalType())
                .build();

        makeWaterAndEleResponse response=dormitoryServer.makeWaterAndEle
                (makeWaterAndEleRequest.newBuilder().setId(666).setProduct(waterAndEleMassage).build());
        System.out.println(response);*/



        managedChannel.shutdown();


    }
}
