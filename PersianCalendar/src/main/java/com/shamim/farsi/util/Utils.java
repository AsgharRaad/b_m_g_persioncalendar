package com.shamim.farsi.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
iget.TextView;
import android.widget.Toast;

import com.azizhuss.arabicreshaper.ArabicShaping;
import com.shamim.farsi.R;
import com.shamim.farsi.adapter.ShapedArrayAdapter;
import com.shamim.farsi.entity.CityEntity;
import com.shamim.farsi.entity.DayEntity;
import com.shamim.farsi.entity.EventEntity;
import com.shamim.farsi.enums.CalendarTypeEnum;
import com.shamim.farsi.enums.SeasonEnum;
import com.shamim.farsi.service.BroadcastReceivers;
import com.github.praytimes.CalculationMethod;
import com.github.praytimes.Clock;
import com.github.praytimes.Coordinate;
import com.github.praytimes.PrayTime;
import com.github.praytimes.PrayTimesCalculator;
import com.github.twaddington.TypefaceSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import calendar.AbstractDate;
import calendar.CivilDate;
import calendar.DateConverter;
import calendar.DayOutOfRangeException;
import calendar.IslamicDate;
import calendar.PersianDate;

import static com.shamim.farsi.Constants.AM_IN_PERSIAN;
import static com.shamim.farsi.Constants.ARABIC_DIGITS;
m.farsi.Constants.PERSIAN_DIGITS;
import static com.shamim.farsi.Constants.PM_IN_PERSIAN;
import static com.shamim.farsi.Constants.PREF_ALTITUDE;
import static com.shamim.farsi.Constants.PREF_APP_LANGUAGE;
import static com.shamim.farsi.Constants.PREF_ATHAN_ALARM;
import static com.shamim.farsi.Constants.PREF_ATHAN_GAP;
import static com.shamim.farsi.Constants.PREF_ATHAN_VOLUME;
import static com.shamim.farsi.Constants.PREF_GEOCODED_CITYNAME;
import static com.shamim.farsi.Constants.PREF_IRAN_TIME;
import static com.shamim.farsi.Constants.PREF_ISLAMIC_OFFSET;
import static com.shamim.farsi.Constants.PREF_LATITUDE;
import static com.shamim.farsi.Constants.PREF_LONGITUDE;
import static com.shamim.farsi.Constants.PREF_NOTIFY_DATE;
import static com.shamim.farsi.Constants.PREF_PERSIAN_DIGITS;
import static com.shamim.farsi.Constants.PREF_PRAY_TIME_METHOD;
import static com.shamim.farsi.Constants.PREF_SELECTED_LOCATION;
import static com.shamim.farsi.Constants.PREF_SELECTED_WIDGET_TEXT_COLOR;
import static com.shamim.farsi.Constants.PREF_THEME;
import static com.shamim.farsi.Constants.PREF_WIDGET_CLOCK;
import static com.shamim.farsi.Constants.PREF_WIDGET_IN_24;
import static com.shamim.farsi.Constants.RED_THEME;

/**
 * Common utilities that needed for this calendar
 *
 * @author ebraminio
 */

public class Utils {

    private final String TAG = Utils.class.getName();
    private Context context;
    private Typeface typeface;
    private SharedPreferences prefs;

    private List<EventEntity> events;
    private PrayTimesCalculator prayTimesCalculator;
    private Map<PrayTime, Clock> prayTimes;
    public static String roy;
    private String[] persianMonths;
    private String[] islamicMonths;
    private String[] gregorianMonths;
    private String[] weekDays;
    public static int p = 0;
    private String cachedCityKey = "";
    private CityEntity cachedCity;

    private Utils(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        updateStoredPreference();
    }


    private static WeakReference<Utils> myWeakInstance;

    public static Utils getInstance(Context context) {
        if (myWeakInstance == null || myWeakInstance.get() == null) {
            myWeakInstance = new WeakReference<>(new Utils(context.getApplicationContext()));
        }
        return myWeakInstance.get();
    }

    /**
     * Text shaping is a essential thing on supporting Arabic script text on older Android versions.
     * It converts normal Arabic character to their presentation forms according to their position
     * on the text.
     *
     * @param text Arabic string
     * @return Shaped text
     */
   
