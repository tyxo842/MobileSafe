package tyxo.mobilesafe.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
* @author ly
* @created at 2016/8/25 16:11
* @des : recyclerView 的基类adapter
*/
public abstract class BaseStaggeredAdapter<T extends RecyclerView.ViewHolder,E> extends
        RecyclerView.Adapter<T> {

    private Context context;
    protected ArrayList<E> mDatas;
    protected LayoutInflater mInflater;
    protected int layoutItemId;
    protected List<Integer> mHeights;                 //随机item的高度
    protected OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener<E> {
        void onItemClick(View view,E bean ,int position);
        void onItemLongClick(View view, E bean ,int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public BaseStaggeredAdapter(Context context, ArrayList<E> datas,int layoutItemId) {
        this.context = context;
        //mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.layoutItemId = layoutItemId;

        /*mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }*/
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, layoutItemId, null);
        //MyViewHolder holder = new MyViewHolder(mInflater.inflate(layoutItemId, parent, false));
        return getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final T holder, final int position) {

        /*// 设置随机高度
        LayoutParams lp = holder.item.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.item.setLayoutParams(lp);*/

        final E bean = mDatas.get(position);
        initItemView(holder,bean);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,bean,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView,bean,pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /** 返回 viewHolder */
    protected abstract T getViewHolder(View itemView);

    /** 设置数据 */
    protected abstract void initItemView(T holder,E bean);

    public void addData(int position,E bean) {
        mDatas.add(position, bean);
        mHeights.add((int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /*protected class MyViewHolder extends ViewHolder {
        View item;

        public MyViewHolder(View view) {
            super(view);
            item = view;
        }
    }*/
}

































