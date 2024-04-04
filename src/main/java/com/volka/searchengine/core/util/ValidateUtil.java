package com.volka.searchengine.core.util;

import lombok.extern.slf4j.Slf4j;

/**
 * FIXME : 서비스 레이어에서 쓸건지? volka
 *
 * @author volka
 */
@Slf4j
@Deprecated
public class ValidateUtil {

//    private final Validator validator;
//
//    public void validAcit(List<Acit> acitList) throws ConstraintViolationException, BizException, Exception {
//
//        if (acitList == null || acitList.isEmpty()) throw new BizException("FL0001");
//
//        Set<ConstraintViolation<Acit>> violations = null;
//
//        for (Acit acit : acitList) {
//            violations = validator.validate(acit);
//
//            if (!violations.isEmpty()) {
//                StringBuilder sb = new StringBuilder();
//                for (ConstraintViolation<Acit> violation : violations) {
//                    sb.append(violation.getMessage());
//                }
//                throw new ConstraintViolationException(sb.toString(), violations);
//            }
//        }
//    }
//
//    public void validTrdp(List<Trdp> trdpList) throws ConstraintViolationException, BizException, Exception {
//
//        if (trdpList == null || trdpList.isEmpty()) throw new BizException("FL0001");
//
//        Set<ConstraintViolation<Trdp>> violations = null;
//
//        for (Trdp trdp : trdpList) {
//            violations = validator.validate(trdp);
//
//            if (!violations.isEmpty()) {
//                StringBuilder sb = new StringBuilder();
//                for (ConstraintViolation<Trdp> violation : violations) {
//                    sb.append(violation.getMessage());
//                }
//                throw new ConstraintViolationException(sb.toString(), violations);
//            }
//        }
//    }
}