            theme = R.style.OrangeTheme;
        }

        context.setTheme(theme);
    }


    public boolean isWidgetClock() {
        return prefs.getBoolean(PREF_WIDGET_CLOCK, DEFAULT_WIDGET_CLOCK);
    }

    public boolean isNotifyDate() {
        return prefs.getBoolean(PREF_NOTIFY_DATE, DEFAULT_NOTIFY_DATE);
    }

    public int getAthanVolume() {
        return prefs.getInt(PREF_ATHAN_VOLUME, DEFAULT_ATHAN_VOLUME);
    }

    public String getAppLanguage() {
        String language = prefs.getString(PREF_APP_LANGUAGE, DEFAULT_APP_LANGUAGE);
        // If is empty for whatever reason (pref dialog bug, etc), return Persian at least
        return TextUtils.isEmpty(language) ? DEFAULT_APP_LANGUAGE : language;
    }

    public String getTheme() {
        return prefs.getString(PREF_THEME, LIGHT_THEME);
    }

    public String getSelectedWidgetTextColor() {
        return prefs.getString(PREF_SELECTED_WIDGET_TEXT_COLOR, DEFAULT_SELECTED_WIDGET_TEXT_COLOR);
    }

    public PersianDate getToday() {
        return DateConverter.civilToPersian(new CivilDate(makeCalendarFromDate(new Date())));
    }

    public Calendar makeCalendarFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (iranTime) {
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        }
        calendar.setTime(date);
        return calendar;
    }

    public String clockToString(int hour, int minute) {
        return formatNumber(String.format(Locale.ENGLISH, "%d:%02d", hour, minute));
    }

    public String getNextOghatTime(Clock clock, boolean changeDate) {
        Coordinate coordinate = getCoordinate();


        String result = clockToString(hour, clock.getMinute());
        if (!clockIn24) {
            result = result + " " + timeText;
        }
        return result;
    }

    public String getPersianFormattedClock(Calendar calendar) {
        String timeText = null;

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (!clockIn24) {
            if (calendar.get(Calendar.HOUR_OF_DAY) >= 12) {
                timeText = PM_IN_PERSIAN;
                hour -= 12;
            } else {
                timeText = AM_IN_PERSIAN;
            }
        }

        String result = clockToString(hour, calendar.get(Calendar.MINUTE));
        if (!clockIn24) {
            result = result + " " + timeText;
        }
        return result;
    }

    public String formatNumber(int number) {
        return formatNumber(Integer.toString(number));
    }

    public String formatNumber(String number) {
        if (preferredDigits == ARABIC_DIGITS)
            return number;

        char[] result = number.toCharArray();
        for (int i = 0; i < result.length; ++i) {
            char c = number.charAt(i);
            if (Character.isDigit(c))
                result[i] = preferredDigits[Character.getNumericValue(c)];
        }
        return String.valueOf(result);
    }

    public String dateToString(AbstractDate date) {
        return formatNumber(date.getDayOfMonth()) + ' ' + getMonthName(date) + ' ' +
                formatNumber(date.getYear());
    }

    public String dateToString1(AbstractDate date) {
        return formatNumber(date.getDayOfMonth());

//        return formatNumber(date.getDayOfMonth()) +"\n"+ getMonthName(date) + "\n" +
//                formatNumber(date.getYear());
    }

    public String dateToString2(AbstractDate date) {
        return formatNumber(getMonthName(date));

    }

    public String dateToString3(AbstractDate date) {
        return formatNumber(date.getYear());

    }

    public String dateToString4(AbstractDate date) {
        return formatNumber(date.getDayOfWeek());

    }

    public String dayTitleSummary(PersianDate persianDate) {
        return getWeekDayName(persianDate) + PERSIAN_COMMA + " " + dateToString(persianDate)
                + " " + dateToString1(persianDate)+ " " + dateToString2(persianDate)+ " " + dateToString3(persianDate);
    }

    public String[] monthsNamesOfCalendar(AbstractDate date) {
        // the next step would be using them so lets check if they have initialized already
        if (persianMonths == null || gregorianMonths == null || islamicMonths == null)
            loadLanguageResource();

        if (date instanceof PersianDate)
            return persianMonths.clone();
        else if (date instanceof IslamicDate)
            return islamicMonths.clone();
        else
            return gregorianMonths.clone();
    }

    public String getMonthName(AbstractDate date) {
        return monthsNamesOfCalendar(date)[date.getMonth() - 1];
    }

    public String getWeekDayName(AbstractDate date) {
        if (date instanceof IslamicDate)
            date = DateConverter.islamicToCivil((IslamicDate) date);
        else if (date instanceof PersianDate)
            date = DateConverter.persianToCivil((PersianDate) date);

        if (weekDays == null)
            loadLanguageResource();
        p = date.getDayOfWeek() % 7;
        return weekDays[date.getDayOfWeek() % 7];
    }

    public void quickToast(String message) {
        Toast.makeText(context, shape(message), Toast.LENGTH_SHORT).show();
    }

    public void longToast(String message) {
        Toast.makeText(context, shape(message), Toast.LENGTH_LONG).show();
    }

    public int getDayIconResource(int day) {
        try {
            return preferredDigits == ARABIC_DIGITS ? DAYS_ICONS_AR[day] : DAYS_ICONS[day];
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "No such field is available");
            return 0;
        }
    }

    private String readStream(InputStream is) {
        // http://stackoverflow.com/a/5445161
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String readRawResource(@RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    private String persianStringToArabic(String text) {
        return text
                .replaceAll("ی", "ي")
                .replaceAll("ک", "ك")
                .replaceAll("گ", "كی")
                .replaceAll("ژ", "زی")
                .replaceAll("چ", "جی")
                .replaceAll("پ", "بی");
    }

    private <T> Iterable<T> iteratorToIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }

    public List<CityEntity> getAllCities(boolean needsSort) {
        List<CityEntity> result = new ArrayList<>();
        try {
            JSONObject countries = new JSONObject(readRawResource(R.raw.cities));

            for (String countryCode : iteratorToIterable(countries.keys())) {
                JSONObject country = countries.getJSONObject(countryCode);

                String countryEn = country.getString("en");
                String countryFa = country.getString("fa");

                JSONObject cities = country.getJSONObject("cities");

                for (String key : iteratorToIterable(cities.keys())) {
                    JSONObject city = cities.getJSONObject(key);

                    String en = city.getString("en");
                    String fa = city.getString("fa");

                    Coordinate coordinate = new Coordinate(
                            city.getDouble("latitude"),
                            city.getDouble("longitude"),
                            0 // city.getDouble("elevation")
                    );

                    result.add(new CityEntity(key, en, fa, countryCode, countryEn, countryFa, coordinate));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        if (!needsSort) {
            return result;
        }

        final String locale = getAppLanguage();

        CityEntity[] cities = result.toArray(new CityEntity[result.size()]);
        // Sort first by country code then city
        Arrays.sort(cities, new Comparator<CityEntity>() {
            @Override
            public int compare(CityEntity l, CityEntity r) {
                if (l.getKey().equals("")) {
                    return -1;
                }
                if (r.getKey().equals(DEFAULT_CITY)) {
                    return 1;
                }
                int compare = r.getCountryCode().compareTo(l.getCountryCode());
                if (compare != 0) return compare;
                if (locale.equals("en")) {
                    return l.getEn().compareTo(r.getEn());
                } else {
                    return persianStringToArabic(l.getFa())
                            .compareTo(persianStringToArabic(r.getFa()));
                }
            }
        });

            JSONArray days = new JSONObject(readRawResource(R.raw.events)).getJSONArray("events");

            int length = days.length();
            for (int i = 0; i < length; ++i) {
                JSONObject event = days.getJSONObject(i);

                int year = event.getInt("year");
                int month = event.getInt("month");
                int day = event.getInt("day");

                String title = event.getString("title");

                boolean holiday = event.getBoolean("holiday");

                events.add(new EventEntity(new PersianDate(year, month, day), title, holiday));
            }

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        this.events = events;
    }

    private int maxSupportedYear = -1;
    private int minSupportedYear = -1;
    private boolean isYearWarnGivenOnce = false;

    public void checkYearAndWarnIfNeeded(int selectedYear) {
        // once is enough, see #clearYearWarnFlag() also
        if (isYearWarnGivenOnce)
            return;

        if (maxSupportedYear == -1 || minSupportedYear == -1)
            loadMinMaxSupportedYear();

        if (selectedYear < minSupportedYear) {
//            try {
//                longToast(context.getString(R.string.holidaysIncompletenessWarning));
//            }catch (RuntimeException e){
//
//            }
            isYearWarnGivenOnce = true;
        }

        if (selectedYear > maxSupportedYear) {
//           try {
//               longToast(context.getString(getToday().getYear() > maxSupportedYear
//                       ? R.string.shouldBeUpdated
//                       : R.string.holidaysIncompletenessWarning));
//           } catch (RuntimeException e){
//
//           }

            isYearWarnGivenOnce = true;
        }
    }

    // called from CalendarFragment to make it once per calendar view
    public void clearYearWarnFlag() {
        isYearWarnGivenOnce = false;
    }

    private void loadMinMaxSupportedYear() {
        if (events == null) {
            loadEvents();
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (EventEntity eventEntity : events) {
            int year = eventEntity.getDate().getYear();

            if (min > year && year != -1) {
                min = year;
            }

            if (max < year) {
                max = year;
            }
        }

        minSupportedYear = min;
        maxSupportedYear = max;
    }

    public List<EventEntity> getEvents(PersianDate day) {
        if (events == null) {
            loadEvents();
        }

        List<EventEntity> result = new ArrayList<>();
        for (EventEntity eventEntity : events) {
            if (eventEntity.getDate().equals(day)) {
                result.add(eventEntity);
            }
        }
        return result;
    }


    public void loadApp() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 1);
        Intent intent = new Intent(context, BroadcastReceivers.class);
        intent.setAction(BROADCAST_RESTART_APP);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC, startTime.getTimeInMillis(), pendingIntent);
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public String setToCommaSeparated(Set<String> set) {
        return TextUtils.join(",", set);
    }

    public Set<String> commaSeparatedToSet(String commaSeparated) {
        Set<String> result = new HashSet<>();
        result.addAll(Arrays.asList(TextUtils.split(commaSeparated, ",")));
        return result;
    }

    public void loadAlarms() {
        String prefString = prefs.getString(PREF_ATHAN_ALARM, "");
        Log.d(TAG, "reading and loading all alarms from prefs: " + prefString);
        CalculationMethod calculationMethod = getCalculationMethod();
        Coordinate coordinate = getCoordinate();

        if (calculationMethod != null && coordinate != null && !TextUtils.isEmpty(prefString)) {
            PrayTimesCalculator calculator = new PrayTimesCalculator(calculationMethod);
            Map<PrayTime, Clock> prayTimes = calculator.calculate(new Date(), coordinate);

            Set<String> alarmTimesSet = commaSeparatedToSet(prefString);
            // in the past IMSAK was used but now we figured out FAJR was what we wanted
            if (alarmTimesSet.remove("IMSAK")) {
                alarmTimesSet.add("FAJR");
            }

            String[] alarmTimesNames = alarmTimesSet.toArray(new String[alarmTimesSet.size()]);
            for (int i = 0; i < alarmTimesNames.length; i++) {
                PrayTime prayTime = PrayTime.valueOf(alarmTimesNames[i]);

                Clock alarmTime = prayTimes.get(prayTime);

                if (alarmTime != null) {
                    setAlarm(prayTime, alarmTime, i);
                }
            }
        }
    }

    public void setAlarm(PrayTime prayTime, Clock clock, int id) {
        Calendar triggerTime = Calendar.getInstance();
        triggerTime.set(Calendar.HOUR_OF_DAY, clock.getHour());
        triggerTime.set(Calendar.MINUTE, clock.getMinute());
        setAlarm(prayTime, triggerTime.getTimeInMillis(), id);
    }

    public void setAlarm(PrayTime prayTime, long timeInMillis, int id) {
        String valAthanGap = prefs.getString(PREF_ATHAN_GAP, "0");
        long athanGap;
        try {
            athanGap = (long) (Double.parseDouble(valAthanGap) * 60);
        } catch (NumberFormatException e) {
            athanGap = 0;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public static void setExactAlarm(AlarmManager alarmManager,
                                         int type, long triggerAtMillis, PendingIntent pendingIntent) {
            alarmManager.setExact(type, triggerAtMillis, pendingIntent);
        }
    }

    public Uri getAthanUri() {
        String defaultSoundUri = "android.resource://" + context.getPackageName() + "/" + R.raw.mm;
        return Uri.parse(defaultSoundUri);
    }

    // Context preferably should be activity context not application
    public void changeAppLanguage(Context context) {
        String localeCode = getAppLanguage().replaceAll("-(IR|AF)", "");
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(config.locale);
        }
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void loadLanguageResource() {
        @RawRes int messagesFile;
        String lang = getAppLanguage();

        if (lang.equals("fa-AF"))
            messagesFile = R.raw.messages_fa_af;
        else if (lang.equals("ps"))
            messagesFile = R.raw.messages_ps;
        else
           
            CopyToClipboard.copyToClipboard(text, context);
            quickToast("«" + text + "»\n" + context.getString(R.string.date_copied_clipboard));
        }
    }

    private static class CopyToClipboard {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public static void copyToClipboard(CharSequence text, Context context) {
            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE))
                    .setPrimaryClip(ClipData.newPlainText("converted date", text));
        }
    }

    public SeasonEnum getSeason() {
        int month = getToday().getMonth();

        if (month < 4) {
    
        year = year + (month / 12);
        month = month % 12;
        if (month < 0) {
            year -= 1;
            month += 12;
        }
        month += 1;
        persianDate.setMonth(month);
        persianDate.setYear(year);
        persianDate.setDayOfMonth(1);

        int dayOfWeek = DateConverter.persianToCivil(persianDate).getDayOfWeek() % 7;


                days.add(dayEntity);
                dayOfWeek++;
                if (dayOfWeek == 7) {
                    dayOfWeek = 0;
                }
            }
        } catch (DayOutOfRangeException e) {
            // okay, it was expected
        }

        return days;
    }

    /
    }
}
