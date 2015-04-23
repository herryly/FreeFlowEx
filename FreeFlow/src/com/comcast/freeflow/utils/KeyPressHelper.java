/**
 * Generated by smali2java 1.0.0.558
 * Copyright (C) 2013 Hensence.com
 */

package com.comcast.freeflow.utils;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

public class KeyPressHelper {
    private Handler mHandler;
    private KeyPressHelper.OnKeyPressListener mOnKeyPressListener;
    private static final String TAG = KeyPressHelper.class.getName();
    private boolean mPressed = false;
    private boolean mLongPressed = false;
    private int mLastKeyCode = -0x3e8;
    private boolean mActive = false;

    public interface OnKeyPressListener {
        public void onKeyLongPressedStart(int keyCode);

        public void onKeyLongPressedEnd(int keyCode);

        public void onKeyPressed(int keyCode);
    }

    public KeyPressHelper() {
        mHandler = new Handler();
    }

    public void dispatchKeyEvent(KeyEvent event) {
        Log.i(TAG, "dispatchKeyEvent: keyCode = " + event.getKeyCode()
                + ", action = " + event.getAction());
        int keyCode = event.getKeyCode();
        if (keyCode == 0) {
            return;
        }
        if (event.getAction() == 0) {
            if ((mPressed) && (mLastKeyCode != -0x1)
                    && (keyCode != mLastKeyCode)) {
                onKeyUp(keyCode);
            }
            if ((mPressed) && (!mLongPressed)) {
                if (mOnKeyPressListener != null) {
                    mOnKeyPressListener.onKeyLongPressedStart(keyCode);
                }
                mLongPressed = true;
                longPressed(keyCode);
            }
            mPressed = true;
            mLastKeyCode = keyCode;
            mActive = true;
            return;
        }
        if (event.getAction() == 0x1) {
            onKeyUp(keyCode);
        }
    }

    private void onKeyUp(int keyCode) {
        if (mOnKeyPressListener != null) {
            if (mLongPressed) {
                mOnKeyPressListener.onKeyLongPressedEnd(keyCode);
            } else {
                mOnKeyPressListener.onKeyPressed(keyCode);
            }
        }
        mPressed = false;
        mLongPressed = false;
        mActive = false;
        mLastKeyCode = -0x1;
    }

    private void longPressed(int keyCode) {
        if (mLongPressed) {
            mActive = false;
            mHandler.postDelayed(new Runnable() {

                public void run() {
                    if (mActive) {
                        return;
                    }
                    if (mPressed) {
                    }
                }
            }, 0x96);
        }
    }

    public void setOnKeyPressListener(
            KeyPressHelper.OnKeyPressListener onKeyPressListener) {
        mOnKeyPressListener = onKeyPressListener;
    }
}
