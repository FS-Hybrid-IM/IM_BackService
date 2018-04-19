package com.accenture.im.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int num, LocalDate localDate,
            JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(num, Date.valueOf(localDate));
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, String str) throws SQLException {
        Date date = resultSet.getDate(str);
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, int num) throws SQLException {
        return resultSet.getDate(num).toLocalDate();
    }

    @Override
    public LocalDate getNullableResult(CallableStatement callableStatement, int num) throws SQLException {
        return callableStatement.getDate(num).toLocalDate();
    }
}
