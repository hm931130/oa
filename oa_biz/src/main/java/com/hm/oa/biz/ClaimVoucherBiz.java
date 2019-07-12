package com.hm.oa.biz;

import com.hm.oa.entity.ClaimVoucher;
import com.hm.oa.entity.ClaimVoucherItem;
import com.hm.oa.entity.DealRecord;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/11/011 20:33
 */
public interface ClaimVoucherBiz {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);

    List<ClaimVoucherItem> getItems(int cvid);

    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher,List<ClaimVoucherItem> items);

    void submint(int id);

    void deal(DealRecord dealRecord);

}
