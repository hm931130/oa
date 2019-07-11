package com.hm.oa.dto;

import com.hm.oa.entity.ClaimVoucher;
import com.hm.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/11/011 20:43
 */
public class ClaimVoucherInfo {

    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
