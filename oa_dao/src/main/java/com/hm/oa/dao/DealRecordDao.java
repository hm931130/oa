package com.hm.oa.dao;

import com.hm.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: oa
 * @Date: 2019/7/11 18:02
 * @Author: Mr.Han
 * @Description:
 */
@Repository("dealRecordDao")
public interface DealRecordDao {

 void insert(DealRecord dealRecord);


 List<DealRecord> searchByVoucher(int cvid);

}
