package ru.iteco.fmhandroid.ui.helpers;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Вспомогательный класс для поиска элементов внутри RecyclerView.
 */
public class RecyclerViewMatcher {
    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    /**
     * Возвращает Matcher, который находит элемент на указанной позиции,
     * а внутри него — дочерний View с заданным id.
     *
     * @param position Индекс позиции в RecyclerView
     * @param targetViewId id искомого дочернего View внутри элемента
     * @return Matcher<View>
     */
    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
        return new TypeSafeMatcher<View>() {
            View childView;

            @Override
            public void describeTo(Description description) {
                description.appendText("RecyclerView with id: " + recyclerViewId +
                        " at position: " + position);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (childView == null) {
                    RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                    if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null) {
                            childView = viewHolder.itemView;
                        } else {
                            return false; // элемент на позиции еще не создан или не виден
                        }
                    } else {
                        return false; // RecyclerView не найден
                    }
                }

                if (targetViewId == -1) {
                    return view == childView;
                } else {
                    View targetView = childView.findViewById(targetViewId);
                    return view == targetView;
                }
            }
        };
    }
}