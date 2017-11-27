package com.example.lijinduo.mydemo.redbag.yanhua;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class DrawTopBase implements Runnable, Callback ,OnTouchListener{
    protected SurfaceView mSurfaceView;
    protected SurfaceHolder mSurfaceHolder;
    protected Context mContext;
    protected Rect mSurfaceRect = new Rect(0, 0, 0, 0);

    public DrawTopBase() {
        setSurfaceView(HolderSurfaceView.getInstance().getSurfaceView());
    }

    public void setSurfaceView(SurfaceView view) {
        mSurfaceView = view;
        mContext = mSurfaceView.getContext();
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceRect.set(new Rect(0, 0, mSurfaceView.getWidth(), mSurfaceView
                .getHeight()));
        set();
    }

    /**
     * 根据长宽来设置范围
     */
    public void set() {
        setRect(mSurfaceRect);
    }

    protected Thread mThread = null;

    /**
     * 开始，为了必免重复开始，定义线程变量
     */
    public void begin() {
        if (mThread == null) {
            mThread = new Thread(this);
            mThread.start();
        }
    }

    public void end() {
        mStatus = DrawStatus.Ending;
    }

    /**
     * 画内容
     *
     * @param canvas
     */
    protected void doWork(Canvas canvas) {

    }

    /**
     * 结束工作
     */
    protected void endWork() {

    }

    protected Paint mPaint = new Paint();

    /**
     * 清空
     *
     * @param canvas
     */
    protected void clear(Canvas canvas) {
        mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawPaint(mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC));
    }

    protected void clear() {
        synchronized (mSurfaceHolder) {
            Canvas canvas = this.mSurfaceHolder.lockCanvas();
            try {
                clear(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null)
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    protected void doChange() {
        change();
    }

    /**
     * 变化
     */
    protected void change() {

    }

    protected Rect mRect = new Rect(0, 0, 0, 0);

    public void setRect(Rect r) {
        mRect.set(r);
    }

    public Rect getRect() {
        return mRect;
    }

    protected DrawStatus mStatus = DrawStatus.NoWork;

    /**
     * 工作状态 noWork 没有工作 draw 在主循环中 end 正常结束 destroy 非正常结束
     *
     * @author gary
     *
     */
    protected enum DrawStatus {
        NoWork, Drawing, Ending, Destroyed
    };

    protected long mBegintime;

    @Override
    public void run() {
        mStatus = DrawStatus.Drawing;
        starttime = System.currentTimeMillis();
        mBegintime = System.currentTimeMillis();
        // 建立两次缓存
        clear();
        clear();
        while (mStatus == DrawStatus.Drawing) {
            synchronized (mSurfaceHolder) {
                Canvas canvas = this.mSurfaceHolder.lockCanvas(getRect());
                try {
                    if (canvas != null) {
                        clear(canvas);
                        doWork(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
                doChange();
            }
            calculatePerframe();

        }
        if (mStatus != DrawStatus.Destroyed)
            clear();
        mThread = null;
        endWork();
    }

    protected long starttime;
    // 每帧时间 60帧附近 第一次计算前使用 毫秒
    private float perframe = 16;

    private int count = 0;

    // 每隔多长时间测试一次帧时间
    private int mRefreshSpeed = 12;

    // 设定要求多长时间每帧 24帧每秒
    private float mFPS = 1000 / 12;
    private float sleep = mFPS;

    // 设置刷新频率
    public void setFPS(int fps) {
        mFPS = 1000 / fps;
    }

    /**
     * 等待时间
     */
    protected void calculatePerframe() {
        count++;
        if (count == mRefreshSpeed) // 由于每帧计算会降低游戏效率。20到50差不多
        {
            long timepass = System.currentTimeMillis();
            long time = timepass - starttime;
            perframe = time / mRefreshSpeed;// 每帧时间
            sleep = perframe > mFPS ? mFPS - (perframe - mFPS) / mRefreshSpeed
                    : mFPS;
            starttime = timepass;
            count = 0;
        }
        try {
            Thread.sleep((long) (sleep));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mSurfaceRect.set(new Rect(0, 0, width, height));
        set();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mStatus = DrawStatus.Destroyed;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return false;
    }
}