package android.cs.aui.oilcollection.classes;

import android.app.Activity;
import android.content.Context;
import android.cs.aui.oilcollection.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abderrahmanedarhmaoui on 2017-12-27.
 */

    public class collectorAdapter extends BaseAdapter  {
        Activity context;
        List<Shop> shops;
        double longi, lati;

        private static LayoutInflater inflater = null;

        public collectorAdapter(Activity context , List<Shop> shops ){
            this.context = context;
            this.shops = shops;
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return shops.size();
        }

        @Override
        public Object getItem(int position) {
            return shops.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.model, null):itemView;
            TextView nameList = (TextView) itemView.findViewById(R.id.nameList);
            TextView addressList = (TextView) itemView.findViewById(R.id.addressList);
            Shop selected = shops.get(position);
            nameList.setText(selected.getShopname());
            addressList.setText(selected.getAddress());
            return itemView;


        }

    }

