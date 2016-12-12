package com.betterda.shopping.sort.model;
import com.betterda.shopping.sort.contract.SortContract;

import java.util.List;

/**
* Created by Administrator on 2016/12/08
*/

public class SortModelImpl implements SortContract.Model{

    @Override
    public void addSort(List<Sort> list) {
        list.add(new Sort("综合排序",true));
        list.add(new Sort("销量最高",false));
        list.add(new Sort("价格最低",false));
        list.add(new Sort("价格最高",false));
    }

    @Override
    public void clear(List<Sort> mSortList, int adapterPosition) {
        int size = mSortList.size();
        for (int i=0;i<size;i++) {
            if (i == adapterPosition) {
                mSortList.get(i).setSelect(true);
            } else {
                mSortList.get(i).setSelect(false);
            }
        }
    }
}