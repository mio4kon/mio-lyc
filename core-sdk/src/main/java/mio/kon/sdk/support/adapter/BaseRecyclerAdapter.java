package mio.kon.sdk.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mio on 15-5-19.
 * Recycler Adapter的基类
 * <p>泛型为:传入数据的类型</p>
 * <p>可复写onItemClick实现item的点击事件</p>
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    protected final Context ctx;
    protected final List<T> datas;

    public BaseRecyclerAdapter(Context ctx, List<T> datas) {
        this.ctx = ctx;
        this.datas = datas == null ? new ArrayList<T>() : new ArrayList<T>(datas);
    }

    /**
     * 改方法需要子类实现，需要返回item布局的resource id
     *
     * @return
     */
    public abstract int getItemResource();

    /**
     * 获取组件
     * <p> 通过holder.findView(resId)获取组件 </p>
     *
     * @param holder
     */
    public abstract void findView(ViewHolder holder);


    /**
     * <p> 处理viewHolder里组件的显示 </p>
     *
     * @param holder
     * @param position
     */
    public abstract void onBindedViewHolder(ViewHolder holder, int position);


    /**
     * item的点击事件处理
     *
     * @param itemView
     * @param position
     */
    public void onItemClick(View itemView, int position) {
        //do nothing
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private OnItemClickListener itemClickListener;

        interface OnItemClickListener {
            void onItemClick(View itemView, int position);
        }

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        private SparseArray<View> views = new SparseArray<View>();

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(itemView, getPosition());
            });

        }

        public <V extends View> V findView(int resId) {
            View view = views.get(resId);
            if (null == view) {
                view = itemView.findViewById(resId);
                views.put(resId, view);
            }
            return (V) view;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(getItemResource(), parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        findView(holder);
        onBindedViewHolder(holder, position);
        holder.setItemClickListener((v, p) -> {
            onItemClick(v, p);
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void addDatas(List<T> ads){
        datas.addAll (ads);
        notifyDataSetChanged ();
    }

    public void replaceDatas(List<T> rds){
        datas.clear ();
        datas.addAll (rds);
        notifyDataSetChanged ();
    }

}
