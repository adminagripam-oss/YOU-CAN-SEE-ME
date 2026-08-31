package com.pichillilorenzo.flutter_inappwebview_android.credential_database;

import android.content.ContentValues;
import android.database.Cursor;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class URLCredentialDao {
    CredentialDatabaseHelper credentialDatabaseHelper;
    String[] projection = {"_id", URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME, URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD, URLCredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID};

    public URLCredentialDao(CredentialDatabaseHelper credentialDatabaseHelper) {
        this.credentialDatabaseHelper = credentialDatabaseHelper;
    }

    public long delete(URLCredential uRLCredential) {
        return this.credentialDatabaseHelper.getWritableDatabase().delete(URLCredentialContract.FeedEntry.TABLE_NAME, "_id = ?", new String[]{uRLCredential.getId().toString()});
    }

    public URLCredential find(String str, String str2, Long l8) {
        Cursor query = this.credentialDatabaseHelper.getReadableDatabase().query(URLCredentialContract.FeedEntry.TABLE_NAME, this.projection, "username = ? AND password = ? AND protection_space_id = ?", new String[]{str, str2, l8.toString()}, null, null, null);
        URLCredential uRLCredential = query.moveToNext() ? new URLCredential(Long.valueOf(query.getLong(query.getColumnIndexOrThrow("_id"))), query.getString(query.getColumnIndexOrThrow(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME)), query.getString(query.getColumnIndexOrThrow(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD)), l8) : null;
        query.close();
        return uRLCredential;
    }

    public List<URLCredential> getAllByProtectionSpaceId(Long l8) {
        Cursor query = this.credentialDatabaseHelper.getReadableDatabase().query(URLCredentialContract.FeedEntry.TABLE_NAME, this.projection, "protection_space_id = ?", new String[]{l8.toString()}, null, null, null);
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            arrayList.add(new URLCredential(Long.valueOf(query.getLong(query.getColumnIndexOrThrow("_id"))), query.getString(query.getColumnIndexOrThrow(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME)), query.getString(query.getColumnIndexOrThrow(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD)), l8));
        }
        query.close();
        return arrayList;
    }

    public long insert(URLCredential uRLCredential) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME, uRLCredential.getUsername());
        contentValues.put(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD, uRLCredential.getPassword());
        contentValues.put(URLCredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID, uRLCredential.getProtectionSpaceId());
        return this.credentialDatabaseHelper.getWritableDatabase().insert(URLCredentialContract.FeedEntry.TABLE_NAME, null, contentValues);
    }

    public long update(URLCredential uRLCredential) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME, uRLCredential.getUsername());
        contentValues.put(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD, uRLCredential.getPassword());
        return this.credentialDatabaseHelper.getWritableDatabase().update(URLCredentialContract.FeedEntry.TABLE_NAME, contentValues, "protection_space_id = ?", new String[]{uRLCredential.getProtectionSpaceId().toString()});
    }
}
