package tw.kao.pocket.ui.travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import tw.kao.pocket.R;

public class TravelRecyclerAdapter extends RecyclerView.Adapter<TravelRecyclerAdapter.travel_viewhoder> implements View.OnClickListener{

    private ArrayList<HashMap<String, String>> recycleData;
    private Context mContext;

    public TravelRecyclerAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        recycleData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public travel_viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.travel_recyclerview, parent, false);
        view.setOnClickListener(this);
        return new travel_viewhoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull travel_viewhoder holder, int position) {

        holder.attractions_name.setText(recycleData.get(position).get("name"));
        holder.attractions_open.setText(recycleData.get(position).get("Opentime"));
        holder.attractions_address.setText(recycleData.get(position).get("add"));
        holder.attractions_tel.setText(recycleData.get(position).get("tel"));
        holder.attractions_ticket.setText(recycleData.get(position).get("ticket"));



        //將position保存在itemView的Tag中，以便點擊時進行獲取
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return recycleData.size();
    }


    //-----------------------------------監聽實作----------------------------------------------------

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意這裡使用getTag方法獲取position
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //----------------------------------------innerClass--------------------------------------------
    public class travel_viewhoder extends RecyclerView.ViewHolder {

        private TextView attractions_name,attractions_open,attractions_address,attractions_tel,attractions_ticket;

        public travel_viewhoder(@NonNull View itemView) {
            super(itemView);

            attractions_name = (TextView)itemView.findViewById(R.id.attractions_name);
            attractions_open = (TextView)itemView.findViewById(R.id.attractions_open);
            attractions_address = (TextView)itemView.findViewById(R.id.attractions_address);
            attractions_tel = (TextView)itemView.findViewById(R.id.attractions_tel);
            attractions_ticket = (TextView)itemView.findViewById(R.id.attractions_ticket);

        }
    }
}
