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


    private Map mMap;
    private SparseArray mSparseArray;
    private JSONObject mJsonObject;
    private Bundle mBundle;


    public static Putin the(Map map) {//todo: check twice call
        instance().setMap(map);
        return instance();
    }

    public static Putin the(SparseArray sparseArray) {
        instance().setSparseArray(sparseArray);
        return instance();
    }

    public static Putin the(JSONObject jsonObject) {
        instance().setJsonObject(jsonObject);
        return instance();
    }

    public static Putin the(Bundle bundle) {
        instance().setBundle(bundle);
        return instance();
    }

    public static Putin newMap() {
        instance().setMap(new HashMap());
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

    public Putin pair(Object key, Serializable value) {
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

    public <KEY, VALUE> Map getMap() {
        if (mMap != null) {
            return (Map<KEY, VALUE>) mMap;
        }

        return null;
    }


    private static String getKeyString(int key) {
        return KEY_PREFIX + key;
    }


    public void setMap(Map map) {
        mMap = map;
    }

    public void setSparseArray(SparseArray sparseArray) {
        mSparseArray = sparseArray;
    }

    public void setJsonObject(JSONObject jsonObject) {
        mJsonObject = jsonObject;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }
}
