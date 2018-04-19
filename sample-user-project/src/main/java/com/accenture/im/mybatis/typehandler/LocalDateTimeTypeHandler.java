package com.accenture.im.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(value = LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {
    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts == null) {
            return null;
        }
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int num, LocalDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setTimestamp(num, Timestamp.from(parameter.atZone(ZoneId.systemDefault()).toInstant()));
    }
}
