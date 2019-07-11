package com.hm.oa.global;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO(常量类)
 * @Author Administrator
 * @Date 2019/7/7/007 0:49
 */
public class Constants {


    /**
     * 用户Session标识
     */
    public static final String SESSION_USER = "session_user";

    /**
     * 职务
     */
    public static final String POST_STAFF = "员工";
    public static final String POST_FM = "部门经理";
    public static final String POST_GM = "总经理";
    public static final String POST_CASHIER = "财务";


    public static List<String> getPost() {
        List<String> postList = new ArrayList<String>(4);
        postList.add(POST_STAFF);
        postList.add(POST_FM);
        postList.add(POST_GM);
        postList.add(POST_CASHIER);
        return postList;
    }

    /**
     * 费用类别
     */

    public static List<String> getFeeType() {
        List<String> fees = new ArrayList<String>();
        fees.add("交通");
        fees.add("餐饮");
        fees.add("办公");
        fees.add("住宿");
        return fees;
    }

    /**
     * 报销单状态
     */

    public static final String CLAIMVOCHER_CREATED = "新创建";
    public static final String CLAIMVOCHER_SUBMIT = "已提交";
    public static final String CLAIMVOCHER_APPROVED = "已审核";
    public static final String CLAIMVOCHER_BACK = "已打回";
    public static final String CLAIMVOCHER_TERMINATED = "已终止";
    public static final String CLAIMVOCHER_RECHECK = "待复审";
    public static final String CLAIMVOCHER_PAID = "已打款";

    /**
     * 审核额度
     */
    public static final double LIMIT_CHECK = 5000.00;

    /**
     * 报销单处理
     */
    public static final String DEAIL_CREATE = "创建";
    public static final String DEAIL_SUBMIT = "提交";
    public static final String DEAIL_UPDATE = "修改";
    public static final String DEAIL_BACK = "打回";
    public static final String DEAIL_REJECT = "拒绝";
    public static final String DEAIL_PASS = "通过";
    public static final String DEAIL_PAID = "打款";

}
