package com.hm.oa.dao;

import com.hm.oa.entity.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: oa
 * @Date: 2019/7/11 17:43
 * @Author: Mr.Han
 * @Description:
 */
@Repository("claimVoucherItemDao")
public interface ClaimVoucherItemDao {

 void insert(ClaimVoucherItem claimVoucherItem);

 void update(ClaimVoucherItem claimVoucherItem);

 void delete(int id);

 List<ClaimVoucherItem> searchByClaimVoucher(int cvid);

}
