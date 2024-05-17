package com.satrasia.Reminder;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskItemDao_Impl implements TaskItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskItem> __insertionAdapterOfTaskItem;

  private final EntityDeletionOrUpdateAdapter<TaskItem> __deletionAdapterOfTaskItem;

  private final EntityDeletionOrUpdateAdapter<TaskItem> __updateAdapterOfTaskItem;

  public TaskItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskItem = new EntityInsertionAdapter<TaskItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reminder_table` (`name`,`desc`,`dueTimeString`,`completedDateString`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskItem entity) {
        if (entity.getName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getName());
        }
        if (entity.getDesc() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDesc());
        }
        if (entity.getDueTimeString() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDueTimeString());
        }
        if (entity.getCompletedDateString() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCompletedDateString());
        }
        statement.bindLong(5, entity.getId());
      }
    };
    this.__deletionAdapterOfTaskItem = new EntityDeletionOrUpdateAdapter<TaskItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `reminder_table` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTaskItem = new EntityDeletionOrUpdateAdapter<TaskItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `reminder_table` SET `name` = ?,`desc` = ?,`dueTimeString` = ?,`completedDateString` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskItem entity) {
        if (entity.getName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getName());
        }
        if (entity.getDesc() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDesc());
        }
        if (entity.getDueTimeString() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDueTimeString());
        }
        if (entity.getCompletedDateString() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCompletedDateString());
        }
        statement.bindLong(5, entity.getId());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertTaskItem(final TaskItem taskItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTaskItem.insert(taskItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTaskItem(final TaskItem taskItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTaskItem.handle(taskItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTaskItem(final TaskItem taskItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTaskItem.handle(taskItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TaskItem>> allTaskItems() {
    final String _sql = "SELECT * FROM reminder_table ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminder_table"}, new Callable<List<TaskItem>>() {
      @Override
      @NonNull
      public List<TaskItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
          final int _cursorIndexOfDueTimeString = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeString");
          final int _cursorIndexOfCompletedDateString = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDateString");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<TaskItem> _result = new ArrayList<TaskItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskItem _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDesc;
            if (_cursor.isNull(_cursorIndexOfDesc)) {
              _tmpDesc = null;
            } else {
              _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
            }
            final String _tmpDueTimeString;
            if (_cursor.isNull(_cursorIndexOfDueTimeString)) {
              _tmpDueTimeString = null;
            } else {
              _tmpDueTimeString = _cursor.getString(_cursorIndexOfDueTimeString);
            }
            final String _tmpCompletedDateString;
            if (_cursor.isNull(_cursorIndexOfCompletedDateString)) {
              _tmpCompletedDateString = null;
            } else {
              _tmpCompletedDateString = _cursor.getString(_cursorIndexOfCompletedDateString);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item = new TaskItem(_tmpName,_tmpDesc,_tmpDueTimeString,_tmpCompletedDateString,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
