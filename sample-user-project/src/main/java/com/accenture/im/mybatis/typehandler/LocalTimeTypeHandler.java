package com.accenture.im.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(LocalTime.class)
public class LocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int num, LocalTime localTime,
            JdbcType jdbcType) throws SQLException {
        preparedStatement.setTime(num, Time.valueOf(localTime));
    }

    @Override
    public LocalTime getNullableResult(ResultSet resultSet, String str) throws SQLException {
        Time time = resultSet.getTime(str);
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }

    @Override
    public LocalTime getNullableResult(ResultSet resultSet, int num) throws SQLException {
        return resultSet.getTime(num).toLocalTime();
    }

    @Override
    public LocalTime getNullableResult(CallableStatement callableStatement, int num) throws SQLException {
        return callableStatement.getTime(num).toLocalTime();
    }
}
