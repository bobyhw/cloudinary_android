package com.cloudinary.android.uploadwidget.ui.imagepreview.gestures;

import android.graphics.Rect;
import android.view.MotionEvent;

class CropRightSideHandler extends CropOverlayGestureHandler {

    private final Rect overlay;
    private final CropOverlayGestureCallback listener;

    CropRightSideHandler(Rect overlay, CropOverlayGestureCallback listener) {
        this.overlay = overlay;
        this.listener = listener;
    }

    @Override
    public void handleGesture(MotionEvent event, boolean isAspectRatioLocked) {
        bounds.set(overlay.right - RESIZING_OFFSET, overlay.top + RESIZING_OFFSET, overlay.right + RESIZING_OFFSET, overlay.bottom - RESIZING_OFFSET);

        super.handleGesture(event, isAspectRatioLocked);
    }

    @Override
    public void handleCropGesture(MotionEvent event, boolean isAspectRatioLocked) {
        int left = overlay.left;
        int top = overlay.top;
        int right = (int) event.getX();
        int bottom = overlay.bottom;

        if (isAspectRatioLocked) {
            top -= right - overlay.right;
            bottom += right - overlay.right;
        }

        if (listener != null) {
            listener.onOverlaySizeChanged(left, top, right, bottom);
        }
    }
}
