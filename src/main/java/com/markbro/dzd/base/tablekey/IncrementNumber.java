package com.markbro.dzd.base.tablekey;

/**
 * Created by Administrator on 2016/6/20.
 */
public abstract  class IncrementNumber {
    /** 区间最大值 */
    protected int intervalMax = 0;

    /** 每次增加量 */
    protected int interval = 20;

    /** 预定的最大值 */
    protected int maxNum = 9999;

    /** 序列号 */
    protected int serialNum = -1;
    public IncrementNumber() {}

    public IncrementNumber(int interval, int maxNum) {
        this.interval = interval;
        this.maxNum = maxNum;
    }

    public synchronized int cal() throws Exception {
        if (serialNum == -1) {
            serialNum = initStartNum(); // 已经使用的序列号一定 小于 缓存的序列号
            intervalMax = serialNum + interval;
            updateStartNum(intervalMax);
            return serialNum;
        }
        if (isMax(serialNum)) { // 达到预定的最大值
            resetSerialNum();
            return serialNum;
        }
        serialNum++;
        if (serialNum >= (intervalMax - 1)) { // 到达区间最大值
            intervalMax += interval;
            updateStartNum(intervalMax);
        }
        return serialNum;
    }

    /**
     * 初始化序列号，从缓存系统中来，比如数据库、文件等
     * @return 初始序列号
     * @throws Exception
     */
    public abstract int initStartNum() throws Exception;

    /**
     * 更新区间最大值到缓存系统，比如数据库、文件中。
     * @param intervalMax 区间最大值
     * @throws Exception
     */
    public abstract void updateStartNum(int intervalMax) throws Exception;

    /**
     * 重置序列号，从1开始
     */
    protected void resetSerialNum() throws Exception {
        this.serialNum = 1;
        intervalMax = serialNum + interval;
        updateStartNum(intervalMax);
    }

    /**
     * 是否是最大值
     * @param num
     * @return
     */
    private boolean isMax(int num) {
        return num >= maxNum;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getMaxNum() {
        return this.maxNum;
    }


}
