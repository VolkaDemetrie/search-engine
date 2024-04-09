package com.volka.searchengine.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 검색 결과 문서 변환 Advice
 *
 * @author volka
 */
@Aspect
@Component
public class DocConverter {

//    @Pointcut("@annotation(com.volka.searchengine.core.annotation.convert.DocConvert)")
//    private void convertDoc() {}
//
//    @SuppressWarnings("unchecked")
//    @Around("convertDoc()")
//    public List<DocumentModel> convertDoc(ProceedingJoinPoint joinPoint, DocConvert docConvert) throws Throwable {
//        try {
//            Object result = joinPoint.proceed();
//            Class<?> type = docConvert.model();
//
//            List<Document> documentList = (List<Document>) result;
//
//
//            if (documentList != null && !documentList.isEmpty()) {
//                List<DocumentModel> documentModelList = new ArrayList<>(10);
//
//                for (Document document : documentList) {
////                    documentModelList.add(type.cast());
//                }
//            }
//
//            return null;
//        } catch (BizException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new BizException("FL0003");
//        }
//    }

}
