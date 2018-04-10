package com.example.lijinduo.mydemo.camera;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/27
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PuzzleBean {
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;

    public int getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }

    private int oldPosition;

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isMoveState() {
        return moveState;
    }

    public void setMoveState(boolean moveState) {
        this.moveState = moveState;
    }

    private int position;
    private boolean moveState;
}
