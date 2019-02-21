package com.ca.scotiabankhomeassignment;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;
    private Instrumentation.ActivityMonitor activityMonitor = InstrumentationRegistry.getInstrumentation().
            addMonitor(PictureFullScreen.class.getName(), null, false);

    private String pictureTitle = "Play Saturn's Rings Like a Harp";

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void lunchFirstScreenTest() {
        FrameLayout nasaPicture = mainActivity.findViewById(R.id.frmLayout);
        TextView nasaPictureTitle = mainActivity.findViewById(R.id.txtPictureTitle);
        assertNotNull(nasaPicture);
        assertNotNull(nasaPictureTitle);
    }

    @Test
    public void checkPictureTitle(){
        //this test will throw an exception. If we comment PictureTitle animation in MainActivity, then test will get success
    Espresso.onView(ViewMatchers.withId(R.id.txtPictureTitle)).check(ViewAssertions.matches(ViewMatchers.withText(pictureTitle)));
    }

    @Test
    public void onClickImageView() {
        assertNotNull(mainActivity.findViewById(R.id.imgDailyPicture));
        Espresso.onView(ViewMatchers.withId(R.id.imgDailyPicture)).perform(ViewActions.click());
        Activity secondActivity = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(secondActivity);
        secondActivity.finish();
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}