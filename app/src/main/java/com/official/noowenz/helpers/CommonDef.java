package com.official.noowenz.helpers;

/**
 * Created by nabin on 9/7/16.
 */
public class CommonDef {
    public static final String TAG = "CrewgoStaff";
    public static final String REFRESH_DATA_INTENT = "geofence";
    public static final String ZONE = "country:au";
    public static final int REQUEST_GOOGLE_SIGN_IN = 1;
    public static final int REQUEST_STORAGE_CAMERA = 01;
    public static final int REQUEST_LOCATION = 02;
    public static final int SKILL = 010;
    public static final int SUB_SKILL = 020;
    public static final int IMAGE_CROP = 200;
    public static final int SELECT_SUBURBS = 120;
    public static final int REQUEST_ACCEPT_IGNORE = 03;
    public static final int REQUEST_CONFORMATION = 04;
    public static final int REQUEST_MSG_DETAIL = 05;
    public static final int REQUEST_BREAK_CONFORMATION = 06;
    public static final int ALERTS_JOB_PAGE = 0;
    public static final int ONGOING_JOB_PAGE = 1;
    public static final String JOB_ALERTS = "JobAlerts";
    public static final String ONGOING_JOB = "OngoingJob";
    public static final String MESSAGES = "Messages";

    public class SharedPreferences {
        public final static String isLoggedIn="isLoggedIn";
        public final static String USER_ID="userId";
        public final static String DEVICE_ID="deviceId";
        public final static String HASH_CODE="hashcode";
        public final static String AUTH_KEY="auth_key";
        public final static String IMAGE_URL="image_url";

        public final static String NAME="name";
        public final static String FULL_ADDRESS="full_address";
        public final static String EMAIL="email";
        public final static String PHONE="phone";
        public final static String BRIEF_DESCRIPTION="brief_description";
        public final static String EXPERIENCE_YEAR="experience_year";
        public final static String EXPERIENCE_MONTH="experience_month";
        public final static String LHC_NAME="lhc_name";
        public final static String IS_LINKED="is_linked";
        public final static String POSTCODES="postcodes";
        public final static String ORIGINAL_SUBURBS_CACHE="original_suburbs_cache";
        public final static String PRE_ADDRESS="pre_address";

        public final static String USER_HOME_STATUS = "user_home_status";
        public final static String PROFILE_IMG_URL = "profile_img_url";
        public final static String PROFILE_BG_IMG_URL = "profile_bg_img_url";

        public final static String LATITUDE = "latitude";
        public final static String LONGITUDE = "longitude";
        public final static String GEO_FENCE_FLAG = "geofence_flag";
        public final static String REACHED_INTO_LOCATION = "reached_into_location";
        public final static String FENCE_RADIUS = "fence_radius";
        public final static String IS_ONGOING_JOB_RUNNING = "is_ongoing_job_running";
        public final static String IS_BREAK_RUNNING = "is_break_running";
        public final static String IS_JOB_CHECKED_IN = "is_job_checked_in";
        public final static String JOB_DETAIL_ID = "job_detail_id";
        public final static String PREVIOUS_FENCE_STATUS = "previous_fence_status";
        public final static String HOME_PAGE_IS = "home_page_is";
        public final static String FIREBASE_TOKEN = "firebase_token";

    }
}
