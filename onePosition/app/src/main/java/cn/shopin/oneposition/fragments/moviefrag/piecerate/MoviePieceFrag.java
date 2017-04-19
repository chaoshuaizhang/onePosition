package cn.shopin.oneposition.fragments.moviefrag.piecerate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.PieceRecyclerAdapter;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.calendar.CaledarAdapter;
import cn.shopin.oneposition.calendar.CalendarBean;
import cn.shopin.oneposition.calendar.CalendarDateView;
import cn.shopin.oneposition.calendar.CalendarView;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.MyDecoration;
import cn.shopin.oneposition.entity.movie.PieceEntity;
import cn.shopin.oneposition.util.DataUtil;
import cn.shopin.oneposition.util.DateUtil;
import cn.shopin.oneposition.util.RetrofitUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/11.
 */
//http://moviewapp.dazui.com/MovieSheet/GetDataByDate?timestamp='当前毫秒数'
public class MoviePieceFrag extends Fragment implements OnChartValueSelectedListener, PieceRecyclerAdapter.pieceClickListener {
    private View view;
    private PieChart mPieChart;
    private Typeface mTfLight;
    private Typeface mTfRegular;
    private List<PieceEntity> dataList;
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerview;
    @BindView(R.id.curr_day_detail)
    protected TextView textCurrDay;
    private LinearLayoutManager layoutManager;
    private PieceRecyclerAdapter recyclerAdapter;
    @BindView(R.id.session_count)
    protected TextView sessionCount;
    @BindView(R.id.piece_count)
    protected TextView pieceCount;
    private long sessionCounts = 0;
    private int pieceCounts = 10;
    private int[] selectedDay = new int[4];
    private PopupWindow calendarPopWindow;
    private CalendarDateView calendarDateView;
    private TextView selectYMD;
    private static final int[] LIBERTY_COLORS = {
            Color.rgb(220, 87, 0), Color.rgb(17, 87, 156), Color.rgb(246, 179, 0),
            Color.rgb(14, 84, 43), Color.rgb(214, 35, 74), Color.rgb(109, 184, 156),
            Color.rgb(174, 24, 124), Color.rgb(94, 45, 134), Color.rgb(220, 211, 129),
            Color.rgb(156, 47, 42)
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        selectedDay = DateUtil.getCurYMD(selectedDay);
        dayOffset = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frag_moviepiece, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
            return view;
        }
        ButterKnife.bind(this, view);
        View calView = inflater.inflate(R.layout.activity_calendar, container, false);
        calendarDateView = (CalendarDateView) calView.findViewById(R.id.calendarDateView);
        selectYMD = (TextView) calView.findViewById(R.id.selectYMD);
        calendarPopWindow = new PopupWindow(calView, 500, 550, true);
        calendarPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), ""));
        calendarPopWindow.setOutsideTouchable(true);
        sessionCount.setCompoundDrawablePadding(3);
        pieceCount.setCompoundDrawablePadding(3);
        textCurrDay.setText(String.format("%d月%d日", selectedDay[1], selectedDay[2]));
        recyclerAdapter = new PieceRecyclerAdapter(getActivity(), dataList, this);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(recyclerAdapter);
        recyclerview.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        initPieChart();
        initData(DateUtil.currdate2MillSecond());
        calendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_xiaomi, null);
                }
                TextView chinaText = (TextView) convertView.findViewById(R.id.chinaText);
                TextView text = (TextView) convertView.findViewById(R.id.text);
                text.setText(Integer.toString(bean.day));
                if (bean.mothFlag != 0) {
                    text.setTextColor(0xff9299a1);
                } else {
                    text.setTextColor(0xff444444);
                }
                chinaText.setText(bean.chinaDay);
                return convertView;
            }
        });
        calendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {
                initData(DateUtil.date2MillSecond(bean.year, bean.moth, bean.day));
                selectedDay[0] = bean.year;
                selectedDay[1] = bean.moth;
                selectedDay[2] = bean.day;
                selectedDay = DateUtil.setDate(selectedDay);
                textCurrDay.setText(String.format("%d月%d日", selectedDay[1], selectedDay[2]));
                calendarPopWindow.dismiss();
            }

            @Override
            public void onItemChange(View view, int postion, CalendarBean bean) {
                selectYMD.setText(String.format("%d-%d-%d", bean.year, bean.moth, bean.day));
            }
        });
        return view;
    }

    private void initPieChart() {
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mPieChart = (PieChart) view.findViewById(R.id.piechart);
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setCenterTextTypeface(mTfLight);
        mPieChart.setCenterText(generateCenterSpannableText());
        mPieChart.setDrawHoleEnabled(false);//内部圆的空白部分
//        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);
//        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(1f);
        mPieChart.setDrawCenterText(false);
        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(false);
        mPieChart.setHighlightPerTapEnabled(true);
        // mPieChart.setUnit(" €");
        // mPieChart.setDrawUnitsInChart(true);
        // add a selection listener
        mPieChart.setOnChartValueSelectedListener(this);
        //setData
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutBack);//设置图表显示时的动画效果
        // mPieChart.spin(2000, 0, 360);
        Legend l = mPieChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTypeface(mTfRegular);
        mPieChart.setEntryLabelTextSize(12f);
        mPieChart.setNoDataText("");
    }

    private static int dayOffset = 0;

    @OnClick({R.id.pre_day, R.id.next_day, R.id.more_day})
    public void dayClick(View view) {
        switch (view.getId()) {
            case R.id.pre_day:
                selectedDay = DateUtil.getPreDayByYMD(selectedDay, -1);
                initData(DateUtil.date2MillSecond(selectedDay[0], selectedDay[1], selectedDay[2]));
                calendarDateView.chonghui(selectedDay[0], selectedDay[1], selectedDay[2]);
                break;
            case R.id.next_day:
                selectedDay = DateUtil.getNextDayByYMD(selectedDay, +1);
                initData(DateUtil.date2MillSecond(selectedDay[0], selectedDay[1], selectedDay[2]));
                calendarDateView.chonghui(selectedDay[0], selectedDay[1], selectedDay[2]);
                break;
            case R.id.more_day:
                selectYMD.setText(String.format("%d-%d-%d", selectedDay[0], selectedDay[1], selectedDay[2]));
                calendarPopWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    private void initData(String dateStr) {
        MovieApi movieApi = RetrofitUtil.createService(MovieApi.class, Cans.TAG_MOVIE);
        movieApi.getMoviePieceEntity(dateStr)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PieceEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TTAAGG11", e.getMessage());
                    }

                    @Override
                    public void onNext(List<PieceEntity> moviePieceEntities) {
                        dataList.clear();
                        dataList.addAll(moviePieceEntities);
                        sessionCount.setText(String.format("今日全国总场次:%s场", getMovieData()));
                        pieceCount.setText(String.format("总排片数:%d部", pieceCounts));
                        for (int i = 0; i < dataList.size(); i++) {
                            dataList.get(i).setRatio((float) dataList.get(i).getEvents() / sessionCounts);
                        }
                        recyclerAdapter.notifyDataSetChanged();
                        setData(10, 100);
                        textCurrDay.setText(String.format("%d月%d日", selectedDay[1], selectedDay[2]));
                    }
                });
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    ArrayList<PieEntry> entries;

    private void setData(int count, float range) {
        float mult = range;
        entries = new ArrayList<PieEntry>();
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        if (dataList.size() == 0) {

        } else {
            for (int i = 0; i < count; i++) {
                PieEntry pieEntry = new PieEntry(dataList.get(i).getRatio() * 100);
                entries.add(pieEntry);
            }
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(0.0f);
        dataSet.setSelectionShift(15f);
        // add a lot of colors
        dataSet.setColors(LIBERTY_COLORS);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);//不显示占比文字
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mPieChart.setData(data);
        // undo all highlights
        mPieChart.highlightValues(null);
        mPieChart.invalidate();
    }

    private String getMovieData() {
        sessionCounts = 0;
        if (dataList.size() == 0) {
            return String.valueOf("0");
        }
        for (int i = 0; i < dataList.size(); i++) {
            sessionCounts += dataList.get(i).getEvents();
        }
        return DataUtil.getDataFormat1(sessionCounts);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getX());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void pieceClick(int position) {
        Log.d("TTAAG", "" + mPieChart.getDrawAngles()[0] + "  " + mPieChart.getDrawAngles()[1] + "   " + mPieChart.getDrawAngles()[2]);
    }
}