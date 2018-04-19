package com.accenture.im.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.accenture.im.entity.AuthEntity;
import com.accenture.im.entity.AuthTokenEntity;
import com.accenture.im.entity.DeviceEntity;
import com.accenture.im.exception.BusinessFailureException;
import com.accenture.im.repository.AuthRepository;
import com.accenture.im.repository.DeviceRepository;
import com.accenture.im.requestdto.CreateUserRequestForm;
import com.accenture.im.requestdto.LoginRequestForm;
import com.accenture.im.service.LoginService;
import com.accenture.im.utils.EncryptionUtils;
import com.accenture.im.utils.HttpRequestUtils;

@Service
@Primary
public class LoginServiceImpl implements LoginService {
    private static final String USER_LOCK_LOG_MESSAGE = "User has been locked.";

    @Autowired
    MessageSource messageSource;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public AuthTokenEntity login(LoginRequestForm loginReq) {
        final String loginName =
                loginReq.getLoginName() == null ? StringUtils.EMPTY : loginReq.getLoginName();
        final String loginPassword =
                loginReq.getLoginPassword() == null ? StringUtils.EMPTY : loginReq.getLoginPassword();

        LocalDateTime systemTime = LocalDateTime.now();
        AuthEntity authEntity = authRepository.selectByLoginName(loginName);
        if (authEntity == null) {
            throw new BusinessFailureException("Auth data not found.");
        }

        if (authEntity.getAccountLockDate() != null && systemTime.compareTo(authEntity.getAccountLockDate().plusHours(24)) > 0 && "Y".equals(authEntity.getAccountLockFlag())) {
            throw new BusinessFailureException(USER_LOCK_LOG_MESSAGE);
        }

        String encryptedPassword = EncryptionUtils.derivePassword(authEntity.getSalt(), loginPassword);
        if (!encryptedPassword.equals(authEntity.getLoginPassword())) {
            authEntity.setLoginFailedCount(authEntity.getLoginFailedCount() + 1);
            if (3 < authEntity.getLoginFailedCount()) {
                authEntity.setAccountLockDate(systemTime);
                authEntity.setAccountLockFlag("Y");
            }
            authRepository.update(authEntity);
            throw new BusinessFailureException("Login is fail");
        }

        AuthTokenEntity result = new AuthTokenEntity();
        DeviceEntity tokenResult;
        String token;
        do {
            token = UUID.randomUUID().toString();
            tokenResult = deviceRepository.selectToken(token);
        } while (tokenResult != null);
        String deviceId = HttpRequestUtils.getDeviceId();
        DeviceEntity entity = new DeviceEntity(loginName);
        entity.setAuthToken(token);
        entity.setAuthTokenPublishDate(LocalDateTime.now().plusMonths(1));
        entity.setDeviceId(deviceId);
        entity.setLastSyncDate(LocalDateTime.now());
        DeviceEntity dbEntity = deviceRepository.selectByLoginName(loginName);
        if (dbEntity == null) {
            deviceRepository.insert(entity);
        } else {
            deviceRepository.update(entity);
        }
        result.setAuthToken(token);
        result.setName(authEntity.getName());

        authEntity.setLoginFailedCount(0);
        authEntity.setAccountLockDate(null);
        authRepository.update(authEntity);
        return result;
    }

    public void createUser(CreateUserRequestForm loginReq) {
        if (!loginReq.getLoginPassword().equals(loginReq.getNextLoginPassword())) {
            throw new BusinessFailureException("password is not same");
        }
        AuthEntity authEntity = authRepository.selectByLoginName(loginReq.getLoginName());
        if (authEntity != null) {
            throw new BusinessFailureException("Login name Has been registered.");
        }
        authEntity = new AuthEntity(loginReq.getLoginName());
        authEntity.setName(loginReq.getName());
        String salt = RandomStringUtils.randomAlphanumeric(20);
        authEntity.setLoginPassword(EncryptionUtils.derivePassword(salt, loginReq.getLoginPassword()));
        authEntity.setSalt(salt);
        authRepository.insert(authEntity);
    }
}
