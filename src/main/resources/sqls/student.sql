/* studentInfo.get */
SELECT sr.id id, st.name studentName, st.identity_number identityNumber, CASE WHEN st.gender=0 THEN '男' ELSE '女' END gender,
sr.school_name schoolName, sr.clazz_name clazzName, sr.study_way studyWay, CASE WHEN sr.is_study=0 THEN '是' ELSE '否' END isStudy, sr.study_time studyTime
FROM school_roll sr
LEFT JOIN person st on st.id = sr.belong_id
$condition limit @pageSize offset @pageNumber;

/* studentInfo.totalSize */
SELECT COUNT(*) totalNumber FROM school_roll sr LEFT JOIN person st on st.id = sr.belong_id $condition
