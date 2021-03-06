package mijey.kau_javaproject2017;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import java.text.ParseException;

/**
 * Created by yeaji on 2017-05-28.
 */

public class DBAdapter extends CursorAdapter {
    public DBAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvMemo = (TextView)view.findViewById(R.id.tvMemo);
        TextView tvDate = (TextView)view.findViewById(R.id.tvDate);
        //DB에 있는 type, date, memo를 NLP를 이용해서 자연어로 바꿔서 출력

        try {
            NLP msg = new NLP(cursor.getInt(cursor.getColumnIndex("type")),
                              cursor.getString(cursor.getColumnIndex("date")),
                              cursor.getString(cursor.getColumnIndex("memo")));
            tvMemo.setText(msg.getNaturalDate() + msg.getMemo());
            tvDate.setText(msg.getDate());

        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.todolist_layout, parent, false);
        return v;
    }
}