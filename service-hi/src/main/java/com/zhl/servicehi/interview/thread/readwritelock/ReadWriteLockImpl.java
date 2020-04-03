package com.zhl.servicehi.interview.thread.readwritelock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 11:00
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    /**
     * 定义对象锁
     */
    private final Object MUTEX = new Object();

    /**
     * 当前有多少个线程正在写入
     */
    private int writingWriters = 0;

    /**
     * 当前有多少个线程正在等待写入
     */
    private int waitingWriters = 0;

    /**
     * 当前有多少个线程正在read
     */
    private int readingReaders = 0;

    /**
     * read和write的偏好设置
     */
    private boolean preferWriter;

    /**
     * 默认情况下preferWriter为true
     */
    public ReadWriteLockImpl(){
        this(true);
    }

    /**
     * 构造ReadWriteLockImpl并且传入preferWriter
     * @param preferWriter boolean
     */
    public ReadWriteLockImpl(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    /**
     * 使写线程的数量增加
     */
    void incrementWritingWriters(){
        this.waitingWriters++;
    }

    /**
     * 使等待写入的线程数量增加
     */
    void incrementWaitingWriters(){
        this.waitingWriters++;
    }

    /**
     * 使读线程数量增加
     */
    void incrementReadingReaders(){
        this.readingReaders++;
    }

    /**
     * 使写线程的数量减少
     */
    void decrementWritingWriters(){
        this.waitingWriters--;
    }

    /**
     * 使等待获取写入锁的数量减一
     */
    void decrementWaitingWriters(){
        this.waitingWriters--;
    }

    /**
     * 使读取线程的数量减少
     */
    void decrementReadingReaders(){
        this.readingReaders--;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    /**
     * 获取对象锁
     * @return object
     */
    Object getMutex(){
        return this.MUTEX;
    }

    /**
     * 获取当前是否偏向写锁
     * @return boolean
     */
    boolean getPreferWrite(){
        return this.preferWriter;
    }

    /**
     * 设置写锁偏好
     * @param preferWriter Boolean
     */
    void changePrefer(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

}
