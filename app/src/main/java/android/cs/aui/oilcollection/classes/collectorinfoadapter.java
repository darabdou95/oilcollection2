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
 * Created by abderrahmanedarhmaoui on 2018-01-03.
 */

public class collectorinfoadapter extends BaseAdapter {


        Activity context;
        List<collectorinfo> collectors;

        private static LayoutInflater inflater = null;

        public collectorinfoadapter(Activity context , List<collectorinfo> collectors ){
            this.context = context;
            this.collectors = collectors;
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return collectors.size();
        }

        @Override
        public Object getItem(int position) {
            return collectors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.model2, null):itemView;
            TextView nameList = (TextView) itemView.findViewById(R.id.nameList);
            collectorinfo selected = collectors.get(position);
            nameList.setText(selected.getName());
            return itemView;


        }

    }


