package com.example.busschedule.data.bus;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
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
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BusScheduleDao_Impl implements BusScheduleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BusSchedule> __insertionAdapterOfBusSchedule;

  private final EntityDeletionOrUpdateAdapter<BusSchedule> __deletionAdapterOfBusSchedule;

  private final EntityDeletionOrUpdateAdapter<BusSchedule> __updateAdapterOfBusSchedule;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByDepartureAndDestination;

  public BusScheduleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBusSchedule = new EntityInsertionAdapter<BusSchedule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `favorite` (`id`,`departure_code`,`destination_code`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BusSchedule entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDepartureCode());
        statement.bindString(3, entity.getDestinationCode());
      }
    };
    this.__deletionAdapterOfBusSchedule = new EntityDeletionOrUpdateAdapter<BusSchedule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `favorite` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BusSchedule entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfBusSchedule = new EntityDeletionOrUpdateAdapter<BusSchedule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `favorite` SET `id` = ?,`departure_code` = ?,`destination_code` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BusSchedule entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDepartureCode());
        statement.bindString(3, entity.getDestinationCode());
        statement.bindLong(4, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteByDepartureAndDestination = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "    DELETE FROM favorite \n"
                + "    WHERE departure_code = ? AND destination_code = ?\n"
                + "    ";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final BusSchedule busSchedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBusSchedule.insert(busSchedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final BusSchedule busSchedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBusSchedule.handle(busSchedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final BusSchedule busSchedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBusSchedule.handle(busSchedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByDepartureAndDestination(final String departure, final String destination,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByDepartureAndDestination.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, departure);
        _argIndex = 2;
        _stmt.bindString(_argIndex, destination);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteByDepartureAndDestination.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BusSchedule>> getAll() {
    final String _sql = "\n"
            + "        SELECT * FROM favorite    \n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorite"}, new Callable<List<BusSchedule>>() {
      @Override
      @NonNull
      public List<BusSchedule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDepartureCode = CursorUtil.getColumnIndexOrThrow(_cursor, "departure_code");
          final int _cursorIndexOfDestinationCode = CursorUtil.getColumnIndexOrThrow(_cursor, "destination_code");
          final List<BusSchedule> _result = new ArrayList<BusSchedule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BusSchedule _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDepartureCode;
            _tmpDepartureCode = _cursor.getString(_cursorIndexOfDepartureCode);
            final String _tmpDestinationCode;
            _tmpDestinationCode = _cursor.getString(_cursorIndexOfDestinationCode);
            _item = new BusSchedule(_tmpId,_tmpDepartureCode,_tmpDestinationCode);
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

  @Override
  public Flow<Boolean> existsByDepartureAndDestination(final String departure,
      final String destination) {
    final String _sql = "\n"
            + "        SELECT EXISTS(\n"
            + "            SELECT 1 FROM favorite \n"
            + "            WHERE departure_code = ? AND destination_code = ?\n"
            + "        )\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, departure);
    _argIndex = 2;
    _statement.bindString(_argIndex, destination);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorite"}, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
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
