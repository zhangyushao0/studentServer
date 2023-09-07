package com.studentDemo.dormitory;

import java.util.List;

public interface DormitoryDAO {

    //管理员功能
    void saveDormi(Dormitory dormitory);//添加学生住宿情况
    List<Dormitory> getAllStuDor();//返回所有学生宿舍情况
    void deleteDormi(long stuNum);//删除学生宿舍
    // void updateDorRepair(Repair repair);//修改报修情况
    void saveDorWal(WaterAndEle waterAndEle);//增加水电费状况
    //List<Repair> getAllDorRar();

    //学生功能
    Dormitory getStuByNum(long stuNum);//查询学生宿舍
    void saveDorRepair(Repair repair);//添加报修情况
    List<Repair> getALLDorRe();//查看我的报修
    List<WaterAndEle> getAllDorWal();//查看我的水电费

}