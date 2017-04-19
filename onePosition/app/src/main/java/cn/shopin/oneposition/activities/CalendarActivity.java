package cn.shopin.oneposition.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.calendar.CaledarAdapter;
import cn.shopin.oneposition.calendar.CalendarBean;
import cn.shopin.oneposition.calendar.CalendarDateView;
import cn.shopin.oneposition.calendar.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    private CalendarDateView calendarDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarDateView = (CalendarDateView) findViewById(R.id.calendarDateView);
        calendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_xiaomi, null);
                }
                TextView chinaText = (TextView) convertView.findViewById(R.id.chinaText);
                TextView text = (TextView) convertView.findViewById(R.id.text);

                text.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    text.setTextColor(0xff9299a1);
                } else {
                    text.setTextColor(0xff444444);
                }
                chinaText.setText(bean.chinaDay);
                return convertView;
            }
        });
    }
}
