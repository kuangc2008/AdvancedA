package com.kc.widget.pullrefreshview;

/**
 * Run a hook runnable, the runnable will run only once.
 * After the runnable is done, call resume to resume.
 * Once run, call takeover will directory call the resume action
 */
public abstract class PtrUIHandlerHook implements Runnable {

    private Runnable mResumeAction;
    private static final byte STATUS_PREPARE = 0;
    private static final byte STATUS_IN_HOOK = 1;
    private static final byte STATUS_RESUMED = 2;
    private byte mStatus = STATUS_PREPARE;

    public void takeOver() {
        takeOver(null);
    }

    /**
     * prepare状态下，调用taskOver，会执行子类的run方法，状态为IN_HOOK，进而调用啥都没用啦
     * 必须进行一次reset才能再调用
     *
     * <BR>
     * 如果调用resume，则会调用属性里的run方法，并taskOver是重复一遍流程
     * @param resumeAction
     */
    public void takeOver(Runnable resumeAction) {
        if (resumeAction != null) {
            mResumeAction = resumeAction;
        }
        switch (mStatus) {
            case STATUS_PREPARE:
                mStatus = STATUS_IN_HOOK;
                run();
                break;
            case STATUS_IN_HOOK:
                break;
            case STATUS_RESUMED:
                resume();
                break;
        }
    }

    public void reset() {
        mStatus = STATUS_PREPARE;
    }

    public void resume() {
        if (mResumeAction != null) {
            mResumeAction.run();
        }
        mStatus = STATUS_RESUMED;
    }

    /**
     * Hook should always have a resume action, which is hooked by this hook.
     *
     * @param runnable
     */
    public void setResumeAction(Runnable runnable) {
        mResumeAction = runnable;
    }
}