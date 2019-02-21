package com.ca.scotiabankhomeassignment;

import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PictureFullScreenTest {

    @Rule
    public ActivityTestRule<PictureFullScreen>activityTestRule = new ActivityTestRule<>(PictureFullScreen.class);
    private PictureFullScreen pictureFullScreen = null;

    @Before
    public void setUp() throws Exception {
        pictureFullScreen = activityTestRule.getActivity();
    }

    @Test
    public void lunchSecondScreenTest(){
        ImageView imgFullScreen = pictureFullScreen.findViewById(R.id.imgFullScreen);
        ImageView imgExpandImage = pictureFullScreen.findViewById(R.id.expanded_image);

        assertNotNull(imgFullScreen);
        assertNotNull(imgExpandImage);

    }
    @After
    public void tearDown() throws Exception {
    }
}