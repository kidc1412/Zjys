package cnzjys.zhuimj.zjys.model.bean;

public class UploadBean {
    long currentBytesCount;
    long totalBytesCount;

    public UploadBean(long currentBytesCount, long totalBytesCount) {
        this.currentBytesCount = currentBytesCount;
        this.totalBytesCount = totalBytesCount;
    }

    public long getCurrentBytesCount() {
        return currentBytesCount;
    }

    public void setCurrentBytesCount(long currentBytesCount) {
        this.currentBytesCount = currentBytesCount;
    }

    public long getTotalBytesCount() {
        return totalBytesCount;
    }

    public void setTotalBytesCount(long totalBytesCount) {
        this.totalBytesCount = totalBytesCount;
    }
}
