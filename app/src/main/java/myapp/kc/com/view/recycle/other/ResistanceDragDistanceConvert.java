package myapp.kc.com.view.recycle.other;


import myapp.kc.com.view.recycle.IDragDistanceConverter;

public class ResistanceDragDistanceConvert implements IDragDistanceConverter {
    private final float mMaxScrollDistance;

    public ResistanceDragDistanceConvert(float maxScrollDistance) {
      this.mMaxScrollDistance = maxScrollDistance;
    }

    @Override
    public float convert(float scrollDistance, float refreshDistance) {
        return mMaxScrollDistance - 2 * (mMaxScrollDistance * mMaxScrollDistance)
            / (scrollDistance + 2 * mMaxScrollDistance);
    }
}
