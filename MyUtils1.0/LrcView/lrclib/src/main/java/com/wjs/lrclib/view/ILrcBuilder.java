package com.wjs.lrclib.view;

import java.util.List;

public interface ILrcBuilder {
    List<LrcRow> getLrcRows(String rawLrc);
}
