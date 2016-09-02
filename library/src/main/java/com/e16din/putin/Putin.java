package com.e16din.putin;


import android.os.Bundle;
import android.util.SparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class Putin {

    public static final String KEY_PREFIX = "KEY_";

    public static class Holder {
        public static final Putin HOLDER_INSTANCE = new Putin();
    }

    public static Putin instance() {
        return Holder.HOLDER_INSTANCE;
    }


    private static Map mMap;
    private static SparseArray mSparseArray;
    private static JSONObject mJsonObject;
    private static Bundle mBundle;


    public static Putin the(Map map) {//todo: check twice call
        mMap = map;
        return instance();
    }

    public static Putin the(SparseArray sparseArray) {
        mSparseArray = sparseArray;
        return instance();
    }

    public static Putin the(JSONObject jsonObject) {
        mJsonObject = jsonObject;
        return instance();
    }

    public static Putin the(Bundle bundle) {
        mBundle = bundle;
        return instance();
    }

    public static Putin newContainer() {
        mMap = new HashMap();
        return instance();
    }

    public Putin pair(int key, Serializable value) {//todo: check different pairs
        if (mMap != null) {
            mMap.put(key, value);
        } else if (mSparseArray != null) {
            mSparseArray.put(key, value);
        } else if (mJsonObject != null) {
            try {
                mJsonObject.put(getKeyString(key), value);
            } catch (JSONException ignore) {
                ignore.printStackTrace();
            }
        } else if (mBundle != null) {
            mBundle.putSerializable(getKeyString(key), value);
        }

        return instance();
    }

    public Putin pair(String key, Serializable value) {
        if (mMap != null) {
            mMap.put(key, value);
        } else if (mJsonObject != null) {
            try {
                mJsonObject.put(key, value);
            } catch (JSONException ignore) {
                ignore.printStackTrace();
            }
        } else if (mBundle != null) {
            mBundle.putSerializable(key, value);
        }

        return instance();
    }

    public Putin pair(Object key, Object value) {
        if (mMap != null) {
            mMap.put(key, value);
        }

        return instance();
    }

    public Putin pairs(Map map) {//todo: update
        if (mMap != null) {
            mMap.putAll(map);
        }

        return instance();
    }

    public Putin pairs(Bundle bundle) {//todo: update
        if (mBundle != null) {
            mBundle.putAll(bundle);
        }

        return instance();
    }

//    public Putin pairs(SparseArray sparseArray) {//todo: update
//        return instance();
//    }
//
//    public Putin pairs(JSONObject jsonObject) {//todo: update
//        return instance();
//    }

    public Putin value(Serializable value) {
        putValue(value);

        return instance();
    }

    private void putValue(Serializable value) {
        if (mMap != null) {
            mMap.put(getKeyString(mMap.size() - 1), value);
        } else if (mSparseArray != null) {
            mSparseArray.put(mSparseArray.size() - 1, value);
        } else if (mJsonObject != null) {
            //todo:
//            try {
//                mJsonObject.put(getKeyString(mJsonObject.), value);
//            } catch (JSONException ignore) {
//                ignore.printStackTrace();
//            }
        } else if (mBundle != null) {
            mBundle.putSerializable(getKeyString(mBundle.size() - 1), value);
        }
    }

    public Putin values(Serializable... values) {
        for (Serializable v : values) {
            putValue(v);
        }

        return instance();
    }


    public Object get() {
        if (mMap != null) {
            return mMap;
        } else if (mSparseArray != null) {
            return mSparseArray;
        } else if (mJsonObject != null) {
            return mJsonObject;
        } else if (mBundle != null) {
            return mBundle;
        }

        return null;
    }

    private static String getKeyString(int key) {
        return KEY_PREFIX + key;
    }
}
