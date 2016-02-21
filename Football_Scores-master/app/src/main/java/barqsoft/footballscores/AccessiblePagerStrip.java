package barqsoft.footballscores;

import android.content.Context;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;

/**
 * Created by mario on 21/02/2016.
 */
public class AccessiblePagerStrip extends PagerTabStrip{
    public AccessiblePagerStrip(Context context) {
        super(context);
    }

    public AccessiblePagerStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) {

        final String textViewTitle = ((TextView) child).getText().toString();
        final ViewPager viewPager = (ViewPager) this.getParent();
        final int itemIndex = viewPager.getCurrentItem();

        String title = viewPager.getAdapter().getPageTitle(itemIndex).toString();

        if (textViewTitle.equalsIgnoreCase(title)) {
            child.setContentDescription( String.format(getResources().getConfiguration().locale, getContext().getString(R.string.score_section_selected_accessible),textViewTitle));
        } else {
            child.setContentDescription( String.format(getResources().getConfiguration().locale, getContext().getString(R.string.score_section_not_selected_accessible),textViewTitle));
        }

        return super.onRequestSendAccessibilityEvent(child, event);
    }
}
