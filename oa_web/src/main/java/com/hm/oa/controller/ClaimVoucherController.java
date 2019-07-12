package com.hm.oa.controller;

import com.hm.oa.biz.ClaimVoucherBiz;
import com.hm.oa.dto.ClaimVoucherInfo;
import com.hm.oa.entity.DealRecord;
import com.hm.oa.entity.Employee;
import com.hm.oa.global.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/11/011 20:44
 */
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

 @Autowired
 private ClaimVoucherBiz claimVoucherBiz;


 @RequestMapping("/to_add")
 public String toAdd(Map<String, Object> map) {
  map.put("items", Constants.getFeeType());
  map.put("info", new ClaimVoucherInfo());
  return "claim_voucher_add";
 }

 @RequestMapping("/add")
 public String add(HttpSession session, ClaimVoucherInfo info) {
  Employee employee = (Employee) session.getAttribute(Constants.SESSION_USER);
  info.getClaimVoucher().setCreateSn(employee.getSn());
  claimVoucherBiz.save(info.getClaimVoucher(), info.getItems());
  return "redirect:deal";
 }

 @RequestMapping("/detail")
 public String detail(int id, Map<String, Object> map) {
  map.put("claimVoucher", claimVoucherBiz.get(id));
  map.put("items", claimVoucherBiz.getItems(id));
  map.put("records", claimVoucherBiz.getRecords(id));
  return "claim_voucher_detail";
 }

 @RequestMapping("/self")
 public String self(HttpSession session, Map<String, Object> map) {
  Employee employee = (Employee) session.getAttribute(Constants.SESSION_USER);
  map.put("list", claimVoucherBiz.getForSelf(employee.getSn()));
  return "claim_voucher_self";
 }

 @RequestMapping("/deal")
 public String deal(HttpSession session, Map<String, Object> map) {
  Employee employee = (Employee) session.getAttribute(Constants.SESSION_USER);
  map.put("list", claimVoucherBiz.getForDeal(employee.getSn()));
  return "claim_voucher_deal";
 }

 @RequestMapping("/to_update")
 public String toUpdate(int id, Map<String, Object> map) {
  ClaimVoucherInfo info = new ClaimVoucherInfo();
  info.setClaimVoucher(claimVoucherBiz.get(id));
  info.setItems(claimVoucherBiz.getItems(id));
  map.put("items", Constants.getFeeType());
  map.put("info", info);
  return "claim_voucher_update";
 }


 @RequestMapping("/update")
 public String update(HttpSession session, ClaimVoucherInfo info) {
  Employee employee = (Employee) session.getAttribute(Constants.SESSION_USER);
  info.getClaimVoucher().setCreateSn(employee.getSn());
  claimVoucherBiz.update(info.getClaimVoucher(), info.getItems());
  return "redirect:deal";
 }

 @RequestMapping("/submit")
 public String toUpdate(int id) {
  claimVoucherBiz.submint(id);
  return "redirect:deal";
 }

 @RequestMapping("/to_check")
 public String toCheck(int id, Map<String, Object> map) {
  map.put("claimVoucher", claimVoucherBiz.get(id));
  map.put("items", claimVoucherBiz.getItems(id));
  map.put("records", claimVoucherBiz.getRecords(id));
  DealRecord dealRecord = new DealRecord();
  dealRecord.setClaimVoucherId(id);
  map.put("record", dealRecord);
  return "claim_voucher_check";
 }

 @RequestMapping("check")
 public String check(HttpSession session, DealRecord dealRecord) {
  dealRecord.setDealSn(((Employee) session.getAttribute(Constants.SESSION_USER)).getSn());
  claimVoucherBiz.deal(dealRecord);
  return "redirect:deal";
 }


}
