package com.accenture.im.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(value = ZonedDateTime.class)
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {
    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int num, ZonedDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setTimestamp(num, Timestamp.from(parameter.toInstant()));
    }
}
