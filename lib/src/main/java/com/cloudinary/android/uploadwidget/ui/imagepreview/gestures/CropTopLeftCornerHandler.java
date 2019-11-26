package com.cloudinary.android.uploadwidget.ui.imagepreview.gestures;

import android.graphics.Rect;
import android.view.MotionEvent;

class CropTopLeftCornerHandler extends CropOverlayGestureHandler {

    private final Rect overlay;
    private final CropOverlayGestureCallback listener;

    CropTopLeftCornerHandler(Rect overlay, CropOverlayGestureCallback listener) {
        this.overlay = overlay;
        this.listener = listener;
    }

    @Override
    public void handleGesture(MotionEvent event, boolean isAspectRatioLocked) {
        bounds.set(overlay.left - RESIZING_OFFSET, overlay.top - RESIZING_OFFSET, overlay.left + RESIZING_OFFSET, overlay.top + RESIZING_OFFSET);

        super.handleGesture(event, isAspectRatioLocked);
    }

    @Override
    public void handleCropGesture(MotionEvent event, boolean isAspectRatioLocked) {
        int left = (int) event.getX();
        int top = (int) event.getY();
        int right = overlay.right;
        int bottom = overlay.bottom;

        if (isAspectRatioLocked) {
            bottom -= left - overlay.left;
            right -= top - overlay.top;
        }

        if (listener != null) {
            listener.onOverlaySizeChanged(left, top, right, bottom);
        }
    }

}
