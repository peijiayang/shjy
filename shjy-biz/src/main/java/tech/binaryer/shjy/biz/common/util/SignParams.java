package tech.binaryer.shjy.biz.common.util;

public class SignParams {
    /**
     * token生成签名校验
     */
    public static final String SIGN_JWC="ZHIJIAPINGTAI";

    /**
     * HEADER存储名称
     */
    public static final String TOKEN_NAME="token";
    /**
     * 任务执行状态
     */
    public static final String TASK_STATUS_UNDO="队列中";
    public static final String TASK_STATUS_DOING="进行中";
    public static final String TASK_STATUS_DOFAIL="失败";
    public static final String TASK_STATUS_DOSUCCESS="成功";
    /**
     * 退款清分
     *
     */
    public static final int TASK_CATE_REFOUND = 1;
}
