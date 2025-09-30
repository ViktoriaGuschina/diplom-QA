package ru.iteco.fmhandroid.ui.helpers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static ru.iteco.fmhandroid.ui.page.FilterNewsPage.dateEndPublish;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.ui.page.FilterNewsPage;


public class DataHelper {
    public User getValidUser() {
        return new User("login2", "password2");
    }

    public static String validLogin() {
        return ("login2");
    }

    public static String validPassword() {
        return ("password2");
    }

    public static String invalidDataOneDigit() {
        return ("0");
    }

    public static String invalidDataSpaces() {
        return ("       ");
    }

    public static String invalidDataCapitalLettersENInPassword() {
        return ("PASSWORD2");
    }

    public static String invalidDataCapitalLettersENInLogin() {
        return ("LOGIN2");
    }

    public static String invalidDataRULetters() {
        return ("тест");
    }

    public static String invalidDataSpecialCharacters() {
        return ("@#$%^&*()");
    }

    public static String invalidDataLeaveFieldEmpty() {
        return ("");
    }

    public static String invalidDataNumbers() {
        return ("0987654321");
    }

    public static ViewAction waitForElement(final Matcher matcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with attribute <" + matcher + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = matcher;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        try {
                            if (viewMatcher.matches(child)) {
                                return;
                            }
                        } catch (NoMatchingViewException ignored) {
                        }

                        uiController.loopMainThreadForAtLeast(100);
                    }

                }
                while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };

    }

    public static String getCurrentDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }


    public static String getCurrentTime() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(currentDate);
    }


    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }


    public static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    public static Matcher<View> childAtPosition(Matcher<View> matcher, final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    public static class Rand {
        static final Random random = new Random();

        public static String randomCategory() {
            String[] category = {
                    "Зарплата",
                    "Объявление",
                    "День рождения",
                    "Профсоюз",
                    "Массаж",
                    "Праздник",
                    "Нужна помощь",
                    "Благодарность",

            };
            return category[random.nextInt(category.length)];
        }
    }

    public static Calendar getDateMinusSevenDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        return calendar;
    }


    public static class Text {
        public static String getText(ViewInteraction matcher) {
            final String[] text = new String[1];
            ViewAction viewAction = new ViewAction() {

                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public String getDescription() {
                    return "Text of the view";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView textView = (TextView) view;
                    text[0] = textView.getText().toString();
                }
            };

            matcher.perform(viewAction);

            return text[0];
        }
    }


    public static void elementWaiting(Matcher matcher, int millis) {
        onView(isRoot()).perform(waitForElement(matcher, millis));
    }


    public static class User {
        private final String login;
        private final String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static void searchInSheet(int id) {
        onView(withId(id))
                .check(new ViewAssertion() {
                    @Override
                    public void check(View view, NoMatchingViewException noViewFoundException) {
                        if (noViewFoundException != null) {
                            throw noViewFoundException;
                        }
                        RecyclerView recyclerView = (RecyclerView) view;
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
                        if (viewHolder != null) {
                            View itemView = viewHolder.itemView;
                            String text = getTextFromView(itemView); // ищем текст внутри элемента
                            if (text != null) {
                                Log.i("ESPRESSO", "Первый элемент содержит текст: " + text);
                            } else {
                                Log.d("ESPRESSO", "Текст в первом элементе не найден");
                            }
                        } else {
                            Log.d("ESPRESSO", "Первый элемент не загружен или отсутствует");
                        }
                    }
                });
    }

    // Рекурсивная функция поиска TextView внутри View
    private static String getTextFromView(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        } else if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                String text = getTextFromView(vg.getChildAt(i));
                if (text != null && !text.isEmpty()) {
                    return text;
                }
            }
        }
        return null;
    }

    public static String getTextFromRecyclerViewItem(int recyclerViewId, int position, int textViewId) {
        final String[] textHolder = new String[1];

        onView(withId(recyclerViewId))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        // можно оставить null или расширенный, чтобы убедиться, что view — это RecyclerView
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "Получить текст из элемента по позиции " + position;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        if (view instanceof RecyclerView) {
                            RecyclerView recyclerView = (RecyclerView) view;
                            View itemView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                            TextView descriptionTextView = itemView.findViewById(textViewId);
                            if (descriptionTextView != null) {
                                textHolder[0] = descriptionTextView.getText().toString();
                            }
                        }
                    }
                });
        return textHolder[0];
    }

        public static ViewAction clickChildViewWithId(@IdRes final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(View.class);
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    if (v != null && v.isClickable()) {
                        v.performClick();
                    }
                }
            };
        }

        public static void selectDateInFilter(){

            // Дата начала = сегодня минус 7 дней
            Calendar calStart = Calendar.getInstance();
            calStart.add(Calendar.DAY_OF_YEAR, -7);
            int startYear = calStart.get(Calendar.YEAR);
            int startMonth = calStart.get(Calendar.MONTH) + 1;
            int startDay = calStart.get(Calendar.DAY_OF_MONTH);

            // Дата конца = сегодня
            Calendar calEnd = Calendar.getInstance();
            int endYear = calEnd.get(Calendar.YEAR);
            int endMonth = calEnd.get(Calendar.MONTH) + 1;
            int endDay = calEnd.get(Calendar.DAY_OF_MONTH);

            // Выбор даты начала публикации
            FilterNewsPage.dateStartPublish.perform(click());
            onView(withClassName(equalTo(DatePicker.class.getName()))).perform(setDate(startYear, startMonth, startDay));
            onView(withId(android.R.id.button1)).perform(click());

            // Выбор конечной даты (сегодня)
            dateEndPublish.perform(click());
            onView(withClassName(equalTo(DatePicker.class.getName()))).perform(setDate(endYear, endMonth, endDay));
            onView(withId(android.R.id.button1)).perform(click());
            Date startDate = calStart.getTime();
            Date endDate = calEnd.getTime();
        }

    public static ViewAction setDate(int year, int month, int day) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(DatePicker.class);
            }

            @Override
            public String getDescription() {
                return "Установить дату в DatePicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                DatePicker datePicker = (DatePicker) view;
                datePicker.updateDate(year, month - 1, day); // месяц -0-based
            }
        };
    }

    // Получение Calendar с отступом в днях
    public static Calendar getCalendarWithOffset(int daysOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysOffset);
        return calendar;
    }

    public static void selectDateInDatePicker(Calendar calendar, int dateInputFieldId) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        onView(withId(dateInputFieldId)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static void selectEndDateInDatePicker(Calendar calendar, int dateInputFieldId) {
        selectDateInDatePicker(calendar, dateInputFieldId);
    }
    }
