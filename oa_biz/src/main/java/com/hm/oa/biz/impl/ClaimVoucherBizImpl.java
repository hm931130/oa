package com.hm.oa.biz.impl;

import com.hm.oa.biz.ClaimVoucherBiz;
import com.hm.oa.dao.ClaimVoucherDao;
import com.hm.oa.dao.ClaimVoucherItemDao;
import com.hm.oa.dao.DealRecordDao;
import com.hm.oa.dao.EmployeeDao;
import com.hm.oa.entity.ClaimVoucher;
import com.hm.oa.entity.ClaimVoucherItem;
import com.hm.oa.entity.DealRecord;
import com.hm.oa.entity.Employee;
import com.hm.oa.global.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/11/011 20:35
 */
@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

 @Autowired
 private ClaimVoucherDao claimVoucherDao;
 @Autowired
 private ClaimVoucherItemDao claimVoucherItemDao;

 @Autowired
 private DealRecordDao dealRecordDao;

 @Autowired
 private EmployeeDao employeeDao;

 public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {

  claimVoucher.setCreateTime(new Date());
  claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
  claimVoucher.setStatus(Constants.CLAIMVOCHER_CREATED);
  claimVoucherDao.insert(claimVoucher);
  for (ClaimVoucherItem item : items) {
   item.setClaimVoucherId(claimVoucher.getId());
   claimVoucherItemDao.insert(item);
  }
 }

 public ClaimVoucher get(int id) {
  return claimVoucherDao.select(id);
 }

 public List<ClaimVoucherItem> getItems(int cvid) {
  return claimVoucherItemDao.searchByClaimVoucher(cvid);
 }

 public List<DealRecord> getRecords(int cvid) {
  return dealRecordDao.searchByVoucher(cvid);
 }

 public List<ClaimVoucher> getForSelf(String sn) {
  return claimVoucherDao.selectByCreateSn(sn);
 }

 public List<ClaimVoucher> getForDeal(String sn) {
  return claimVoucherDao.selectByNextDealSn(sn);
 }

 public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
  claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
  claimVoucher.setStatus(Constants.CLAIMVOCHER_CREATED);
  claimVoucherDao.update(claimVoucher);
  List<ClaimVoucherItem> olds = claimVoucherItemDao.searchByClaimVoucher(claimVoucher.getId());

  for (ClaimVoucherItem old : olds) {
   boolean isHave = false;
   for (ClaimVoucherItem item : items) {
    if (item.getId() == old.getId()) {
     isHave = true;
     break;
    }
   }
   if (!isHave) {
    claimVoucherItemDao.delete(old.getId());
   }
  }

  for (ClaimVoucherItem item : items) {
   item.setClaimVoucherId(claimVoucher.getId());
   if (item.getId() != null && item.getId() > 0) {
    claimVoucherItemDao.update(item);
   } else {
    claimVoucherItemDao.insert(item);
   }
  }

 }

 public void submint(int id) {
  ClaimVoucher claimVoucher = claimVoucherDao.select(id);
  Employee employee = employeeDao.select(claimVoucher.getCreateSn());
  claimVoucher.setStatus(Constants.CLAIMVOCHER_SUBMIT);
  claimVoucher.setNextDealSn(employeeDao.selectByDepartmetAndPost(employee.getDepartmentSn(), Constants.POST_FM).get(0).getSn());
  claimVoucherDao.update(claimVoucher);

  /**
   * 提交信息记录
   */
  DealRecord dealRecord = new DealRecord();
  dealRecord.setDealWay(Constants.DEAIL_SUBMIT);
  dealRecord.setDealSn(employee.getSn());
  dealRecord.setClaimVoucherId(claimVoucher.getId());
  dealRecord.setDealResult(Constants.CLAIMVOCHER_SUBMIT);
  dealRecord.setDealTime(new Date());
  dealRecord.setComment("无");
  dealRecordDao.insert(dealRecord);
 }

 public void deal(DealRecord dealRecord) {

  int voucherId = dealRecord.getClaimVoucherId();
  ClaimVoucher claimVoucher = claimVoucherDao.select(voucherId);

  Employee employee = employeeDao.select(dealRecord.getDealSn());

  dealRecord.setDealTime(new Date());

  if (dealRecord.getDealWay().equals(Constants.DEAIL_PASS)) {
   if (claimVoucher.getTotalAmount() < Constants.LIMIT_CHECK || employee.getPost().equals(Constants.POST_GM)) {
    claimVoucher.setStatus(Constants.CLAIMVOCHER_APPROVED);
    claimVoucher.setNextDealSn(employeeDao.selectByDepartmetAndPost(null, Constants.POST_CASHIER).get(0).getSn());
    dealRecord.setDealResult(Constants.CLAIMVOCHER_APPROVED);
   } else {
    //需要复审
    claimVoucher.setStatus(Constants.CLAIMVOCHER_RECHECK);
    claimVoucher.setNextDealSn(employeeDao.selectByDepartmetAndPost(null, Constants.POST_GM).get(0).getSn());
    dealRecord.setDealResult(Constants.CLAIMVOCHER_RECHECK);
   }

  } else if (dealRecord.getDealWay().equals(Constants.DEAIL_BACK)) {
   claimVoucher.setStatus(Constants.CLAIMVOCHER_BACK);
   claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

   dealRecord.setDealResult(Constants.CLAIMVOCHER_BACK);

  } else if (dealRecord.getDealWay().equals(Constants.DEAIL_REJECT)) {
   claimVoucher.setStatus(Constants.CLAIMVOCHER_TERMINATED);
   claimVoucher.setNextDealSn("");

   dealRecord.setDealResult(Constants.CLAIMVOCHER_TERMINATED);

  } else if (dealRecord.getDealWay().equals(Constants.DEAIL_PAID)) {
   claimVoucher.setStatus(Constants.CLAIMVOCHER_PAID);
   claimVoucher.setNextDealSn("");
   dealRecord.setDealResult(Constants.CLAIMVOCHER_PAID);
  }

  claimVoucherDao.update(claimVoucher);
  dealRecordDao.insert(dealRecord);
 }

}
