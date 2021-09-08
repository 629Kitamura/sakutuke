package com.example.sakutuke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class DemoListAdapter extends ArrayAdapter<miyakonojyo> {
    private int mResource;
    private List<miyakonojyo> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public DemoListAdapter(Context context, int resource, List<miyakonojyo> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        miyakonojyo item = mItems.get(position);

        // 日付設定
        TextView date = (TextView) view.findViewById(R.id.textView);
        date.setText(item.getDate());

        // 温度を設定
        TextView title = (TextView)view.findViewById(R.id.textView2);
        title.setText(item.getOndo()+"度");

        return view;
    }
}