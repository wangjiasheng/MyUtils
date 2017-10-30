package com.wjs.lrclib.view;

import java.util.List;

public interface ILrcView {
    void setLrc(List<LrcRow> lrcRows);
    void seekLrc(int position);
    void seekLrcToTime(long time);
    void setListener(LrcViewListener l);
    public static interface LrcViewListener 
    {
        void onLrcSeeked(int newPosition, LrcRow row);
        void onLrcViewScoll(int newPosition, LrcRow row, long currentTime);
    }
}
