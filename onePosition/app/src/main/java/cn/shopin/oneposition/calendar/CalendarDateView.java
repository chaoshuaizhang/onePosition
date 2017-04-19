package cn.shopin.oneposition.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.util.DateUtil;

import static cn.shopin.oneposition.calendar.CalendarFactory.getMonthOfDayList;

public class CalendarDateView extends ViewPager {

    HashMap<Integer, CalendarView> views = new HashMap<>();
    private CalendarView.OnItemClickListener onItemClickListener;
    private LinkedList<CalendarView> cache = new LinkedList();
    private int row = 6;

    private CaledarAdapter mAdapter;

    public void setAdapter(CaledarAdapter adapter) {
        mAdapter = adapter;
        initData();
    }

    public void setOnItemClickListener(CalendarView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CalendarDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, cn.shopin.oneposition.R.styleable.CalendarView);
        row = a.getInteger(R.styleable.CalendarView_calendar_row, 6);
        a.recycle();
        dateArrs = CalendarUtil.getYMD(new Date());
        dateArrs[2] = 1;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int calendarHeight = 0;
        if (getAdapter() != null) {
            CalendarView view = (CalendarView) getChildAt(0);
            if (view != null) {
                calendarHeight = view.getMeasuredHeight();
            }
        }
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(calendarHeight, MeasureSpec.EXACTLY));
    }

    private void init() {
        final int[] dateArr = CalendarUtil.getYMD(new Date());

        setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                CalendarView view;
                if (!cache.isEmpty()) {
                    view = cache.removeFirst();
                } else {
                    view = new CalendarView(container.getContext(), row);
                }
                view.setOnItemClickListener(onItemClickListener);
                view.setAdapter(mAdapter);
                Log.d("getMonthOfDayList", "-----+++++-----");
                Log.d("getMonthOfDayList", dateArrs[0] + "+++++" + dateArrs[1] + "+++++" + dateArrs[2]);
                view.setData(getMonthOfDayList(dateArrs[0], dateArrs[1] + position - Integer.MAX_VALUE / 2), position == Integer.MAX_VALUE / 2, dateArrs[2]);
                container.addView(view);
                views.put(position, view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
                cache.addLast((CalendarView) object);
                views.remove(position);
            }
        });
        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (onItemClickListener != null) {
                    CalendarView view = views.get(position);
                    Object[] obs = view.getSelect();
                    onItemClickListener.onItemChange((View) obs[0], (int) obs[1], (CalendarBean) obs[2]);
                }
            }
        });
    }

    private int[] dateArrs = null;

    public void chonghui(int year, int month, int dayOffset) {
        dateArrs[0] = year;
        dateArrs[1] = month;
        dateArrs[2] = dayOffset;
    }

    private void initData() {
        setCurrentItem(Integer.MAX_VALUE / 2, false);
        getAdapter().notifyDataSetChanged();
    }
}
