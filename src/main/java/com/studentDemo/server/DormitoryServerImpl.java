package com.studentDemo.server;

import io.grpc.stub.StreamObserver;

import com.studentDemo.dormitory.*;
import com.studentDemo.dormitory.DormitoryProto;
import com.studentDemo.dormitory.DormitoryServerGrpc;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class DormitoryServerImpl extends DormitoryServerGrpc.DormitoryServerImplBase{

    private List<RepairMesssage> convertToRepairMessages(List<Repair> repairs){
        List<RepairMesssage> repairMesssages=new ArrayList<>();
        for(Repair repair:repairs){
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
            repairMesssages.add(repairMesssage);

        }
        return repairMesssages;
    }

    private List<WaterAndEleMassage> convertToWaterAndEle(List<WaterAndEle>waterAndEles){
        List<WaterAndEleMassage> waterAndEleMassages=new ArrayList<>();
        for(WaterAndEle waterAndEle:waterAndEles){
            WaterAndEleMassage waterAndEleMassage=WaterAndEleMassage.newBuilder()
                    .setWalId(waterAndEle.getWalId())
                    .setWalDate(waterAndEle.getWalDate())
                    .setWalNum(waterAndEle.getWalNum())
                    .setWalType(waterAndEle.getWalType())
                    .build();
            waterAndEleMassages.add(waterAndEleMassage);
        }
        return waterAndEleMassages;
    }

    private DormitoryMessage convertToDormitoryMessage(Dormitory dormitory){
        DormitoryMessage dormitoryMessage=DormitoryMessage.newBuilder()
                .setDorId(dormitory.getDorId())
                .setStuName(dormitory.getStuName())
                .setStuNum(dormitory.getStuNum())
                .setDorNum(dormitory.getDorNum())
                .setBedNum(dormitory.getBedNum())
                .addAllRepair(convertToRepairMessages(dormitory.getRepair()))
                .addAllWaterAndEle(convertToWaterAndEle(dormitory.getWaterAndEle()))
                .build();
        return dormitoryMessage;
    }

    private DormitoryMessage convertToDorMessageRe(Dormitory dormitory){
        DormitoryMessage dormitoryMessage=DormitoryMessage.newBuilder()
                .addAllRepair(convertToRepairMessages(dormitory.getRepair()))
                .build();
        return dormitoryMessage;
    }

    private DormitoryMessage convertToDorMessageWal(Dormitory dormitory){
        DormitoryMessage dormitoryMessage=DormitoryMessage.newBuilder()
                .addAllWaterAndEle(convertToWaterAndEle(dormitory.getWaterAndEle()))
                .build();
        return dormitoryMessage;
    }

    @Override
    public void getDormitoryId(getDormitoryRequest request, StreamObserver<getDormitoryResponse> responseObserver) {
        System.out.println("Request received from client:\n"+request);
        Dormitory dormitory=new Dormitory();
        dormitory.setStuNum(request.getId());
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitory=dormitoryDAO.getStuByNum(request.getId());
        getDormitoryResponse response=getDormitoryResponse.newBuilder()
                .setProduct(convertToDormitoryMessage(dormitory))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getDormitories(getDormitoriesRequest request, StreamObserver<getDormitoriesResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();

        List<Dormitory> dormitories=dormitoryDAO.getAllStuDor();

        List<DormitoryMessage> dormitoryMessages=new ArrayList<>();
        getDormitoriesResponse.Builder builder =getDormitoriesResponse.newBuilder();
        for (Dormitory dormitory:dormitories) {
            DormitoryMessage dormitoryMessage = DormitoryMessage.newBuilder()
                    .setDorId(dormitory.getDorId())
                    .setStuName(dormitory.getStuName())
                    .setStuNum(dormitory.getStuNum())
                    .setDorNum(dormitory.getDorNum())
                    .setBedNum(dormitory.getBedNum())
                    .build();
        builder.addProducts(dormitoryMessage);
        }
        getDormitoriesResponse response=builder.build();
        System.out.println(response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setDormitory(setDormitoryRequest request, StreamObserver<setDormitoryResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Dormitory dormitory=new Dormitory();
        if(request.getProduct().getStuNum()==0){
            dormitory.setStuNum(0);
        }else {
            dormitory.setStuNum(request.getProduct().getStuNum());
        }
        dormitory.setDorId(request.getProduct().getDorId());
        dormitory.setStuName(request.getProduct().getStuName());
        dormitory.setStuNum(request.getProduct().getStuNum());
        dormitory.setDorNum(request.getProduct().getDorNum());
        dormitory.setBedNum(request.getProduct().getBedNum());

        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitoryDAO.saveDormi(dormitory);
        setDormitoryResponse response=setDormitoryResponse.newBuilder().setId(dormitory.getStuNum()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getDormitoryIdRepair(getDormitoryRequest request, StreamObserver<getDormitoryResponse> responseObserver) {
        System.out.println("Request received from client:\n"+request);
        Dormitory dormitory=new Dormitory();
        dormitory.setStuNum(request.getId());
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitory=dormitoryDAO.getStuByNum(request.getId());
        getDormitoryResponse response=getDormitoryResponse.newBuilder()
                .setProduct(convertToDorMessageRe(dormitory))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getDormitoryIdWal(getDormitoryRequest request, StreamObserver<getDormitoryResponse> responseObserver) {
        System.out.println("Request received from client:\n"+request);
        Dormitory dormitory=new Dormitory();
        dormitory.setStuNum(request.getId());
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitory=dormitoryDAO.getStuByNum(request.getId());
        getDormitoryResponse response=getDormitoryResponse.newBuilder()
                .setProduct(convertToDorMessageWal(dormitory))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void makeRepair(makeRepairRequest request, StreamObserver<makeRepairResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Dormitory dormitory=new Dormitory();
        if(request.getId()==0){
            dormitory.setStuNum(0);
        }else {
            dormitory.setStuNum(request.getId());
        }
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitory=dormitoryDAO.getStuByNum(request.getId());
        RepairMesssage repairMesssage=request.getProduct();
        Repair repair=new Repair();
        repair.setReId(repairMesssage.getReId());
        repair.setReType(repairMesssage.getReType());
        repair.setReCampus(repairMesssage.getReCampus());
        repair.setRePlace(repairMesssage.getRePlace());
        repair.setRePhone(repairMesssage.getRePhone());
        repair.setReDate(repairMesssage.getReDate());
        repair.setReCondition(repairMesssage.getReCondition());
        repair.setReEvaluate(repairMesssage.getReEvaluate());
        repair.setDormitory(dormitory);

        dormitoryDAO.saveDorRepair(repair);
        makeRepairResponse response= makeRepairResponse.newBuilder().setSucess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void makeWaterAndEle(makeWaterAndEleRequest request, StreamObserver<makeWaterAndEleResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Dormitory dormitory=new Dormitory();
        if(request.getId()==0){
            dormitory.setStuNum(0);
        }else {
            dormitory.setStuNum(request.getId());
        }
        DormitoryDAOImpl dormitoryDAO=new DormitoryDAOImpl();
        dormitory=dormitoryDAO.getStuByNum(request.getId());
        WaterAndEleMassage waterAndEleMassage=request.getProduct();
        WaterAndEle waterAndEle=new WaterAndEle();
        waterAndEle.setWalId(waterAndEleMassage.getWalId());
        waterAndEle.setWalDate(waterAndEleMassage.getWalDate());
        waterAndEle.setWalNum(waterAndEleMassage.getWalNum());
        waterAndEle.setWalType(waterAndEleMassage.getWalType());
        waterAndEle.setDormitory(dormitory);

        dormitoryDAO.saveDorWal(waterAndEle);
        makeWaterAndEleResponse response= makeWaterAndEleResponse.newBuilder().setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}


