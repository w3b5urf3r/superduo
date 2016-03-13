package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mario on 29/02/2016.
 */
public class ScoreWidgetListProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context = null;
    private int appWidgetId;
    private final ArrayList<String> scores =new ArrayList<>();

    public ScoreWidgetListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);


    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.layout_score_widget_item);
        remoteView.setTextViewText(R.id.widget_score_textview,  scores.get(position));

        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        Cursor scoreCursor = context.getContentResolver().query(DatabaseContract.scores_table.buildScoreWithDate(), null, null, new String[]{mformat.format(today)}, null);
        if (scoreCursor.moveToFirst()) {

            do {
                scores.add(scoreCursor.getString(scoresAdapter.COL_HOME) +" "
                        + Utilies.getScores(context.getResources(), scoreCursor.getInt(scoresAdapter.COL_HOME_GOALS), scoreCursor.getInt(scoresAdapter.COL_AWAY_GOALS)) +
                        " " +scoreCursor.getString(scoresAdapter.COL_AWAY));
            } while (scoreCursor.moveToNext());
        }

    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}