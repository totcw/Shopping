package com.betterda.shopping.comment.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.javabean.Comment;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class CommentContract {

    public interface View extends IView{
    }

    public interface Presenter extends IPresenter<View>{
        RecyclerView.Adapter getRvCommentAdapter();
    }

    public interface Model extends IModel{
        void setCommentList(List<Comment> list,List<Comment> commentList);
    }


}