package tyxo.mobilesafe.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tyxo.mobilesafe.R;

/**
 * Created by LY on 2016/7/26 09: 53.
 * Mail      1577441454@qq.com
 * Describe : 可添加头布局的 recycler 适配器,原理是type
 */
public class AdapterRecyclerHeader extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private List<String> mDatas = new ArrayList<>();
    private View mHeaderView;
    private OnItemClickListenerRecycler mListener;

    public void setOnItemClickListener(OnItemClickListenerRecycler li){
        mListener = li;
    }

    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    public void addDatas(List<String> datas){
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setDatas(List<String> datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (mHeaderView==null) {
            return TYPE_NORMAL;
        }else{}
        if (position==0) {
            return TYPE_HEADER;
        }else{}
        return TYPE_NORMAL;
    }

    interface OnItemClickListenerRecycler{
        void onItemClick(int position, View view);
        void onItemLongClick(int position, View view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView!=null && viewType==TYPE_HEADER) {
            return new Holder(mHeaderView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_recycler,parent,false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEADER) {
            return;
        }
        final int po = getRealPosition(holder);
        //final int po = holder.getLayoutPosition();
        final String data = mDatas.get(po);
        if (holder instanceof Holder) {
            ((Holder) holder).text.setText(data);
            if (mListener==null) {
                return;
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(po,holder.itemView);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(po,holder.itemView);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderView==null?mDatas.size():mDatas.size()+1;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder){
        int position = holder.getLayoutPosition();
        return mHeaderView == null?position:position-1;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView text;

        public Holder(View itemView) {
            super(itemView);
            if (itemView==mHeaderView) {
                return;
            }
            text = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}






















