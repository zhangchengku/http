package com.myp.myapplication;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater mInflater;
    ViewHolder holder;
    List<Book> Book;
    Book book;

    public ListAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setList(List<Book> Book) {
        this.Book = Book;
    }

    // 选中当前选项时，让其他选项不被选中
    public void select(int position) {
        if (!Book.get(position).isSelected()) {
            Book.get(position).setSelected(true);
            for (int i = 0; i < Book.size(); i++) {
                if (i != position) {
                    Book.get(i).setSelected(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Book.size();
    }

    @Override
    public Object getItem(int position) {
        return Book.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.sms_model_item, null);
            holder.radioBtn = (RadioButton) convertView
                    .findViewById(R.id.radioButton);
            holder.radioBtn.setClickable(false);
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        book = (Book) getItem(position);
        holder.radioBtn.setChecked(book.isSelected());
        holder.textView.setText(book.getBookName() + " " + book.getAuthor()
                + " " + book.getPublishTime());
        return convertView;
    }

    class ViewHolder {
        RadioButton radioBtn;
        TextView textView;
    }

}
