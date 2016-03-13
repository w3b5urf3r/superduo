package barqsoft.footballscores.service;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.ScoreWidgetListProvider;

/**
 * Created by mario on 29/02/2016.
 */
public class WidgetScoreService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return (new ScoreWidgetListProvider(this.getApplicationContext(), intent));
    }
}
