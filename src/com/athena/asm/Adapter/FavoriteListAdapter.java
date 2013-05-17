package com.athena.asm.Adapter;

import java.util.List;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.athena.asm.R;
import com.athena.asm.aSMApplication;
import com.athena.asm.data.Board;


public class FavoriteListAdapter extends BaseAdapter {
    private LayoutInflater m_inflater;
    private List<Board> m_favorites;
    class ChildViewHolder{
        TextView categoryNameTextView;
        TextView moderatorIDTextView;
        TextView boardNameTextView;
    }

    public FavoriteListAdapter(LayoutInflater inflater, List<Board> boards) {
        super();
        this.m_inflater = inflater;
        this.m_favorites = boards;
    }

    public List<Board> getFavoriteBoards() {
        return m_favorites;
    }

    public boolean moveItem(int from, int to) {
        Board board = m_favorites.get(from);
        m_favorites.remove(board);
        m_favorites.add(to, board);
        notifyDataSetChanged();

        // TODO: save ordered list to file
        return true;
    }

    @Override
    public int getCount() {
        return m_favorites.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Board board = m_favorites.get(position);

        ChildViewHolder holder;
        if (convertView == null) {
            convertView = m_inflater.inflate(R.layout.favorite_list_item, null);

            holder = new ChildViewHolder();
            holder.categoryNameTextView = (TextView) convertView.findViewById(R.id.CategoryName);
            holder.moderatorIDTextView = (TextView) convertView.findViewById(R.id.ModeratorID);
            holder.boardNameTextView = (TextView) convertView.findViewById(R.id.BoardName);
            convertView.setTag(R.id.tag_first, holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag(R.id.tag_first);
        }
        String directoryName = board.getDirectoryName();
        if (directoryName == null || directoryName == "") {
            holder.categoryNameTextView.setText("[" + board.getCategoryName() + "]");
        } else {
            holder.categoryNameTextView.setText(directoryName + " - [" + board.getCategoryName() + "]");
        }
        holder.moderatorIDTextView.setText(board.getModerator());
        holder.boardNameTextView.setText("[" + board.getEngName() + "]" + board.getChsName());
        holder.boardNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, aSMApplication.getCurrentApplication()
                .getGuidanceSecondFontSize());

        convertView.setTag(R.id.tag_second, board);

        if (aSMApplication.getCurrentApplication().isNightTheme()) {
            holder.categoryNameTextView.setTextColor(convertView.getResources().getColor(R.color.status_text_night));
            holder.moderatorIDTextView.setTextColor(convertView.getResources().getColor(R.color.blue_text_night));
            holder.boardNameTextView.setTextColor(convertView.getResources().getColor(R.color.status_text_night));
        }
        return convertView;
    }

}
