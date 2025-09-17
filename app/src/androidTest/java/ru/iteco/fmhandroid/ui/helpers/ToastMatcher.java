package ru.iteco.fmhandroid.ui.helpers;

import android.os.Build;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            return true;
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {
            return true;
        }
        
        return false;
    }
    
    public static Matcher<Root> isToast() {
        return new ToastMatcher();
    }
}