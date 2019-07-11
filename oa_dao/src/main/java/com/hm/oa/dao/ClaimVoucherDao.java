package com.hm.oa.dao;

import com.hm.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: oa
 * @Date: 2019/7/11 14:25
 * @Author: Mr.Han
 * @Description:
 */
@Repository("cliamVoucherDao")
public interface ClaimVoucherDao {
 void insert(ClaimVoucher voucher);

 void update(ClaimVoucher voucher);

 void delete(int id);

 ClaimVoucher select(int id);

 List<ClaimVoucher> selectByCreateSn(String sn);

 List<ClaimVoucher> selectByNextDealSn(String ndsn);



}
